package mem.MrPonerYea.PoolControl.exception;

import org.springframework.http.HttpStatus;

public class AccessDeniedException extends BaseException {

    public AccessDeniedException(Class<?> entityClass) {
        super(ErrorCode.ACCESS_DENIED, HttpStatus.FORBIDDEN,
                "Доступ запрещен");

    }
}
