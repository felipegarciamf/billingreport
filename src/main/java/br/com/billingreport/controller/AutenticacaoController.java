package br.com.billingreport.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.billingreport.config.security.TokenService;
import br.com.billingreport.dto.TokenDto;
import br.com.billingreport.form.LoginForm;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

	private final static Logger logger = Logger.getLogger(AutenticacaoController.class);

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private TokenService tokenService;

	@CrossOrigin
	@GetMapping
	public String Lista() {
		return "mais sorte da proxima vez";
	}

	
	@PostMapping
	public ResponseEntity<TokenDto> autenticar(@RequestBody @Validated LoginForm form) {
		UsernamePasswordAuthenticationToken dadosLogin =  form.converter();
		try {			
			Authentication authentication = authManager.authenticate(dadosLogin);
			String token = tokenService.gerarToken(authentication);
			return ResponseEntity.ok(new TokenDto(token, "Bearer"));
			
		} catch (AuthenticationException e) {
			logger.error("Error: ", e);
			return ResponseEntity.badRequest().build();
		}
		
	}

}
