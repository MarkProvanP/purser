package scot.provan.purser.core.objects;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Mark on 07/06/2015.
 */
public class Project extends PurserObject {
    private Project parent;
    private Collection<Project> children;
    private Collection<Transaction> transactions;
    private ProjectDataBundle bundle;
    private User addedBy;
    private Organisation org;

    public Project(Project parent, ProjectDataBundle bundle, User addedBy, Organisation org) {
        super();

        if (bundle == null) throw new NullPointerException("Bundle is null.");
        if (addedBy == null) throw new NullPointerException("User is null.");
        if (org == null) throw new NullPointerException("Organisation is null.");

        this.parent = parent;
        children = new ArrayList<Project>();
        transactions = new ArrayList<Transaction>();
        this.bundle = bundle;
        this.org = org;
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

        private String name;
        private String description;
        private Collection<User> users;
    }
}
