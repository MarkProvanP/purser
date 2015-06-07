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

    public Organisation() {
        transactions = new ArrayList<Transaction>();
        users = new ArrayList<User>();
        traders = new ArrayList<Trader>();
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
        Expense expense = new Expense(amount, tradeWith, user, this);
        transactions.add(expense);
        return expense;
    }

    public Income createIncome(double amount, Trader tradeWith, User user) throws TransactionCreationException {
        Income income = new Income(amount, tradeWith, user, this);
        transactions.add(income);
        return income;
    }
}
