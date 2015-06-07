package scot.provan.purser.core.objects;

import scot.provan.purser.core.exceptions.TransactionAmountException;
import scot.provan.purser.core.exceptions.TransactionCreationException;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Mark on 07/06/2015.
 */
public class Organisation {
    private HashMap<UUID, Transaction> transactions;
    private HashMap<UUID, User> users;
    private HashMap<UUID, Trader> traders;
    private HashMap<UUID, Project> projects;

    public Organisation() {
        super();

        transactions = new HashMap<UUID, Transaction>();
        users = new HashMap<UUID, User>();
        traders = new HashMap<UUID, Trader>();
        projects = new HashMap<UUID, Project>();
    }

    public User createUser(String name) {
        User user = new User(name, this);
        users.put(user.getUUID(), user);
        return user;
    }

    public Trader createTrader(String name, User user) {
        Trader trader = new Trader(name, user, this);
        traders.put(trader.getUUID(), trader);
        return trader;
    }

    public Expense createExpense(double amount, Trader tradeWith, User user) throws TransactionAmountException {
        Transaction.TransactionDataBundle bundle = new Transaction.TransactionDataBundle();
        bundle.setAmount(-100.00)
                .setShortDesc("Test expense short description")
                .setLongDesc("Test expense long description")
                .setTradeWith(tradeWith);
        Expense expense = new Expense(bundle, user, this);
        transactions.put(expense.getUUID(), expense);
        return expense;
    }

    public Income createIncome(double amount, Trader tradeWith, User user) throws TransactionCreationException {
        Transaction.TransactionDataBundle bundle = new Transaction.TransactionDataBundle();
        bundle.setAmount(150.00)
                .setShortDesc("Test income short description")
                .setLongDesc("Test income long description")
                .setTradeWith(tradeWith);
        Income income = new Income(bundle, user, this);
        transactions.put(income.getUUID(), income);
        return income;
    }

    public Project createProject(Project parent, String name, String desc, User addedBy, Organisation org) {
        Project.ProjectDataBundle bundle = new Project.ProjectDataBundle();
        bundle.setName(name)
                .setDescription(desc);
        Project project = new Project(parent, bundle, addedBy, org);
        projects.put(project.getUUID(), project);
        return project;
    }
}
