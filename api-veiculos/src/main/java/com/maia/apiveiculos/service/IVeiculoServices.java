package com.maia.apiveiculos.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maia.apiveiculos.entity.dto.VeiculoDTO;
import com.maia.apiveiculos.entity.dto.VeiculoNewDTO;

@Service
public interface IVeiculoServices {

	@Transactional
	VeiculoNewDTO salvar(VeiculoNewDTO dto);

	@Transactional(readOnly = true)
	VeiculoDTO buscarPorId(Long id);

	@Transactional(readOnly = true)
	List<VeiculoDTO> listarTodos();

}
