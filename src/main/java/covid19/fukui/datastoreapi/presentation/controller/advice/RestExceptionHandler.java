package covid19.fukui.datastoreapi.presentation.controller.advice;

import covid19.fukui.datastoreapi.presentation.dto.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.net.BindException;
import java.net.SocketTimeoutException;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

  @ExceptionHandler
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ErrorResponse handleException(HttpServletRequest request, ValidationException exception) {
    log.error(createErrorMessage("パラメータバリデーションエラーが発生しました", request), exception);
    return createErrorResponse(HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ErrorResponse handleException(
      HttpServletRequest request, ConstraintViolationException exception) {
    log.error(createErrorMessage("パラメータバリデーションエラーが発生しました", request), exception);
    return createErrorResponse(HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ErrorResponse handleException(HttpServletRequest request, BindException exception) {
    log.error(createErrorMessage("パラメータバリデーションエラーが発生しました", request), exception);
    return createErrorResponse(HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ResponseBody
  public ErrorResponse handleException(
      HttpServletRequest request, NoHandlerFoundException exception) {
    log.error(createErrorMessage("存在しないパスにアクセスされてます", request), exception);
    return createErrorResponse(HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler
  @ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
  @ResponseBody
  public ErrorResponse handleException(
      HttpServletRequest request, SocketTimeoutException exception) {
    return createErrorResponse(HttpStatus.REQUEST_TIMEOUT);
  }

  @ExceptionHandler
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ResponseBody
  public ErrorResponse handleException(HttpServletRequest request, Throwable exception) {
    log.error(createErrorMessage("API呼び出し時にタイムアウトが発生しています", request), exception);
    return createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler
  @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
  @ResponseBody
  public ErrorResponse handleException(
      HttpServletRequest request, HttpRequestMethodNotSupportedException exception) {
    log.error(createErrorMessage("許可されていないHTTPメソッドでアクセスされています", request), exception);
    return createErrorResponse(HttpStatus.METHOD_NOT_ALLOWED);
  }

  private String createErrorMessage(String message, HttpServletRequest request) {
    return message + ": リクエストURL:" + request.getRequestURI();
  }

  private ErrorResponse createErrorResponse(HttpStatus httpStatus) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setCode(httpStatus.value());
    errorResponse.setMessage(httpStatus.getReasonPhrase());
    return errorResponse;
  }
}
