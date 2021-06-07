package com.maia.apiprojecaogastos.service;

import java.util.Set;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import com.maia.apiprojecaogastos.entity.dto.VeiculoDTO;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "api-veiculos", url = "localhost:8081", path = "/api/v1/veiculos")
public interface IWokerFeignClient {

	@GetMapping()
	ResponseEntity<Set<VeiculoDTO>> obterTodosOsVeiculos();

	@GetMapping("/{id}")
	ResponseEntity<VeiculoDTO> obterVeiculoPorId(@PathVariable Long id);

}
