package br.com.frete;

import br.com.frete.controller.FreteController;
import br.com.frete.dto.FreteResponse;
import br.com.frete.service.FreteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
public class FreteApplicationTests {

	@Autowired
	private FreteService freteService;

	@Test
	public void testCalculateFreteProdutoIndisponivel() {
		FreteResponse response = freteService.calculateFrete("20771-000", 5);
		assertEquals("Estoque indisponível", response.getMensagem());
	}

	@Test
	public void testCalculateFreteCEPSuportado() {
		FreteResponse response = freteService.calculateFrete("20771-000", null);
		assertEquals("ASAP LOG", response.getTransportadora());
		assertNotNull(response.getDataEstimadaEntrega());
	}

	@Test
	public void testCalculateFreteCEPNaoSuportado() {
		FreteResponse response = freteService.calculateFrete("00000-000", null);
		assertEquals(404, response.getHttpStatus());
	}
}

