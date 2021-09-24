package mem.MrPonerYea.PoolControl.model.dto;

import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import mem.MrPonerYea.PoolControl.model.enumeration.GenderEnum;
import mem.MrPonerYea.PoolControl.model.enumeration.RoleEnum;

import java.util.Date;

@Data
@ApiModel("DTO - для регистрации пользователя")
public class UserRequestDto {
    @NotNull(message = "email - не должен быть пустым")
    private String email;

    @NotNull(message = "username - не должен быть пустым")
    private String username;

    @NotNull(message = "username - не должен быть пустым")
    private String password;

    private RoleEnum role;

    private GenderEnum gender;

    private Integer timeToWork;

    private Integer preferTimeEnd;

    private Integer preferTimeStart;
}
