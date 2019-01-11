/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdlm.transactions;

import jtps.jTPS_Transaction;
import tdlm.data.ToDoData;
import tdlm.data.ToDoItemPrototype;

/**
 *
 * @author yiwang
 */

public class MoveUp_Transaction implements jTPS_Transaction {
    ToDoData data;
    ToDoItemPrototype itemsToMoveUp;
    
    public MoveUp_Transaction(ToDoData initData, ToDoItemPrototype initNewItem) {
        data = initData;
        itemsToMoveUp = initNewItem;
        //System.out.println(initNewItem.getAssign()+initNewItem.getCategory());
    }

    @Override
    public void doTransaction() {
        data.moveItem(data.getItemIndex(itemsToMoveUp), data.getItemIndex(itemsToMoveUp)-1);
        data.clearSelected();
        data.selectItem(itemsToMoveUp);        
    }

    @Override
    public void undoTransaction() {
        data.moveItem(data.getItemIndex(itemsToMoveUp), data.getItemIndex(itemsToMoveUp)+1);
        data.clearSelected();
        data.selectItem(itemsToMoveUp);  
    }
}
