package mem.MrPonerYea.PoolControl.controller.group;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mem.MrPonerYea.PoolControl.exception.RequestDataException;
import mem.MrPonerYea.PoolControl.model.dto.GroupRequestDto;
import mem.MrPonerYea.PoolControl.model.entity.group.GroupEntity;
import mem.MrPonerYea.PoolControl.model.entity.user.UserEntity;
import mem.MrPonerYea.PoolControl.model.entity.user.UserGroupEntity;
import mem.MrPonerYea.PoolControl.service.group.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("/groups")
@Api(tags = "Группы")
public class GroupController {
    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    @ApiOperation(httpMethod = "POST", value = "Создание группы", produces = "application/json")
    public ResponseEntity<GroupEntity> createGroup(
            @AuthenticationPrincipal UserEntity user,
            @Valid @RequestBody GroupRequestDto groupRequestDto) {
        return new ResponseEntity<>(groupService.createGroup(groupRequestDto), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(path = "/users_groups/admin/{group_id}", produces = {"application/json"}, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    @ApiOperation(httpMethod = "POST", value = "Присоединение в группу")
    public ResponseEntity<UserGroupEntity> addToGroup(
            @PathVariable(value = "group_id") Long groupId,
            @RequestBody MultiValueMap<String, String> formData) {
        if (formData.getFirst("userId") == null || !Objects.requireNonNull(formData.getFirst("userId")).matches("[0-9]+"))
            throw new RequestDataException("Поле userId не должно быть пустым или неверный формат");
        return new ResponseEntity<>(groupService.joinToGroup(Long.valueOf(formData.getFirst("userId")), groupId), HttpStatus.ACCEPTED);
    }

    @PostMapping(path = "/users_groups/{group_id}", produces = {"application/json"})
    @ApiOperation(httpMethod = "POST", value = "Присоединение в группу")
    public ResponseEntity<UserGroupEntity> joinToGroup(
            @PathVariable(value = "group_id") Long groupId,
            @AuthenticationPrincipal UserEntity user) {
        return new ResponseEntity<>(groupService.joinToGroup(user.getId(), groupId), HttpStatus.ACCEPTED);
    }

    // Пока не работает так как есть связи с таблице users_groups и нужно делать каскадное удаление
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{group_id}")
    @ApiOperation(httpMethod = "DELETE", value = "Удаление группы", produces = "application/json")
    public ResponseEntity<Boolean> deleteGroup(@PathVariable(value = "group_id") Long dealId) {
        return new ResponseEntity<>(groupService.deleteGroup(dealId), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/{group_id}/{instructor_id}")
    @ApiOperation(httpMethod = "PATCH", value = "Обновление инструктора в группе", produces = "application/json")
    public ResponseEntity<GroupEntity> updateInstructorInGroup(
            @PathVariable(value = "group_id") Long groupId,
            @PathVariable(value = "instructor_id") Long instructorId) {
        return new ResponseEntity<>(groupService.updateInstructorInGroup(instructorId, groupId), HttpStatus.OK);
    }
}
