package com.example.arendapro.dao;

import com.example.arendapro.security.token.Token;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Repository
@RequiredArgsConstructor
public class TokenDaoImpl implements TokenDao{

    private final RedisTemplate redisTemplate;
    private static final String KEY = "TOKEN";

    @Override
    public void save(Token token) {
        log.info(3+" "+ token.toString());
        try {
            redisTemplate.opsForHash().put(KEY, token.getToken(), token);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Token> findByToken(String token) {
        log.info(2+" " +token.toString());
        return (Optional<Token>) redisTemplate.opsForHash().get(KEY, token);

    }

    @Override
    public void saveAll(List<Token> tokens) {
        log.info(tokens.toString());
        for (Token tk : tokens){
            save(tk);
        }
    }

    @Override
    public List<Token> findAllValidTokensByUser(Integer userId) {
        List<Token> tokens = redisTemplate.opsForList().leftPop(KEY, 1);
        log.info(1+" " +tokens.toString());
        return tokens.stream()
                .filter(token -> token.getUser().getId().equals(userId) && (!token.isExpired() || !token.isRevoked()))
                .collect(Collectors.toList());
    }

}
