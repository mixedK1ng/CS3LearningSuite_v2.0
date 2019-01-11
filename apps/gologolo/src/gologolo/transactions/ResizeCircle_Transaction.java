/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.transactions;

import gologolo.GologoloApp;
import gologolo.data.GoLogoData;
import gologolo.data.GoLogoItemPrototype;
import javafx.scene.shape.Circle;
import jtps.jTPS_Transaction;

/**
 *
 * @author yiwang
 */
public class ResizeCircle_Transaction implements jTPS_Transaction{
    GologoloApp app;
    GoLogoData data;
    GoLogoItemPrototype itemToAdd;
    double oldr,newr;
    
    public ResizeCircle_Transaction(GologoloApp initapp,GoLogoData initData,double oldR, double newR ) {
        data = initData;
        app = initapp;
        itemToAdd = data.getSelectedItem();
        oldr = oldR;
        newr = newR;

    }
    
    @Override
    public void doTransaction() {
        ((Circle)itemToAdd.getObject()).setRadius(newr);
    }

    @Override
    public void undoTransaction() {
        ((Circle)itemToAdd.getObject()).setRadius(oldr);
    }
}
