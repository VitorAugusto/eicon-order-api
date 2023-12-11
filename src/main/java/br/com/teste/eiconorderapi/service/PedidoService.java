package br.com.teste.eiconorderapi.service;


import br.com.teste.eiconorderapi.model.PedidoDto;
import br.com.teste.eiconorderapi.model.Cliente;
import br.com.teste.eiconorderapi.model.Pedido;
import br.com.teste.eiconorderapi.repository.ClienteRepository;
import br.com.teste.eiconorderapi.repository.PedidoRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Serviço responsável por manipular operações relacionadas a pedidos.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final PedidoConverter pedidoConverter;

    /**
     * Manipula a criação de pedidos a partir de objetos PedidoDto.
     *
     * @param pedidoDtos Lista de objetos PedidoDto.
     * @return Lista de pedidos salvos.
     */
    public List<Pedido> handlePedidos(List<PedidoDto> pedidoDtos) {
        List<Pedido> pedidosToSave = convert(pedidoDtos);

        return pedidosToSave.stream()
            .filter(pedido -> {
                Optional<Pedido> existingPedido = pedidoRepository.findByNumeroControle(pedido.getNumeroControle());
                if (existingPedido.isPresent()) {
                    log.error("Número de controle já existe no banco de dados: " + pedido.getNumeroControle());
                    return false;
                }
                return true;
            })
            .filter(pedido -> {
                Optional<Cliente> existingCliente = clienteRepository.findById(pedido.getCliente().getId());
                if(existingCliente.isPresent()) {
                    pedido.setCliente(existingCliente.get());
                    return true;
                } else {
                    log.info("Cliente de ID [{}] não existe. Abortando salvamento do registro [{}]", pedido.getCliente().getId(), pedido);
                    return false;
                }
            })
            .map(pedidoRepository::save)
            .collect(Collectors.toList());
    }

    /**
     * Busca um pedido pelo número de controle.
     *
     * @param numControle Número de controle do pedido.
     * @return Pedido encontrado, se existir.
     */
    public Optional<Pedido> findByNumeroControle(String numControle) {
        return pedidoRepository.findByNumeroControle(numControle);
    }

    /**
     * Busca pedidos com base em parâmetros específicos.
     *
     * @param dataCadastro Data de cadastro do pedido.
     * @param nome Nome associado ao pedido.
     * @param quantidade Quantidade de produtos no pedido.
     * @param valor Valor unitário do produto no pedido.
     * @param valorTotal Valor total do pedido.
     * @return Lista de pedidos encontrados.
     */
    public List<Pedido> findByParams(LocalDate dataCadastro, String nome, Integer quantidade, Long valor, Long valorTotal) {
        return pedidoRepository.findByParams(dataCadastro, nome, valor, quantidade, valorTotal);
    }

    /**
     * Busca pedidos associados a um cliente pelo ID do cliente.
     *
     * @param id Identificador do cliente.
     * @return Lista de pedidos encontrados para o cliente.
     */
    public List<Pedido> findPedidosByClienteId(Long id) {
        return pedidoRepository.findPedidosByClienteId(id);
    }

    private List<Pedido> convert(List<PedidoDto> pedidoDtos) {
        return pedidoDtos.stream()
            .map(pedidoConverter::convert)
            .collect(Collectors.toList());
    }
}
