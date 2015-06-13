package scot.provan.purser.core.exceptions;

import scot.provan.purser.core.objects.Refund;

import java.util.UUID;

/**
 * Created by Mark on 13/06/2015.
 */
public class RefundNotFoundException extends PurserObjectNotFoundException {
    public RefundNotFoundException(UUID refundUUID) {
        super(refundUUID, Refund.class);
    }
}
