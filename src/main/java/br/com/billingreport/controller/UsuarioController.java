package br.com.billingreport.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.billingreport.dto.DetalheUsuarioDto;
import br.com.billingreport.dto.UsuarioDto;
import br.com.billingreport.form.UsuarioForm;
import br.com.billingreport.modelo.Usuario;
import br.com.billingreport.repository.UsuarioRepository;

@RestController
@RequestMapping("/cadastro")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping
	private List<UsuarioDto> lista(String nome) {
		List<Usuario> usuario;

		if (nome == null) {
			usuario = usuarioRepository.findAll();
		} else {
			usuario = usuarioRepository.findByNome(nome);
		}

		return UsuarioDto.converter(usuario);
	}

	@PostMapping
	@Transactional
	public ResponseEntity<UsuarioDto> cadastrar(@RequestBody UsuarioForm form, UriComponentsBuilder uriBuilder) {
		Optional<Usuario> findByEmail = usuarioRepository.findByEmail(form.getEmail());
		if (!findByEmail.isPresent()) {
			Usuario usuario = form.converter();
			usuarioRepository.save(usuario);
			URI uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
			return ResponseEntity.created(uri).body(new UsuarioDto(usuario));
		}
		return ResponseEntity.notFound().build();
	}


	
	@GetMapping("/{id}")
	public ResponseEntity<DetalheUsuarioDto> detalhar(@PathVariable Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if (usuario.isPresent()) {
			return ResponseEntity.ok(new DetalheUsuarioDto(usuario.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	

}
