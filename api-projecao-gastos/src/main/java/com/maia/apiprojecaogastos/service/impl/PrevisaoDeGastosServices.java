package com.maia.apiprojecaogastos.service.impl;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maia.apiprojecaogastos.entity.dto.DadosPrevisoesNewDTO;
import com.maia.apiprojecaogastos.entity.dto.VeiculoComPrevisaoDeGastoDTO;
import com.maia.apiprojecaogastos.entity.dto.VeiculoDTO;
import com.maia.apiprojecaogastos.service.IPrevisaoDeGastosServices;
import com.maia.apiprojecaogastos.service.IWokerFeignClient;


@Service
public class PrevisaoDeGastosServices implements IPrevisaoDeGastosServices {

	private final IWokerFeignClient wokerFeignClient; 

	@Autowired
	public PrevisaoDeGastosServices(IWokerFeignClient wokerFeignClient) {
		this.wokerFeignClient = wokerFeignClient;
	}

	@Override
	public Set<VeiculoComPrevisaoDeGastoDTO> obterPrevisoesDeGastosPorVeiculo(DadosPrevisoesNewDTO dto) {
		
		var veiculos = wokerFeignClient.obterTodosOsVeiculos().getBody();

		Set<VeiculoComPrevisaoDeGastoDTO> result = new HashSet<>();
		if (!veiculos.isEmpty()) {
			veiculos.forEach(v -> {
				VeiculoComPrevisaoDeGastoDTO veiculoResult = new VeiculoComPrevisaoDeGastoDTO( 
						v.getId(), 
						v.getNome(),
						v.getMarca(), 
						v.getModelo(), 
						v.getDataDeFabricacao().getYear(),
						obterQuantidadeDeCombustivelGasto(v, dto), 
						this.obterValorTotalGastoComCombustivel(v, dto));

				result.add(veiculoResult);
			});
		}

		return result;
	}

	private int obterQuantidadeDeCombustivelGasto(VeiculoDTO veiculo, DadosPrevisoesNewDTO dpDto) {
		var consumoMedioNaCidade = veiculo.getConsumoMedioDeCombustivelInCidade();
		var consumoMedioNaRodovia = veiculo.getConsumoMedioDeCombustivelEmRodovia();

		var totalKmPercorridoNaCidade = dpDto.getTotalDeKmPercorridoNaCidade();
		var totalKmPercorridoNaRrodovia = dpDto.getTotalDeKmPercorridoNaRodovia();

		var consumoTotalDeCombustivelNaCidade = consumoMedioNaCidade * totalKmPercorridoNaCidade;
		var consumoTotalDeCombustivelNaRodovia = consumoMedioNaRodovia * totalKmPercorridoNaRrodovia;

		return consumoTotalDeCombustivelNaCidade + consumoTotalDeCombustivelNaRodovia;
	}

	private BigDecimal obterValorTotalGastoComCombustivel(VeiculoDTO veiculo, DadosPrevisoesNewDTO dpDto) {
		var precoCombustivel = dpDto.getPrecoLitroGasolina();
		var totalDeCombustivelGasto = this.obterQuantidadeDeCombustivelGasto(veiculo, dpDto);
		
		var total = totalDeCombustivelGasto * precoCombustivel;
		
		return new BigDecimal(total).setScale(2, BigDecimal.ROUND_HALF_EVEN); 
	}
	 


	
}
