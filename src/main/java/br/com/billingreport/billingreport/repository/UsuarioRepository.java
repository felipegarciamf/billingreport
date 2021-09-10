package br.com.billingreport.billingreport.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.billingreport.billingreport.modelo.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Optional<Usuario> findByEmail(String username);

	@Query("SELECT t FROM Usuario t WHERE t.email = :email AND t.senha = :senha")
	Optional<Usuario> findByEmailSenha(String email, String senha);

}
