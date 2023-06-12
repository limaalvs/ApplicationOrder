package br.com.fit.ApplicationOrder.ApplicationOrder.entity;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder()
@Table(name = "TBL_ITEM_ORDER")
public class ItemPedidoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id; 
	
	@Column(name = "NUMBER")
	private Long numero; 
	
	@Column(name = "INDEX")
	private Integer indice;
	
	@Column(name = "SKU")
	private String sku;
	
	@Column(name = "PRODUCT")
	private String produto;
	
	@Column(name = "PRICE")
	private Double preco; 
	
	@Column(name = "AMOUNT")
	private Integer quantidade; 
}