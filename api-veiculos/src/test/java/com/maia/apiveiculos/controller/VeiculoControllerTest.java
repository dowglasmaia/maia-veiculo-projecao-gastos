package com.maia.apiveiculos.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maia.apiveiculos.entity.dto.VeiculoNewDTO;
import com.maia.apiveiculos.service.IVeiculoServices;
import lombok.RequiredArgsConstructor;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(controllers = VeiculoController.class)
@AutoConfigureMockMvc
@RequiredArgsConstructor
public class VeiculoControllerTest {

    static String URL_API = "/api/v1/veiculos";

    @Autowired
    MockMvc mvc;

    @MockBean
    IVeiculoServices services;


    @Test
    @DisplayName("Deve lançar error de validação ao salvar Veiculo com dados invalidos ou nulos.")
    public void createInvalidVeiculoNewDTOTest() throws Exception {
        String jsonBody = new ObjectMapper().writeValueAsString(new VeiculoNewDTO() );

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(URL_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonBody);

        mvc.perform(request)
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("erros", Matchers.hasSize(3)));

    }


}
