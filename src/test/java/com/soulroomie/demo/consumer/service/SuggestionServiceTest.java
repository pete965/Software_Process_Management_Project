package com.soulroomie.demo.consumer.service;

import com.soulroomie.demo.consumer.service.model.ExpectationModel;
import com.soulroomie.demo.consumer.service.model.UserInfoModel;
import com.soulroomie.demo.tools.Result;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;



@RunWith(SpringRunner.class)
@SpringBootTest
class SuggestionServiceTest {

    @InjectMocks
    SuggestionService suggestionService;

    @Mock
    ExpectationService expectationService ;

    @Mock
    UserInfoService userInfoService ;


    private ArrayList<ExpectationModel> expectationModels;

    private ExpectationModel target;

    @BeforeEach
    public void initial() {
        expectationModels = new ArrayList<>();
        ExpectationModel expectationModel1 = new ExpectationModel(0,"a", "House", "2", "2", "Yes", "3-6 Months", "20201101", "20201130", "Female"
                , "", "Student", "Cat");
        ExpectationModel expectationModel2 = new ExpectationModel(1,"b", "House", "2", "2", "Yes", "3-6 Months", "20201101", "20201130", "Female"
                , "", "Student", "Cat");
        ExpectationModel expectationModel3 = new ExpectationModel(2,"c", "House", "2", "2", "Yes", "3-6 Months", "20201101", "20201130", "Female"
                , "", "Student", "Cat");
        ExpectationModel expectationModel4 = new ExpectationModel(3,"d", "House", "2", "2", "Yes", "3-6 Months", "20201101", "20201130", "Female"
                , "", "Student", "Cat");
        target = new ExpectationModel(4,"e", "House", "2", "2", "Yes", "3-6 Months", "20201101", "20201130", "Female"
                , "", "Student", "Cat");

        expectationModels.add(expectationModel1);
        expectationModels.add(expectationModel2);
        expectationModels.add(expectationModel3);
        expectationModels.add(expectationModel4);
    }


    @Test
    public void testEqualSuggestion() {

        Mockito.when(expectationService.listExpectation()).thenReturn(expectationModels);
        Mockito.when(expectationService.selectByUserName(Mockito.anyString())).thenReturn(target);
        List<String> userNames = suggestionService.findSuggestedUserName("e");
        assert (userNames.size() == 4);
    }

}