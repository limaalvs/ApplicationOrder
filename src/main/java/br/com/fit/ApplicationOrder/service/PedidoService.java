package br.com.fit.ApplicationOrder.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.fit.ApplicationOrder.dto.ItemPedido;
import br.com.fit.ApplicationOrder.dto.PedidoRequest;
import br.com.fit.ApplicationOrder.dto.PedidoResponse;
import br.com.fit.ApplicationOrder.entity.ItemPedidoEntity;
import br.com.fit.ApplicationOrder.entity.PedidoEntity;
import br.com.fit.ApplicationOrder.mapper.PedidoPersistenceMapper;
import br.com.fit.ApplicationOrder.repository.ItemPedidoRepository;
import br.com.fit.ApplicationOrder.repository.PedidoRepository;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoService {
	
	private final PedidoRepository repository;
	private final PedidoPersistenceMapper mapper; 
	private final ItemPedidoRepository itemRepository; 

	public PedidoResponse createNewPedido(PedidoRequest request) {
		PedidoEntity entity = this.mapper.toPedidoEntity(request);
		this.generateSkuCode(entity.getItemPedido()); 
		return this.mapper.toPedidoResponse(repository.save(entity));
	}

	private void generateSkuCode(List<ItemPedidoEntity> itemPedidoList) {
		for (ItemPedidoEntity item : itemPedidoList) {
			item.setSku(UUID.randomUUID().toString()); 
		}
	}

	public PedidoResponse findByIdPedido(Long numero) {
		Optional<PedidoEntity> entity = Optional.of(repository.findById(numero).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Numero nao encontrado no banco de dados")));
		return this.mapper.toPedidoResponse(entity.get()); 
	}

	public List<PedidoResponse> getAllPedidos() {
		return this.mapper.toListPedidosResponse( repository.findAll());
	}

	public PedidoResponse createNewItemPedido(Long numero, ItemPedido itemPedido) {
		
		Optional<PedidoEntity> entity = Optional.of(repository.findById(numero).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Numero nao encontrado no banco de dados"))); 
		
		if (!Objects.isNull(entity)) {
			ItemPedidoEntity itemEntity = this.mapper.toItemPedidoEntity(itemPedido);
			itemEntity.setSku(UUID.randomUUID().toString());
			entity.get().getItemPedido().add(itemEntity);
			return this.mapper.toPedidoResponse(repository.save(entity.get())) ;			
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Numero nao encontrado no banco de dados");		
		}
	}

	public ItemPedido findPedidoItemByIdAndIndice(Long numero, Integer indice) {
		ItemPedidoEntity itemPedidoEntity  = repository.findItemPedidoByPedidoIdAndIndice(numero, indice);	
		
		if (!Objects.isNull(itemPedidoEntity)) {
			return this.mapper.toItemPedidoResponse(itemPedidoEntity);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Numero ou Indice nao encontrado no banco de dados");
		}
	}

	public List<ItemPedido> findItemPedidoByIdPedido(Long numero) {
		Optional<PedidoEntity> entity = Optional.of(repository.findById(numero).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Numero nao encontrado no banco de dados"))); 

		if (!Objects.isNull(entity)) {
			return this.mapper.toItemPedidoListResponse(entity.get().getItemPedido());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Numero nao encontrado no banco de dados");
		}
	}

	public List<ItemPedido> findItensByProduto(String produto) {
		List<ItemPedidoEntity> itensList = itemRepository.findByProduto(produto);
		if (!Objects.isNull(itensList) && !itensList.isEmpty()) {			
			return mapper.toItemPedidoListResponse(itensList);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto nao encontrado no banco de dados");
		}
		
	}

	public void deleteById(Long numero) {
		repository.deleteById(numero);
	}

}
