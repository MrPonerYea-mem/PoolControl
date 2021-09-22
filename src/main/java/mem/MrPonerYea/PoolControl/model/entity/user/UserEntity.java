package mem.MrPonerYea.PoolControl.model.entity.user;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mem.MrPonerYea.PoolControl.model.entity.BaseEntity;
import mem.MrPonerYea.PoolControl.model.enumeration.GenderEnum;
import mem.MrPonerYea.PoolControl.model.enumeration.RoleEnum;

import javax.persistence.*;
import java.util.Date;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "users")
@ApiModel(value = "Пользователи - user")
public class UserEntity extends BaseEntity {
    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private GenderEnum gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private RoleEnum role;

    @Column(name = "time_to_work")
    private Integer timeToWork;

    @Column(name = "prefer_time_start")
    private Integer preferTimeStart;

    @Column(name = "prefer_time_end")
    private Integer preferTimeEnd;
}
