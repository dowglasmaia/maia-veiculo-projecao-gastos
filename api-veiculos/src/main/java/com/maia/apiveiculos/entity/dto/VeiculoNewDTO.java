package com.maia.apiveiculos.entity.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
public class VeiculoNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	@NotEmpty(message = "o campo nome é requerido.")
	private String nome;

	@NotEmpty(message = "o campo marca é requerido.")
	private String marca;

	@NotEmpty(message = "o campo modelo é requerido.")
	private String modelo;

	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dataDeFabricacao;

	//@NotNull(message = "o campo consumo médio de combustível dentro da cidade é requerido.")
	private int consumoMedioDeCombustivelInCidade;

	//@NotNull(message = "o campo consumo médio de combustível em rodovia  é requerido.")
	private int consumoMedioDeCombustivelEmRodovia;

	public static VeiculoNewDTO createVeiculoNewDTOToVeiculo(Veiculo obj) {
		return new ModelMapper().map(obj, VeiculoNewDTO.class);
	}

}
