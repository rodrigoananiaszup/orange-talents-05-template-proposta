package br.zup.criacao.proposta.rodrigo.criacaoproposta.validation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ErrorHandlerValidation {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorHandler> handle(MethodArgumentNotValidException methodArgumentNotValidException) {
		Collection<String> mensagens = new ArrayList<>();
		BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();

		fieldErrors.forEach(fieldError -> {
			String message = String.format("Campo %s %s", fieldError.getField(), fieldError.getDefaultMessage());
			mensagens.add(message);
		});

		ErrorHandler errorHandler = new ErrorHandler(mensagens);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorHandler);
	}

	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<ErrorHandler> handleResponseStatusException(ResponseStatusException responseStatusException) {
	    Collection<String> mensagens = new ArrayList<>();
	    mensagens.add(responseStatusException.getReason());

	    ErrorHandler errorHandler = new ErrorHandler(mensagens);
	    return ResponseEntity.status(responseStatusException.getStatus()).body(errorHandler);
	}
}
