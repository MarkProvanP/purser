package scot.provan.purser.core.exceptions;

import scot.provan.purser.core.objects.Project;

import java.util.UUID;

/**
 * Created by Mark on 08/06/2015.
 */
public class ProjectNotFoundException extends PurserObjectNotFoundException {

    public ProjectNotFoundException(UUID projectUUID) {
        super(projectUUID, Project.class);

    }
}
