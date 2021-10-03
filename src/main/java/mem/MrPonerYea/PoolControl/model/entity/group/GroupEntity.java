package mem.MrPonerYea.PoolControl.model.entity.group;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mem.MrPonerYea.PoolControl.model.entity.BaseEntity;
import mem.MrPonerYea.PoolControl.model.entity.user.UserEntity;
import mem.MrPonerYea.PoolControl.model.entity.user.UserGroupEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;
import java.util.Set;

@EqualsAndHashCode(callSuper = false)
@Data
@Table(name = "groups")
@Entity
@ApiModel(value = "Группы - groups")
public class GroupEntity extends BaseEntity {
    /*@Column(name = "time_start", nullable = false)
    private Integer timeStart;*/

    @Column(name = "time_start", nullable = false)
    private LocalTime timeStart;

    @Column(name = "cloakroom_m", nullable = false)
    private Integer cloakroomM;

    @Column(name = "cloakroom_w", nullable = false)
    private Integer cloakroomW;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity instructor;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date", nullable = false)
    private Date date;
}