package com.maia.apiveiculos.entity.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VeiculoComPrevisaoDeGastoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String marca;
	private String modelo;
	private int anoFabricacao;
	private int qtdaCombustivelGasto;
	
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	private BigDecimal valorTotalGastoComCombustivel;

	public VeiculoComPrevisaoDeGastoDTO() {
		
	}

	public VeiculoComPrevisaoDeGastoDTO(Long id, String nome, String marca, String modelo, int anoFabricacao,
			int qtdaCombustivelGasto, BigDecimal valorTotalGastoComCombustivel) {
		super();
		this.id = id;
		this.nome = nome;
		this.marca = marca;
		this.modelo = modelo;
		this.anoFabricacao = anoFabricacao;
		this.qtdaCombustivelGasto = qtdaCombustivelGasto;
		this.valorTotalGastoComCombustivel = valorTotalGastoComCombustivel;
	}

}
