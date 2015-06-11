package scot.provan.purser.ui.text;

import scot.provan.purser.core.exceptions.FundNotFoundException;
import scot.provan.purser.core.exceptions.ProjectNotFoundException;
import scot.provan.purser.core.exceptions.TraderNotFoundException;
import scot.provan.purser.core.exceptions.UserNotFoundException;
import scot.provan.purser.core.objects.*;

import java.util.UUID;

/**
 * Created by Mark on 11/06/2015.
 */
public class TextUIMain {
    private Organisation org;

    public static void main(String[] args) {
        Organisation o = new Organisation();
        TextUIMain tui = new TextUIMain(o);
        tui.run();
    }

    public TextUIMain(Organisation org) {
        this.org = org;
    }

    private void run() {
        clearTerminal();
    }

    private void printHeader() {
        System.out.println("---- Purser Text Interface ----");
    }

    private void listUsers() {
        System.out.printf("Organisation has %d user(s)\n", org.getUsers().size());
        for (UUID userUUID : org.getUsers().keySet()) {
            try {
                User user = org.getUser(userUUID);
                System.out.println(user.getDetails());
            } catch (UserNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void listFunds() {
        System.out.printf("Organisation hsa %d fund(s)\n", org.getFunds().size());
        for (UUID fundUUID : org.getFunds().keySet()) {
            try {
                Fund fund = org.getFund(fundUUID);
                System.out.println(fund.getDetails());
            } catch (FundNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void listTraders() {
        System.out.printf("Organisation has %d trader(s)\n", org.getTraders().size());
        for (UUID traderUUID : org.getTraders().keySet()) {
            try {
                Trader trader = org.getTrader(traderUUID);
                System.out.println(trader.getDetails());
            } catch (TraderNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void listProjects() {
        System.out.printf("Organisation has %d project(s)\n", org.getProjects().size());
        for (UUID projectUUID : org.getProjects().keySet()) {
            try {
                Project project = org.getProject(projectUUID);
                System.out.println(project.getDetails());
            } catch (ProjectNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void clearTerminal() {
        final String ANSI_CLS = "\u001b[2J";
        final String ANSI_HOME = "\u001b[H";
        System.out.print(ANSI_CLS + ANSI_HOME);
        System.out.flush();
    }
}
