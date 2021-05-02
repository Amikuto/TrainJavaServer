package uni.ami.restdb.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class FindException extends  RuntimeException {

    public FindException() {
        super("error");
    }
}
