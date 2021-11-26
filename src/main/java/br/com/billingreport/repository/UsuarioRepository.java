package br.com.billingreport.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.billingreport.modelo.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Optional<Usuario> findByEmail(String username);

	@Query("SELECT t FROM Usuario t WHERE t.email = :email AND t.senha = :senha")
	Optional<Usuario> findByEmailSenha(String email, String senha);

	List<Usuario> findByNome(String nome);

}
