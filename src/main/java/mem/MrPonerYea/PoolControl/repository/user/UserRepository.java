package mem.MrPonerYea.PoolControl.repository.user;

import mem.MrPonerYea.PoolControl.model.entity.group.GroupEntity;
import mem.MrPonerYea.PoolControl.model.entity.user.UserEntity;
import mem.MrPonerYea.PoolControl.model.enumeration.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("select u.username " +
            "from UserEntity u " +
            "where u.role = 'INSTRUCTOR'")
    List<UserEntity> getListInstructorByFilter();

    /*@Query("select u.user.username " +
            "from UserGroupEntity u " +
            "where u.user.role = 'INSTRUCTOR' and :start between u.user.preferTimeStart and u.user.preferTimeEnd and u.user.timeToWork > (" +
            "select count(g.id) from GroupEntity g where cast(g.createdAt as date) = :date and g.instructor = u.user)")
    List<UserEntity> getListInstructorByFilter1(Date date, Integer start);*/

    @Query("from UserEntity u " +
            "where u.role = 'INSTRUCTOR' and :start between u.preferTimeStart and u.preferTimeEnd and u.timeToWork > (" +
            "select count(g.id) from GroupEntity g where cast(g.createdAt as date) = :date and g.instructor = u)")
    List<UserEntity> getListInstructorByFilter1(@Param("date") Date date, @Param("start") Integer start);

    UserEntity findByUsername(String username);
}
