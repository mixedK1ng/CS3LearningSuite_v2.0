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
import jtps.jTPS_Transaction;

/**
 *
 * @author yiwang
 */
public class Move_Transaction implements jTPS_Transaction{
    
    GologoloApp app;
    GoLogoData data;
    double originalX,originalY,newX,newY;
    Object obj;
    int index;
    
    
    public Move_Transaction(GologoloApp initapp,GoLogoData initdata,Object initobj,int initindex,double oriX,double oriY,double nX,double nY ) {
        app = initapp;
        data = initdata;
        originalX = oriX;
        originalY = oriY;
        newX = nX;
        newY = nY;
        obj = initobj;
        index = initindex;

    }
    
    
    
    @Override
    public void doTransaction() {
        
        data.getNodeInPaneAtIndex(index).setTranslateX(newX);
        data.getNodeInPaneAtIndex(index).setTranslateY(newY);

      
    }

    @Override
    public void undoTransaction() {
        data.getNodeInPaneAtIndex(index).setTranslateX(originalX);
        data.getNodeInPaneAtIndex(index).setTranslateY(originalY);

    }
}
