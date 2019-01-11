/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.transactions;

import gologolo.GologoloApp;
import gologolo.data.GoLogoData;
import gologolo.data.GoLogoItemPrototype;
import jtps.jTPS_Transaction;

/**
 *
 * @author yiwang
 */
public class MoveDown_Transaction implements jTPS_Transaction{
    GologoloApp app;
    GoLogoData data;
    GoLogoItemPrototype itemToMove;
    int index;
    int indexInPane;
    
    public MoveDown_Transaction(GoLogoData initData, GoLogoItemPrototype initNewItem,GologoloApp initapp,int initindex) {
        data = initData;
        itemToMove = initNewItem;
        app = initapp;
        index = initindex;
        indexInPane = data.getNodeIndexInPane(initNewItem);
    }
    
    
    @Override
    public void doTransaction() {
        data.moveItem(index, index+1);
        data.moveItemInPane(indexInPane, indexInPane+1);
        data.resetOrder();
        data.clearSelected();
        data.selectItem(itemToMove);
        
    }

    @Override
    public void undoTransaction() {
        data.moveItem(index, index-1);
        data.moveItemInPane(indexInPane, indexInPane-1);
        data.resetOrder();
        data.clearSelected();
        data.selectItem(itemToMove);  
    }
}
