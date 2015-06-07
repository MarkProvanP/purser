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

    public UUID createUser(User.UserDataBundle bundle) {
        User user = new User(bundle, this);
        users.put(user.getUUID(), user);
        return user.getUUID();
    }

    public UUID createTrader(Trader.TraderDataBundle bundle, UUID user) {
        Trader trader = new Trader(bundle, user, this);
        traders.put(trader.getUUID(), trader);
        return trader.getUUID();
    }

    public UUID createExpense(Transaction.TransactionDataBundle bundle, UUID user) throws TransactionAmountException {
        Expense expense = new Expense(bundle, user, this);
        transactions.put(expense.getUUID(), expense);
        return expense.getUUID();
    }

    public UUID createIncome(Transaction.TransactionDataBundle bundle, UUID user) throws TransactionCreationException {
        Income income = new Income(bundle, user, this);
        transactions.put(income.getUUID(), income);
        return income.getUUID();
    }

    public UUID createProject(UUID parent, Project.ProjectDataBundle bundle, UUID addedBy, Organisation org) {
        Project project = new Project(parent, bundle, addedBy, org);
        projects.put(project.getUUID(), project);
        return project.getUUID();
    }
}
