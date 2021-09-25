package mem.MrPonerYea.PoolControl.repository;

import mem.MrPonerYea.PoolControl.model.entity.user.UserGroupEntity;
import mem.MrPonerYea.PoolControl.model.enumeration.GenderEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGroupRepository extends JpaRepository<UserGroupEntity, Long> {

    @Query("select count(ug.user.id) from UserGroupEntity ug where ug.group.id = :groupId")
    Integer getUsersCountInGroup(@Param("groupId") Long groupId);

    @Query("select count(ug.user.id) from UserGroupEntity ug where ug.group.id = :groupId and ug.user.gender = :gender")
    Integer getUsersCountInGroupByGender(@Param("groupId") Long groupId, @Param("gender")GenderEnum gender);
}
