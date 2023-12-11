package br.com.teste.eiconorderapi.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * Entidade que representa um cliente.
 */
@Setter
@Getter
@ToString
@Table(schema = "cliente")
@Entity
public class Cliente {

    /**
     * Identificador único do cliente.
     */
    @Id
    private Long id;
    /**
     * Nome do cliente.
     */
    private String nome;
}
