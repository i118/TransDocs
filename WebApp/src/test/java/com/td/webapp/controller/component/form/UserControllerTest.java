package com.td.webapp.controller.component.form;

import com.td.model.dto.dictionary.user.UserDTO;
import com.td.model.entity.dictionary.user.UserModel;
import com.td.service.crud.CRUDFacade;
import com.td.service.crud.dictionary.user.UserCRUDService;
import com.td.webapp.context.WebMvcContext;
import com.td.webapp.controller.AbstractController;
import com.td.webapp.controller.component.AbstractControllerTest;
import com.td.webapp.controller.dictionary.UserController;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by konstantinchipunov on 18.08.14.
 */
@WebAppConfiguration
@ContextConfiguration(classes = {WebMvcContext.class})
public class UserControllerTest extends AbstractControllerTest {

    @Mock
    private CRUDFacade<UserModel, UserDTO> facade;

    @InjectMocks
    UserController controller;

    @BeforeTest
    public void setUp() throws Exception {
        super.setUp();
        reset(facade);
    }

    @Override
    protected AbstractController getController() {
        return controller;
    }

    @Test(enabled = false)
    public void testCreateUser() throws Exception {
       mockMvc.perform(post("/"+UserController.CONTROLLER_NAME+"/create.object?password=1")
               .content(getUserJson())
               .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
       .andExpect(status().isOk())
       .andExpect(jsonPath("success", is(true)))
       .andExpect(jsonPath("results", hasSize(1)))
       .andExpect(jsonPath("results[0].objectId", notNullValue()));
        Map<String, String> args = new HashMap<>();
        args.put("password", "1");
    }

    @Test(enabled = false)
    public void testUpdateUser() throws Exception {
       mockMvc.perform(put("/"+UserController.CONTROLLER_NAME+"/update.object/{persistentId}", "20b1129a-7728-4d4e-a2d8-8ace92f98362")
               .content(getUserJson())
               .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
       .andExpect(status().isOk())
       .andExpect(jsonPath("success", is(true)))
       .andExpect(jsonPath("results", hasSize(0)))
       .andExpect(jsonPath("message", nullValue()));
    }



    private byte[] getUserJson() throws UnsupportedEncodingException {
        String userJson = "{\"objectId\":\"\",\"version\":-1,\"creationDate\":\"\"," +
                "\"modifyDate\":\"\",\"deleted\":\"\",\"mail\":\"1\",\"phone\":\"1\"," +
                "\"login\":\"sotr_5\",\"gender\":\"MAN\",\"patronymic\":\"sotr_5\"," +
                "\"lastName\":\"sotr_5\",\"firstName\":\"sotr_5\",\"fullName\":\"\"," +
                "\"objectType\":\"td_user\",\"parentId\":\"\",\"leaf\":false,\"" +
                "roleModels\":" +
                    "[{\"objectId\":\"210b1ed8-6afa-4de8-889b-e25624f89ae4\"," +
                    "\"version\":1,\"creationDate\":1408210277000," +
                    "\"modifyDate\":1408087618000,\"deleted\":false," +
                    "\"roleName\":\"ROLE_ADMIN\",\"description\":\"\u0410\u0434\u043c\u0438\u043d\u0438\u0441\u0442\u0440\u0430\u0442\u043e\u0440\"," +
                    "\"objectType\":\"td_role\"}," +
                    "{\"objectId\":\"c4df1e4f-0224-425b-bbc6-78eeeefddc43\"," +
                    "\"version\":0,\"creationDate\":1408087619000,\"modifyDate\":1408087619000," +
                    "\"deleted\":false,\"roleName\":\"ROLE_MANAGER\",\"description\":\"\u041c\u0435\u043d\u0435\u0434\u0436\u0435\u0440\"," +
                    "\"objectType\":\"td_role\"}]}";
       return userJson.getBytes("UTF-8");
    }
}
