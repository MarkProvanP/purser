package scot.provan.purser.core.objects;

import scot.provan.purser.core.PurserCommon;
import scot.provan.purser.core.exceptions.ProjectNotFoundException;
import scot.provan.purser.core.exceptions.PurserObjectNotFoundException;
import scot.provan.purser.core.exceptions.UserNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

/**
 * Created by Mark on 07/06/2015.
 */
public class Project extends PurserObject {
    private UUID parent;
    private Collection<UUID> children;
    private Collection<UUID> transactions;
    private UUID addedBy;
    private Organisation org;

    private String name;
    private String description;
    private Collection<UUID> users;
    private double budget;

    public Project(UUID parent, ProjectDataBundle bundle, UUID addedBy, Organisation org) throws PurserObjectNotFoundException {
        super();

        if (bundle == null) throw new NullPointerException("Bundle is null.");
        if (addedBy == null) throw new NullPointerException("User is null.");
        if (org == null) throw new NullPointerException("Organisation is null.");

        this.parent = parent;
        this.addedBy = addedBy;
        children = new ArrayList<UUID>();
        transactions = new ArrayList<UUID>();
        this.org = org;

        // Have to test that the provided addedBy User UUID does exist in the Organisation's list of users.
        try {
            org.getUser(this.addedBy);
        } catch (UserNotFoundException e) {
            PurserCommon.log(PurserCommon.LogLevel.INFO,
                    String.format("Error when creating Project: %s - Project creator User UUID not found.",
                            e.getMessage()));
            throw e;
        }

        // If the parent Project UUID is not null, it must be checked to ensure that it does exist in the Organisation's
        // list of projects. If it is null, then there is no need to check.
        if (parent != null) {
            try {
                org.getProject(this.parent);
            } catch (ProjectNotFoundException e) {
                PurserCommon.log(PurserCommon.LogLevel.INFO,
                        String.format("Error when creating Project: %s - Project parent Project UUID not found.",
                                e.getMessage()));
                throw e;
            }
        }

        this.name = bundle.getName();
        this.description = bundle.getDescription();
        this.users = bundle.getUsers() != null ? bundle.getUsers() : new ArrayList<UUID>();

        // Have to test that each of the provided users' User UUID does exist in the Organisation's list of users.
        for (UUID usersUUID : this.users) {
            try {
                org.getUser(usersUUID);
            } catch (UserNotFoundException e) {
                PurserCommon.log(PurserCommon.LogLevel.INFO,
                        String.format("Error when creating Project: %s - Project users' User UUID not found.",
                                e.getMessage()));
                throw e;
            }
        }

        this.budget = bundle.getBudget();
    }

    public static class ProjectDataBundle {
        public String getName() {
            return name;
        }

        public ProjectDataBundle setName(String name) {
            this.name = name;
            return this;
        }

        public String getDescription() {
            return description;
        }

        public ProjectDataBundle setDescription(String description) {
            this.description = description;
            return this;
        }

        public Collection<UUID> getUsers() {
            return users;
        }

        public ProjectDataBundle setUsers(Collection<UUID> users) {
            this.users = users;
            return this;
        }

        public double getBudget() {
            return budget;
        }

        public ProjectDataBundle setBudget(double budget) {
            this.budget = budget;
            return this;
        }

        private String name;
        private String description;
        private Collection<UUID> users;
        private double budget;
    }

    public String getDetails() {
        String addedByString = "UNKNOWN";
        try {
            User addedByUser = org.getUser(addedBy);
            addedByString = addedByUser.getDetails();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return String.format("START PROJECT | UUID: %s, Added by: %s | END PROJECT", super.getUUID(), addedByString);
    }
}
