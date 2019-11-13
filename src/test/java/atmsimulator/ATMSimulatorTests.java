package atmsimulator;

import atmsilmulation.AtmSilmulation;
import atmsilmulation.model.Account;
import atmsilmulation.services.UserServices;
import atmsilmulation.services.WithdrawServices;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AtmSilmulation.class})
public class ATMSimulatorTests {
    @Autowired
    private UserServices userServices;

    @Autowired
    private WithdrawServices withdrawServices;


    @Test
    public void validateLoginTest() {
        Account account = userServices.validate("159509", "112233");
        Assert.assertEquals(account.getAccountNumber(), "159509");
    }

    @Test
    public void calculateWithdrawAmountTest() {
        Account account = new Account("AA","112233","50","159509");
        boolean result = withdrawServices.calculateWithdrawAmount(account, 40);
        Assert.assertTrue(result);
    }


}