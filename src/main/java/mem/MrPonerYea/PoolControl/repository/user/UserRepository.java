package mem.MrPonerYea.PoolControl.repository.user;

import mem.MrPonerYea.PoolControl.model.entity.group.GroupEntity;
import mem.MrPonerYea.PoolControl.model.entity.user.UserEntity;
import mem.MrPonerYea.PoolControl.model.enumeration.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    @Query("select u.username " +
            "from UserEntity u " +
            "where u.role = 'INSTRUCTOR'")
    List<UserEntity> getListInstructorByFilter();

    @Query("select u.user.username " +
            "from UserGroupEntity u " +
            "where u.user.username = 'INSTRUCTOR' and :start between u.user.preferTimeStart and u.user.preferTimeEnd and 40 < (" +
            "select count(g.id) from GroupEntity g where g.createdAt > :date and g.instructor = u.user)")
    List<UserEntity> getListInstructorByFilter1(Date date, Date start);
}
