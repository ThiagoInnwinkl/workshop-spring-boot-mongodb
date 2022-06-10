package com.thiagoinnwinkl.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.thiagoinnwinkl.workshopmongo.domain.Post;
import com.thiagoinnwinkl.workshopmongo.domain.User;
import com.thiagoinnwinkl.workshopmongo.dto.AuthorDTO;
import com.thiagoinnwinkl.workshopmongo.dto.ComentDTO;
import com.thiagoinnwinkl.workshopmongo.repository.PostRepository;
import com.thiagoinnwinkl.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob ));
		
		Post post1 = new Post(null, sdf.parse("21/03/2022"), "Partiu viagem", "Vou viajar para SP", new AuthorDTO (maria));
		Post post2 = new Post(null, sdf.parse("21/03/2022"), "Bom dia!", "Acordei feliz hoje!", new AuthorDTO (maria));
		
		ComentDTO c1 = new ComentDTO("Boa Viagem, mano!", sdf.parse("21/03/2018"), new AuthorDTO(alex));
		ComentDTO c2 = new ComentDTO("Aproveite, véio!", sdf.parse("22/03/2018"), new AuthorDTO(bob));
		ComentDTO c3 = new ComentDTO("Tenha um ótimo dia.", sdf.parse("23/03/2018"), new AuthorDTO(alex));
		
		post1.getComents().addAll(Arrays.asList(c1, c2));
		post2.getComents().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
	}

}
