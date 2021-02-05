package com.maia.apiveiculos.entity.dto;

import java.io.Serializable;
import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.maia.apiveiculos.entity.Veiculo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
public class VeiculoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;	
	private String marca;
	private String modelo;
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dataDeFabricacao;
	private int consumoMedioDeCombustivelInCidade;	
	private int consumoMedioDeCombustivelEmRodovia;
	
	public static VeiculoDTO createVeiculoDTOToVeiculo(Veiculo obj) {
		return new ModelMapper().map(obj, VeiculoDTO.class);
	}
}
