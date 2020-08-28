package org.docksidestage.bizfw.basic.buyticket;

public class OneDayTicket implements Ticket2 {
    private final int displayPrice;
    private boolean alreadyIn;

    public OneDayTicket(int displayPrice) {
        this.displayPrice = displayPrice;
    }

    @Override
    public void doInPark() {
        if (alreadyIn) {
            throw new IllegalStateException("Already in park by this ticket: displayedPrice=" + displayPrice);
        }
        alreadyIn = true;
    }

    @Override
    public int getDisplayPrice() {
        return displayPrice;
    }

    public boolean isAlreadyIn() {
        return alreadyIn;
    }

}
