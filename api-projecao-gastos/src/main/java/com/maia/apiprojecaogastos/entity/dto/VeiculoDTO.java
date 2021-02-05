package com.maia.apiprojecaogastos.entity.dto;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
public class VeiculoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String marca;
	private String modelo;
	private int consumoMedioDeCombustivelInCidade;
	private int consumoMedioDeCombustivelEmRodovia;

	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dataDeFabricacao;

}
