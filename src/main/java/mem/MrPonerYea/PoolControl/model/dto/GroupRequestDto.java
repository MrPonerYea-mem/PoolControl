package mem.MrPonerYea.PoolControl.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import mem.MrPonerYea.PoolControl.model.entity.user.UserEntity;

import javax.validation.constraints.NotNull;

@Data
@ApiModel("DTO - для работы с группами")
public class GroupRequestDto {
    @NotNull(message = "timeStart - не должен быть пустым")
    private Integer timeStart;
    @NotNull(message = "cloakroomM - не должен быть пустым")
    private Integer cloakroomM;
    @NotNull(message = "cloakroomW - не должен быть пустым")
    private Integer cloakroomW;
    @NotNull(message = "instructor - не должен быть пустым")
    private UserEntity instructor;
}
