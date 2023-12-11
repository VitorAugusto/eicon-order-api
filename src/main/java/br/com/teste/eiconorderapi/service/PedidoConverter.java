package br.com.teste.eiconorderapi.service;


import br.com.teste.eiconorderapi.model.PedidoDto;
import br.com.teste.eiconorderapi.model.Cliente;
import br.com.teste.eiconorderapi.model.Pedido;
import java.time.LocalDate;
import lombok.var;
import org.springframework.stereotype.Component;

/**
 * Componente responsável por converter objetos do tipo PedidoDto para a entidade Pedido.
 */
@Component
public class PedidoConverter {

    /**
     * Converte um objeto PedidoDto em um objeto Pedido.
     *
     * @param pedidoDto Objeto de transferência de dados de pedido.
     * @return Pedido convertido.
     */
    public Pedido convert(PedidoDto pedidoDto) {
        Pedido pedido = new Pedido();
        Cliente cliente = new Cliente();
        cliente.setId(pedidoDto.getCodigoCliente());

        pedido.setNumeroControle(pedidoDto.getNumeroControle());
        pedido.setDataCadastro((pedidoDto.getDataCadastro() == null) ? LocalDate.now() : pedidoDto.getDataCadastro());
        pedido.setNome(pedidoDto.getNome());
        pedido.setValor(pedidoDto.getValor());
        pedido.setQuantidade((pedidoDto.getQuantidade() == 0) ? 1 : pedidoDto.getQuantidade());
        pedido.setCliente(cliente);
        pedido.setValorTotal(getTotalAmountWithDiscount(pedidoDto));

        return pedido;
    }

    /**
     * Calcula o valor total do pedido aplicando descontos, se aplicável.
     *
     * @param pedidoDto Objeto de transferência de dados de pedido.
     * @return Valor total com desconto, se aplicável.
     */
    protected Long getTotalAmountWithDiscount(PedidoDto pedidoDto) {
        int quantidade = Math.max(pedidoDto.getQuantidade(), 1);
        var valorTotalParcial = pedidoDto.getValor() * quantidade;

        if (quantidade > 5) {
            double percentualDesconto = (quantidade >= 10) ? 0.10 : 0.05;
            double desconto = valorTotalParcial * percentualDesconto;
            return Math.round(valorTotalParcial - desconto);
        }

        return valorTotalParcial;
    }
}
