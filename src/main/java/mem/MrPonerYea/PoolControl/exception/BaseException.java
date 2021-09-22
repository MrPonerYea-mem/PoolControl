package mem.MrPonerYea.PoolControl.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Slf4j
@Data
public class BaseException extends RuntimeException {
    private ErrorCode errorCode;
    private HttpStatus httpStatus;
    private String errorMessage;

    public BaseException(ErrorCode errorCode,
                         HttpStatus httpStatus,
                         String errorMessage) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
        log.error(errorMessage);
    }
}
