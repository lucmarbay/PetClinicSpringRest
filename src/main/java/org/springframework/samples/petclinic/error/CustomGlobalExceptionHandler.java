package org.springframework.samples.petclinic.error;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletResponse;
import javax.validation.UnexpectedTypeException;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	// Let Spring BasicErrorController handle the exception, we just override the
	// status code
//    @ExceptionHandler(PetNotFoundException.class)
//    public void springHandleNotFound(HttpServletResponse response) throws IOException {
//        response.sendError(HttpStatus.NOT_FOUND.value());
//    }

//	@ExceptionHandler(PetNotFoundException.class)
//	public ResponseEntity<CustomErrorResponse> customHandleNotFound(Exception ex, WebRequest request) {
//
//		CustomErrorResponse errors = new CustomErrorResponse();
//		errors.setTimestamp(LocalDateTime.now());
//		errors.setError(ex.getMessage());
//		errors.setStatus(HttpStatus.NOT_FOUND.value());
//
//		return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
//
//	}
//	@ExceptionHandler(PetConstraintViolationException.class)
//	public ResponseEntity<CustomErrorResponse> customHandleNotCreate(Exception ex, WebRequest request) {
//
//		CustomErrorResponse errors = new CustomErrorResponse();
//		errors.setTimestamp(LocalDateTime.now());
//		errors.setError(ex.getMessage());
//		errors.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
//
//		return new ResponseEntity<>(errors, HttpStatus.UNPROCESSABLE_ENTITY);
//
//	}
	
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public ResponseEntity<CustomErrorResponse> customHandleNotFound(Exception ex, WebRequest request) {
//
//		CustomErrorResponse errors = new CustomErrorResponse();
//		errors.setTimestamp(LocalDateTime.now());
//		errors.setError(ex.getMessage());
//		errors.setStatus(HttpStatus.METHOD_FAILURE.value());
//
//		return new ResponseEntity<>(errors, HttpStatus.METHOD_FAILURE);
//
//	}

	// @Validate For Validating Path Variables and Request Parameters
//	@ExceptionHandler(ConstraintViolationException.class)
//	public void constraintViolationException(HttpServletResponse response) throws IOException {
//		response.sendError(HttpStatus.BAD_REQUEST.value());
//	}
	
	@ExceptionHandler(UnexpectedTypeException.class)
	public void UnexpectedTypeException(HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.BAD_REQUEST.value());
	}

	// error handle for @Valid
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		//Metodo 1
//		Map<String, Object> body = new LinkedHashMap<>();
//		body.put("timestamp", new Date());
//		body.put("status", status.value());
//
//		// Get all fields errors
//		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
//				.collect(Collectors.toList());
//
//		body.put("errors", errors);
//
//		return new ResponseEntity<>(body, headers, status);
		
		//Metodo 2
		CustomErrorResponse errors = new CustomErrorResponse();
		errors.setTimestamp(LocalDateTime.now());
		List<String> mensajeErrors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());
		errors.setError(mensajeErrors.toString());
		errors.setStatus(HttpStatus.METHOD_FAILURE.value());

		return new ResponseEntity<>(errors, HttpStatus.METHOD_FAILURE);

	}
	@ExceptionHandler(CustomPetException.class)
	public ResponseEntity<CustomErrorResponse> controlCustomPetException(HttpServletResponse response, CustomPetException customPetException) throws IOException {
		
		CustomErrorResponse errors = new CustomErrorResponse();
		errors.setTimestamp(LocalDateTime.now());
		errors.setError(customPetException.obtenerListaMensajes().toString());
		errors.setStatus(HttpStatus.NOT_ACCEPTABLE.value());

		return new ResponseEntity<>(errors, HttpStatus.NOT_ACCEPTABLE);
	}

    // @Validate For Validating Path Variables and Request Parameters
    @ExceptionHandler(ConstraintViolationException.class)
    public void constraintViolationException(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }
    

}