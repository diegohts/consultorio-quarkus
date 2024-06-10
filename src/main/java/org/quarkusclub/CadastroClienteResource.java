package org.quarkusclub;

import org.jboss.resteasy.reactive.RestResponse;
import jakarta.ws.rs.POST;
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

    List<ClienteDTO> clientes = new ArrayList<>();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public RestResponse<ClienteDTO> criarCliente(ClienteDTO cliente) {
        if(cliente == null) {
            ClienteDTO responseCliente = new ClienteDTO();
            responseCliente.setResponseMessage("Cliente nao informado");
        }

        cliente.setId(UUID.randomUUID());
        clientes.add(cliente);

        return RestResponse.status(Response.Status.CREATED, cliente);
    }
}
