package mem.MrPonerYea.PoolControl.repository;

import mem.MrPonerYea.PoolControl.model.entity.user.UserGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGroupRepository extends JpaRepository<UserGroupEntity, Long> {
}
