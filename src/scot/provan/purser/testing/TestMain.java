package scot.provan.purser.testing;

import scot.provan.purser.core.exceptions.*;
import scot.provan.purser.core.objects.*;

import java.util.UUID;

/**
 * Created by Mark on 07/06/2015.
 */
public class TestMain {
    public static void main(String[] args) {
        System.out.println("Testing one two three...");
        Organisation myOrg = new Organisation();

        // Create test user
        User.UserDataBundle userDataBundle = new User.UserDataBundle();
        userDataBundle.setFirstname("Test").setLastname("User");
        UUID myUserUUID = myOrg.createUser(userDataBundle);
        User myUser;
        try {
            myUser = myOrg.getUser(myUserUUID);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        // Create test trader
        Trader.TraderDataBundle traderDataBundle = new Trader.TraderDataBundle();
        traderDataBundle.setName("Test trader");
        UUID myTraderUUID = myOrg.createTrader(traderDataBundle, myUserUUID);
        try {
            Trader myTrader = myOrg.getTrader(myTraderUUID);
        } catch (TraderNotFoundException e) {
            e.printStackTrace();
        }

        // Create test project
        Project.ProjectDataBundle projectDataBundle = new Project.ProjectDataBundle();
        projectDataBundle.setName("Test project").setDescription("Test project description");
        UUID myProjectUUID = myOrg.createProject(null, projectDataBundle, myUserUUID, myOrg);
        try {
            Project myProject = myOrg.getProject(myProjectUUID);
        } catch (ProjectNotFoundException e) {
            e.printStackTrace();
        }

        // Create test fund
        Fund.FundDataBundle fundDataBundle = new Fund.FundDataBundle();
        fundDataBundle.setName("Test fund");
        UUID myFundUUID = myOrg.createFund(fundDataBundle, myUserUUID);
        try {
            Fund myFund = myOrg.getFund(myFundUUID);
        } catch (FundNotFoundException e) {
            e.printStackTrace();
        }

        // Create test expense
        UUID myExpenseUUID = null;
        Expense myExpense = null;
        Transaction.TransactionDataBundle expenseDataBundle = new Transaction.TransactionDataBundle();
        expenseDataBundle.setAmount(-100.00).setTradeWith(myTraderUUID).setOrgFund(myFundUUID);
        try {
            myExpenseUUID = myOrg.createExpense(expenseDataBundle, myUserUUID);
        } catch (TransactionAmountException e) {
            e.printStackTrace();
            System.exit(1);
        }
        try {
            myExpense = (Expense) myOrg.getTransaction(myExpenseUUID);
        } catch (TransactionNotFoundException e) {
            e.printStackTrace();
        }

        // Create test income
        UUID myIncomeUUID = null;
        Income myIncome = null;
        Transaction.TransactionDataBundle incomeDataBundle = new Transaction.TransactionDataBundle();
        incomeDataBundle.setAmount(150.00).setTradeWith(myTraderUUID).setOrgFund(myFundUUID);
        try {
            myIncomeUUID = myOrg.createIncome(incomeDataBundle, myUserUUID);
        } catch (TransactionCreationException e) {
            e.printStackTrace();
            System.exit(1);
        }
        try {
            myIncome = (Income) myOrg.getTransaction(myIncomeUUID);
        } catch (TransactionNotFoundException e) {
            e.printStackTrace();
        }


    }
}
