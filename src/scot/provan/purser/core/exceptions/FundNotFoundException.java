package scot.provan.purser.core.exceptions;

import scot.provan.purser.core.objects.Fund;

import java.util.UUID;

/**
 * Created by Mark on 10/06/2015.
 */
public class FundNotFoundException extends PurserObjectNotFoundException {

    public FundNotFoundException(UUID fundUUID) {
        super(fundUUID, Fund.class);
    }
}
