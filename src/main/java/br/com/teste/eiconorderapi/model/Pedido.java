package br.com.teste.eiconorderapi.model;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Entidade que representa um pedido.
 */
@Setter
@Getter
@ToString
@Table(schema = "pedido")
@Entity
public class Pedido {

    /**
     * Identificador único do pedido.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Cliente associado ao pedido.
     */
    @ManyToOne
    @JoinColumn(name = "codigoCliente")
    private Cliente cliente;

    /**
     * Número de controle do pedido.
     */
    private String numeroControle;

    /**
     * Nome associado ao pedido.
     */
    private String nome;

    /**
     * Valor unitário do produto no pedido.
     */
    private Long valor;

    /**
     * Quantidade de produtos no pedido.
     */
    private Integer quantidade;

    /**
     * Valor total do pedido.
     */
    private Long valorTotal;

    /**
     * Data de cadastro do pedido.
     */
    private LocalDate dataCadastro;
}
