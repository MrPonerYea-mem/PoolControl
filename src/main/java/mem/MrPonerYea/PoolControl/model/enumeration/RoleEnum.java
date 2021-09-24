package mem.MrPonerYea.PoolControl.model.enumeration;

import org.springframework.security.core.GrantedAuthority;

public enum RoleEnum implements GrantedAuthority {
    ADMIN,
    USER,
    INSTRUCTOR;

    @Override
    public String getAuthority() {
        return name();
    }
}
