package com.maia.apiveiculos.service;

import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maia.apiveiculos.entity.dto.DadosPrevisoesNewDTO;
import com.maia.apiveiculos.entity.dto.VeiculoComPrevisaoDeGastoDTO;

@Service
public interface IPrevisaoDeGastosServices {

	@Transactional(readOnly = true)
	Set<VeiculoComPrevisaoDeGastoDTO> obterPrevisoesDeGastosPorVeiculo(DadosPrevisoesNewDTO dto);

}
