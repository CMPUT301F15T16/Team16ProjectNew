package com.loveboyuan.smarttrader;

import java.util.ArrayList;

/**
 * Created by boyuangu on 2015-10-16.
 */
public class Trade {

    // A Trade has a borrower(the person proposed the trade)
    private int borrowerID;
    // A Trade has an owner(the person receives a trade request)
    private int ownerID;
    // A Trade has an owner Item(what the borrower wants)
    private Item oItem;
    // A Trade has a list of borrower Items(what borrowers wish to give up for owner item)
    private ArrayList<Item> bItems;
    // A Trade has a trade result(showing if the trade is successful or not)
    private boolean tradeResult;


    // Constructor! A Trade has owner, trade for item, borrower and trade using items(what the borrower uses to trade).
    public Trade(int owner, Item oItem, int borrower, ArrayList<Item> bItems) {
        this.ownerID = owner;
        this.oItem = oItem;
        this.borrowerID = borrower;
        this.bItems = bItems;

    }

    // Trade can get the owner of the trade
    public int getOwner() {
        return ownerID;
    }

    // Trade can get the borrower of the trade
    public int getBorrower() {
        return borrowerID;
    }

    // Trade can get borrower items
    public Object getBItems() {
        return this.bItems;
    }

    // Trade can get owner item
    public Item getOItem() {
        return oItem;
    }

    // Trade can get its trade result
    public boolean getTradeResult() {
        return tradeResult;
    }

    // Trade can be accepted by the owner
    public void acceptTrade() {
        this.tradeResult = Boolean.TRUE;

        // send email to both parties
    }

    // Trade can be rejected by the owner
    public void rejectTrade() {
        this.tradeResult = Boolean.FALSE;
    }


    // Trade can be turned into a counter trade by the owner
    public Trade makeCounterTrader() {

        ArrayList<Item>items  = new ArrayList<Item>();
        items.add(oItem);
        return new Trade(borrowerID, null, ownerID, items);

    }

    // Trade can change owner item by editing
    public void editOItem(Item newOItem) {
        this.oItem = newOItem;
    }

    // Trade can change borrower items by editing
    public void editBItems(ArrayList<Item> bItems) {
        this.bItems = bItems;

    }

}
