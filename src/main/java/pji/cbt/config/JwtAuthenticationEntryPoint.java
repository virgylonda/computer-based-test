package pji.cbt.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

import static javax.servlet.http.HttpServletResponse.SC_FORBIDDEN;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
	 private static final long serialVersionUID = -8970718410437077606L;

	    @Override
	    public void commence(HttpServletRequest request,
	                         HttpServletResponse response,
	                         AuthenticationException authException) throws IOException {
	        // This is invoked when user tries to access a secured REST resource without supplying any credentials
	        // We should just send a 401 Unauthorized response because there is no 'login page' to redirect to
	        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
	    }
	}