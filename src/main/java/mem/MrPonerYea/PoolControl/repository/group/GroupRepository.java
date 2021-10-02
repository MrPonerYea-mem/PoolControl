package mem.MrPonerYea.PoolControl.repository.group;

import mem.MrPonerYea.PoolControl.model.entity.group.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<GroupEntity, Long> {

    List<GroupEntity> getAllByCloakroomM(Integer cloakroomM);
}
// 0000-00-00