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
public class ChangeTextColo_Tran implements jTPS_Transaction{
    GologoloApp app;
    GoLogoData data;
    GoLogoItemPrototype itemToAdd;
    Text node;
    String orico;
    Color newco;
    

    
    public ChangeTextColo_Tran(GoLogoData initData,GologoloApp initapp, Text text,Color color ) {
        data = initData;
        app = initapp;
        node = text;
        newco = color;
        orico = text.getFill().toString();



    }
    
    @Override
    public void doTransaction() {
        node.setFill(newco);
            
    }

    @Override
    public void undoTransaction() {
        node.setFill(Paint.valueOf(orico));

    }
}
