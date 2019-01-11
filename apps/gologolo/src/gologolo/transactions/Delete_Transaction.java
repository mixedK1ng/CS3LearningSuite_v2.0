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
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import jtps.jTPS_Transaction;

/**
 *
 * @author yiwang
 */
public class Delete_Transaction implements jTPS_Transaction{

    GologoloApp app;
    GoLogoData data;
    GoLogoItemPrototype itemToDelete;
    int index;
    
    public Delete_Transaction(GoLogoData initData, GoLogoItemPrototype initNewItem,GologoloApp initapp,int initindex ) {
        data = initData;
        itemToDelete = initNewItem;
        app = initapp;
        index = initindex;

    }
    @Override
    public void doTransaction() {
        data.removeNodeinPane(itemToDelete);
        data.removeItem(itemToDelete);   
        data.resetOrder();
        data.clearSelected();
    }

    @Override
    public void undoTransaction() {
       
        data.addNodeinPaneatIndex(itemToDelete, index);
        data.addItemAt(itemToDelete, index);
        data.resetOrder();
        data.selectItem(itemToDelete);
        data.hightlightOneNodeInPane(itemToDelete);
    }
    
}
