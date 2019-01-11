/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.transactions;

import gologolo.GologoloApp;
import gologolo.data.GoLogoData;
import gologolo.data.GoLogoItemPrototype;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import jtps.jTPS_Transaction;

/**
 *
 * @author yiwang
 */
public class ChangeDimension_Transaction implements jTPS_Transaction{
    GologoloApp app;
    GoLogoData data;
    StackPane pane;
    double oH,oW,nH,nW;
    public ChangeDimension_Transaction(GologoloApp initapp,GoLogoData initData,double oldH, double oldW,double newH,double newW) {
        data = initData;
        app = initapp;
        oH = oldH;
        oW = oldW;
        nH = newH;
        nW = newW;

    }
    @Override
    public void doTransaction() {

        data.getWorkPane().setMaxSize(nW, nH);
        data.getWorkPane().setClip(new Rectangle(nW,nH));
    }

    @Override
    public void undoTransaction() {
        data.getWorkPane().setMaxSize(oW, oH);
        data.getWorkPane().setClip(new Rectangle(oW,oH));
    }
    
    
}
