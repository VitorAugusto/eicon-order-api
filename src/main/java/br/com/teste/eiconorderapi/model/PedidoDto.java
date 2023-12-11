package br.com.teste.eiconorderapi.model;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Objeto de transferência de dados (DTO) representando um Pedido.
 */
@Data
@NoArgsConstructor
public class PedidoDto {

    /**
     * Número de controle do pedido.
     */
    @NotNull
    private String numeroControle;

    /**
     * Nome associado ao pedido.
     */
    @NotNull
    private String nome;

    /**
     * Valor unitário do produto no pedido.
     */
    @NotNull
    private Long valor;

    /**
     * Data de cadastro do pedido.
     */
    private LocalDate dataCadastro;

    /**
     * Quantidade de produtos no pedido.
     */
    private int quantidade;

    /**
     * Código do cliente associado ao pedido.
     */
    @NotNull
    private Long codigoCliente;
}
