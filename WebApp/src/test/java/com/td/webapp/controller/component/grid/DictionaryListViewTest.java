package com.td.webapp.controller.component.grid;

import com.td.model.entity.dictionary.DictionaryModel;
import com.td.model.entity.dictionary.DictionaryType;
import com.td.service.crud.DictionariesService;
import com.td.webapp.TestUtil;
import com.td.webapp.controller.AbstractController;
import com.td.webapp.controller.component.AbstractControllerTest;
import com.td.webapp.controller.dictionary.DictionaryListViewController;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by konstantinchipunov on 18.08.14.
 */

public class DictionaryListViewTest extends AbstractControllerTest {


    @Mock
    private DictionariesService dictionaryService;

    @InjectMocks
    DictionaryListViewController controller;

    @BeforeTest
    public void setUp() throws Exception {
        super.setUp();
        reset(dictionaryService);
    }

    @Override
    protected AbstractController getController() {
        return controller;
    }

    @Test
    public void getContentTest() throws Exception {
      List<DictionaryModel> dictionaries = getDictionaries();
      when(dictionaryService.findAll()).thenReturn(dictionaries );
      mockMvc.perform(get("/"+ DictionaryListViewController.CONTROLLER_NAME+"/"+DictionaryListViewController.RequestName.GET_CONTENT))
      .andExpect(status().isOk())
      .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
      .andExpect(jsonPath("success", is(true)))
      .andExpect(jsonPath("results", hasSize(1)))
      .andExpect(jsonPath("results[0].description",is(dictionaries.iterator().next().getDescription())))
      .andExpect(jsonPath("results[0].dictionaryType", is(dictionaries.iterator().next().getDictionaryType().toString())))
      .andExpect(jsonPath("results[0].objectId", is(dictionaries.iterator().next().getObjectId().toString())));
    }




    protected List<DictionaryModel> getDictionaries(){
       DictionaryModel dictionaryModel = new DictionaryModel();
        dictionaryModel.init();
        dictionaryModel.setDescription("users");
        dictionaryModel.setDictionaryType(DictionaryType.USER_DICTIONARY);
        List<DictionaryModel> dictionaryModels = new ArrayList<>();
        dictionaryModels.add(dictionaryModel);
        return dictionaryModels;
    }
}
