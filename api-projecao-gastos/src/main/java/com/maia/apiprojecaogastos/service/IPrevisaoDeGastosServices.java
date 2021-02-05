package com.maia.apiprojecaogastos.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.maia.apiprojecaogastos.entity.dto.DadosPrevisoesNewDTO;
import com.maia.apiprojecaogastos.entity.dto.VeiculoComPrevisaoDeGastoDTO;



@Service
public interface IPrevisaoDeGastosServices {

	
	Set<VeiculoComPrevisaoDeGastoDTO> obterPrevisoesDeGastosPorVeiculo(DadosPrevisoesNewDTO dto);

}
