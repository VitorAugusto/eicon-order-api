package br.com.teste.eiconorderapi.repository;

import br.com.teste.eiconorderapi.model.Pedido;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repositório para a entidade Pedido.
 */
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    /**
     * Busca pedidos com base em parâmetros específicos.
     *
     * @param dataCadastro Data de cadastro do pedido.
     * @param nome Nome associado ao pedido.
     * @param valor Valor unitário do produto no pedido.
     * @param quantidade Quantidade de produtos no pedido.
     * @param valorTotal Valor total do pedido.
     * @return Lista de pedidos encontrados.
     */
    @Query("SELECT p FROM Pedido p "
        + "WHERE "
        + "(:dataCadastro is null or p.dataCadastro = :dataCadastro) "
        + "AND (:nome is null or p.nome = :nome) "
        + "AND (:valor is null or p.valor = :valor) "
        + "AND (:quantidade is null or p.quantidade = :quantidade) "
        + "AND (:valorTotal is null or p.valorTotal = :valorTotal)")
    List<Pedido> findByParams(LocalDate dataCadastro, String nome, Long valor, Integer quantidade, Long valorTotal);

    /**
     * Busca pedidos associados a um cliente pelo ID do cliente.
     *
     * @param id Identificador do cliente.
     * @return Lista de pedidos encontrados para o cliente.
     */
    List<Pedido> findPedidosByClienteId(Long id);

    /**
     * Busca um pedido pelo número de controle.
     *
     * @param numeroControle Número de controle do pedido.
     * @return Pedido encontrado, se existir.
     */
    Optional<Pedido> findByNumeroControle(String numeroControle);
}
