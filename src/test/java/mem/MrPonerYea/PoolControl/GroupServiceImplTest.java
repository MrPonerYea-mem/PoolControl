package mem.MrPonerYea.PoolControl;

import mem.MrPonerYea.PoolControl.exception.UserException;
import mem.MrPonerYea.PoolControl.model.dto.GroupRequestDto;
import mem.MrPonerYea.PoolControl.model.entity.group.GroupEntity;
import mem.MrPonerYea.PoolControl.model.entity.user.UserEntity;
import mem.MrPonerYea.PoolControl.model.enumeration.GenderEnum;
import mem.MrPonerYea.PoolControl.model.enumeration.RoleEnum;
import mem.MrPonerYea.PoolControl.repository.UserGroupRepository;
import mem.MrPonerYea.PoolControl.repository.group.GroupRepository;
import mem.MrPonerYea.PoolControl.service.group.GroupServiceImpl;
import mem.MrPonerYea.PoolControl.service.user.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@Import(value = GroupServiceImpl.class)
@RunWith(SpringRunner.class)
public class GroupServiceImplTest {
    @Autowired
    private GroupServiceImpl groupService;

    @MockBean
    private GroupRepository groupRepository;
    @MockBean
    private UserService userService;
    @MockBean
    private UserGroupRepository userGroupRepository;
    Long userId = 1L;

    @Test
    public void testCreateGroup() {
        GroupRequestDto groupDto = createGroupDto(userId, 5, 7, 12000);
        UserEntity user = createUser(userId, GenderEnum.FEMALE, RoleEnum.INSTRUCTOR);
        GroupEntity groupEntity = getGroupEntity(user);
        Mockito.when(userService.findByIdOrThrow(userId)).thenReturn(user);
        Mockito.when(groupRepository.save(groupEntity)).thenReturn(groupEntity);
        groupEntity = groupService.createGroup(groupDto);
        Mockito.verify(userService).findByIdOrThrow(userId);
        Mockito.verify(groupRepository).save(groupEntity);

        Assert.assertEquals(groupEntity.getInstructor().getId(), userId);
        Assert.assertEquals(groupEntity.getCloakroomM().intValue(), 5);
        Assert.assertEquals(groupEntity.getCloakroomW().intValue(), 7);
        Assert.assertEquals(groupEntity.getTimeStart().intValue(), 12000);
    }

    private GroupEntity getGroupEntity(UserEntity user) {
        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setInstructor(user);
        groupEntity.setCloakroomM(5);
        groupEntity.setCloakroomW(7);
        groupEntity.setTimeStart(12000);
        return groupEntity;
    }

    private GroupRequestDto createGroupDto(Long id, Integer cloakroomM, Integer cloakroomW, Integer timeStart) {
        GroupRequestDto groupRequestDto = new GroupRequestDto();
        groupRequestDto.setCloakroomM(cloakroomM);
        groupRequestDto.setCloakroomW(cloakroomW);
        groupRequestDto.setTimeStart(timeStart);
        groupRequestDto.setInstructorId(id);
        return groupRequestDto;
    }

    private UserEntity createUser(Long id, GenderEnum gender, RoleEnum role) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(id);
        userEntity.setUsername("User_name_first");
        userEntity.setPassword("user123");
        userEntity.setGender(gender);
        userEntity.setRole(role);
        userEntity.setPreferTimeEnd(42300);
        userEntity.setPreferTimeEnd(54000);
        userEntity.setTimeToWork(12);
        return userEntity;
    }

    // тест с провекрой, что нельзя установить в группу пользователя не с ролью инструктор
    @Test(expected = UserException.class)
    public void testCreateGroupFailed() {
        GroupRequestDto groupDto = createGroupDto(userId, 5, 7, 12000);
        UserEntity user = createUser(userId, GenderEnum.FEMALE, RoleEnum.USER);
        GroupEntity groupEntity = getGroupEntity(user);
        Mockito.when(userService.findByIdOrThrow(userId)).thenReturn(user);
        Mockito.when(groupRepository.save(groupEntity)).thenReturn(groupEntity);
        groupEntity = groupService.createGroup(groupDto);
        Mockito.verify(userService).findByIdOrThrow(userId);

        Assert.assertEquals(groupEntity.getInstructor().getId(), userId);
        Assert.assertEquals(groupEntity.getCloakroomM().intValue(), 5);
        Assert.assertEquals(groupEntity.getCloakroomW().intValue(), 7);
        Assert.assertEquals(groupEntity.getTimeStart().intValue(), 12000);
    }
}
