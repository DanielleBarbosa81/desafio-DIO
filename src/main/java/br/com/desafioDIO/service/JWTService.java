package br.com.desafioDIO.service;

import br.com.desafioDIO.models.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class JWTService {
    private final String chaveSecreta = "minhaChaveSecretaSuperSegura";

    public String gerarToken(Usuario usuario) {
        return Jwts.builder()
                .setSubject(usuario.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SignatureAlgorithm.HS256, chaveSecreta.getBytes())
                .compact();
    }
}
