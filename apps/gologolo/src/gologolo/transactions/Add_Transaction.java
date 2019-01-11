/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.transactions;

import gologolo.GologoloApp;
import gologolo.data.GoLogoData;
import gologolo.data.GoLogoItemPrototype;
import javafx.scene.Node;
import jtps.jTPS_Transaction;

/**
 *
 * @author yiwang
 */
public class Add_Transaction implements jTPS_Transaction{
    GologoloApp app;
    GoLogoData data;
    GoLogoItemPrototype itemToAdd;
    int index=-1;
    
    public Add_Transaction(GoLogoData initData, GoLogoItemPrototype initNewItem,GologoloApp initapp ) {
        data = initData;
        itemToAdd = initNewItem;
        app = initapp;
        if(data.isItemSelected())
        {
            index = data.getItemIndex(data.getSelectedItem());
        }

    }
    
    @Override
    public void doTransaction() {
        data.addNodeinPane(itemToAdd);  
        data.addItem(itemToAdd);
        data.resetOrder();
        data.clearSelected();
        data.selectItem(itemToAdd);
        data.hightlightOneNodeInPane(itemToAdd);
    }

    @Override
    public void undoTransaction() {
        
        data.removeNodeinPane(itemToAdd);
        data.removeItem(itemToAdd);
        data.resetOrder();
        data.clearSelected();
        
        if(index!=-1)
        {
            data.selectItemAtIndex(index);
            data.hightlightOneNodeInPaneAtIndex(index);
        }
    }
}
