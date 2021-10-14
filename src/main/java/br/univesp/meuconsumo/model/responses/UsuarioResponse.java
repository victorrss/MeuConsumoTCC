package br.univesp.meuconsumo.model.responses;

import lombok.Data;

@Data
public class UsuarioResponse {
	private Long id;
	private String nome;
	private String email;
	private String usuario;
}