package scot.provan.purser.core;

import scot.provan.purser.core.exceptions.TransactionAmountException;
import scot.provan.purser.core.exceptions.TransactionCreationException;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Mark on 07/06/2015.
 */
public class Organisation {
    private Collection<Transaction> transactions;
    private Collection<User> users;
    private Collection<Trader> traders;
    private Collection<Project> projects;

    public Organisation() {
        transactions = new ArrayList<Transaction>();
        users = new ArrayList<User>();
        traders = new ArrayList<Trader>();
        projects = new ArrayList<Project>();
    }

    public User createUser(String name) {
        User user = new User(name, this);
        users.add(user);
        return user;
    }

    public Trader createTrader(String name, User user) {
        Trader trader = new Trader(name, user, this);
        traders.add(trader);
        return trader;
    }

    public Expense createExpense(double amount, Trader tradeWith, User user) throws TransactionAmountException {
        Transaction.TransactionDataBundle bundle = new Transaction.TransactionDataBundle();
        bundle.setAmount(-100.00)
                .setShortDesc("Test expense short description")
                .setLongDesc("Test expense long description")
                .setTradeWith(tradeWith);
        Expense expense = new Expense(bundle, user, this);
        transactions.add(expense);
        return expense;
    }

    public Income createIncome(double amount, Trader tradeWith, User user) throws TransactionCreationException {
        Transaction.TransactionDataBundle bundle = new Transaction.TransactionDataBundle();
        bundle.setAmount(150.00)
                .setShortDesc("Test income short description")
                .setLongDesc("Test income long description")
                .setTradeWith(tradeWith);
        Income income = new Income(bundle, user, this);
        transactions.add(income);
        return income;
    }

    public Project createProject(Project parent, String name, String desc, User addedBy, Organisation org) {
        Project.ProjectDataBundle bundle = new Project.ProjectDataBundle();
        bundle.setName(name)
                .setDescription(desc);
        Project project = new Project(parent, bundle, addedBy, org);
        projects.add(project);
        return project;
    }
}
