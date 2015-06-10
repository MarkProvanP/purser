package scot.provan.purser.core.objects;

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
    private Collection<User> users;
    private double budget;

    public Project(UUID parent, ProjectDataBundle bundle, UUID addedBy, Organisation org) {
        super();

        if (bundle == null) throw new NullPointerException("Bundle is null.");
        if (addedBy == null) throw new NullPointerException("User is null.");
        if (org == null) throw new NullPointerException("Organisation is null.");

        this.parent = parent;
        children = new ArrayList<UUID>();
        transactions = new ArrayList<UUID>();
        this.org = org;

        this.name = bundle.getName();
        this.description = bundle.getDescription();
        this.users = bundle.getUsers();
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

        public Collection<User> getUsers() {
            return users;
        }

        public ProjectDataBundle setUsers(Collection<User> users) {
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
        private Collection<User> users;
        private double budget;
    }
}
