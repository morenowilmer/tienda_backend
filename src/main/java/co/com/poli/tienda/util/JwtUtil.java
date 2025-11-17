package co.com.poli.tienda.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${timepo.token.milisegundos}")
    private Long tiempoMilisegundos;

    private static final String CLAVE_SECRETA = "M1T13nd4V1LL4v3S3Cr3t4JWTP0l1J1CPr0GD1sTR18U1d4DyP4R4L3l4";

    private Key getSignKey() {
        byte[] key = Decoders.BASE64.decode(CLAVE_SECRETA);
        return Keys.hmacShaKeyFor(key);
    }

    public String generarToken(String usuario) {
        return Jwts.builder()
                .setSubject(usuario)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tiempoMilisegundos))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String validarToken(String token) {
        return Jwts.parser()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
