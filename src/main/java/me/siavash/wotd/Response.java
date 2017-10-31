package me.siavash.wotd;


import me.siavash.wotd.entities.Word;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public class Response extends ResponseEntity<Word> {


    public Response(HttpStatus status) {
        super(status);
    }

    public Response(Word body, HttpStatus status) {
        super(body, status);
    }

    public Response(MultiValueMap<String, String> headers, HttpStatus status) {
        super(headers, status);
    }

    public Response(Word body, MultiValueMap<String, String> headers, HttpStatus status) {
        super(body, headers, status);
    }
}
