package mem.MrPonerYea.PoolControl.service.user;

import mem.MrPonerYea.PoolControl.model.dto.UserRequestDto;
import mem.MrPonerYea.PoolControl.model.dto.UserResponseDto;
import mem.MrPonerYea.PoolControl.model.entity.user.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Date;
import java.util.List;

public interface UserService extends UserDetailsService {
    UserEntity registration(UserRequestDto userRequest);

    List<UserResponseDto> getInstructors(Date date, Integer timeStart);

    UserEntity findByIdOrThrow(Long id);
}
