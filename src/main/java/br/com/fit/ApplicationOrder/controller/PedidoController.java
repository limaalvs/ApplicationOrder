package br.com.fit.ApplicationOrder.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fit.ApplicationOrder.dto.ItemPedido;
import br.com.fit.ApplicationOrder.dto.PedidoRequest;
import br.com.fit.ApplicationOrder.dto.PedidoResponse;
import br.com.fit.ApplicationOrder.service.PedidoService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/v1/pedido")
@Validated
public class PedidoController {
	
	private final PedidoService service; 
	
	public PedidoController(PedidoService service) {
        this.service = service;
    }
	
	@PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<PedidoResponse> createNewPedido(@RequestBody PedidoRequest request) {	
		PedidoResponse response = service.createNewPedido(request); 
		return new ResponseEntity<>(response , HttpStatus.CREATED);
	}
	
	@GetMapping("/{numero}")
	public ResponseEntity<PedidoResponse> findPedidoById(@PathVariable("numero") @Min(value = 1, message = "Numero deve ser maior que 0") Long numero) {
		PedidoResponse response = service.findByIdPedido(numero);
		return new ResponseEntity<>(response , HttpStatus.OK);
	}
	
	@GetMapping()
	public ResponseEntity<List<PedidoResponse>> getAllPedidos() {
		List<PedidoResponse> responseList = service.getAllPedidos();
		return new ResponseEntity<>(responseList , HttpStatus.OK);
	}

	@PostMapping(value = "/{numero}/item", consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<PedidoResponse> addItemToPedido(@PathVariable("numero") @Min(value = 1, message = "Numero deve ser maior que 0") Long numero, @RequestBody ItemPedido itemPedido) {
		PedidoResponse response = service.createNewItemPedido(numero, itemPedido);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/{numero}/item/{indice}")
	public ResponseEntity<ItemPedido> getItemPedidoByIdAndIndex(@PathVariable("numero") @Min(value = 1, message = "Numero deve ser maior que 0") Long numero, @PathVariable("indice") @Min(value = 1, message = "Indice deve ser maior que 0")Integer indice) {
		ItemPedido itemResponse = service.findPedidoItemByIdAndIndice(numero, indice);
		return new ResponseEntity<>(itemResponse, HttpStatus.OK);
	}
	
	@GetMapping("/{numero}/item")
	public ResponseEntity<List<ItemPedido>> getAllItensByPedidoId(@PathVariable("numero") @Min(value = 1, message = "Numero deve ser maior que 0")Long numero) {
		List<ItemPedido> itensResponse = service.findItemPedidoByIdPedido(numero);
		return new ResponseEntity<>(itensResponse, HttpStatus.OK);
	}
	
	@GetMapping("/item")
	public ResponseEntity<List<ItemPedido>> findItensByProduto( @RequestParam("produto") @NotBlank(message = "O parametro 'produto' não pode ser vazio ou nulo") String produto) {
		List<ItemPedido> itensResponse = service.findItensByProduto(produto);
		return new ResponseEntity<>(itensResponse, HttpStatus.OK);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ResponseEntity<String> handleConstraintValidatioException(ConstraintViolationException ex) {
		return new ResponseEntity<>("Error: "+ ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
}
