package com.td.model.repository.dictionary.user;

import com.td.model.repository.context.ModelTestContext;
import com.td.model.entity.dictionary.user.IPassword;
import com.td.model.entity.dictionary.user.IUserModel;
import com.td.model.entity.dictionary.user.Password;
import com.td.model.entity.dictionary.user.UserModel;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotEquals;
import static org.testng.AssertJUnit.assertEquals;


/**
 * Created by konstantinchipunov on 28.07.14.
 */
@ContextConfiguration(classes = {ModelTestContext.class})
public class PasswordTest extends AbstractTestNGSpringContextTests {

    @Test
    public void testPassword(){
        IPassword admin = new Password("admin", null );
        IPassword pass1 = new Password("1", null);
        IPassword pass2 = new Password("2", null);
        IUserModel userModel = new UserModel();
        userModel.setLogin("login");
        IPassword adminAndSalt = new Password("admin", userModel);
        assertEquals(admin.toString(), "d033e22ae348aeb5660fc2140aec35850c4da997");
        assertEquals(pass1.toString(), "356a192b7913b04c54574d18c28d46e6395428ab");
        assertEquals(pass2.toString(), "da4b9237bacccdf19c0760cab7aec4a8359010b0");
        assertEquals(adminAndSalt.toString(), "ede2f4229bd4bc5637619a4dcf08890c2b66d5dd");
        assertNotEquals(adminAndSalt, admin);
    }
}
