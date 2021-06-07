package com.maia.apiprojecaogastos.controller;

import com.maia.apiprojecaogastos.entity.dto.VeiculoComProjecaoDeGastoDTO;
import com.maia.apiprojecaogastos.entity.dto.VeiculoDTO;
import com.maia.apiprojecaogastos.exception.ResourceNotFoundException;
import com.maia.apiprojecaogastos.service.IProjecaoDeGastosServices;
import com.maia.apiprojecaogastos.service.IWokerFeignClient;
import com.maia.apiprojecaogastos.service.impl.ProjecaoDeGastosServices;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.lang.module.FindException;
import java.util.ArrayList;
import java.util.HashSet;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(controllers = ProjecaoDeGastosController.class)
@AutoConfigureMockMvc
@RequiredArgsConstructor
public class ProjecaoDeGastosControllerTest {

    static String URL_API = "/api/v1/projecao-gastos";

    @Autowired
    MockMvc mvc;

    @MockBean
    IProjecaoDeGastosServices services;

    @Test
    @DisplayName("Deve lançar exception para Recorsos não encontrados.")
    public void obterProjecoesDeGastoTest() throws Exception {
        BDDMockito.given(services.obterVeiculoPorId(Mockito.anyLong()) )
                .willThrow(new ResourceNotFoundException(HttpStatus.NOT_FOUND.value(), "Veiculo não encontrado!"));

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(URL_API.concat("/"+1L))
                .accept(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isNotFound());
    }
}
