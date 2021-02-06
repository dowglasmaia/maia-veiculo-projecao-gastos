package com.maia.apiprojecaogastos.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.maia.apiprojecaogastos.entity.dto.VeiculoComProjecaoDeGastoDTO;

@Service
public interface IProjecaoDeGastosServices {

	Set<VeiculoComProjecaoDeGastoDTO> obterPrevisoesDeGastosPorVeiculo(
			Double precoLitroGasolina,
			Integer totalDeKmPercorridoNaCidade,
			Integer totalDeKmPercorridoNaRodovia );

}
