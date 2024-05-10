package com.upb.coffe;

import com.upb.coffe.db.model.usuario.Usuario;
import com.upb.coffe.db.repository.usuario.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
public class CoffeManagerApplication implements CommandLineRunner {

	private final UsuarioRepository usuarioRepository;

	public static void main(String[] args) {
		SpringApplication.run(CoffeManagerApplication.class, args);
	}


	@Override
	public void run(String... arg) throws Exception{
		addUser();
	}

	private void addUser() {
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		//CREAR ADMIN - USUARIO
//		Usuario usuario1 = new Usuario();
//		usuario1.setNombreUsuario("admin");
//		usuario1.setPassword(encoder.encode("4708"));
//		usuario1.setNombre("Gerente de Sistema");
//		usuario1.setApellido("Admin");
//		usuario1.setEstado(false);
//
//		usuarioRepository.save(usuario1);
	}
}

