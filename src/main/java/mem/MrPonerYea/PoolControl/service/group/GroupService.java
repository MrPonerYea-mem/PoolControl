package mem.MrPonerYea.PoolControl.service.group;

import mem.MrPonerYea.PoolControl.model.dto.GroupRequestDto;
import mem.MrPonerYea.PoolControl.model.entity.group.GroupEntity;
import mem.MrPonerYea.PoolControl.model.entity.user.UserEntity;
import mem.MrPonerYea.PoolControl.model.entity.user.UserGroupEntity;

public interface GroupService {
    GroupEntity createGroup(GroupRequestDto groupRequestDto);

    GroupEntity updateInstructorInGroup(UserEntity userEntity, Long groupId);

    GroupEntity updateInstructorInGroup(Long userId, Long groupId);

    boolean deleteGroup(Long groupId);

    GroupEntity joinToGroup(Long groupId);

    UserGroupEntity joinToGroup(Long userId, Long groupId);

    GroupEntity findByIdOrThrow(Long id);
}
