package br.com.billingreport.billingreport.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.billingreport.billingreport.modelo.Usuario;
import br.com.billingreport.billingreport.repository.UsuarioRepository;

@Service
public class AutenticacaoService implements UserDetailsService {
	@Autowired
	UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		Optional<Usuario> usuario = usuarioRepository.findByEmail(username);
		if (usuario.isPresent()) {
			return usuario.get();
		}
		throw new UsernameNotFoundException("Dados Inválidos");
	}

}
