package mem.MrPonerYea.PoolControl.repository.group;

import mem.MrPonerYea.PoolControl.model.entity.group.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<GroupEntity, Integer> {
}
