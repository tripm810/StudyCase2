package atmsilmulation.controller;


import atmsilmulation.model.Account;
import atmsilmulation.model.History;
import atmsilmulation.repository.HistoryRepository;
import atmsilmulation.services.FundTransferServices;
import atmsilmulation.services.TransactionHistory;
import atmsilmulation.services.UserServices;
import atmsilmulation.services.WithdrawServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class AtmController {

    @Autowired
    UserServices userServices;

    @Autowired
    TransactionHistory transactionHistory;

    @Autowired
    FundTransferServices fundTransferServices;

    @Autowired
    WithdrawServices withdrawServices;


    private Account currentAccount;
    private String amount;


    @RequestMapping("/")
    public ModelAndView showFormLogin(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView view = new ModelAndView("Login");
        Account loginBean = new Account();
        view.addObject("loginBean", loginBean);
        return view;

    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response,
                             @ModelAttribute("loginBean") Account loginBean) throws Exception {
        ModelAndView view = null;

        currentAccount = userServices.validate(loginBean.getAccountNumber(),loginBean.getPin());
        if (currentAccount == null) {
            request.setAttribute("msg", "Invalid account or PIN");
            view = new ModelAndView("Login");
        } else {
            view = new ModelAndView("redirect:/account-screen");
        }
        return view;
    }

    @RequestMapping("/account-screen")
    public String accountScreen(Model m) {
        currentAccount = userServices.validate(currentAccount.getAccountNumber(), currentAccount.getPin());
        m.addAttribute("account", currentAccount);
        return "AccountScreen";
    }

    @RequestMapping("/withdraw")
    public String withdraw() {
        return "WithdrawScreen";
    }

    @RequestMapping(value = "/withdraw", method = RequestMethod.POST)
    public ModelAndView submitWithdraw(HttpServletRequest request,
                                       @RequestParam(value = "withdraw-value", required = false) String value,
                                       @RequestParam("other-value") String otherValue) {
        ModelAndView view = null;
        if (value == null) {
            request.setAttribute("msg", "Error");
            return new ModelAndView("redirect:/withdraw");
        }
        if ("other".equals(value)) {
            amount = otherValue;
        } else {
            amount = value;
        }
        String validate = withdrawServices.validateWithdrawAmount(currentAccount.getBalance(), amount);

        if (validate == null) {
            if (withdrawServices.calculateWithdrawAmount(currentAccount, Integer.parseInt(amount))) {
                view = new ModelAndView("redirect:/summary-screen");
            } else {
                request.setAttribute("msg", "Error");
            }
        } else {
            request.setAttribute("msg", validate);
            view = new ModelAndView("WithdrawScreen");
        }
        return view;
    }


    @RequestMapping("/welcome")
    public String welcome() {
        return "WelcomeScreen";
    }

    @RequestMapping("/summary-screen")
    public String summary(Model model) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Account account = userServices.validate(currentAccount.getAccountNumber(),currentAccount.getPin());
        if (account != null){
            model.addAttribute("accountNumber", account.getAccountNumber());
            model.addAttribute("date", dateTimeFormatter.format(now));
            model.addAttribute("amount", amount );
            model.addAttribute("balance", account.getBalance());
        }
        return "SummaryScreen";
    }

    @RequestMapping("/fund-transfer")
    public String fundTransfer() {
        return "FundTransferScreen";
    }

    @RequestMapping(value = "/fund-transfer", method = RequestMethod.POST)
    public ModelAndView submitTransfer(
            HttpServletRequest request,
            @RequestParam("accountDestination") String accountDestination,
            @RequestParam("amount") String amount,
            @RequestParam("ref") String ref
    ) {
        ModelAndView view = null;
        String result =
                fundTransferServices.submitFundTransaction(
                        currentAccount.getAccountNumber(),
                        currentAccount.getPin(),
                        accountDestination,
                        Integer.parseInt(amount),
                        ref
                );
        if (result.equals("SUCCESS")) {
            view = new ModelAndView("redirect:/account-screen");
        } else {
            request.setAttribute("msg", result);
            view = new ModelAndView("FundTransferScreen");

        }
        return view;
    }

    @RequestMapping("/transaction-history")
    public String latest10Transaction(Model m) {
        List<History> transactionList = transactionHistory.findByAccNumber(currentAccount.getAccountNumber());
        m.addAttribute("transactionList", transactionList);
        return "TransactionHistoryScreen";
    }

}
