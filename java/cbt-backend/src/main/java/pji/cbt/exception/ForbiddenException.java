package pji.cbt.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7359903802645850804L;
	
	
	public ForbiddenException(String string) {
		super(string);
	}


}
