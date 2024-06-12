package org.quarkusclub;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class CadastroClienteService {

    List<ClienteDTO> clientes = new ArrayList<>();

    public ClienteDTO criarCliente(ClienteDTO cliente) {
        cliente.setId(UUID.randomUUID());
        clientes.add(cliente);

        return cliente;
    }

    public ClienteDTO atualizarParcialCliente(UUID idCliente, ClienteDTO cliente) throws BusinessException {
        for(ClienteDTO c : clientes) {
            if(c.getId().equals(idCliente)) {
                if(cliente.getNome() != null) {
                    c.setNome(cliente.getNome());
                }
                if(cliente.getCpf() != null) {
                    c.setCpf(cliente.getCpf());
                }
                if(cliente.getEndereco() != null) {
                    c.setEndereco(cliente.getEndereco());
                }
                if(cliente.getCidade() != null) {
                    c.setCidade(cliente.getCidade());
                }
                if(cliente.getEstado() != null) {
                    c.setEstado(cliente.getEstado());
                }
                if(cliente.getEmail() != null) {
                    c.setEmail(cliente.getEmail());
                }
                if(cliente.getTelefone() != null) {
                    c.setTelefone(cliente.getTelefone());
                }
                if(cliente.getNomePlano() != null) {
                    c.setNomePlano(cliente.getNomePlano());
                }
                if(cliente.getIndicacao() != null) {
                    c.setIndicacao(cliente.getIndicacao());
                }
                return c;
            }
        }
        throw new BusinessException("Cliente nao encontrado");
    }

    public ClienteDTO atualizarTodosCamposCliente(ClienteDTO cliente) throws BusinessException {
        for(ClienteDTO c : clientes) {
            if(c.getId().equals(cliente.getId())) {
                c.setNome(cliente.getNome());
                c.setCpf(cliente.getCpf());
                c.setEndereco(cliente.getEndereco());
                c.setCidade(cliente.getCidade());
                c.setEstado(cliente.getEstado());
                c.setEmail(cliente.getEmail());
                c.setTelefone(cliente.getTelefone());
                c.setNomePlano(cliente.getNomePlano());
                c.setIndicacao(cliente.getIndicacao());
                return c;
            }
        }
        throw new BusinessException("Cliente nao encontrado");
    }

    public List<ClienteDTO> consultarClientes() {
        if(clientes == null) {
            throw new RuntimeException("Banco de dados nao inicializado");
        }
        return clientes;
    }

}
