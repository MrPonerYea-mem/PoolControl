package mem.MrPonerYea.PoolControl.service.group;

import mem.MrPonerYea.PoolControl.model.dto.GroupRequestDto;
import mem.MrPonerYea.PoolControl.model.entity.group.GroupEntity;
import mem.MrPonerYea.PoolControl.model.entity.user.UserEntity;

public interface GroupService {
    GroupEntity createGroup(GroupRequestDto groupRequestDto);

    GroupEntity updateInstructorInGroup(UserEntity userEntity, Integer groupId);

    GroupEntity updateInstructorInGroup(Integer userId, Integer groupId);

    boolean deleteGroup(Integer groupId);

    GroupEntity joinToGroup(Integer groupId);

    GroupEntity joinToGroup(Integer userId, Integer groupId);

    GroupEntity findByIdOrThrow(Integer id);
}
