package pji.cbt.rest.controller;

import static java.time.ZoneOffset.UTC;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import pji.cbt.entities.Roles;
import pji.cbt.entities.User;
import pji.cbt.exception.ForbiddenException;
import pji.cbt.services.UserService;

@CrossOrigin(origins = "http://localhost", maxAge = 3600)
@RestController
@RequestMapping("/authentication")
public class AuthenticationRestController {
	
	@Autowired
	private UserService usrService;
	
	Roles role;
	
	private static Logger logger = Logger.getLogger(AuthenticationRestController.class);
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	 public LoginResponse login(@RequestBody User login) throws ServletException, IOException {

	  logger.info("Trying to login by : "+login.getUsername());
		
	  String jwtToken = "";
	  Date expiration = Date.from(LocalDateTime.now(UTC).plusHours(2).toInstant(UTC));
	  
	  
	  if (login.getUsername() == null || login.getPassword() == null) {
	   throw new ForbiddenException("Please fill in username and password");
	  }

	  String username = login.getUsername();
	  String password = login.getPassword();

	  User user = usrService.findOneUser(username);
	  
	  if (user == null) {
	   throw new ForbiddenException("Username not found.");
	  }
	  
	  BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); 

	  if(!encoder.matches(password, user.getPassword())){
	   throw new ForbiddenException("Invalid login. Please check your name and password.");
	  }
	  
	  return new LoginResponse(Jwts.builder()
	    .setSubject(username)
	    .claim("roleId", user.getRole_id())
	    .claim("userId", user.getUserId())
	    .claim("orgId", user.getOrganization().getId())
	    .setExpiration(expiration)
	    .setIssuedAt(new Date())
	    .signWith(SignatureAlgorithm.HS256, "secretkey")
	    .compact());
	 }

	    @SuppressWarnings("unused")
	    private static class LoginResponse {
	        public String token;

	        public LoginResponse(final String token) {
	            this.token = token;
	        }
	    }
}