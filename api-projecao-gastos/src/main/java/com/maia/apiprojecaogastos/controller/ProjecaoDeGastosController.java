package com.maia.apiprojecaogastos.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.maia.apiprojecaogastos.entity.dto.VeiculoComProjecaoDeGastoDTO;
import com.maia.apiprojecaogastos.service.IProjecaoDeGastosServices;




@RestController
@RequestMapping("/api/v1/projecao-gastos")
public class ProjecaoDeGastosController {

	private final IProjecaoDeGastosServices previsaoGastosServices;

	@Autowired
	public ProjecaoDeGastosController(IProjecaoDeGastosServices previsaoGastosServices) {
		this.previsaoGastosServices = previsaoGastosServices;
	}

	@GetMapping(produces = { "application/json" })
	public ResponseEntity<Set<VeiculoComProjecaoDeGastoDTO>> obterProjecoes( 
			@RequestParam("precoLitroGasolina") Double precoLitroGasolina,
			@RequestParam("totalDeKmPercorridoNaCidade") Integer totalDeKmPercorridoNaCidade,
			@RequestParam("totalDeKmPercorridoNaRodovia") Integer totalDeKmPercorridoNaRodovia)
	{
		var resultadoProgecao  = previsaoGastosServices.obterPrevisoesDeGastosPorVeiculo(precoLitroGasolina,totalDeKmPercorridoNaCidade,totalDeKmPercorridoNaRodovia);

		return ResponseEntity.ok().body(resultadoProgecao);
	}

}
