package br.com.teste.eiconorderapi.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.teste.eiconorderapi.model.Cliente;
import br.com.teste.eiconorderapi.model.Pedido;
import br.com.teste.eiconorderapi.model.PedidoDto;
import br.com.teste.eiconorderapi.repository.ClienteRepository;
import br.com.teste.eiconorderapi.repository.PedidoRepository;
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

@ExtendWith(MockitoExtension.class)
class PedidoServiceTest {

    @Mock
    PedidoRepository pedidoRepository;

    @Mock
    ClienteRepository clienteRepository;

    @Mock
    private PedidoConverter pedidoConverter;

    @InjectMocks
    PedidoService pedidoService;

    @Test
    @DisplayName("Teste handlePedidos - Pedido Correto")
    void testHandlePedidos_PedidoCorreto() {
        PedidoDto pedidoDto = new PedidoDto();
        pedidoDto.setNumeroControle("1111");
        pedidoDto.setNome("Macbook");
        pedidoDto.setValor(10000L);
        pedidoDto.setDataCadastro(LocalDate.now());
        pedidoDto.setQuantidade(10);
        pedidoDto.setCodigoCliente(1L);


        List<PedidoDto> pedidoDtoList = new ArrayList<>();
        pedidoDtoList.add(pedidoDto);

        Pedido pedido = new PedidoConverter().convert(pedidoDto);

        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Cliente_1");

        when(pedidoConverter.convert(any())).thenReturn(pedido);
        when(pedidoRepository.findByNumeroControle(pedido.getNumeroControle())).thenReturn(Optional.empty());
        when(clienteRepository.findById(any())).thenReturn(Optional.of(cliente));

        List<Pedido> result = pedidoService.handlePedidos(pedidoDtoList);

        assertFalse(result.isEmpty());
        verify(pedidoRepository, times(1)).findByNumeroControle(any());
        verify(clienteRepository, times(1)).findById(any());
        verify(pedidoRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Teste handlePedidos - Pedido Duplicado")
    void testHandlePedidos_DuplicatePedido() {
        PedidoDto pedidoDto = new PedidoDto();
        pedidoDto.setNumeroControle("1111");
        pedidoDto.setNome("Macbook");
        pedidoDto.setValor(10000L);
        pedidoDto.setDataCadastro(LocalDate.now());
        pedidoDto.setQuantidade(10);
        pedidoDto.setCodigoCliente(1L);


        List<PedidoDto> pedidoDtoList = new ArrayList<>();
        pedidoDtoList.add(pedidoDto);

        Pedido pedido = new PedidoConverter().convert(pedidoDto);

        when(pedidoConverter.convert(any())).thenReturn(pedido);
        when(pedidoRepository.findByNumeroControle(pedido.getNumeroControle())).thenReturn(Optional.of(pedido));

        List<Pedido> result = pedidoService.handlePedidos(pedidoDtoList);

        assertTrue(result.isEmpty());
        verify(pedidoRepository, times(1)).findByNumeroControle(any());
        verify(clienteRepository, never()).findById(any());
        verify(pedidoRepository, never()).save(any());
    }

    @Test
    @DisplayName("Teste handlePedidos - Cliente Inexistente")
    void testHandlePedidos_ClienteInexistente() {
        PedidoDto pedidoDto = new PedidoDto();
        pedidoDto.setNumeroControle("1111");
        pedidoDto.setNome("Macbook");
        pedidoDto.setValor(10000L);
        pedidoDto.setDataCadastro(LocalDate.now());
        pedidoDto.setQuantidade(10);
        pedidoDto.setCodigoCliente(1L);


        List<PedidoDto> pedidoDtoList = new ArrayList<>();
        pedidoDtoList.add(pedidoDto);

        Pedido pedido = new PedidoConverter().convert(pedidoDto);

        when(pedidoConverter.convert(any())).thenReturn(pedido);
        when(pedidoRepository.findByNumeroControle(pedido.getNumeroControle())).thenReturn(Optional.empty());
        when(clienteRepository.findById(any())).thenReturn(Optional.empty());

        List<Pedido> result = pedidoService.handlePedidos(pedidoDtoList);

        assertTrue(result.isEmpty());
        verify(pedidoRepository, times(1)).findByNumeroControle(any());
        verify(clienteRepository, times(1)).findById(any());
        verify(pedidoRepository, never()).save(any());
    }

    @Test
    @DisplayName("Teste handlePedidos - Cliente Existe")
    void testHandlePedidos_ClienteExiste() {
        PedidoDto pedidoDto = new PedidoDto();
        pedidoDto.setNumeroControle("1111");
        pedidoDto.setNome("Macbook");
        pedidoDto.setValor(10000L);
        pedidoDto.setDataCadastro(LocalDate.now());
        pedidoDto.setQuantidade(10);
        pedidoDto.setCodigoCliente(1L);


        List<PedidoDto> pedidoDtoList = new ArrayList<>();
        pedidoDtoList.add(pedidoDto);

        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Cliente_1");

        Pedido pedido = new PedidoConverter().convert(pedidoDto);

        when(pedidoConverter.convert(any())).thenReturn(pedido);
        when(pedidoRepository.findByNumeroControle(pedido.getNumeroControle())).thenReturn(Optional.empty());
        when(clienteRepository.findById(any())).thenReturn(Optional.of(cliente));

        List<Pedido> result = pedidoService.handlePedidos(pedidoDtoList);

        assertFalse(result.isEmpty());
        verify(pedidoRepository, times(1)).findByNumeroControle(any());
        verify(clienteRepository, times(1)).findById(any());
        verify(pedidoRepository, times(1)).save(any());
    }


    @Test
    @DisplayName("Teste findByNumeroControle")
    void findByNumeroControle() {
        String numControle = "1111";

        when(pedidoRepository.findByNumeroControle(numControle)).thenReturn(Optional.empty());
        pedidoService.findByNumeroControle(numControle);

        verify(pedidoRepository).findByNumeroControle(numControle);
    }

    @Test
    @DisplayName("Teste findByParams")
    void findByParams() {
        LocalDate dataCadastro = LocalDate.now();
        String nome = "Macbook";
        Integer quantidade = 10;
        Long valor = 10000L;
        Long valorTotal = 100000L;

        when(pedidoRepository.findByParams(dataCadastro, nome, valor, quantidade, valorTotal)).thenReturn(Collections.emptyList());
        pedidoService.findByParams(dataCadastro, nome, quantidade, valor, valorTotal);

        verify(pedidoRepository).findByParams(dataCadastro, nome, valor, quantidade, valorTotal);
    }

    @Test
    @DisplayName("Teste findPedidosByClienteId")
    void findPedidosByClienteId() {
        Long id = 100L;

        when(pedidoRepository.findPedidosByClienteId(id)).thenReturn(Collections.emptyList());
        pedidoService.findPedidosByClienteId(id);

        verify(pedidoRepository).findPedidosByClienteId(id);
    }

}