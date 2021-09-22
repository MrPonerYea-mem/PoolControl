package mem.MrPonerYea.PoolControl.model.entity.user;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mem.MrPonerYea.PoolControl.model.entity.BaseEntity;
import mem.MrPonerYea.PoolControl.model.entity.group.GroupEntity;
import mem.MrPonerYea.PoolControl.model.entity.user.UserEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = false)
@Data
@Table(name = "users_groups")
@Entity
@ApiModel(value = "Посетители в группе - users groups")
public class UserGroupEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private GroupEntity group;
}
