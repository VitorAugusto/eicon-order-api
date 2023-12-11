package br.com.teste.eiconorderapi.service;

import static org.junit.jupiter.api.Assertions.*;

import br.com.teste.eiconorderapi.model.Pedido;
import br.com.teste.eiconorderapi.model.PedidoDto;
import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PedidoConverterTest {

    @Test
    @DisplayName("Conversão DTO -> Pedido")
    void convert() {
        PedidoDto pedidoDto = new PedidoDto();
        pedidoDto.setNumeroControle("1111");
        pedidoDto.setNome("Macbook");
        pedidoDto.setValor(10000L);
        pedidoDto.setDataCadastro(LocalDate.now());
        pedidoDto.setQuantidade(10);
        pedidoDto.setCodigoCliente(1L);

        Pedido pedido = new PedidoConverter().convert(pedidoDto);

        assertNotNull(pedido);
        assertEquals(pedidoDto.getNumeroControle(), pedido.getNumeroControle());

        assertEquals(pedidoDto.getNome(), pedido.getNome());
        assertEquals(pedidoDto.getValor(), pedido.getValor());
        assertEquals(pedidoDto.getCodigoCliente(), pedido.getCliente().getId());
        assertEquals(pedidoDto.getQuantidade(), pedido.getQuantidade());
        assertEquals(pedidoDto.getDataCadastro(), pedido.getDataCadastro());
        assertEquals(90000L, pedido.getValorTotal());
    }

    @Test
    @DisplayName("Teste getTotalAmountWithDiscount - Sem Desconto")
    void testGetTotalAmountWithDiscount_NoDiscount() {
        PedidoDto pedidoDto = new PedidoDto();
        pedidoDto.setQuantidade(5);
        pedidoDto.setValor(100L);

        Long result = new PedidoConverter().getTotalAmountWithDiscount(pedidoDto);

        assertEquals(500L, result);
    }

    @Test
    @DisplayName("Teste getTotalAmountWithDiscount - Desconto de 5%")
    void testGetTotalAmountWithDiscount_5PercentDiscount() {
        PedidoDto pedidoDto = new PedidoDto();
        pedidoDto.setQuantidade(6);
        pedidoDto.setValor(100L);

        Long result = new PedidoConverter().getTotalAmountWithDiscount(pedidoDto);

        assertEquals(570L, result);
    }

    @Test
    @DisplayName("Teste getTotalAmountWithDiscount - Desconto de 10%")
    void testGetTotalAmountWithDiscount_10PercentDiscount() {
        PedidoDto pedidoDto = new PedidoDto();
        pedidoDto.setQuantidade(10);
        pedidoDto.setValor(100L);

        Long result = new PedidoConverter().getTotalAmountWithDiscount(pedidoDto);

        assertEquals(900L, result);
    }

}