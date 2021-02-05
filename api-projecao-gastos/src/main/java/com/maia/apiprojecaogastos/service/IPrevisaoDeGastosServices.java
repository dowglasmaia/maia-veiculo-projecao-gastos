package com.maia.apiprojecaogastos.service;

import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maia.apiprojecaogastos.DadosPrevisoesNewDTO;
import com.maia.apiprojecaogastos.VeiculoComPrevisaoDeGastoDTO;

@Service
public interface IPrevisaoDeGastosServices {

	@Transactional(readOnly = true)
	Set<VeiculoComPrevisaoDeGastoDTO> obterPrevisoesDeGastosPorVeiculo(DadosPrevisoesNewDTO dto);

}
