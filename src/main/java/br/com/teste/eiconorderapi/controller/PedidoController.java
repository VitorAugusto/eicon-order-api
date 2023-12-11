package br.com.teste.eiconorderapi.controller;

import br.com.teste.eiconorderapi.model.PedidoDto;
import br.com.teste.eiconorderapi.model.Pedido;
import br.com.teste.eiconorderapi.service.PedidoService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javax.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador para operações relacionadas a pedidos.
 */
@RestController
@RequestMapping(value = "/v1/pedido")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    /**
     * Criação de pedidos.
     *
     * @param pedidoDto Lista de objetos de transferência de dados de pedidos.
     * @return ResponseEntity contendo a lista de pedidos salvos.
     */
    @PostMapping(value= "/", consumes = {MimeTypeUtils.APPLICATION_JSON_VALUE, MimeTypeUtils.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Pedido>> handlePedidos(@Size(min=1, max=10) @RequestBody List<PedidoDto> pedidoDto) {
        List<Pedido> pedidosSaved = pedidoService.handlePedidos(pedidoDto);
        return ResponseEntity.status(pedidosSaved.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK).body(pedidosSaved);
    }

    /**
     * Busca um pedido pelo número de controle.
     *
     * @param id Número de controle do pedido.
     * @return ResponseEntity contendo o pedido encontrado ou status NO_CONTENT se não encontrado.
     */
    @GetMapping(value= "/find/{id}")
    public ResponseEntity<Pedido> findByNumeroControle(@PathVariable String id) {
        Optional<Pedido> pedido = pedidoService.findByNumeroControle(id);
        return pedido.map(value -> ResponseEntity.status(HttpStatus.OK).body(value))
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }

    /**
     * Busca pedidos associados a um cliente.
     *
     * @param id Identificador do cliente.
     * @return ResponseEntity contendo a lista de pedidos encontrados ou status NO_CONTENT se não houver pedidos.
     */
    @GetMapping(value= "find/cliente/{id}")
    public ResponseEntity<List<Pedido>> findPedidosByClienteId(@PathVariable Long id) {
        List<Pedido> pedidos = pedidoService.findPedidosByClienteId(id);
        return ResponseEntity.status(pedidos.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK).body(pedidos);
    }

    /**
     * Busca pedidos com base em parâmetros específicos.
     *
     * @param dataCadastro Data de cadastro do pedido.
     * @param nome Nome associado ao pedido.
     * @param quantidade Quantidade de produtos no pedido.
     * @param valor Valor unitário do produto no pedido.
     * @param valorTotal Valor total do pedido.
     * @return ResponseEntity contendo a lista de pedidos encontrados ou status NO_CONTENT se não houver pedidos.
     */
    @GetMapping(value= "/find")
    public ResponseEntity<List<Pedido>> findByParams(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @RequestParam(required = false, name = "data") LocalDate dataCadastro,
            @RequestParam(required = false, name="nome") String nome,
            @RequestParam(required = false, name="quantidade") Integer quantidade,
            @RequestParam(required = false, name="valor") Long valor,
            @RequestParam(required = false, name = "valorTotal") Long valorTotal) {
        List<Pedido> pedidos = pedidoService.findByParams(dataCadastro, nome, quantidade, valor, valorTotal);
        return ResponseEntity.status(pedidos.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK).body(pedidos);
    }
}
