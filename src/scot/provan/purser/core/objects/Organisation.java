package scot.provan.purser.core.objects;

import scot.provan.purser.core.exceptions.*;

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

    public HashMap<UUID, Fund> getFunds() {
        return funds;
    }

    public HashMap<UUID, Refund> getRefunds() {
        return refunds;
    }

    private HashMap<UUID, Transaction> transactions;
    private HashMap<UUID, User> users;
    private HashMap<UUID, Trader> traders;
    private HashMap<UUID, Project> projects;
    private HashMap<UUID, Fund> funds;
    private HashMap<UUID, Refund> refunds;

    public Organisation() {
        super();

        transactions = new HashMap<UUID, Transaction>();
        users = new HashMap<UUID, User>();
        traders = new HashMap<UUID, Trader>();
        projects = new HashMap<UUID, Project>();
        funds = new HashMap<UUID, Fund>();
        refunds = new HashMap<UUID, Refund>();
    }

    public UUID createUser(User.UserDataBundle bundle) {
        User user = new User(bundle, this);
        users.put(user.getUUID(), user);
        return user.getUUID();
    }

    public UUID createTrader(Trader.TraderDataBundle bundle, UUID user) throws PurserObjectNotFoundException {
        Trader trader = new Trader(bundle, user, this);
        traders.put(trader.getUUID(), trader);
        return trader.getUUID();
    }

    public UUID createExpense(Transaction.TransactionDataBundle bundle, UUID user) throws TransactionAmountException, PurserObjectNotFoundException {
        Expense expense = new Expense(bundle, user, this);
        transactions.put(expense.getUUID(), expense);
        return expense.getUUID();
    }

    public UUID createIncome(Transaction.TransactionDataBundle bundle, UUID user) throws TransactionCreationException, PurserObjectNotFoundException {
        Income income = new Income(bundle, user, this);
        transactions.put(income.getUUID(), income);
        return income.getUUID();
    }

    public UUID createProject(UUID parent, Project.ProjectDataBundle bundle, UUID addedBy) throws PurserObjectNotFoundException {
        Project project = new Project(parent, bundle, addedBy, this);
        projects.put(project.getUUID(), project);
        return project.getUUID();
    }

    public UUID createFund(Fund.FundDataBundle bundle, UUID user) throws PurserObjectNotFoundException {
        Fund fund = new Fund(bundle, user, this);
        funds.put(fund.getUUID(), fund);
        return fund.getUUID();
    }

    public UUID createRefund(Refund.RefundDataBundle bundle, UUID user) throws PurserObjectNotFoundException {
        Refund refund = new Refund(bundle, user, this);
        refunds.put(refund.getUUID(), refund);
        return refund.getUUID();
    }

    public User getUser(UUID userUUID) throws UserNotFoundException {
        User user = users.get(userUUID);
        if (user == null) {
            throw new UserNotFoundException(userUUID);
        }
        return user;
    }

    public Trader getTrader(UUID traderUUID) throws TraderNotFoundException {
        Trader trader = traders.get(traderUUID);
        if (trader == null) {
            throw new TraderNotFoundException(traderUUID);
        }
        return trader;
    }

    public Project getProject(UUID projectUUID) throws ProjectNotFoundException {
        Project project = projects.get(projectUUID);
        if (project == null) {
            throw new ProjectNotFoundException(projectUUID);
        }
        return project;
    }

    public Transaction getTransaction(UUID transactionUUID) throws TransactionNotFoundException {
        Transaction transaction = transactions.get(transactionUUID);
        if (transaction == null) {
            throw new TransactionNotFoundException(transactionUUID);
        }
        return transaction;
    }

    public Fund getFund(UUID fundUUID) throws FundNotFoundException {
        Fund fund = funds.get(fundUUID);
        if (fund == null) {
            throw new FundNotFoundException(fundUUID);
        }
        return fund;
    }

    public Refund getRefund(UUID refundUUID) throws RefundNotFoundException {
        Refund refund = refunds.get(refundUUID);
        if (refund == null) {
            throw new RefundNotFoundException(refundUUID);
        }
        return refund;
    }
}
