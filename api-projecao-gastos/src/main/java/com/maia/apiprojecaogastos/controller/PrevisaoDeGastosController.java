package com.maia.apiprojecaogastos.controller;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maia.apiprojecaogastos.entity.dto.DadosPrevisoesNewDTO;
import com.maia.apiprojecaogastos.entity.dto.VeiculoComPrevisaoDeGastoDTO;
import com.maia.apiprojecaogastos.service.IPrevisaoDeGastosServices;




@RestController
@RequestMapping("/api/v1/previsoes-gastos")
public class PrevisaoDeGastosController {

	private final IPrevisaoDeGastosServices previsaoGastosServices;

	@Autowired
	public PrevisaoDeGastosController(IPrevisaoDeGastosServices previsaoGastosServices) {
		this.previsaoGastosServices = previsaoGastosServices;
	}

	@PostMapping(produces = { "application/json", "application/xml" }, 
			consumes = { "application/json", "application/xml"})
	public ResponseEntity<Set<VeiculoComPrevisaoDeGastoDTO>> create(@Valid @RequestBody DadosPrevisoesNewDTO dto) {
		var resultadoProgecao  = previsaoGastosServices.obterPrevisoesDeGastosPorVeiculo(dto);

		return ResponseEntity.ok().body(resultadoProgecao);
	}

}
