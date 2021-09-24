package mem.MrPonerYea.PoolControl;

import mem.MrPonerYea.PoolControl.controller.user.UserController;
import mem.MrPonerYea.PoolControl.model.dto.UserRequestDto;
import mem.MrPonerYea.PoolControl.model.enumeration.GenderEnum;
import mem.MrPonerYea.PoolControl.model.enumeration.RoleEnum;
import mem.MrPonerYea.PoolControl.repository.user.UserRepository;
import mem.MrPonerYea.PoolControl.service.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@Import(value = UserController.class)
@RunWith(SpringRunner.class)
public class UserControllerTest {
    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testCreateUser() {
        UserRequestDto user = createUser("user_test_second", "user321", GenderEnum.MALE, RoleEnum.USER,
                42300, 63200, 12);

    }

    private UserRequestDto createUser(String userName, String pass, GenderEnum gender, RoleEnum role,
                                      Integer timeStart, Integer timeEnd, Integer timeWork) {
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setUsername(userName);
        userRequestDto.setPassword(pass);
        userRequestDto.setGender(gender);
        userRequestDto.setRole(role);
        userRequestDto.setPreferTimeEnd(timeEnd);
        userRequestDto.setPreferTimeStart(timeStart);
        userRequestDto.setTimeToWork(timeWork);
        return userRequestDto;
    }
}
