package com.maia.apiveiculos.entity;


import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

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
@EqualsAndHashCode
@Entity
@Table(name = "veiculo")
public class Veiculo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 100, nullable = false)
	@NotEmpty(message = "o campo nome é requerido.")
	private String nome;

	@Column(length = 100, nullable = false)
	@NotEmpty(message = "o campo marca é requerido.")
	private String marca;

	@Column(length = 100, nullable = false)
	@NotEmpty(message = "o campo modelo é requerido.")
	private String modelo;

	@Column(nullable = false)
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dataDeFabricacao;

	@Column(length = 100, nullable = false)
	@NotEmpty(message = "o campo consumo médio de combustível dentro da cidade é requerido.")
	private long consumoMedioDeCombustivelInCidade;

	@Column(length = 100, nullable = false)
	@NotEmpty(message = "o campo consumo médio de combustível em rodovia  é requerido.")
	private long consumoMedioDeCombustivelEmRodovia;
	
	

}
