package org.docksidestage.bizfw.basic.buyticket;

public class FewDaysTicket implements Ticket {

    private final int displayPrice;
    private boolean alreadyIn;
    private final int days;
    private int dayCount = 1;

    public FewDaysTicket(int displayPrice, int days) {
        this.displayPrice = displayPrice;
        this.days = days;
    }

    @Override
    public void doInPark() {

        if (alreadyIn) {
            throw new IllegalStateException("Already in park by this ticket: displayedPrice=" + displayPrice);
        }
        if (dayCount < days) {
            dayCount++;
        } else {
            alreadyIn = true;
        }

    }

    @Override
    public int getDisplayPrice() {
        return displayPrice;
    }

    public boolean isAlreadyIn() {
        return alreadyIn;
    }

}
