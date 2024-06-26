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
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.UUID;

@Slf4j
@Path("/v1/clientes")
public class CadastroClienteResource {

    @Inject
    CadastroClienteService cadastroClienteService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public RestResponse<List<ClienteDTO>> consultarClientes() {
        try {
            List<ClienteDTO> clientes = cadastroClienteService.consultarClientes();
            return RestResponse.status(Response.Status.OK, clientes);
        } catch (RuntimeException e) {
            ClienteDTO clienteDTO = new ClienteDTO();
            clienteDTO.setResponseMessage(e.getMessage());
            log.error("Erro ao consultar clientes", e);
            return RestResponse.status(Response.Status.INTERNAL_SERVER_ERROR, List.of(clienteDTO));
        }
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

        try {
            ClienteDTO novoCliente = cadastroClienteService.criarCliente(cliente);
            return RestResponse.status(Response.Status.CREATED, novoCliente);
        } catch (RuntimeException e) {
            ClienteDTO clienteDTO = new ClienteDTO();
            clienteDTO.setResponseMessage(e.getMessage());
            log.error("Erro ao criar cliente", e);
            return RestResponse.status(Response.Status.INTERNAL_SERVER_ERROR, clienteDTO);
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{idCliente}")
    public RestResponse<ClienteDTO> atualizarTodosCamposCliente(@PathParam("idCliente") UUID idCliente, ClienteDTO cliente) {
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

        try {
            ClienteDTO clienteAtualizado = cadastroClienteService.atualizarTodosCamposCliente(cliente);
            return RestResponse.status(Response.Status.OK, clienteAtualizado);
        } catch (BusinessException e) {
            ClienteDTO clienteDTO = new ClienteDTO();
            clienteDTO.setResponseMessage(e.getMessage());
            log.error("Cliente nao cadastrado", e);
            return RestResponse.status(Response.Status.NOT_FOUND, clienteDTO);
        } catch (RuntimeException e) {
            ClienteDTO clienteDTO = new ClienteDTO();
            clienteDTO.setResponseMessage(e.getMessage());
            log.error("Erro ao atualizar cliente", e);
            return RestResponse.status(Response.Status.INTERNAL_SERVER_ERROR, clienteDTO);
        }
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

        try {
            ClienteDTO clienteAtualizado = cadastroClienteService.atualizarParcialCliente(idCliente, cliente);
            return RestResponse.status(Response.Status.OK, clienteAtualizado);
        } catch (BusinessException e) {
            ClienteDTO clienteDTO = new ClienteDTO();
            clienteDTO.setResponseMessage(e.getMessage());
            log.error("Cliente nao cadastrado", e);
            return RestResponse.status(Response.Status.NOT_FOUND, clienteDTO);
        } catch (RuntimeException e) {
            ClienteDTO clienteDTO = new ClienteDTO();
            clienteDTO.setResponseMessage(e.getMessage());
            log.error("Erro ao atualizar cliente", e);
            return RestResponse.status(Response.Status.INTERNAL_SERVER_ERROR, clienteDTO);
        }
    }
}
