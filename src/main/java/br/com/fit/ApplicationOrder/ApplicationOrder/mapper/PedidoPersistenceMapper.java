package br.com.fit.ApplicationOrder.ApplicationOrder.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import br.com.fit.ApplicationOrder.ApplicationOrder.dto.ItemPedido;
import br.com.fit.ApplicationOrder.ApplicationOrder.dto.PedidoRequest;
import br.com.fit.ApplicationOrder.ApplicationOrder.dto.PedidoResponse;
import br.com.fit.ApplicationOrder.ApplicationOrder.entity.ItemPedidoEntity;
import br.com.fit.ApplicationOrder.ApplicationOrder.entity.PedidoEntity;

@Mapper(componentModel = "spring")
public interface PedidoPersistenceMapper {
	PedidoEntity toPedidoEntity(PedidoRequest request);
	PedidoResponse toPedidoResponse(PedidoEntity entity);
	List<PedidoResponse> toListPedidosResponse (List<PedidoEntity> entityList);
	ItemPedidoEntity toItemPedidoEntity (ItemPedido request); 
	ItemPedido toItemPedidoResponse (ItemPedidoEntity itemEntity);
	List<ItemPedido> toItemPedidoListResponse (List<ItemPedidoEntity> itemEntity);
}
