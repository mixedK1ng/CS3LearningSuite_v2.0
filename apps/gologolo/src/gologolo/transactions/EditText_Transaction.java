/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.transactions;

import gologolo.GologoloApp;
import gologolo.data.GoLogoData;
import gologolo.data.GoLogoItemPrototype;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import jtps.jTPS_Transaction;

/**
 *
 * @author yiwang
 */
public class EditText_Transaction implements jTPS_Transaction{

    GologoloApp app;
    GoLogoData data;
    GoLogoItemPrototype itemToEdit;
    int index;
    String editText;
    String original;
    
    public EditText_Transaction(GoLogoData initData, GoLogoItemPrototype initNewItem,GologoloApp initapp,int initindex,String after,String initoriginal) {
        data = initData;
        itemToEdit = initNewItem;
        app = initapp;
        index = initindex;
        editText = after;
        original = initoriginal;
    }
    
    
    @Override
    public void doTransaction() {
        
        if(editText=="empty")
        {
            data.removeItem(itemToEdit);
            data.removeNodeinPane(itemToEdit);
        }
        else{
        
        ((Text)((StackPane)((StackPane)app.getWorkspaceComponent().getWorkspace().getChildren().get(0)).getChildren().get(0)).getChildren().get(index)).setText(editText);
        }
    }

    @Override
    public void undoTransaction() {
        
        if(!((StackPane)((StackPane)app.getWorkspaceComponent().getWorkspace().getChildren().get(0)).getChildren().get(0)).getChildren().contains((Text)itemToEdit.getObject()))
        {
            data.addNodeinPane(itemToEdit);
            data.addItem(itemToEdit);   
        }
        else{    
            
        ((Text)((StackPane)((StackPane)app.getWorkspaceComponent().getWorkspace().getChildren().get(0)).getChildren().get(0)).getChildren().get(index)).setText(original);
        }
    }
    
}
