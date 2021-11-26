package br.com.billingreport.controller;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.billingreport.dto.UsuarioDto;
import br.com.billingreport.form.LoginForm;
import br.com.billingreport.modelo.Usuario;
import br.com.billingreport.repository.UsuarioRepository;


@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	UsuarioRepository usuarioRepository;

	@CrossOrigin
	@PostMapping
	public ResponseEntity<UsuarioDto> login(@RequestBody @Validated LoginForm loginForm) {
		Optional<Usuario> optional = usuarioRepository.findByEmailSenha(loginForm.getEmail(), loginForm.getSenha());
		if (optional.isPresent()) {
			return ResponseEntity.ok(new UsuarioDto(optional.get()));
		}
		return ResponseEntity.notFound().build();
	}



}
