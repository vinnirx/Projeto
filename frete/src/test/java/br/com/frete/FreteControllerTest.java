package br.com.frete;

import br.com.frete.controller.FreteController;
import br.com.frete.dto.FreteResponse;
import br.com.frete.service.FreteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FreteController.class)
public class FreteControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FreteService freteService;

	@Test
	public void testGetFreteDetailsProdutoIndisponivel() throws Exception {
		when(freteService.calculateFrete(anyString(), anyInt()))
				.thenReturn(new FreteResponse(null, 0, 0, "20771-000", 200, "Estoque indisponível", 0, null));

		mockMvc.perform(get("/api/frete/custo/20771-000")
						.param("produtoId", "5"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.mensagem", is("Estoque indisponível")));
	}
}
