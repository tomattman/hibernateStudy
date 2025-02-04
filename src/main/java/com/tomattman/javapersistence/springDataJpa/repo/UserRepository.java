package com.tomattman.javapersistence.springDataJpa.repo;

import com.tomattman.javapersistence.springDataJpa.model.Projection;
import com.tomattman.javapersistence.springDataJpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    List<User> findAllByOrderByUsernameAsc();
    List<User> findByRegistrationDateBetween(LocalDate start, LocalDate end);
    List<User> findByUsernameAndEmail(String username, String email);
    List<User> findByUsernameOrEmail(String username, String email);
    List<User> findByUsernameIgnoreCase(String username);
    List<User> findByLevelOrderByUsernameDesc(int level);
    List<User> findByLevelGreaterThanEqual(int level);
    List<User> findByUsernameContaining(String text);
    List<User> findByUsernameLike(String text);
    List<User> findByUsernameStartingWith(String text);
    List<User> findByUsernameEndingWith(String text);
    List<User> findByActive(boolean active);
    List<User> findByRegistrationDateIn(Collection<LocalDate> dates);
    List<User> findByRegistrationDateNotIn(Collection<LocalDate> dates);

    //return projections
    List<Projection.UserSummary> findByRegistrationDateAfter(LocalDate date);
    List<Projection.UsernameOnly>findByEmail(String email);
    <T> List<T> findByEmail(String email, Class<T> clazz);

    //modify
    @Transactional
    int deleteByLevel(int level); // delete one by one
    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.level = ?1") // delete group
    int deleteBulkByLevel(int level);
}
