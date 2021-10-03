package mem.MrPonerYea.PoolControl.service.group;

import mem.MrPonerYea.PoolControl.exception.EntityDoesNotExistException;
import mem.MrPonerYea.PoolControl.exception.GroupException;
import mem.MrPonerYea.PoolControl.exception.UserException;
import mem.MrPonerYea.PoolControl.model.dto.GroupRequestDto;
import mem.MrPonerYea.PoolControl.model.entity.group.GroupEntity;
import mem.MrPonerYea.PoolControl.model.entity.user.UserEntity;
import mem.MrPonerYea.PoolControl.model.entity.user.UserGroupEntity;
import mem.MrPonerYea.PoolControl.model.enumeration.GenderEnum;
import mem.MrPonerYea.PoolControl.model.enumeration.RoleEnum;
import mem.MrPonerYea.PoolControl.repository.UserGroupRepository;
import mem.MrPonerYea.PoolControl.repository.group.GroupRepository;
import mem.MrPonerYea.PoolControl.service.user.UserService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
        GroupEntity group = new GroupEntity();
        group.setTimeStart(groupRequestDto.getTimeStart());
        group.setCloakroomW(groupRequestDto.getCloakroomW());
        group.setCloakroomM(groupRequestDto.getCloakroomM());
        group.setDate(groupRequestDto.getDate());
        UserEntity user = userService.findByIdOrThrow(groupRequestDto.getInstructorId());
        checkUserRole(user, RoleEnum.INSTRUCTOR);
        checkInstructorWorkTime(group, user);
        checkInstructorFreeTime(group, user, groupRequestDto.getDate());
        return groupRepository.save(group);
    }

    private void checkInstructorFreeTime(GroupEntity group, UserEntity user, Date date) {
        // Проверка пересечения группы с другой по времени у инструктора
        List<GroupEntity> userGroupsBetweenTime =
                groupRepository.getUserGroupsBetweenTime(user, group.getTimeStart(), date);
        if (!userGroupsBetweenTime.isEmpty())
            throw new UserException("У инструктора " + user.getUsername() + " уже есть группа в данном промежутке ");
        group.setInstructor(user);
    }

    private void checkInstructorWorkTime(GroupEntity group, UserEntity user) {
        // Проверка попадает ли группа вовремя работы инструктора
        if (group.getTimeStart().isBefore(user.getTimeStart()) || group.getTimeStart().isAfter(user.getTimeEnd())) {
            throw new UserException("Время работы " + user.getTimeStart() + " | " + user.getTimeEnd()
                    + " инструктора " + user.getUsername() + " не попадает во время начала группы " + group.getTimeStart());
        }
    }

    @Override
    public GroupEntity updateInstructorInGroup(UserEntity userEntity, Long groupId) {
        checkUserRole(userEntity, RoleEnum.INSTRUCTOR);
        GroupEntity group = findByIdOrThrow(groupId);
        return new GroupEntity();
    }

    @Override
    public GroupEntity updateInstructorInGroup(Long userId, Long groupId) {
        UserEntity user = userService.findByIdOrThrow(userId);
        checkUserRole(user, RoleEnum.INSTRUCTOR);
        GroupEntity group = findByIdOrThrow(groupId);
        checkInstructorWorkTime(group, user);
        checkInstructorFreeTime(group, user, group.getDate());
        group.setInstructor(user);
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

        Integer countPlacesInGroup = groupEntity.getCloakroomM() + groupEntity.getCloakroomW();
        Integer usersInGroup = userGroupRepository.getUsersCountInGroup(groupId);
        if (usersInGroup >= countPlacesInGroup)
            throw new GroupException("В группе больше нет мест");

        switch (userEntity.getGender()) {
            case FEMALE:
                Integer WomenInGroup = userGroupRepository.getUsersCountInGroupByGender(groupId, GenderEnum.FEMALE);
                if (WomenInGroup >= groupEntity.getCloakroomW())
                    throw new GroupException("В группе больше нет мест для женщин");
                break;
            case MALE:
                Integer MenInGroup = userGroupRepository.getUsersCountInGroupByGender(groupId, GenderEnum.MALE);
                if (MenInGroup >= groupEntity.getCloakroomM())
                    throw new GroupException("В группе больше нет мест для мужчин");
                break;
        }

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

    @Override
    public List<GroupEntity> getListByCloakroomM(Integer cloakroomM) {
        return groupRepository.getAllByCloakroomM(cloakroomM);
    }
}
