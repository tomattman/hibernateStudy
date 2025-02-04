package com.tomattman.javapersistence.springDataJpa.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

public class Projection {

    public interface UserSummary {
        String getUsername();//closed projection optimize query

        @Value("#{target.username} #{target.email}") //open projection not optimize query
        String getInfo();
    }

    @AllArgsConstructor
    @Getter
    public static class UsernameOnly {
        private String username;
    }
}
