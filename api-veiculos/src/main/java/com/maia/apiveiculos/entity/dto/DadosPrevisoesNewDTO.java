package com.maia.apiveiculos.entity.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class DadosPrevisoesNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull(message = "informe o preço do litro da gasolina.")
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	private Double precoLitroGasolina;

	@NotNull(message = "informe o total de KM que será percorrido na Cidade.")
	private Integer totalDeKmPercorridoNaCidade;

	@NotNull(message = "informe o total de KM que será percorrido na Rodovia.")
	private Integer totalDeKmPercorridoNaRodovia;

}
