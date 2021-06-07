package com.maia.apiveiculos.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maia.apiveiculos.entity.Veiculo;
import com.maia.apiveiculos.entity.dto.VeiculoDTO;
import com.maia.apiveiculos.entity.dto.VeiculoNewDTO;
import com.maia.apiveiculos.exception.ResourceNotFoundException;
import com.maia.apiveiculos.exception.ResourceValidationException;
import com.maia.apiveiculos.repository.IVeiculoRepository;
import com.maia.apiveiculos.service.IVeiculoServices;

@Service
public class VeiculoServices implements IVeiculoServices {

	private static Logger log = LoggerFactory.getLogger(VeiculoServices.class);
	
	private final IVeiculoRepository repository;

	@Autowired
	public VeiculoServices(IVeiculoRepository repository) {
		this.repository = repository;
	}

	@Override
	public VeiculoNewDTO salvar(VeiculoNewDTO dto) {
		try {
			Veiculo veiculo = repository.save(Veiculo.createVeiculoToNewDTO(dto));
			log.info("Veiculo Salvo com Sucesso");
			return VeiculoNewDTO.createVeiculoNewDTOToVeiculo(veiculo);
		} catch (Exception e) {
			log.error(e.getMessage(), e.getCause());
			throw new ResourceValidationException("Ocorreu uma falha au tentar Salvar um Novo Veiculo.");
		}
	}

	@Override
	public List<VeiculoDTO> listarTodos() {
		List<Veiculo> veiculos = repository.findAll();
		List<VeiculoDTO> listDto = veiculos.stream().map(v -> VeiculoDTO.createVeiculoDTOToVeiculo(v))
				.collect(Collectors.toList());

		return listDto;
	}

	@Override
	public Optional<VeiculoDTO> buscarPorId(Long id) {
		return Optional.of(VeiculoDTO.createVeiculoDTOToVeiculo(repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Veiculo não encotrado para o ID: " + id))) );
	}

}
