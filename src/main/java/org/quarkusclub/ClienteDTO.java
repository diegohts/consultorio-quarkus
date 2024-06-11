package org.quarkusclub;

import lombok.Data;
import java.util.UUID;

@Data
public class ClienteDTO {

    private UUID id;
    private String responseMessage;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private String endereco;
    private String cidade;
    private String estado;
    private String nomePlano;
    private String indicacao;
}
