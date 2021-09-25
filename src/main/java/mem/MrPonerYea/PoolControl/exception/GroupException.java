package mem.MrPonerYea.PoolControl.exception;

import org.springframework.http.HttpStatus;

public class GroupException extends BaseException {
    public GroupException(String str) {
        super(ErrorCode.USER_ERROR, HttpStatus.BAD_REQUEST, str);
    }
}