package br.com.frete.controller;

import br.com.frete.dto.FreteResponse;
import br.com.frete.service.FreteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/frete")
public class FreteController {

    private static final Logger logger = LoggerFactory.getLogger(FreteController.class);

    @Autowired
    private FreteService freteService;

    @GetMapping("/custo/{cep}")
    public ResponseEntity<FreteResponse> calculateFrete(@PathVariable String cep, @RequestParam(required = false) Integer produtoId) {
        logger.info("Calculando frete para CEP: {} e produtoId: {}", cep, produtoId);

        FreteResponse frete = freteService.calculateFrete(cep, produtoId);
        logger.info("Frete calculado: {}", frete);

        return ResponseEntity.ok(frete);
    }
}