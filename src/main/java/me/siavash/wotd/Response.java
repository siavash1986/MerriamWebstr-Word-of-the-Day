package me.siavash.wotd;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public class Response<T> extends ResponseEntity<T> {
  public Response(HttpStatus status) {
    super(status);
  }

  public Response(T body, HttpStatus status) {
    super(body, status);
  }

  public Response(MultiValueMap<String, String> headers, HttpStatus status) {
    super(headers, status);
  }

  public Response(T body, MultiValueMap<String, String> headers, HttpStatus status) {
    super(body, headers, status);
  }
}
