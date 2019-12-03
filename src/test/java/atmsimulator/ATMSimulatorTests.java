package atmsimulator;

import atmsimulation.AtmSimulation;
import atmsimulation.controller.AtmController;
import atmsimulation.dto.FundTransferDTO;
import atmsimulation.dto.WithdrawDTO;
import atmsimulation.exception.FundTransactionException;
import atmsimulation.exception.WithdrawException;
import atmsimulation.model.Account;
import atmsimulation.repository.AccountRepository;
import atmsimulation.services.FundTransferServices;
import atmsimulation.services.UserServices;
import atmsimulation.services.WithdrawServices;
import atmsimulation.utils.Constant;
import constant.Data;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.junit.Assert.assertEquals;
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

    @Mock
    private FundTransferServices fundTransferServices;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AtmController atmController;

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
    @WithMockUser(username = Data.DATA_ACC_NO1)
    public void accountScreen() throws Exception {

        when(userServices.findAccountByAccountNumber(Data.DATA_ACC_NO1)).thenReturn(Data.account);

        mockMvc.perform(get("/account-screen")
        )
                .andExpect(status().isOk())
                .andExpect(view().name("account-screen"))
                .andExpect(model().attribute("account", Data.account));
    }

    @Test
    @WithMockUser(username = Data.DATA_ACC_NO1)
    public void submitWithdrawIfWithdrawDTOAmountIsNull() throws Exception {
        when(userServices.findAccountByAccountNumber(Data.DATA_ACC_NO1)).thenReturn(Data.account);

        mockMvc.perform(post("/account-screen/withdraw")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .flashAttr("withdrawDTO", new WithdrawDTO("159509", "112233", null, "0", "", ""))
        )
                .andExpect(status().isOk())
                .andExpect(view().name("withdraw"))
                .andExpect(model().attribute("msg", Data.MSG_CHOOSE_WITHDRAW_AMOUNT));
    }

    @Test
    @WithMockUser(username = Data.DATA_ACC_NO1)
    public void submitWithdrawWithNormalAmount() throws Exception {
        when(userServices.findAccountByAccountNumber(Data.DATA_ACC_NO1)).thenReturn(Data.account);
        when(withdrawServices.calculateWithdrawAmount(Data.account, 50)).thenReturn(true);
        mockMvc.perform(post("/account-screen/withdraw")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .flashAttr("withdrawDTO", new WithdrawDTO("159509", "112233", "50", "0", "", ""))
        )
                .andExpect(status().isOk())
                .andExpect(view().name("summary-screen"));
    }


    @Test
    @WithMockUser(username = Data.DATA_ACC_NO1)
    public void submitWithdrawIfWithdrawDontHaveEnoughMoney() throws Exception {
        when(userServices.findAccountByAccountNumber(Data.DATA_ACC_NO1)).thenReturn(Data.accountDontHaveMoney);
        when(withdrawServices.validateWithdrawAmount(Data.accountDontHaveMoney.getBalance(), "50")).thenThrow(new WithdrawException(Constant.INSUFFICIENT_BALANCE + "50"));

        mockMvc.perform(post("/account-screen/withdraw")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .flashAttr("withdrawDTO", new WithdrawDTO("159509", "112233", "50", "0", "", ""))
        )
                .andExpect(status().isOk())
                .andExpect(view().name("withdraw"))
                .andExpect(model().attribute("msg", Data.MSG_INSUFFICIENT_BALANCE + "50"));
    }

    @Test
    @WithMockUser(username = Data.DATA_ACC_NO1)
    public void transferTransaction() throws Exception {
        when(userServices.findAccountByAccountNumber(Data.DATA_ACC_NO1)).thenReturn(Data.account);
        when(fundTransferServices.submitFundTransaction(Data.DATA_ACC_NO1, Data.DATA_PIN_NO1, Data.DATA_ACC_NO2, 50, "")).thenReturn(Data.MSG_SUCCESS);


        mockMvc.perform(post("/account-screen/fund-transfer")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .flashAttr("fundTransferDTO", new FundTransferDTO("363068", "50", ""))
        )
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/account-screen"));
    }

    @Test
    @WithMockUser(username = Data.DATA_ACC_NO1)
    public void transferTransactionNotEnoughFund() throws Exception {

        when(userServices.findAccountByAccountNumber(Data.DATA_ACC_NO1)).thenReturn(Data.account);
        when(fundTransferServices.submitFundTransaction(Data.DATA_ACC_NO1, Data.DATA_PIN_NO1, Data.DATA_ACC_NO2, 70, "")).thenThrow(new FundTransactionException(Constant.INSUFFICIENT_BALANCE + "70"));


        mockMvc.perform(post("/account-screen/fund-transfer")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .flashAttr("fundTransferDTO", new FundTransferDTO("363068", "70", ""))
        )
                .andExpect(status().isOk())
                .andExpect(view().name("fund-transfer"))
                .andExpect(model().attribute("msg", Data.MSG_INSUFFICIENT_BALANCE + "70"));;
    }


    @Test
    @WithMockUser(username = Data.DATA_ACC_NO1)
    public void validateLoginTest() {
        Account account = userServices.validate("159509", "112233");
        Assert.assertEquals(account.getAccountNumber(), "159509");
    }
    public void destinationAccountDoesNotExist() throws Exception {

        when(userServices.findAccountByAccountNumber(Data.DATA_ACC_NO1)).thenReturn(Data.account);
        when(fundTransferServices.submitFundTransaction(Data.DATA_ACC_NO1, Data.DATA_PIN_NO1, Data.DATA_ACC_NO3, 70, "")).thenThrow(new FundTransactionException(Constant.ACCOUNT_INVALID));


        mockMvc.perform(post("/account-screen/fund-transfer")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .flashAttr("fundTransferDTO", new FundTransferDTO("111111", "70", ""))
        )
                .andExpect(status().isOk())
                .andExpect(view().name("fund-transfer"))
                .andExpect(model().attribute("msg", Data.MSG_ACCOUNT_INVALID));;
    }

}