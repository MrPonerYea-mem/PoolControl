package mem.MrPonerYea.PoolControl.model.dto;

import lombok.Data;

@Data
public class UserResponseDto {
    private String username;
    private Integer timeToWork;
    private Long countGroups;

    public UserResponseDto(String username, Integer timeToWork, Long countGroups) {
        this.username = username;
        this.timeToWork = timeToWork;
        this.countGroups = countGroups;
    }
}
