package mem.MrPonerYea.PoolControl.exception;

import org.springframework.http.HttpStatus;

public class EntityDoesNotExistException extends BaseException {
    public EntityDoesNotExistException(Long id, Class<?> entityClass) {
        super(ErrorCode.ENTITY_DOES_NOT_EXIST, HttpStatus.NOT_FOUND,
                "Объект " + entityClass.getSimpleName() + " с айди " + id.toString() + " не существует.");
    }
}
