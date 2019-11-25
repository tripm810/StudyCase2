package atmsimulator;

import atmsimulation.AtmSimulation;
import atmsimulation.controller.AtmController;
import atmsimulation.dto.WithdrawDTO;
import atmsimulation.model.Account;
import atmsimulation.services.UserServices;
import atmsimulation.services.WithdrawServices;
import constant.Data;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AtmSimulation.class})

public class ATMSimulatorTests {

    private MockMvc mockMvc;

    @Mock
    private UserServices userServices;

    @Mock
    private WithdrawServices withdrawServices;


    @InjectMocks
    private AtmController atmController;

    private Account account = new Account("Hazard", "159509", "112233", "50");

    @Before
    public void setUp() {

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("../resources/templates");
        viewResolver.setSuffix(".html");

        mockMvc = MockMvcBuilders.standaloneSetup(atmController)
                .setViewResolvers(viewResolver)
                .build();
    }


    @Test
    public void validateLoginTest() {
        Account account = userServices.validate("159509", "112233");
        Assert.assertEquals(account.getAccountNumber(), "159509");
    }

    @Test
    public void calculateWithdrawAmountTest() {
        Account account = new Account("Hazard", "159509", "112233", "50");
        boolean result = withdrawServices.calculateWithdrawAmount(account, 40);
        Assert.assertTrue(result);
    }


    @Test
    @WithMockUser(username = Data.DATA_ACC_NO1)
    public void accountScreen() throws Exception {

        when(userServices.findAccountByAccountNumber(Data.DATA_ACC_NO1)).thenReturn(account);

        mockMvc.perform(get("/account-screen")
        )
                .andExpect(status().isOk())
                .andExpect(view().name("account-screen"))
                .andExpect(model().attribute("account", account));
    }

    @Test
    @WithMockUser(username = Data.DATA_ACC_NO1)
    public void submitWithdrawIfWithdrawDTOAmountIsNull() throws Exception {
        when(userServices.findAccountByAccountNumber(Data.DATA_ACC_NO1)).thenReturn(account);

        mockMvc.perform(post("/account-screen/withdraw")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .flashAttr("withdrawDTO", new WithdrawDTO("159509", "112233", null, "50", "", ""))
        )
                .andExpect(status().isOk())
                .andExpect(view().name("withdraw"))
                .andExpect(model().attribute("msg", Data.MSG_CHOOSE_WITHDRAW_AMOUNT));
    }
}