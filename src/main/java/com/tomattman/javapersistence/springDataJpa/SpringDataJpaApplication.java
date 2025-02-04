package com.tomattman.javapersistence.springDataJpa;

import com.tomattman.javapersistence.springDataJpa.model.User;
import com.tomattman.javapersistence.springDataJpa.repo.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class SpringDataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaApplication.class, args);
	}

	@Bean
	public ApplicationRunner configure(UserRepository userRepository) {
		return env -> {
			User user1 = new User("Alex", LocalDate.of(2020, 1, 1));
			User user2 = new User("Bob", LocalDate.of(2020, 2, 2));

			userRepository.save(user1);
			userRepository.save(user2);

			userRepository.findAll().forEach(System.out::println);
		};
	}

}
