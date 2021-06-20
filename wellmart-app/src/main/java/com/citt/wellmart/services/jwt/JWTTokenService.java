package com.citt.wellmart.services.jwt;

import com.citt.wellmart.entities.security.User;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableMap;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.compression.GzipCompressionCodec;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.joda.time.DateTime;
import java.util.Date;
import java.util.Map;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;
import static io.jsonwebtoken.impl.TextCodec.BASE64;
import static java.util.Objects.requireNonNull;
import static lombok.AccessLevel.PRIVATE;
import static org.apache.commons.lang3.StringUtils.substringBeforeLast;

@Service
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class JWTTokenService implements Clock, TokenService {
    private static final String DOT = ".";


    String issuer;
    int expirationSec;
    int clockSkewSec;
    String secretKey;

    JWTTokenService(
            @Value("${jwt.issuer:errabi}") final String issuer,
            @Value("${jwt.expiration-sec:86400}") final int expirationSec,
            @Value("${jwt.clock-skew-sec:300}") final int clockSkewSec,
            @Value("${jwt.secret:secret}") final String secret) {
        super();
        this.issuer = requireNonNull(issuer);
        this.expirationSec = requireNonNull(expirationSec);
        this.clockSkewSec = requireNonNull(clockSkewSec);
        this.secretKey = BASE64.encode(requireNonNull(secret));
    }

    @Override
    public String permanent(User user) {
        return newToken(user, 0);
    }

    @Override
    public String expiring(User user) {
        return newToken(user, expirationSec);
    }

    private String newToken(User user, final int expiresInSec) {

        final Claims claims = Jwts
                .claims()
                .setIssuer(issuer)
                .setIssuedAt(new Date());

        if (expiresInSec > 0) {
            final DateTime expiresAt = DateTime.now().plusSeconds(expiresInSec);
            claims.setExpiration(expiresAt.toDate());
        }
        claims.put("user",user.getUsername());
        claims.put("role",user.getRoles());


        return Jwts
                .builder()
                .setClaims(claims)
                .signWith(HS256, secretKey)
                .compact();
    }

    @Override
    public Map<String, String> verify(final String token) {
        final JwtParser parser = Jwts
                .parser()
                .requireIssuer(issuer)
                .setClock(this)
                .setAllowedClockSkewSeconds(clockSkewSec)
                .setSigningKey(secretKey);
        return parseClaims(() -> parser.parseClaimsJws(token).getBody());
    }

    @Override
    public Map<String, String> untrusted(final String token) {
        final JwtParser parser = Jwts
                .parser()
                .requireIssuer(issuer)
                .setClock(this)
                .setAllowedClockSkewSeconds(clockSkewSec);

        final String withoutSignature = substringBeforeLast(token, DOT) + DOT;
        return parseClaims(() -> parser.parseClaimsJwt(withoutSignature).getBody());
    }

    private static Map<String, String> parseClaims(final Supplier<Claims> toClaims) {
        try {
            final Claims claims = toClaims.get();
            final ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();
            for (final Map.Entry<String, Object> e: claims.entrySet()) {
                builder.put(e.getKey(), String.valueOf(e.getValue()));
            }
            return builder.build();
        } catch (final IllegalArgumentException | JwtException e) {
            return ImmutableMap.of();
        }
    }

    @Override
    public Date now() {
        final DateTime now = DateTime.now();
        return now.toDate();
    }
}