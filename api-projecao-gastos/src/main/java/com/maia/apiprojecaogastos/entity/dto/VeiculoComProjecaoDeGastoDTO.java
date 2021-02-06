package com.maia.apiprojecaogastos.entity.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class VeiculoComProjecaoDeGastoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String marca;
	private String modelo;
	private int anoFabricacao;
	private int qtdaCombustivelGasto;

	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	private BigDecimal valorTotalGastoComCombustivel;

}
