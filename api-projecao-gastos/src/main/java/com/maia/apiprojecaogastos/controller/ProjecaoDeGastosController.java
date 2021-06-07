package com.maia.apiprojecaogastos.controller;

import java.util.Set;

import com.maia.apiprojecaogastos.entity.dto.VeiculoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

	@GetMapping(value = "/{id}", produces = { "application/json" })
	public ResponseEntity<VeiculoDTO> obterVeiculo(@PathVariable Long id){
		var VeiculoDTO  = previsaoGastosServices.obterVeiculoPorId(id);

		return ResponseEntity.ok().body(VeiculoDTO);
	}


}
