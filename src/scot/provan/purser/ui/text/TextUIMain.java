package scot.provan.purser.ui.text;

import scot.provan.purser.core.exceptions.*;
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
        while (true) {
            clearTerminal();
            System.out.println("x - exit / a_ - add / l_ - list / d_ - delete");
            System.out.println("u - user");
            System.out.println("t - trader");
            System.out.println("p - project");
            System.out.println("f - fund");
            System.out.println("i - income");
            System.out.println("e - expense");
            String ans = EasyIn.getString();
            switch (ans) {
                case "x":
                    System.out.println("Exiting...");
                    System.exit(0);
                case "au":
                    createUser();
                    break;
                case "lu":
                    listUsers();
                    break;
                case "at":
                    createTrader();
                    break;
                case "lt":
                    listTraders();
                    break;
                case "ap":
                    createProject();
                    break;
                case "lp":
                    listProjects();
                    break;
                case "af":
                    createFund();
                    break;
                case "lf":
                    listFunds();
                    break;

            }
        }
    }

    private void printHeader() {
        System.out.println("---- Purser Text Interface ----");
    }

    private void listUsers() {
        System.out.printf("Organisation has %d user(s)\n", org.getUsers().size());
        int i = 0;
        for (UUID userUUID : org.getUsers().keySet()) {
            try {
                User user = org.getUser(userUUID);
                System.out.printf("%d -> %s\n", i, user.getDetails());
            } catch (UserNotFoundException e) {
                e.printStackTrace();
            }
            i++;
        }
    }

    private UUID selectUser() {
        System.out.println("Pick a User");
        listUsers();
        int choice = EasyIn.getInt();
        UUID userUUID = (UUID) org.getUsers().keySet().toArray()[choice];
        return userUUID;
    }

    private void listFunds() {
        System.out.printf("Organisation hsa %d fund(s)\n", org.getFunds().size());
        int i = 0;
        for (UUID fundUUID : org.getFunds().keySet()) {
            try {
                Fund fund = org.getFund(fundUUID);
                System.out.printf("%d -> %s\n", i, fund.getDetails());
            } catch (FundNotFoundException e) {
                e.printStackTrace();
            }
            i++;
        }
    }

    private UUID selectFund() {
        System.out.println("Select a Fund");
        listFunds();
        int choice = EasyIn.getInt();
        UUID fundUUID = (UUID) org.getUsers().keySet().toArray()[choice];
        return fundUUID;
    }

    private void listTraders() {
        System.out.printf("Organisation has %d trader(s)\n", org.getTraders().size());
        int i = 0;
        for (UUID traderUUID : org.getTraders().keySet()) {
            try {
                Trader trader = org.getTrader(traderUUID);
                System.out.printf("%d -> %s\n", i, trader.getDetails());
            } catch (TraderNotFoundException e) {
                e.printStackTrace();
            }
            i++;
        }
    }

    private UUID selectTrader() {
        System.out.println("Select a Trader");
        listTraders();
        int choice = EasyIn.getInt();
        UUID traderUUID = (UUID) org.getTraders().keySet().toArray()[choice];
        return traderUUID;
    }

    private void listProjects() {
        System.out.printf("Organisation has %d project(s)\n", org.getProjects().size());
        int i = 0;
        for (UUID projectUUID : org.getProjects().keySet()) {
            try {
                Project project = org.getProject(projectUUID);
                System.out.printf("%d -> %s\n", i, project.getDetails());
            } catch (ProjectNotFoundException e) {
                e.printStackTrace();
            }
            i++;
        }
    }

    private UUID selectProject() {
        System.out.println("Select a Project");
        listProjects();
        int choice = EasyIn.getInt();
        UUID projectUUID = (UUID) org.getProjects().keySet().toArray()[choice];
        return projectUUID;
    }

    private void createUser() {
        System.out.println("Creating user...");
        System.out.println("Enter first name of new user");
        String firstName = EasyIn.getString();
        System.out.println("Enter last name of new user");
        String lastName = EasyIn.getString();

        User.UserDataBundle bundle = new User.UserDataBundle();
        bundle.setFirstName(firstName).setLastName(lastName);
        org.createUser(bundle);
    }

    private void createTrader() {
        System.out.println("Creating trader...");
        UUID userUUID = selectUser();
        System.out.println("Enter name of new trader");
        String traderName = EasyIn.getString();

        Trader.TraderDataBundle bundle = new Trader.TraderDataBundle();
        bundle.setName(traderName);
        try {
            org.createTrader(bundle, userUUID);
        } catch (PurserObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void createProject() {
        System.out.println("Creating project...");
        UUID userUUID = selectUser();
        System.out.println("Enter name of new project");
        String projectName = EasyIn.getString();
        System.out.println("Enter description of new project");
        String projectDesc = EasyIn.getString();
        System.out.println("Enter budget of new project");
        double projectBudget = EasyIn.getDouble();

        Project.ProjectDataBundle bundle = new Project.ProjectDataBundle();
        bundle.setName(projectName).setDescription(projectDesc).setBudget(projectBudget);

        try {
            org.createProject(null, bundle, userUUID);
        } catch (PurserObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void createFund() {
        System.out.println("Creating fund...");
        UUID userUUID = selectUser();
        System.out.println("Enter name of new fund");
        String fundName = EasyIn.getString();

        Fund.FundDataBundle bundle = new Fund.FundDataBundle();
        bundle.setName(fundName);

        try {
            org.createFund(bundle, userUUID);
        } catch (PurserObjectNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void clearTerminal() {
        final String ANSI_CLS = "\u001b[2J";
        final String ANSI_HOME = "\u001b[H";
        System.out.print(ANSI_CLS + ANSI_HOME);
        System.out.flush();
        System.out.println();
    }
}
