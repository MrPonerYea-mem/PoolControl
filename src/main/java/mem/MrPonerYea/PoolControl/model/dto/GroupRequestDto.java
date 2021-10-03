package mem.MrPonerYea.PoolControl.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.Date;

@Data
@ApiModel("DTO - для работы с группами")
public class GroupRequestDto {
    @NotNull(message = "timeStart - не должен быть пустым")
    private LocalTime timeStart;
    @NotNull(message = "cloakroomM - не должен быть пустым")
    private Integer cloakroomM;
    @NotNull(message = "cloakroomW - не должен быть пустым")
    private Integer cloakroomW;
    @NotNull(message = "instructor - не должен быть пустым")
    private Long instructorId;
    @NotNull(message = "date - не должен быть пустым")
    private Date date;
}
