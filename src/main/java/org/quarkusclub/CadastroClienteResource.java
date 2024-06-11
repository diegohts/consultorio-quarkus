package org.quarkusclub;

import org.jboss.resteasy.reactive.RestResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

@Path("/v1/clientes")
public class CadastroClienteResource {

    // Nossa persistência em memória
    List<ClienteDTO> clientes = new ArrayList<>();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public RestResponse<List<ClienteDTO>> consultarClientes() {
        return RestResponse.status(Response.Status.OK, clientes);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public RestResponse<ClienteDTO> criarCliente(ClienteDTO cliente) {
        if(cliente == null) {
            ClienteDTO responseCliente = new ClienteDTO();
            responseCliente.setResponseMessage("Cliente nao informado");
            return RestResponse.status(RestResponse.Status.BAD_REQUEST, responseCliente);
        }

        cliente.setId(UUID.randomUUID());
        clientes.add(cliente);

        return RestResponse.status(Response.Status.CREATED, cliente);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public RestResponse<ClienteDTO> atualizarCliente(ClienteDTO cliente) {
        if(cliente == null) {
            ClienteDTO responseCliente = new ClienteDTO();
            responseCliente.setResponseMessage("Cliente nao informado");
            return RestResponse.status(RestResponse.Status.BAD_REQUEST, responseCliente);
        }

        if(cliente.getId() == null) {
            ClienteDTO responseCliente = new ClienteDTO();
            responseCliente.setResponseMessage("ID do cliente nao informado");
            return RestResponse.status(RestResponse.Status.BAD_REQUEST, responseCliente);
        }

        for(ClienteDTO c : clientes) {
            if(c.getId().equals(cliente.getId())) {
                c.setNome(cliente.getNome());
                c.setCpf(cliente.getCpf());
                c.setEndereco(cliente.getEndereco());
                return RestResponse.status(Response.Status.OK, c);
            }
        }

        ClienteDTO responseCliente = new ClienteDTO();
        responseCliente.setResponseMessage("Cliente nao encontrado");

        return RestResponse.status(Response.Status.NOT_FOUND, responseCliente);
    }
}
