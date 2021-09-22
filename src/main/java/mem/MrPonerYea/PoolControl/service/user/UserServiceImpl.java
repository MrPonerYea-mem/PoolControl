package mem.MrPonerYea.PoolControl.service.user;

import mem.MrPonerYea.PoolControl.exception.EntityDoesNotExistException;
import mem.MrPonerYea.PoolControl.model.dto.UserRequestDto;
import mem.MrPonerYea.PoolControl.model.entity.group.GroupEntity;
import mem.MrPonerYea.PoolControl.model.entity.user.UserEntity;
import mem.MrPonerYea.PoolControl.repository.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity registration(UserRequestDto userRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userRequest.getUsername());
        userEntity.setPassword(userRequest.getPassword());
        return userRepository.save(userEntity);
    }

    @Override
    public List<UserEntity> getInstructors() {
        List<UserEntity> listInstructorByFilter =
                userRepository.getListInstructorByFilter1(new Date(new Date().getTime() - 6 * 24 * 3600 * 1000), new Date());
        return listInstructorByFilter;
    }

    @Override
    public UserEntity findByIdOrThrow(Integer id) {
        return userRepository.findById(id).orElseThrow(
                () -> new EntityDoesNotExistException(id, GroupEntity.class));
    }
}
