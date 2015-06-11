package scot.provan.purser.core.exceptions;

import scot.provan.purser.core.objects.Trader;

import java.util.UUID;

/**
 * Created by Mark on 08/06/2015.
 */
public class TraderNotFoundException extends PurserObjectNotFoundException {

    public TraderNotFoundException(UUID traderUUID) {
        super(traderUUID, Trader.class);
    }
}
