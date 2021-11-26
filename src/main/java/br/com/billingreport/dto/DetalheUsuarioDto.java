package br.com.billingreport.dto;

import br.com.billingreport.modelo.Usuario;

public class DetalheUsuarioDto {

	public DetalheUsuarioDto(Usuario usuario) {
		this.nome = usuario.getNome();
		this.cpf = usuario.getCpf();
		this.email = usuario.getEmail();
	}

	private String nome;
	private String cpf;
	private String email;

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public String getEmail() {
		return email;
	}

}
