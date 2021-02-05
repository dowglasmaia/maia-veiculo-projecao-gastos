package com.maia.apiveiculos.controller;



import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.maia.apiveiculos.entity.dto.VeiculoDTO;
import com.maia.apiveiculos.entity.dto.VeiculoNewDTO;
import com.maia.apiveiculos.service.IVeiculoServices;

@RestController
@RequestMapping("/api/v1/veiculos")
public class VeiculoController {

	private final IVeiculoServices veiculoServices;

	@Autowired
	public VeiculoController(IVeiculoServices veiculoServices) {
		this.veiculoServices = veiculoServices;
	}

	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<VeiculoDTO> findById(@PathVariable("id") Long id) {
		VeiculoDTO dto = veiculoServices.buscarPorId(id);
		return ResponseEntity.ok().body(dto);
	}

	@PostMapping(produces = { "application/json", "application/xml" }, consumes = { "application/json",
			"application/xml", })
	public ResponseEntity<Void> create(@RequestBody VeiculoNewDTO dto) {
		VeiculoNewDTO veiculo = veiculoServices.salvar(dto);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(veiculo.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

}
