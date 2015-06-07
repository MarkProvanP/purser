package scot.provan.purser.testing;

import scot.provan.purser.core.exceptions.TransactionAmountException;
import scot.provan.purser.core.exceptions.TransactionCreationException;
import scot.provan.purser.core.objects.*;

/**
 * Created by Mark on 07/06/2015.
 */
public class TestMain {
    public static void main(String[] args) {
        System.out.println("Testing one two three...");
        Organisation myOrg = new Organisation();
        User myUser = myOrg.createUser("Test User");
        Trader myTrader = myOrg.createTrader("Test trader", myUser);
        Expense myExpense = null;
        try {
            myExpense = myOrg.createExpense(-100.00, myTrader, myUser);
        } catch (TransactionAmountException e) {
            e.printStackTrace();
            System.exit(1);
        }
        Income myIncome = null;
        try {
            myIncome = myOrg.createIncome(150.00, myTrader, myUser);
        } catch (TransactionCreationException e) {
            e.printStackTrace();
            System.exit(1);
        }
        Project project = myOrg.createProject(null, "Test project", "Test project description", myUser, myOrg);

    }
}
