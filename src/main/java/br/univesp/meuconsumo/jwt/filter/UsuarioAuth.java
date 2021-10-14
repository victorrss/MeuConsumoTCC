package br.univesp.meuconsumo.jwt.filter;

import lombok.Data;

@Data
public class UsuarioAuth {
    private String username;
    private String password;
}