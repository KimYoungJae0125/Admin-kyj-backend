package kr.kimyoungjae.admin.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.Cookie;
import kr.kimyoungjae.admin.common.enums.Roles;
import kr.kimyoungjae.admin.domain.auth.model.TokenDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret-key}")
    private String secretKey;
    private static Key key;

    private long milliSeconds = 1000 * 60 * 60 * 24;

    private static String subject = "API-KEY";

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(this.secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public TokenDto createToken(List<Roles> roles) {

        Date now = new Date();

        Claims claims = Jwts.claims().setSubject(subject);
        claims.put("roles", roles.stream().map(role -> role.getValue()).toList());


        String jws = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .signWith(key, SignatureAlgorithm.HS256)
                .setExpiration(new Date(now.getTime() + milliSeconds))
                .compact();


        return new TokenDto(jws, "Bearer");
    }

    public Jws<Claims> parse(String jws) {
        return Jwts.parserBuilder().requireSubject(subject).setSigningKey(key).build().parseClaimsJws(jws.replace("Bearer ", ""));
    }

    public boolean validate(String jws) {
        try {
            return parse(jws).getBody().getExpiration().after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public Authentication getAuthentication(String token) {
        List<SimpleGrantedAuthority> auth = ((List<String>) parse(token)
                .getBody()
                .get("roles"))
                .stream().map(SimpleGrantedAuthority::new).toList();

        return new UsernamePasswordAuthenticationToken("", "", auth);
    }

    public String getToken(Cookie[] cookies) {
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("accesstoken")) {
                    return cookie.getValue();
                }
            }
        }
            return "NotAccessToken";
    }

}
