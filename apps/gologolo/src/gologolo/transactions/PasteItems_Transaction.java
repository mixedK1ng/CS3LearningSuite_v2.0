/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.transactions;

import static djf.AppPropertyType.APP_CLIPBOARD_FOOLPROOF_SETTINGS;
import gologolo.GologoloApp;
import gologolo.data.GoLogoData;
import gologolo.data.GoLogoItemPrototype;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import jtps.jTPS_Transaction;

/**
 *
 * @author yiwang
 */
public class PasteItems_Transaction implements jTPS_Transaction{
    GologoloApp app;
    GoLogoItemPrototype itemsToPaste;
    GoLogoData data;
    int cutItemLocation;
    
    public PasteItems_Transaction(GologoloApp initApp,GoLogoData idata, GoLogoItemPrototype initItemToCut,int index) {
        app = initApp;
        itemsToPaste = initItemToCut;
        data = idata;
        cutItemLocation = index;
        
    }
    
    public PasteItems_Transaction(GologoloApp initApp, GoLogoItemPrototype initItemToCut) {
        app = initApp;
        itemsToPaste = initItemToCut;
        cutItemLocation = -1;
        
    }

    @Override
    public void doTransaction() {
        GoLogoData data = (GoLogoData)app.getDataComponent();
        

        data.addItem(itemsToPaste);
        data.addNodeinPane(itemsToPaste);
        data.resetOrder();
        data.selectItem(itemsToPaste);
        data.hightlightOneNodeInPane(itemsToPaste);
        data.activateNodeAction(itemsToPaste);

        
        data.resetOrder();
        app.getFoolproofModule().updateControls(APP_CLIPBOARD_FOOLPROOF_SETTINGS);
    }

    @Override
    public void undoTransaction() {
        GoLogoData data = (GoLogoData)app.getDataComponent();
        if(cutItemLocation==-1)
        {
            data.removeNodeinPane(itemsToPaste);
            data.removeItem(itemsToPaste);
            data.resetOrder();
            data.clearAllhighlight();
            data.clearSelected();
                
        }
        else{
            data.removeNodeinPane(itemsToPaste);
            data.removeItem(itemsToPaste);
            data.resetOrder();
            data.selectItemAtIndex(cutItemLocation);
            data.hightlightOneNodeInPane(data.getSelectedItem());
        }
        data.resetOrder();
        app.getFoolproofModule().updateControls(APP_CLIPBOARD_FOOLPROOF_SETTINGS);
    }    
}
