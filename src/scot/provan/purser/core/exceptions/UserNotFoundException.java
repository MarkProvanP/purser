package scot.provan.purser.core.exceptions;

import scot.provan.purser.core.objects.User;

import java.util.UUID;

/**
 * Created by Mark on 08/06/2015.
 */
public class UserNotFoundException extends PurserObjectNotFoundException {

    public UserNotFoundException(UUID userID) {
        super(userID, User.class);
    }
}
