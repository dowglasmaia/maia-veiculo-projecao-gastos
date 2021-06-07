package com.maia.apiprojecaogastos.service;

import java.util.Set;

import com.maia.apiprojecaogastos.entity.dto.VeiculoDTO;
import org.springframework.stereotype.Service;

import com.maia.apiprojecaogastos.entity.dto.VeiculoComProjecaoDeGastoDTO;

@Service
public interface IProjecaoDeGastosServices {

	Set<VeiculoComProjecaoDeGastoDTO> obterPrevisoesDeGastosPorVeiculo(
			Double precoLitroGasolina,
			Integer totalDeKmPercorridoNaCidade,
			Integer totalDeKmPercorridoNaRodovia );

	VeiculoDTO obterVeiculoPorId(Long id);
}
