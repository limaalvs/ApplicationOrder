package br.com.fit.ApplicationOrder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.fit.ApplicationOrder.entity.ItemPedidoEntity;
import br.com.fit.ApplicationOrder.entity.PedidoEntity;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {
	@Query("SELECT i FROM PedidoEntity p JOIN p.itemPedido i WHERE p.id = :pedidoId AND i.indice = :indice")
    ItemPedidoEntity findItemPedidoByPedidoIdAndIndice(@Param("pedidoId") Long pedidoId, @Param("indice") Integer indice);
}
