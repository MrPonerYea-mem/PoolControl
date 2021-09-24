package mem.MrPonerYea.PoolControl.service.group;

import mem.MrPonerYea.PoolControl.exception.EntityDoesNotExistException;
import mem.MrPonerYea.PoolControl.exception.UserException;
import mem.MrPonerYea.PoolControl.model.dto.GroupRequestDto;
import mem.MrPonerYea.PoolControl.model.entity.group.GroupEntity;
import mem.MrPonerYea.PoolControl.model.entity.user.UserEntity;
import mem.MrPonerYea.PoolControl.model.entity.user.UserGroupEntity;
import mem.MrPonerYea.PoolControl.model.enumeration.RoleEnum;
import mem.MrPonerYea.PoolControl.repository.UserGroupRepository;
import mem.MrPonerYea.PoolControl.repository.group.GroupRepository;
import mem.MrPonerYea.PoolControl.service.user.UserService;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;
    private final UserService userService;
    private final UserGroupRepository userGroupRepository;

    public GroupServiceImpl(GroupRepository groupRepository,
                            UserService userService,
                            UserGroupRepository userGroupRepository) {
        this.groupRepository = groupRepository;
        this.userService = userService;
        this.userGroupRepository = userGroupRepository;
    }

    @Override
    public GroupEntity createGroup(GroupRequestDto groupRequestDto) {
        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setTimeStart(groupRequestDto.getTimeStart());
        groupEntity.setCloakroomW(groupRequestDto.getCloakroomW());
        groupEntity.setCloakroomM(groupRequestDto.getCloakroomM());

        //TODO це костыль надо будет переделать
        UserEntity userEntity = userService.findByIdOrThrow(groupRequestDto.getInstructor_id());
        checkUserRole(userEntity, RoleEnum.INSTRUCTOR);

        groupEntity.setInstructor(userEntity);
        return groupRepository.save(groupEntity);
    }

    @Override
    public GroupEntity updateInstructorInGroup(UserEntity userEntity, Long groupId) {
        checkUserRole(userEntity, RoleEnum.INSTRUCTOR);
        GroupEntity group = findByIdOrThrow(groupId);
        return new GroupEntity();
    }

    @Override
    public GroupEntity updateInstructorInGroup(Long userId, Long groupId) {
        UserEntity userEntity = userService.findByIdOrThrow(userId);
        checkUserRole(userEntity, RoleEnum.INSTRUCTOR);
        GroupEntity group = findByIdOrThrow(groupId);
        group.setInstructor(userEntity);
        groupRepository.save(group);
        return group;
    }

    @Override
    public boolean deleteGroup(Long groupId) {
        GroupEntity group = findByIdOrThrow(groupId);
        groupRepository.delete(group);
        return true;
    }

    @Override
    public GroupEntity joinToGroup(Long groupId) {
        return null;
    }

    @Override
    public UserGroupEntity joinToGroup(Long userId, Long groupId) {
        UserEntity userEntity = userService.findByIdOrThrow(userId);
        GroupEntity groupEntity = findByIdOrThrow(groupId);

        UserGroupEntity userGroupEntity = new UserGroupEntity();
        userGroupEntity.setGroup(groupEntity);
        userGroupEntity.setUser(userEntity);
        userGroupRepository.save(userGroupEntity);
        return userGroupEntity;
    }


    private void checkUserRole(UserEntity userEntity, RoleEnum role) {
        if (userEntity.getRole() != role)
            throw new UserException("Пользователь не является " + role);
    }

    @Override
    public GroupEntity findByIdOrThrow(Long id) {
        return groupRepository.findById(id).orElseThrow(
                () -> new EntityDoesNotExistException(id, GroupEntity.class));
    }
}
