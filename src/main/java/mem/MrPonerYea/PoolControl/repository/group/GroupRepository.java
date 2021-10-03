package mem.MrPonerYea.PoolControl.repository.group;

import mem.MrPonerYea.PoolControl.model.entity.group.GroupEntity;
import mem.MrPonerYea.PoolControl.model.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<GroupEntity, Long> {

    List<GroupEntity> getAllByCloakroomM(Integer cloakroomM);
    @Query("from GroupEntity g where cast(g.date as date) = :date and g.instructor = :user and :timeStart between g.timeStart and dateadd(hh, 1,g.timeStart)")
    List<GroupEntity> getUserGroupsBetweenTime(UserEntity user, LocalTime timeStart, Date date);
}
