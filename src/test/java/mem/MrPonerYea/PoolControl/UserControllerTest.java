package mem.MrPonerYea.PoolControl;

import mem.MrPonerYea.PoolControl.controller.group.GroupController;
import mem.MrPonerYea.PoolControl.model.dto.UserRequestDto;
import mem.MrPonerYea.PoolControl.model.enumeration.GenderEnum;
import mem.MrPonerYea.PoolControl.model.enumeration.RoleEnum;
import mem.MrPonerYea.PoolControl.repository.UserGroupRepository;
import mem.MrPonerYea.PoolControl.repository.group.GroupRepository;
import mem.MrPonerYea.PoolControl.service.group.GroupService;
import mem.MrPonerYea.PoolControl.service.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Для работы теста нужно удалить @EnableJpaAuditing из главного класса, костыль)
@RunWith(SpringRunner.class)
@WebMvcTest(GroupController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GroupController groupController;

    @MockBean
    private GroupService groupService;

    @MockBean
    private GroupRepository groupRepository;
    @MockBean
    private UserService userService;
    @MockBean
    private UserGroupRepository userGroupRepository;

    @Test
    public void testCreateUserGet() throws Exception {
        mockMvc.perform(get("/groups/users_groups/1")).andExpect(status().is4xxClientError());
    }

    @Test
    public void testCreateUserPost() throws Exception {
        mockMvc.perform(post("/groups/users_groups/admin/1")).andExpect(status().is4xxClientError());
    }
}
