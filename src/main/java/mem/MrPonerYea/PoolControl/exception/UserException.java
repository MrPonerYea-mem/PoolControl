package mem.MrPonerYea.PoolControl.exception;

import org.springframework.http.HttpStatus;

public class UserException extends BaseException {
    public UserException(String str) {
        super(ErrorCode.USER_ERROR, HttpStatus.BAD_REQUEST, str);
    }
}
