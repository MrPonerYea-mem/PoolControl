package mem.MrPonerYea.PoolControl.controller.group;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import mem.MrPonerYea.PoolControl.model.dto.GroupRequestDto;
import mem.MrPonerYea.PoolControl.model.entity.group.GroupEntity;
import mem.MrPonerYea.PoolControl.model.entity.user.UserGroupEntity;
import mem.MrPonerYea.PoolControl.service.group.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/groups")
@Api(tags = "Группы")
public class GroupController {
    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping
    @ApiOperation(httpMethod = "POST", value = "Создание группы", produces = "application/json")
    public ResponseEntity<GroupEntity> createGroup(@Valid @RequestBody GroupRequestDto groupRequestDto) {
        return new ResponseEntity<>(groupService.createGroup(groupRequestDto), HttpStatus.CREATED);
    }

    @PatchMapping("/{group_id}")
    @ApiOperation(httpMethod = "PATCH", value = "Присоединение в группу", produces = "application/json")
    public ResponseEntity<UserGroupEntity> joinToGroup(
            @PathVariable(value = "group_id") Long groupId,
            @RequestParam Long userId) {
        return new ResponseEntity<>(groupService.joinToGroup(userId, groupId), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{group_id}")
    @ApiOperation(httpMethod = "DELETE", value = "Удаление группы", produces = "application/json")
    public ResponseEntity<Boolean> deleteGroup(@PathVariable(value = "group_id") Long dealId) {
        return new ResponseEntity<>(groupService.deleteGroup(dealId), HttpStatus.OK);
    }

    @PatchMapping("/{group_id}/{instructor_id}")
    @ApiOperation(httpMethod = "PATCH", value = "Обновление инструктора в группе", produces = "application/json")
    public ResponseEntity<GroupEntity> updateInstructorInGroup(
            @PathVariable(value = "group_id") Long groupId,
            @PathVariable(value = "instructor_id") Long instructorId) {
        return new ResponseEntity<>(groupService.updateInstructorInGroup(instructorId, groupId), HttpStatus.OK);
    }
}
