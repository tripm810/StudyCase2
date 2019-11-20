package atmsilmulation.controller;


import atmsilmulation.dto.FundTransferDTO;
import atmsilmulation.dto.WithdrawDTO;
import atmsilmulation.exception.FundTransactionException;
import atmsilmulation.exception.WithdrawException;
import atmsilmulation.model.Account;
import atmsilmulation.model.History;
import atmsilmulation.services.FundTransferServices;
import atmsilmulation.services.TransactionHistory;
import atmsilmulation.services.UserServices;
import atmsilmulation.services.WithdrawServices;
import atmsilmulation.utils.Constant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class AtmController {

    private final UserServices userServices;

    private final TransactionHistory transactionHistory;

    private final FundTransferServices fundTransferServices;

    private final WithdrawServices withdrawServices;


    public AtmController(UserServices userServices, TransactionHistory transactionHistory, FundTransferServices fundTransferServices, WithdrawServices withdrawServices) {
        this.userServices = userServices;
        this.transactionHistory = transactionHistory;
        this.fundTransferServices = fundTransferServices;
        this.withdrawServices = withdrawServices;
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "WelcomeScreen";
    }

    @GetMapping(value = {"/login", "/"})
    public String getLogin() {
        return "login";
    }

    @GetMapping("/account-screen")
    public String accountScreen(HttpServletRequest request, Model model) {
        Principal principal = request.getUserPrincipal();
        Account account = userServices.findAccountByAccountNumber(principal.getName());
        model.addAttribute("account", account);
        return "account-screen";
    }

    @GetMapping("/account-screen/withdraw")
    public String withdraw(Model model) {
        WithdrawDTO withdrawDTO = new WithdrawDTO();
        model.addAttribute("withdrawDTO", withdrawDTO);
        return "withdraw";
    }

    @PostMapping("/account-screen/withdraw")
    public String submitWithdraw(Model model, HttpServletRequest request, @ModelAttribute("withdrawDTO") WithdrawDTO withdrawDTO) throws WithdrawException {
        String amount;

        Principal principal = request.getUserPrincipal();
        Account account = userServices.findAccountByAccountNumber(principal.getName());

        if (withdrawDTO.getAmount() == null) {
            model.addAttribute("msg", Constant.CHOOSE_WITHDRAW_AMOUNT);
            return "withdraw";
        }
        if ("other".equals(withdrawDTO.getAmount())) {
            if (withdrawDTO.getInputValue().isEmpty()) {
                withdrawDTO.setInputValue("0");
            }
            amount = withdrawDTO.getInputValue();
            withdrawDTO.setAmount(withdrawDTO.getInputValue());
        } else {
            amount = withdrawDTO.getAmount();
        }
        try {
            String validate = withdrawServices.validateWithdrawAmount(account.getBalance(), amount);
            if (validate == null) {
                if (withdrawServices.calculateWithdrawAmount(account, Integer.parseInt(amount))) {
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    withdrawDTO.setAccountNumber(account.getAccountNumber());
                    withdrawDTO.setDate(LocalDateTime.now().format(dateTimeFormatter));
                    withdrawDTO.setPin(account.getPin());
                    withdrawDTO.setBalance(account.getBalance());
                    model.addAttribute("withDraw", withdrawDTO);
                    return "summary-screen";
                }
            }
        } catch (WithdrawException e) {
            model.addAttribute("msg", e.getMessage());
            return "withdraw";
        }
        return "withdraw";
    }

    @GetMapping("/account-screen/summary-screen")
    public String summary() {
        return "summary-screen";
    }

    @GetMapping("/account-screen/fund-transfer")
    public String fundTransfer(Model model) {
        FundTransferDTO fundTransferDTO = new FundTransferDTO();
        model.addAttribute("fundTransferDTO", fundTransferDTO);
        return "fund-transfer";
    }

    @PostMapping("/account-screen/fund-transfer")
    public String submitTransfer(Model model, HttpServletRequest request, @ModelAttribute("fundTransferDTO") FundTransferDTO fundTransferDTO) throws Exception {
        String result = null;

        Principal principal = request.getUserPrincipal();
        Account account = userServices.findAccountByAccountNumber(principal.getName());

        try {
            result = fundTransferServices.submitFundTransaction(
                    account.getAccountNumber(),
                    account.getPin(),
                    fundTransferDTO.getAccountDestination(),
                    Integer.parseInt(fundTransferDTO.getAmount()),
                    fundTransferDTO.getRef()
            );
        } catch (FundTransactionException e) {
            model.addAttribute("msg", e.getMessage());
            return "fund-transfer";
        }
        if (result.equals(Constant.SUCCESS)) {
            return "redirect:/account-screen";
        } else {
            request.setAttribute("msg", result);
            return "fund-transfer";
        }
    }

    @GetMapping("/account-screen/transaction-history")
    public String latest10Transaction(HttpServletRequest request, Model model) {

        Principal principal = request.getUserPrincipal();
        Account account = userServices.findAccountByAccountNumber(principal.getName());

        List<History> transactionList = transactionHistory.findByAccNumber(account.getAccountNumber());
        model.addAttribute("transactionList", transactionList);
        return "transaction-history";
    }

}
