package kr.co.voard.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JWTUtil {
	
	private final long TOKEN_VALIDATE_DAY = 1000 * 60 * 60 * 1; // 1시간짜리 토큰
	private SecretKey secretKey;
	
	public JWTUtil(@Value("${jwt.secret}") String secret) {
		this.secretKey = Keys.hmacShaKeyFor(secret.getBytes()); // Shakey로 변환
	}
	
	// JWT 에서 클레임값 추출
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		
		Claims claims = Jwts.parserBuilder()
							.setSigningKey(secretKey)
							.build()
							.parseClaimsJws(token)
							.getBody();
				
		return claimsResolver.apply(claims);
	}
	
	// JWT에서 사용자 아이디 추출
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}
	
	// JWT 유효성 검사
	public boolean vaildateToken(String token) {
		Date expiration = getClaimFromToken(token, Claims::getExpiration);
		return !expiration.before(new Date()); // 오늘 날짜가 아니면..
	}
	
	// HTTP 헤더에서 Token 추출
	public String resolveToken(HttpServletRequest req) {
		return req.getHeader("X-AUTH-TOKEN");
	}
	
	// JWT 생성
	public String generateToken(String username) {
		
		Map<String, Object> claims = new HashMap<>();
		
		Date createDate = new Date();
		Date expireDate = new Date(createDate.getTime() + TOKEN_VALIDATE_DAY);
		
		return Jwts.builder()
					.setClaims(claims)
					.setSubject(username)
					.setIssuedAt(createDate)
					.setExpiration(expireDate)
					.signWith(secretKey, SignatureAlgorithm.HS256)
					.compact();
	}
}
