package br.com.fit.ApplicationOrder.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
@Table(name = "TBL_ORDER")
public class PedidoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private Long id; 
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_order_id")
	private List<ItemPedidoEntity> itemPedido; 
	
	@Column(name = "CLIENT")
	private String cliente; 
}

