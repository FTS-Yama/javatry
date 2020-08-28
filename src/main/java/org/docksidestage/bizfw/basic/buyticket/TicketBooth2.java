/*
 * Copyright 2019-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.bizfw.basic.buyticket;

/**
 * @author jflute
 */
public class TicketBooth2 {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final int MAX_QUANTITY = 10;
    private static final int ONE_DAY_PRICE = 7400; // when 2019/06/15
    private static final int TWO_DAY_PRICE = 13200;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private int quantity = MAX_QUANTITY;
    private Integer salesProceeds;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TicketBooth2() {
    }

    // ===================================================================================
    //                                                                          Buy Ticket
    //                                                                          ==========
    public OneDayTicket buyOneDayPassport(int handedMoney) {
        checkQuantity();

        //受け取ったお金が足りてるか確認する
        if (handedMoney < ONE_DAY_PRICE) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }

        salesTicketMoneyManager(ONE_DAY_PRICE);

        OneDayTicket oneDayPass = new OneDayTicket(ONE_DAY_PRICE);
        return oneDayPass;
    }

    public FewDaysTicket buyTwoDayPassport(int handedMoney) {
        checkQuantity();

        if (handedMoney < TWO_DAY_PRICE) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }
        salesTicketMoneyManager(TWO_DAY_PRICE);

        FewDaysTicket twoDayPass = new FewDaysTicket(TWO_DAY_PRICE, 2);
        return twoDayPass;
    }

    //コースの値段を受け取って売れたチケットの枚数分を利益に足す
    public void salesTicketMoneyManager(int dayPrice) {
        --quantity;
        if (salesProceeds != null) {
            salesProceeds = salesProceeds + dayPrice * (MAX_QUANTITY - quantity);
        } else {
            salesProceeds = dayPrice * (MAX_QUANTITY - quantity);
        }
    }

    public void checkQuantity() {
        //チケットの数を確認する
        if (quantity <= 0) {
            throw new TicketSoldOutException("Sold out");
        }
    }

    public static class TicketSoldOutException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketSoldOutException(String msg) {
            super(msg);
        }
    }

    public static class TicketShortMoneyException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketShortMoneyException(String msg) {
            super(msg);
        }
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getQuantity() {
        return quantity;
    }

    public Integer getSalesProceeds() {
        return salesProceeds;
    }

}
