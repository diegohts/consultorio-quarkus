package org.quarkusclub;

import org.jboss.resteasy.reactive.RestResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

@Path("/v1/clientes")
public class CadastroClienteResource {

    @Inject
    CadastroClienteService cadastroClienteService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public RestResponse<List<ClienteDTO>> consultarClientes() {
        List<ClienteDTO> clientes = cadastroClienteService.consultarClientes();
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

        ClienteDTO novoCliente = cadastroClienteService.criarCliente(cliente);

        return RestResponse.status(Response.Status.CREATED, novoCliente);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public RestResponse<ClienteDTO> atualizarTodosCamposCliente(ClienteDTO cliente) {
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

        ClienteDTO clienteAtualizado = cadastroClienteService.atualizarTodosCamposCliente(cliente);
        if(clienteAtualizado == null) {
            ClienteDTO responseCliente = new ClienteDTO();
            responseCliente.setResponseMessage("Cliente nao encontrado");
            return RestResponse.status(RestResponse.Status.NOT_FOUND, responseCliente);
        }

        return RestResponse.status(Response.Status.OK, clienteAtualizado);
    }

    @PATCH
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{idCliente}")
    public RestResponse<ClienteDTO> atualizarParcialCliente(@PathParam("idCliente") UUID idCliente, ClienteDTO cliente) {
        if(cliente == null) {
            ClienteDTO responseCliente = new ClienteDTO();
            responseCliente.setResponseMessage("Cliente nao informado");
            return RestResponse.status(RestResponse.Status.BAD_REQUEST, responseCliente);
        }

        if(idCliente == null) {
            ClienteDTO responseCliente = new ClienteDTO();
            responseCliente.setResponseMessage("ID do cliente nao informado");
            return RestResponse.status(RestResponse.Status.BAD_REQUEST, responseCliente);
        }

        ClienteDTO clienteAtualizado = cadastroClienteService.atualizarParcialCliente(idCliente, cliente);
        if(clienteAtualizado == null) {
            ClienteDTO responseCliente = new ClienteDTO();
            responseCliente.setResponseMessage("Cliente nao encontrado");
            return RestResponse.status(RestResponse.Status.NOT_FOUND, responseCliente);
        }

        return RestResponse.status(Response.Status.OK, clienteAtualizado);
    }
}
