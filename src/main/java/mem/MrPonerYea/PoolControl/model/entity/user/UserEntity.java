package mem.MrPonerYea.PoolControl.model.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import mem.MrPonerYea.PoolControl.model.entity.BaseEntity;
import mem.MrPonerYea.PoolControl.model.enumeration.GenderEnum;
import mem.MrPonerYea.PoolControl.model.enumeration.RoleEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "users")
@ApiModel(value = "Пользователи - user")
public class UserEntity extends BaseEntity implements UserDetails {
    @Column(nullable = false)
    private String email;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @JsonIgnore
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRole();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
