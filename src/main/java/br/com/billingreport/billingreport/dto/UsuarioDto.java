package br.com.billingreport.billingreport.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.billingreport.billingreport.modelo.Usuario;



public class UsuarioDto {
	
	private Long id;
	private String nome;
	private String cpf;
	private String email;


	public UsuarioDto(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.cpf = usuario.getCpf();
		this.email = usuario.getEmail();

	}





	public static List<UsuarioDto> converter(List<Usuario> usuario) {
		return usuario.stream().map(UsuarioDto::new).collect(Collectors.toList());
	}


}
