package com.td.webapp.controller.component;

import com.td.webapp.controller.AbstractController;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeTest;

import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by konstantinchipunov on 18.08.14.
 */
@WebAppConfiguration
//@ContextConfiguration(locations = {"classpath:config.webapp/test/webapp-context.xml"})
public abstract class AbstractControllerTest   {

    protected MockMvc mockMvc;

    @BeforeTest
    public void setUp() throws Exception {
        initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(getController()).build();
    }

    protected abstract AbstractController getController();
}
