package mem.MrPonerYea.PoolControl.service.user;

import mem.MrPonerYea.PoolControl.model.dto.UserRequestDto;
import mem.MrPonerYea.PoolControl.model.entity.user.UserEntity;

import java.util.List;

public interface UserService {
    UserEntity registration(UserRequestDto userRequest);

    List<UserEntity> getInstructors();

    UserEntity findByIdOrThrow(Integer id);
}
