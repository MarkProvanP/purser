package scot.provan.purser.core.exceptions;

import java.util.UUID;

/**
 * Created by Mark on 10/06/2015.
 */
public class PurserObjectNotFoundException extends Exception {
    private UUID uuidNotFound;
    private Class lookingFor;

    public PurserObjectNotFoundException(UUID uuidNotFound, Class lookingFor) {
        this.uuidNotFound = uuidNotFound;
        this.lookingFor = lookingFor;
    }

    @Override
    public String getMessage() {
        if (uuidNotFound == null) {
            return String.format("UUID is null");
        }
        return String.format("Could not find object of type: %s with UUID: %s", lookingFor.getSimpleName(), uuidNotFound.toString());
    }
}
