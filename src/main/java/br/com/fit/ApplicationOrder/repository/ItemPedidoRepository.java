package br.com.fit.ApplicationOrder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fit.ApplicationOrder.entity.ItemPedidoEntity;

public interface ItemPedidoRepository extends JpaRepository<ItemPedidoEntity, Long> {
	List<ItemPedidoEntity> findByProduto(String produto);
}
