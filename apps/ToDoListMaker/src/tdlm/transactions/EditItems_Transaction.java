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
public class EditItems_Transaction implements jTPS_Transaction {
    ToDoData data;
    ToDoItemPrototype itemToEdit;
    ToDoItemPrototype originalItemsCopy;
    int index;
    
    
    public EditItems_Transaction(ToDoData initData, ToDoItemPrototype initEditmItem,ToDoItemPrototype initoriginalItemsCopy,int initIndex) 
    {
        data = initData;
        itemToEdit = initEditmItem;
        originalItemsCopy = initoriginalItemsCopy;
        index = initIndex;
    }

    @Override
    public void doTransaction() {
        data.removeItem(originalItemsCopy);
        data.addItemAt(itemToEdit, index);
        data.clearSelected();
        data.selectItem(itemToEdit);
    }

    @Override
    public void undoTransaction() {
        data.removeItem(itemToEdit);
        data.addItemAt(originalItemsCopy, index);
        data.clearSelected();
        data.selectItem(originalItemsCopy);
    }
}
