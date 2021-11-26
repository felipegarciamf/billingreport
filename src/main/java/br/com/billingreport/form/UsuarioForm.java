package br.com.billingreport.form;

import br.com.billingreport.modelo.Usuario;

public class UsuarioForm {

	private String nome;

	private String cpf;

	private String email;

	private String senha;
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	public Usuario converter() {
		return new Usuario(nome, cpf, email, senha);
	}
	
}
