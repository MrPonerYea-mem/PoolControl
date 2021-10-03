package mem.MrPonerYea.PoolControl.service.user;

import mem.MrPonerYea.PoolControl.exception.EntityDoesNotExistException;
import mem.MrPonerYea.PoolControl.model.dto.UserRequestDto;
import mem.MrPonerYea.PoolControl.model.dto.UserResponseDto;
import mem.MrPonerYea.PoolControl.model.entity.user.UserEntity;
import mem.MrPonerYea.PoolControl.repository.user.UserRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserEntity registration(UserRequestDto userRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userRequest.getUsername());
        userEntity.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        userEntity.setEmail(userRequest.getEmail());
        userEntity.setRole(userRequest.getRole());
        userEntity.setGender(userRequest.getGender());
        userEntity.setTimeStart(userRequest.getPreferTimeStart());
        userEntity.setTimeEnd(userRequest.getPreferTimeEnd());
        userEntity.setTimeToWork(userRequest.getTimeToWork());
        return userRepository.save(userEntity);
    }

    @Override
    public List<UserResponseDto> getInstructors(Date date, LocalTime timeStart) {
        return userRepository.getListInstructorFilter(date, timeStart);
    }

    @Override
    public UserEntity findByIdOrThrow(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new EntityDoesNotExistException(id, UserEntity.class));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
