package scot.provan.purser.core.objects;

import scot.provan.purser.core.exceptions.TransactionAmountException;
import scot.provan.purser.core.exceptions.TransactionCreationException;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Mark on 07/06/2015.
 */
public class Organisation {
    public HashMap<UUID, Transaction> getTransactions() {
        return transactions;
    }

    public HashMap<UUID, User> getUsers() {
        return users;
    }

    public HashMap<UUID, Trader> getTraders() {
        return traders;
    }

    public HashMap<UUID, Project> getProjects() {
        return projects;
    }

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

    public UUID createUser(String name) {
        User user = new User(name, this);
        users.put(user.getUUID(), user);
        return user.getUUID();
    }

    public UUID createTrader(String name, User user) {
        Trader trader = new Trader(name, user, this);
        traders.put(trader.getUUID(), trader);
        return trader.getUUID();
    }

    public UUID createExpense(double amount, Trader tradeWith, User user) throws TransactionAmountException {
        Transaction.TransactionDataBundle bundle = new Transaction.TransactionDataBundle();
        bundle.setAmount(-100.00)
                .setShortDesc("Test expense short description")
                .setLongDesc("Test expense long description")
                .setTradeWith(tradeWith);
        Expense expense = new Expense(bundle, user, this);
        transactions.put(expense.getUUID(), expense);
        return expense.getUUID();
    }

    public UUID createIncome(double amount, Trader tradeWith, User user) throws TransactionCreationException {
        Transaction.TransactionDataBundle bundle = new Transaction.TransactionDataBundle();
        bundle.setAmount(150.00)
                .setShortDesc("Test income short description")
                .setLongDesc("Test income long description")
                .setTradeWith(tradeWith);
        Income income = new Income(bundle, user, this);
        transactions.put(income.getUUID(), income);
        return income.getUUID();
    }

    public UUID createProject(Project parent, String name, String desc, User addedBy, Organisation org) {
        Project.ProjectDataBundle bundle = new Project.ProjectDataBundle();
        bundle.setName(name)
                .setDescription(desc);
        Project project = new Project(parent, bundle, addedBy, org);
        projects.put(project.getUUID(), project);
        return project.getUUID();
    }
}
