package com.tp3055.GestionColis.Service;

import com.tp3055.GestionColis.Model.Entity.Token;
import com.tp3055.GestionColis.Model.Entity.User;

import com.tp3055.GestionColis.Repository.TokenRepository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;



@Service
public class TokenService {
    private final TokenRepository tokenRepository;

    public TokenService(TokenRepository tokenRepository){
    	this.tokenRepository = tokenRepository;
    }

    public Token generateToken(User user){
    	Token token = new Token();
    	token.setKey(UUID.randomUUID().toString());
    	token.setUser(user);
    	return tokenRepository.save(token);
    }

    public Optional<User> getUserByToken(String key){
    	return tokenRepository.findByKey(key).map(Token::getUser);
    }
}
