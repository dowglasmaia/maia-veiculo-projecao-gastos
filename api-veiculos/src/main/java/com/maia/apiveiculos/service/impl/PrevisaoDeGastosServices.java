package com.maia.apiveiculos.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maia.apiveiculos.entity.Veiculo;
import com.maia.apiveiculos.entity.dto.DadosPrevisoesNewDTO;
import com.maia.apiveiculos.entity.dto.VeiculoComPrevisaoDeGastoDTO;
import com.maia.apiveiculos.entity.dto.VeiculoDTO;
import com.maia.apiveiculos.repository.IVeiculoRepository;
import com.maia.apiveiculos.service.IPrevisaoDeGastosServices;

@Service
public class PrevisaoDeGastosServices implements IPrevisaoDeGastosServices {

	private final IVeiculoRepository veiculoRepository;

	@Autowired
	public PrevisaoDeGastosServices(IVeiculoRepository veiculoRepository) {
		this.veiculoRepository = veiculoRepository;
	}

	@Override
	public Set<VeiculoComPrevisaoDeGastoDTO> obterPrevisoesDeGastosPorVeiculo(DadosPrevisoesNewDTO dto) {
		List<Veiculo> veiculos = veiculoRepository.findAll();

		Set<VeiculoComPrevisaoDeGastoDTO> result = new HashSet<>();
		if (!veiculos.isEmpty()) {
			veiculos.forEach(v -> {
				VeiculoDTO veiculo = VeiculoDTO.createVeiculoDTOToVeiculo(v);

				VeiculoComPrevisaoDeGastoDTO veiculoResult = new VeiculoComPrevisaoDeGastoDTO( 
						v.getId(), 
						v.getNome(),
						v.getMarca(), 
						v.getModelo(), 
						v.getDataDeFabricacao().getYear(),
						obterQuantidadeDeCombustivelGasto(veiculo, dto), 
						this.obterValorTotalGastoComCombustivel(veiculo, dto));

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
