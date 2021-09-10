package br.com.billingreport.billingreport.config.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.billingreport.billingreport.modelo.Usuario;
import br.com.billingreport.billingreport.repository.UsuarioRepository;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

	private TokenService tokenService;
	private UsuarioRepository repository;

	public AutenticacaoViaTokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
		this.tokenService = tokenService;
		this.repository = usuarioRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain)
			throws ServletException, IOException {
		String token = recuperarToken(req);

		boolean valido = tokenService.isTokenValido(token);

		if (valido) {
			autenticarCliente(token);
		}

	}

	private void autenticarCliente(String token) {
		Long idUsuario = tokenService.getIdUsuario(token);

		Optional<Usuario> usuarioOptinal = repository.findById(idUsuario);
		if (usuarioOptinal.isPresent()) {
			Usuario usuario = usuarioOptinal.get();
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null,
					usuario.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);

		}

	}

	private String recuperarToken(HttpServletRequest req) {
		String token = req.getHeader("Authorization");
		if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		return token.substring(7, token.length());
	}

}
