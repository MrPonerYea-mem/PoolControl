package mem.MrPonerYea.PoolControl.controller.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mem.MrPonerYea.PoolControl.model.dto.UserRequestDto;
import mem.MrPonerYea.PoolControl.model.entity.user.UserEntity;
import mem.MrPonerYea.PoolControl.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@Api(tags = "Пользователи")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ApiOperation(httpMethod = "POST", value = "Регистрация пользователя", produces = "application/json")
    public ResponseEntity<UserEntity> registration(@Valid @RequestBody UserRequestDto user) {
        UserEntity userEntity = userService.registration(user);
        return new ResponseEntity<>(userEntity, HttpStatus.OK);
    }

    @GetMapping
    @ApiOperation(httpMethod = "GET", value = "Получени списка инструкторов", produces = "application/json")
    public ResponseEntity<List<UserEntity>> getInstructors() {
        List<UserEntity> instructors = userService.getInstructors();
        return new ResponseEntity<>(instructors, HttpStatus.OK);
    }
}
