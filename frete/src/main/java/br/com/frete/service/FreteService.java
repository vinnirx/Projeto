package br.com.frete.service;

import br.com.estoque.dto.EstoqueResposta;
import br.com.frete.dto.FreteResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
public class FreteService {

    private static final Logger logger = LoggerFactory.getLogger(FreteService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${frete.valorMedio}")
    private double valorMedioFrete;

    @Value("${frete.prazoMedio}")
    private int prazoMedioFrete;

    @Value("${estoque.service.url}")
    private String estoqueServiceUrl;

    public FreteResponse calculateFrete(String cep, Integer produtoId) {


        //  Se o produto estiver disponível, chame a API de Frete para calcular o frete
        try {
            FreteResponse freteResponse = restTemplate.getForObject("http://localhost:8080/api/frete/custo/{cep}", FreteResponse.class, cep);

            // Calcule a data estimada de entrega
            freteResponse.setDataEstimadaEntrega(calcularDataEntrega(freteResponse.getPrazoExpedicao(), freteResponse.getPrazoEntrega()));

            return freteResponse;
        } catch (ResourceAccessException e) {
            // Ação de contorno para timeout
            logger.warn("API de frete indisponível ou demorando mais de 5ms. Usando valor médio.");
            return new FreteResponse(null, valorMedioFrete, prazoMedioFrete, cep, 500, "API de frete indisponível. Usando valor médio.", 1, calcularDataEntrega(1, prazoMedioFrete));
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                logger.warn("CEP não suportado: {}", cep);
                return new FreteResponse(null, valorMedioFrete, prazoMedioFrete, cep, 404, "Desculpe-nos, mas no momento não podemos entregar no seu endereço", 1, calcularDataEntrega(1, prazoMedioFrete));
            } else {
                throw e;
            }
        }
    }

    private boolean isProdutoDisponivel(EstoqueResposta estoqueResponse) {
        // Verifique se o estoqueResponse é nulo ou se a lista de disponibilidade está vazia
        return estoqueResponse != null && estoqueResponse.getDisponibilidadeEstoque() != null && !estoqueResponse.getDisponibilidadeEstoque().isEmpty();
    }

    private LocalDate calcularDataEntrega(int prazoExpedicao, int prazoEntrega) {
        logger.info("Calculando data de entrega com prazo de expedição de {} dias e prazo de entrega de {} dias.", prazoExpedicao, prazoEntrega);
        LocalDate hoje = LocalDate.now();
        return hoje.plusDays(prazoExpedicao + prazoEntrega);
    }
}