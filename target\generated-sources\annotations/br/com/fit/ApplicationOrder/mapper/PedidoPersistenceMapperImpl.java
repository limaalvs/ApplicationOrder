package br.com.fit.ApplicationOrder.mapper;

import br.com.fit.ApplicationOrder.dto.ItemPedido;
import br.com.fit.ApplicationOrder.dto.ItemPedido.ItemPedidoBuilder;
import br.com.fit.ApplicationOrder.dto.PedidoRequest;
import br.com.fit.ApplicationOrder.dto.PedidoResponse;
import br.com.fit.ApplicationOrder.dto.PedidoResponse.PedidoResponseBuilder;
import br.com.fit.ApplicationOrder.entity.ItemPedidoEntity;
import br.com.fit.ApplicationOrder.entity.ItemPedidoEntity.ItemPedidoEntityBuilder;
import br.com.fit.ApplicationOrder.entity.PedidoEntity;
import br.com.fit.ApplicationOrder.entity.PedidoEntity.PedidoEntityBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class PedidoPersistenceMapperImpl implements PedidoPersistenceMapper {

    @Override
    public PedidoEntity toPedidoEntity(PedidoRequest request) {
        if ( request == null ) {
            return null;
        }

        PedidoEntityBuilder pedidoEntity = PedidoEntity.builder();

        pedidoEntity.cliente( request.getCliente() );
        pedidoEntity.id( request.getId() );
        pedidoEntity.itemPedido( itemPedidoListToItemPedidoEntityList( request.getItemPedido() ) );

        return pedidoEntity.build();
    }

    @Override
    public PedidoResponse toPedidoResponse(PedidoEntity entity) {
        if ( entity == null ) {
            return null;
        }

        PedidoResponseBuilder pedidoResponse = PedidoResponse.builder();

        pedidoResponse.cliente( entity.getCliente() );
        pedidoResponse.id( entity.getId() );
        pedidoResponse.itemPedido( toItemPedidoListResponse( entity.getItemPedido() ) );

        return pedidoResponse.build();
    }

    @Override
    public List<PedidoResponse> toListPedidosResponse(List<PedidoEntity> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<PedidoResponse> list = new ArrayList<PedidoResponse>( entityList.size() );
        for ( PedidoEntity pedidoEntity : entityList ) {
            list.add( toPedidoResponse( pedidoEntity ) );
        }

        return list;
    }

    @Override
    public ItemPedidoEntity toItemPedidoEntity(ItemPedido request) {
        if ( request == null ) {
            return null;
        }

        ItemPedidoEntityBuilder itemPedidoEntity = ItemPedidoEntity.builder();

        itemPedidoEntity.id( request.getId() );
        itemPedidoEntity.indice( request.getIndice() );
        itemPedidoEntity.numero( request.getNumero() );
        itemPedidoEntity.preco( request.getPreco() );
        itemPedidoEntity.produto( request.getProduto() );
        itemPedidoEntity.quantidade( request.getQuantidade() );
        itemPedidoEntity.sku( request.getSku() );

        return itemPedidoEntity.build();
    }

    @Override
    public ItemPedido toItemPedidoResponse(ItemPedidoEntity itemEntity) {
        if ( itemEntity == null ) {
            return null;
        }

        ItemPedidoBuilder itemPedido = ItemPedido.builder();

        itemPedido.id( itemEntity.getId() );
        itemPedido.indice( itemEntity.getIndice() );
        itemPedido.numero( itemEntity.getNumero() );
        itemPedido.preco( itemEntity.getPreco() );
        itemPedido.produto( itemEntity.getProduto() );
        itemPedido.quantidade( itemEntity.getQuantidade() );
        itemPedido.sku( itemEntity.getSku() );

        return itemPedido.build();
    }

    @Override
    public List<ItemPedido> toItemPedidoListResponse(List<ItemPedidoEntity> itemEntity) {
        if ( itemEntity == null ) {
            return null;
        }

        List<ItemPedido> list = new ArrayList<ItemPedido>( itemEntity.size() );
        for ( ItemPedidoEntity itemPedidoEntity : itemEntity ) {
            list.add( toItemPedidoResponse( itemPedidoEntity ) );
        }

        return list;
    }

    protected List<ItemPedidoEntity> itemPedidoListToItemPedidoEntityList(List<ItemPedido> list) {
        if ( list == null ) {
            return null;
        }

        List<ItemPedidoEntity> list1 = new ArrayList<ItemPedidoEntity>( list.size() );
        for ( ItemPedido itemPedido : list ) {
            list1.add( toItemPedidoEntity( itemPedido ) );
        }

        return list1;
    }
}
