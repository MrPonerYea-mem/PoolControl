package mem.MrPonerYea.PoolControl.exception;

import org.springframework.http.HttpStatus;

public class RequestDataException extends BaseException {
    public RequestDataException(String str) {
        super(ErrorCode.USER_ERROR, HttpStatus.BAD_REQUEST, str);
    }
}