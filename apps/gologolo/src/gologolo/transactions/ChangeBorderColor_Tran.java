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
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import jtps.jTPS_Transaction;

/**
 *
 * @author yiwang
 */
public class ChangeBorderColor_Tran implements jTPS_Transaction{
    GologoloApp app;
    GoLogoData data;
    GoLogoItemPrototype itemToAdd;
    Node node;
    String orico;
    Color newco;
    

    
    public ChangeBorderColor_Tran(GoLogoData initData,GologoloApp initapp, Node initnode,Color color ) {
        data = initData;
        app = initapp;
        node = initnode;
        newco = color;
        if(node instanceof Circle)
            orico = ((Circle)node).getStroke().toString();
        else if(node instanceof Rectangle)
            orico = ((Rectangle)node).getStroke().toString();



    }
    
    @Override
    public void doTransaction() {
        if(node instanceof Circle)
            ((Circle)node).setStroke(newco);
        else if(node instanceof Rectangle)
            ((Rectangle)node).setStroke(newco);

            
    }

    @Override
    public void undoTransaction() { 
        if(node instanceof Circle)
            ((Circle)node).setStroke(Paint.valueOf(orico));
        else if(node instanceof Rectangle)
            ((Rectangle)node).setStroke(Paint.valueOf(orico));
    }
}
