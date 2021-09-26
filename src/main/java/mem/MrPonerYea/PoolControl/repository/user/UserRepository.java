package mem.MrPonerYea.PoolControl.repository.user;

import mem.MrPonerYea.PoolControl.model.dto.UserResponseDto;
import mem.MrPonerYea.PoolControl.model.entity.group.GroupEntity;
import mem.MrPonerYea.PoolControl.model.entity.user.UserEntity;
import mem.MrPonerYea.PoolControl.model.enumeration.RoleEnum;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // Показывает свободных инструкторов, у которых еще есть свободные занятия за указанную дату | Работает
    @Query("from UserEntity u " +
            "where u.role = 'INSTRUCTOR' and :start between u.preferTimeStart and u.preferTimeEnd and u.timeToWork > (" +
            "select count(g.id) from GroupEntity g where cast(g.date as date) = :date and g.instructor = u) " +
            "order by u.id")
    List<UserEntity> getListInstructorByFilter1(@Param("date") Date date, @Param("start") Integer start);

    // Показывает общую нагруженность инструктора без дат | Работает
    @Query("select new mem.MrPonerYea.PoolControl.model.dto.UserResponseDto(ug.user.username as username, ug.user.timeToWork as timeToWork, count(ug.group.instructor.id) as countGroups) " +
            "from UserGroupEntity ug " +
            "where ug.user.role = 'INSTRUCTOR' " +
            "group by ug.user.username, ug.user.timeToWork ")
    List<UserResponseDto> getListInstructorByFilter2();

    // Показывает нагруженность инструктора в определенный день, но не показывает, если он свободен из-за даты в запросе | Работает
    @Query("select new mem.MrPonerYea.PoolControl.model.dto.UserResponseDto(g.instructor.username as username, g.instructor.timeToWork as timeToWork, count(g.instructor.id) as countGroups) " +
            "from GroupEntity g " +
            "where cast(g.date as date) = :date and :start between g.instructor.preferTimeStart and g.instructor.preferTimeEnd " +
            "group by username, timeToWork " +
            "having g.instructor.timeToWork > count(g.instructor.id) ")
    List<UserResponseDto> getListInstructorByFilter2(@Param("date") Date date, @Param("start") Integer start);

    // Показывает свободных инструкторов и их общую нагруженность | Работает
    @Query("select new mem.MrPonerYea.PoolControl.model.dto.UserResponseDto(g.instructor.username as username, g.instructor.timeToWork as timeToWork, count(g.instructor.id) as countGroups) " +
            "from GroupEntity g " +
            "where g.instructor.timeToWork > (select count(g.id) from GroupEntity g where cast(g.date as date) = :date and g.instructor = g) and :start between g.instructor.preferTimeStart and g.instructor.preferTimeEnd " +
            "group by username, timeToWork ")
    List<UserResponseDto> getListInstructorByFilter3(@Param("date") Date date, @Param("start") Integer start);

    // Показывает свободных инструкторов и их нагруженность за указанную дату, исключая перегруженный инструкторов
    // тут идет повторение кода, но через as у меня не работало, так что надо делать через процедуру или функцию | Работает
    @Query("select new mem.MrPonerYea.PoolControl.model.dto.UserResponseDto(g.instructor.username as username, " +
            "g.instructor.timeToWork as timeToWork, " +
            "(select count(ge.id) from GroupEntity ge where cast(ge.date as date) = :date and ge.instructor = g.instructor) as countGroups) " +
            "from GroupEntity g " +
            "where g.instructor.timeToWork > " +
            "(select count(ge.id) from GroupEntity ge " +
            "where cast(ge.date as date) = :date and ge.instructor = g.instructor) and " +
            ":start between g.instructor.preferTimeStart and g.instructor.preferTimeEnd " +
            "group by username, timeToWork ")
    List<UserResponseDto> getListInstructorFilter(@Param("date") Date date, @Param("start") Integer start);

    UserEntity findByUsername(String username);
}
