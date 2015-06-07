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

        // Create test user
        User.UserDataBundle userDataBundle = new User.UserDataBundle();
        userDataBundle.setFirstname("Test").setLastname("User");
        UUID myUserUUID = myOrg.createUser(userDataBundle);
        User myUser = myOrg.getUsers().get(myUserUUID);

        // Create test trader
        Trader.TraderDataBundle traderDataBundle = new Trader.TraderDataBundle();
        traderDataBundle.setName("Test trader");
        UUID myTraderUUID = myOrg.createTrader(traderDataBundle, myUserUUID);
        Trader myTrader = myOrg.getTraders().get(myTraderUUID);

        // Create test project
        Project.ProjectDataBundle projectDataBundle = new Project.ProjectDataBundle();
        projectDataBundle.setName("Test project").setDescription("Test project description");
        UUID myProjectUUID = myOrg.createProject(null, projectDataBundle, myUserUUID, myOrg);
        Project myProject = myOrg.getProjects().get(myProjectUUID);

        // Create test expense
        UUID myExpenseUUID = null;
        Expense myExpense = null;
        Transaction.TransactionDataBundle expenseDataBundle = new Transaction.TransactionDataBundle();
        expenseDataBundle.setAmount(-100.00).setTradeWith(myTraderUUID);
        try {
            myExpenseUUID = myOrg.createExpense(expenseDataBundle, myUserUUID);
            myExpense = (Expense) myOrg.getTransactions().get(myExpenseUUID);
        } catch (TransactionAmountException e) {
            e.printStackTrace();
            System.exit(1);
        }

        // Create test income
        UUID myIncomeUUID = null;
        Income myIncome = null;
        Transaction.TransactionDataBundle incomeDataBundle = new Transaction.TransactionDataBundle();
        incomeDataBundle.setAmount(150.00).setTradeWith(myTraderUUID);
        try {
            myIncomeUUID = myOrg.createIncome(incomeDataBundle, myUserUUID);
            myIncome = (Income) myOrg.getTransactions().get(myIncomeUUID);
        } catch (TransactionCreationException e) {
            e.printStackTrace();
            System.exit(1);
        }


    }
}
