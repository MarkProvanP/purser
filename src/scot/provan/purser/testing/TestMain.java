package scot.provan.purser.testing;

import scot.provan.purser.core.exceptions.TransactionAmountException;
import scot.provan.purser.core.exceptions.TransactionCreationException;
import scot.provan.purser.core.objects.*;

import java.util.UUID;

/**
 * Created by Mark on 07/06/2015.
 */
public class TestMain {
    public static void main(String[] args) {
        System.out.println("Testing one two three...");
        Organisation myOrg = new Organisation();
        UUID myUserUUID = myOrg.createUser("Test User");
        User myUser = myOrg.getUsers().get(myUserUUID);
        UUID myTraderUUID = myOrg.createTrader("Test trader", myUser);
        Trader myTrader = myOrg.getTraders().get(myTraderUUID);
        UUID myExpenseUUID = null;
        Expense myExpense = null;
        try {
            myExpenseUUID = myOrg.createExpense(-100.00, myTrader, myUser);
            myExpense = (Expense) myOrg.getTransactions().get(myExpenseUUID);
        } catch (TransactionAmountException e) {
            e.printStackTrace();
            System.exit(1);
        }
        UUID myIncomeUUID = null;
        Income myIncome = null;
        try {
            myIncomeUUID = myOrg.createIncome(150.00, myTrader, myUser);
            myIncome = (Income) myOrg.getTransactions().get(myIncomeUUID);
        } catch (TransactionCreationException e) {
            e.printStackTrace();
            System.exit(1);
        }
        UUID myProjectUUID = myOrg.createProject(null, "Test project", "Test project description", myUser, myOrg);
        Project myProject = myOrg.getProjects().get(myProjectUUID);
    }
}
