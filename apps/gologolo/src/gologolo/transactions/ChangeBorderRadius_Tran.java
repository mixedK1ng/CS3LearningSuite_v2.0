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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import jtps.jTPS_Transaction;

/**
 *
 * @author yiwang
 */

public class ChangeBorderRadius_Tran implements jTPS_Transaction{
    GologoloApp app;
    GoLogoData data;
    GoLogoItemPrototype itemToAdd;
    Node node;
    double ov,nv;
    

    
    public ChangeBorderRadius_Tran(GoLogoData initData,GologoloApp initapp, Node obj,double oldV,double newV ) {
        data = initData;
        app = initapp;
        node = obj;
        ov = oldV;
        nv = newV;


    }
    
    @Override
    public void doTransaction() {

        ((Rectangle)node).setArcHeight(nv);
        ((Rectangle)node).setArcHeight(nv);

            
            
    }

    @Override
    public void undoTransaction() {

        ((Rectangle)node).setArcHeight(ov);
        ((Rectangle)node).setArcHeight(ov);

    }
}
