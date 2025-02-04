package com.tomattman.javapersistence.springDataJpa;

import com.tomattman.javapersistence.springDataJpa.model.Projection;
import com.tomattman.javapersistence.springDataJpa.model.User;
import com.tomattman.javapersistence.springDataJpa.repo.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SpringDataJpaApplicationTests {

    @Autowired
    UserRepository userRepository;

    @BeforeAll
    void beforeAll() {
        userRepository.saveAll(generateUsers());
    }

    @AfterAll
    void afterAll() {
        userRepository.deleteAll();
    }

    @Test
    void findAll() {
        List<User> users = userRepository.findAll();
        Assertions.assertEquals(3, users.size());
    }
    @Test
    void findByUsername() {
        User user = userRepository.findByUsername("Beth");
        Assertions.assertEquals("Beth", user.getUsername());
    }
    @Test
    void findAllByOrderByUsernameAsc(){
        List<User> users = userRepository.findAllByOrderByUsernameAsc();
        Assertions.assertAll(() -> Assertions.assertEquals(3, users.size()),
                () -> Assertions.assertEquals("Beth", users.get(0).getUsername()),
                () -> Assertions.assertEquals("Steph", users.get(users.size() - 1).getUsername()));
    }

    @Test
    void findByEmail() {
        List<Projection.UsernameOnly> byEmail = userRepository.findByEmail("john@gmail.com");
        Assertions.assertEquals(1, byEmail.size());
    }

    @Test
    void deleteByLevel(){
        //userRepository.deleteByLevel(1);
        //userRepository.deleteBulkByLevel(1);
    }

    @Test
    void testEmailWithQueryByExample() {
        User user = new User();
        user.setEmail("@gmail.com");

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("level", "active") //primitives has default values if not ignoring it will be included into query
                .withMatcher("email", ExampleMatcher.GenericPropertyMatcher::endsWith);

        Example<User> example = Example.of(user, matcher);
        List<User> users = userRepository.findAll(example);
        Assertions.assertEquals(3, users.size());

    }


    private static List<User> generateUsers() {
        List<User> users = new ArrayList<>();
        User john = new User("John", LocalDate.of(1990, 1, 1));
        john.setEmail("john@gmail.com");
        john.setLevel(1);
        john.setActive(true);
        users.add(john);
        User beth = new User("Beth", LocalDate.of(1990, 2, 1));
        beth.setEmail("beth@gmail.com");
        beth.setLevel(1);
        beth.setActive(true);
        users.add(beth);
        User steph = new User("Steph", LocalDate.of(1990, 3, 1));
        steph.setEmail("steph@gmail.com");
        steph.setLevel(1);
        steph.setActive(true);
        users.add(steph);

        return users;
    }

}
