package com.ada.ecosystem.core.v1.utility;

import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;

/**
 * The Class JwtUtil.
 */
public class JwtUtil {
	
	/** The header authorization simple. */
	public final String HEADER_AUTHORIZATION_SIMPLE = "company";
	
	/** The header authorization token. */
	public final String HEADER_AUTHORIZATION_TOKEN = "Authorization";
	
	/** The prefix. */
	public final String PREFIX = "Bearer ";
	
	/** The company. */
	public final String COMPANY = "company";	
	
	/** The codigo empresa. */
	public final String CODIGO_EMPRESA = "codigo_mempresa";
	
	/** The correo. */
	public final String CORREO = "correo";
	
	/** The nombre. */
	public final String NOMBRE = "nombre";
	
	/** The apellido. */
	public final String APELLIDO = "apellido";
	
	/** The user. */
	public final String USER = "user";
	
	/** The id user. */
	public final String ID_USER = "id_user";
	
	/** The env. */
	public final Environment env;
	
	/** The session codigo empresa. */
	private String sessionCodigoEmpresa;
	
	/** The session email. */
	private String sessionEmail;
	
	/** The session company. */
	private String sessionCompany;
	
	/** The session nombre usuario. */
	private String sessionNombreUsuario;
	
	/** The session apellido usuario. */
	private String sessionApellidoUsuario;
	
	/** The session user name. */
	private String sessionUserName;
	
	/** The session id user. */
	private Long sessionIdUser;
	
	/** The log. */
	Logger log = LoggerFactory.getLogger(JwtUtil.class);

	/**
	 * Instantiates a new jwt util.
	 *
	 * @param env the env
	 */
	public JwtUtil(Environment env) {
		this.env = env;
	}
	
	/**
	 * Existe JWT token.
	 *
	 * @param request the request
	 * @return true, if successful
	 */
	public boolean existeJWTToken(HttpServletRequest request) {
		String authenticationHeader = request.getHeader(HEADER_AUTHORIZATION_TOKEN);
		if (authenticationHeader == null) {
			authenticationHeader = request.getHeader(HEADER_AUTHORIZATION_TOKEN.toLowerCase());
		}
		if (authenticationHeader == null || !authenticationHeader.startsWith(PREFIX)) return false;
		return true;
	}
	
	/**
	 * Decode JWT.
	 *
	 * @param jwt the jwt
	 * @return the claims
	 */
	public Claims decodeJWT(String jwt) {
	    //This line will throw an exception if it is not a signed JWS (as expected)
		String ls_secret = env.getProperty("config.security.oauth.jwt.key");
	    Claims claims = Jwts
	    		.parser()
	    		.setSigningKey(ls_secret.getBytes(Charset.forName("UTF-8")))
	            .parseClaimsJws(jwt).getBody();
	    return claims;
	}
	
	/**
	 * Parses the token.
	 *
	 * @param token the token
	 * @return true, if successful
	 */
	public boolean parseToken(String token) {
		token = token.replace(PREFIX, "");
		try {
        Claims body = decodeJWT(token);
        sessionCodigoEmpresa 	= (String) body.get(CODIGO_EMPRESA);
    	sessionEmail			= (String) body.get(CORREO);
    	sessionCompany			= (String) body.get(COMPANY);
    	sessionNombreUsuario	= (String) body.get(NOMBRE);
    	sessionApellidoUsuario	= (String) body.get(APELLIDO);
    	sessionUserName			= (String) body.get(USER);
    	sessionIdUser			= Long.valueOf(String.valueOf(body.get(ID_USER)));
		} catch (JwtException | ClassCastException e) {
            return false;
        }
		return true;
	}

	/**
	 * Gets the session codigo empresa.
	 *
	 * @return the session codigo empresa
	 */
	public String getSessionCodigoEmpresa() {
		return sessionCodigoEmpresa;
	}

	/**
	 * Gets the session email.
	 *
	 * @return the session email
	 */
	public String getSessionEmail() {
		return sessionEmail;
	}

	/**
	 * Gets the session company.
	 *
	 * @return the session company
	 */
	public String getSessionCompany() {
		return sessionCompany;
	}

	/**
	 * Gets the session nombre usuario.
	 *
	 * @return the session nombre usuario
	 */
	public String getSessionNombreUsuario() {
		return sessionNombreUsuario;
	}

	/**
	 * Gets the session apellido usuario.
	 *
	 * @return the session apellido usuario
	 */
	public String getSessionApellidoUsuario() {
		return sessionApellidoUsuario;
	}

	/**
	 * Gets the session user name.
	 *
	 * @return the session user name
	 */
	public String getSessionUserName() {
		return sessionUserName;
	}

	/**
	 * Gets the session id user.
	 *
	 * @return the session id user
	 */
	public Long getSessionIdUser() {
		return sessionIdUser;
	}
}
