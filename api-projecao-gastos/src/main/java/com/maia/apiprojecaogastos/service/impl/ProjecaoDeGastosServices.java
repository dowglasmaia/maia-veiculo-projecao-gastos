package com.maia.apiprojecaogastos.service.impl;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maia.apiprojecaogastos.entity.dto.VeiculoComProjecaoDeGastoDTO;
import com.maia.apiprojecaogastos.entity.dto.VeiculoDTO;
import com.maia.apiprojecaogastos.service.IProjecaoDeGastosServices;
import com.maia.apiprojecaogastos.service.IWokerFeignClient;


@Service
public class ProjecaoDeGastosServices implements IProjecaoDeGastosServices {

	private final IWokerFeignClient wokerFeignClient; 

	@Autowired
	public ProjecaoDeGastosServices(IWokerFeignClient wokerFeignClient) {
		this.wokerFeignClient = wokerFeignClient;
	}

	@Override
	public Set<VeiculoComProjecaoDeGastoDTO> obterPrevisoesDeGastosPorVeiculo(
			Double precoLitroGasolina,
			Integer totalDeKmPercorridoNaCidade,
			Integer totalDeKmPercorridoNaRodovia) {
		
		var veiculos = wokerFeignClient.obterTodosOsVeiculos().getBody();

		Set<VeiculoComProjecaoDeGastoDTO> result = new HashSet<>();
		if (!veiculos.isEmpty()) {
			veiculos.forEach(v -> {
				VeiculoComProjecaoDeGastoDTO veiculoResult = new VeiculoComProjecaoDeGastoDTO( 
						v.getId(), 
						v.getNome(),
						v.getMarca(), 
						v.getModelo(), 
						v.getDataDeFabricacao().getYear(),
						obterQuantidadeDeCombustivelGasto(v,totalDeKmPercorridoNaCidade,totalDeKmPercorridoNaRodovia), 
						this.obterValorTotalGastoComCombustivel(v, precoLitroGasolina, totalDeKmPercorridoNaCidade,totalDeKmPercorridoNaRodovia));

				result.add(veiculoResult);
			});
		}

		return result;
	}

	@Override
	public VeiculoDTO obterVeiculoPorId(Long id) {
		return  wokerFeignClient.obterVeiculoPorId(id).getBody();
	}

	private int obterQuantidadeDeCombustivelGasto(VeiculoDTO veiculo, Integer totalDeKmPercorridoNaCidade, Integer totalDeKmPercorridoNaRodovia) {
		var consumoMedioNaCidade = veiculo.getConsumoMedioDeCombustivelInCidade();
		var consumoMedioNaRodovia = veiculo.getConsumoMedioDeCombustivelEmRodovia();
		
		var consumoTotalDeCombustivelNaCidade = consumoMedioNaCidade * totalDeKmPercorridoNaCidade;
		var consumoTotalDeCombustivelNaRodovia = consumoMedioNaRodovia * totalDeKmPercorridoNaRodovia;

		return consumoTotalDeCombustivelNaCidade + consumoTotalDeCombustivelNaRodovia;
	}

	private BigDecimal obterValorTotalGastoComCombustivel(VeiculoDTO veiculo, Double precoLitroGasolina , Integer totalDeKmPercorridoNaCidade, Integer totalDeKmPercorridoNaRodovia) {		
		var totalDeCombustivelGasto = this.obterQuantidadeDeCombustivelGasto(veiculo, totalDeKmPercorridoNaCidade,totalDeKmPercorridoNaRodovia);		
		var total = totalDeCombustivelGasto * precoLitroGasolina;
		
		return new BigDecimal(total).setScale(2, BigDecimal.ROUND_HALF_EVEN); 
	}
	 


	
}
