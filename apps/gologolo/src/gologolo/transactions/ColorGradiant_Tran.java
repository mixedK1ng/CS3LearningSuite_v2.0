/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.transactions;

import gologolo.GologoloApp;
import gologolo.data.GoLogoData;
import gologolo.data.GoLogoItemPrototype;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Paint;
import javafx.scene.paint.RadialGradient;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import jtps.jTPS_Transaction;

/**
 *
 * @author yiwang
 */

public class ColorGradiant_Tran implements jTPS_Transaction{
    GologoloApp app;
    GoLogoData data;
    GoLogoItemPrototype itemToAdd;
    RadialGradient oldR;
    RadialGradient newR;
    

    
    public ColorGradiant_Tran(GoLogoData initData,GologoloApp initapp, RadialGradient o,RadialGradient n ) {
        data = initData;
        app = initapp;
        
        oldR = o;
        newR =n;
        
        if(data.isItemSelected())
            itemToAdd = data.getSelectedItem();
        else itemToAdd = null;

    }
    
    @Override
    public void doTransaction() {

        if(!data.isItemSelected())
        {
            //data.setBlank();
            data.getWorkPane().setBackground(new Background(new BackgroundFill(newR, CornerRadii.EMPTY, Insets.EMPTY)));
        }
        else if(itemToAdd.getType().equals("Rectangle"))
        {
            Rectangle r = (Rectangle)data.getSelectedItem().getObject();
            r.setFill(newR);
        }
        else if (itemToAdd.getType().equals("Circle"))
        {
            Circle cir = (Circle)data.getSelectedItem().getObject();
            cir.setFill(newR);
        }

            
            
    }

    @Override
    public void undoTransaction() {
        if(!data.isItemSelected())
        {
            data.getWorkPane().setBackground(new Background(new BackgroundFill(oldR, CornerRadii.EMPTY, Insets.EMPTY)));
        }
        else if(itemToAdd.getType().equals("Rectangle"))
        {
            Rectangle r = (Rectangle)data.getSelectedItem().getObject();
            r.setFill(oldR);
        }
        else if (itemToAdd.getType().equals("Circle"))
        {
            Circle cir = (Circle)data.getSelectedItem().getObject();
            cir.setFill(oldR);
        }
    }
}
