package pl.michuu93.prescriptions.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@Slf4j
@ControllerAdvice
@SuppressWarnings("unchecked")
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    protected ResponseEntity<?> handleException(WebRequest req, Exception ex) {
        logger.error(req.toString(), ex);
        var apiError = ApiError.builder().build();
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(DataAccessResourceFailureException.class)
    protected ResponseEntity<?> handleDataAccessResourceFailureException(WebRequest req, Exception ex) {
        logger.error(req.toString(), ex);
        var apiError = ApiError.builder().httpStatus(HttpStatus.SERVICE_UNAVAILABLE).errorCode(ApiErrorCode.DATABASE_UNAVAILABLE).build();
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<?> handleNoSuchElementException(WebRequest req, Exception ex) {
        logger.error(req.toString(), ex);
        var apiError = ApiError.builder().httpStatus(HttpStatus.NOT_FOUND).errorCode(ApiErrorCode.NO_SUCH_ELEMENT).build();
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(PersonalIdException.class)
    protected ResponseEntity<?> handleInvalidPeselException(WebRequest req, Exception ex) {
        logger.error(req.toString(), ex);
        var apiError = ApiError.builder().httpStatus(HttpStatus.BAD_REQUEST).errorCode(ApiErrorCode.INVALID_PESEL).build();
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(BirthdateException.class)
    protected ResponseEntity<?> handleNoBirthdateException(WebRequest req, Exception ex) {
        logger.error(req.toString(), ex);
        var apiError = ApiError.builder().httpStatus(HttpStatus.BAD_REQUEST).errorCode(ApiErrorCode.INVALID_BIRTHDATE).build();
        return buildResponseEntity(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error(request.toString(), ex);
        var apiError = ApiError.builder().httpStatus(HttpStatus.BAD_REQUEST).errorCode(ApiErrorCode.INVALID_XML_JSON).build();
        return (ResponseEntity<Object>) buildResponseEntity(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error(request.toString(), ex);
        var apiError = ApiError.builder().httpStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE).errorCode(ApiErrorCode.UNSUPPORTED_MEDIA_TYPE).build();
        return (ResponseEntity<Object>) buildResponseEntity(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error(request.toString(), ex);
        var apiError = ApiError.builder().httpStatus(HttpStatus.METHOD_NOT_ALLOWED).errorCode(ApiErrorCode.METHOD_NOT_ALLOWED).build();
        return (ResponseEntity<Object>) buildResponseEntity(apiError);
    }

    private ResponseEntity<?> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getHttpStatus());
    }
}