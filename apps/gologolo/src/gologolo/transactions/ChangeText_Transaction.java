/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.transactions;

import gologolo.GologoloApp;
import gologolo.data.GoLogoData;
import gologolo.data.GoLogoItemPrototype;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import jtps.jTPS_Transaction;

/**
 *
 * @author yiwang
 */
public class ChangeText_Transaction implements jTPS_Transaction{
    GologoloApp app;
    GoLogoData data;
    GoLogoItemPrototype itemToAdd;
    int index=-1;
    
    Text t;
    String newFam;
    double newSize;
    String newStyle;

    String orifam;
    double orisize;
    String oristyle;
    
    public ChangeText_Transaction(GologoloApp initapp, GoLogoData initData, Text text,String newF,double size,String style ) {
        data = initData;
        app = initapp;
        t = text;
        newFam = newF;
        newSize = size;
        newStyle = style;
        
        orifam = t.getFont().getFamily();
        orisize = t.getFont().getSize();
        oristyle = t.getFont().getStyle();
        
        if(data.isItemSelected())
        {
            itemToAdd = data.getSelectedItem();
            index = data.getItemIndex(data.getSelectedItem());
        }

    }
    
    @Override
    public void doTransaction() {
        
        if(newStyle.contains("Bold")&&newStyle.contains("Italic"))
        {
            t.setFont(Font.font(newFam, FontWeight.BOLD, FontPosture.ITALIC, newSize));System.out.println("Finally:"+t.getFont().getStyle());
            //System.out.println("1:");
        }
        else if(newStyle.contains("Bold")&&(!newStyle.contains("Italic")))
        {
            t.setFont(Font.font(newFam, FontWeight.BOLD, FontPosture.REGULAR, newSize));
            //System.out.println("2:");
        }
        else if(!(newStyle.contains("Bold"))&&(newStyle.contains("Italic")))
        {
            t.setFont(Font.font(newFam, FontWeight.NORMAL, FontPosture.ITALIC, newSize));
            //System.out.println("3:");
        }
        
        else{
            t.setFont(Font.font(newFam, FontWeight.NORMAL, FontPosture.REGULAR, newSize));
            
        }
        System.out.println("do:"+t.toString());
        
        
    }

    @Override
    public void undoTransaction() {
        
        if(oristyle.contains("Bold")&&oristyle.contains("Italic"))
        {
            t.setFont(Font.font(orifam, FontWeight.BOLD, FontPosture.ITALIC, orisize));
        }
        else if(oristyle.contains("Bold")&&(!oristyle.contains("Italic")))
        {
            t.setFont(Font.font(orifam, FontWeight.BOLD, FontPosture.REGULAR, orisize));
        }
        else if(!(oristyle.contains("Bold"))&&(oristyle.contains("Italic")))
        {
            t.setFont(Font.font(orifam, FontWeight.NORMAL, FontPosture.ITALIC, orisize));
        }
        else{
            t.setFont(Font.font(orifam, FontWeight.NORMAL, FontPosture.REGULAR, orisize));
        }
        
        if(index!=-1)
        {
            data.selectItemAtIndex(index);
            data.hightlightOneNodeInPaneAtIndex(index);
        }
        System.out.println("undo:"+t.toString());
    }
}

