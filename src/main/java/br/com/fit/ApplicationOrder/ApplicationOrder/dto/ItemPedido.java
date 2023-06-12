package br.com.fit.ApplicationOrder.ApplicationOrder.dto;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(value = Include.NON_NULL)
public class ItemPedido {
	private Long id ; 
	private Long numero; 
	private Integer indice; 
	private String sku;
	private String produto; 
	private Double preco; 
	private Integer quantidade; 
}
