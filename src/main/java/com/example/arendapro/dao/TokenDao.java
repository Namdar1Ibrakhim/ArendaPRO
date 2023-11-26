package com.example.arendapro.dao;

import com.example.arendapro.security.token.Token;

import java.util.List;
import java.util.Optional;

public interface TokenDao {

    void save(Token token);
    Optional<Token> findByToken(String token);
    void saveAll(List<Token> tokens);
    List<Token> findAllValidTokensByUser(Integer userId);
}
