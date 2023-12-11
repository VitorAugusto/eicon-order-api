package br.com.teste.eiconorderapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import br.com.teste.eiconorderapi.model.Pedido;
import br.com.teste.eiconorderapi.service.PedidoService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class PedidoControllerTest {

    @Mock
    private PedidoService pedidoService;

    @InjectMocks
    private PedidoController pedidoController;

    @Test
    @DisplayName("Handle Pedidos - OK")
    void testHandlePedidos_OK() {
        List<Pedido> pedidosSaved = new ArrayList<>();
        pedidosSaved.add(new Pedido());

        when(pedidoService.handlePedidos(new ArrayList<>())).thenReturn(pedidosSaved);

        ResponseEntity<List<Pedido>> responseEntity = pedidoController.handlePedidos(new ArrayList<>());

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(pedidosSaved, responseEntity.getBody());
    }

    @Test
    @DisplayName("Handle Pedidos - NO CONTENT")
    void testHandlePedidos_NOCONTENT() {
        when(pedidoService.handlePedidos(new ArrayList<>())).thenReturn(Collections.emptyList());

        ResponseEntity<List<Pedido>> responseEntity = pedidoController.handlePedidos(new ArrayList<>());

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody().isEmpty());
    }

    @Test
    @DisplayName("Find By Numero Controle - OK")
    void testFindByNumeroControle_OK() {
        String pedidoId = "123";

        when(pedidoService.findByNumeroControle(pedidoId)).thenReturn(Optional.of(new Pedido()));

        ResponseEntity<Pedido> responseEntity = pedidoController.findByNumeroControle(pedidoId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    @DisplayName("Find By Numero Controle - NO CONTENT")
    void testFindByNumeroControle_NOCONTENT() {
        String pedidoId = "123";

        when(pedidoService.findByNumeroControle(pedidoId)).thenReturn(Optional.empty());

        ResponseEntity<Pedido> responseEntity = pedidoController.findByNumeroControle(pedidoId);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());
    }

    @Test
    @DisplayName("Find Pedidos By ClienteId - OK")
    void testFindPedidosByClienteId_OK() {
        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(new Pedido());

        Long clienteId = 456L;

        when(pedidoService.findPedidosByClienteId(clienteId)).thenReturn(pedidos);

        ResponseEntity<List<Pedido>> responseEntity = pedidoController.findPedidosByClienteId(clienteId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(pedidos, responseEntity.getBody());
    }

    @Test
    @DisplayName("Find Pedidos By ClienteId - NO CONTENT")
    void testFindPedidosByClienteId_NOCONTENT() {
        Long clienteId = 456L;

        when(pedidoService.findPedidosByClienteId(clienteId)).thenReturn(Collections.emptyList());

        ResponseEntity<List<Pedido>> responseEntity = pedidoController.findPedidosByClienteId(clienteId);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody().isEmpty());
    }

    @Test
    @DisplayName("Find By Params - OK")
    void testFindByParams_OK() {
        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(new Pedido());

        LocalDate dataCadastro = LocalDate.now();
        String nome = "Teste";
        Integer quantidade = 2;
        Long valor = 100L;
        Long valorTotal = 200L;

        when(pedidoService.findByParams(any(), any(), any(), any(), any())).thenReturn(pedidos);

        ResponseEntity<List<Pedido>> responseEntity = pedidoController.findByParams(dataCadastro, nome, quantidade, valor, valorTotal);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
    }

    @Test
    @DisplayName("Find By Params - NO CONTENT")
    void testFindByParams_NOCONTENT() {
        LocalDate dataCadastro = LocalDate.now();
        String nome = "Teste";
        Integer quantidade = 2;
        Long valor = 100L;
        Long valorTotal = 200L;

        when(pedidoService.findByParams(any(), any(), any(), any(), any())).thenReturn(Collections.emptyList());

        ResponseEntity<List<Pedido>> responseEntity = pedidoController.findByParams(dataCadastro, nome, quantidade, valor, valorTotal);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody().isEmpty());
    }
}