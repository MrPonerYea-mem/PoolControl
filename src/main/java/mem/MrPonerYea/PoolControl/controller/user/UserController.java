package mem.MrPonerYea.PoolControl.controller.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mem.MrPonerYea.PoolControl.model.dto.UserRequestDto;
import mem.MrPonerYea.PoolControl.model.dto.UserResponseDto;
import mem.MrPonerYea.PoolControl.model.entity.user.UserEntity;
import mem.MrPonerYea.PoolControl.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/users")
@Api(tags = "Пользователи")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/reg")
    @ApiOperation(httpMethod = "POST", value = "Регистрация пользователя", produces = "application/json")
    public ResponseEntity<UserEntity> registration(@Valid @RequestBody UserRequestDto user) {
        UserEntity userEntity = userService.registration(user);
        return new ResponseEntity<>(userEntity, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    @ApiOperation(httpMethod = "GET",
            value = "Получение списка инструкторов, у которых время работы попадает " +
            "в переданное время и у которых есть свободное время в день переданный в параметрах",
            produces = "application/json")
    public ResponseEntity<List<UserResponseDto>> getInstructors(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
            @RequestParam LocalTime timeStart) {
        List<UserResponseDto> instructors = userService.getInstructors(date, timeStart);
        return new ResponseEntity<>(instructors, HttpStatus.OK);
    }
}
