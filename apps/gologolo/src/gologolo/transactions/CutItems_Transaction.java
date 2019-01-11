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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import jtps.jTPS_Transaction;

/**
 *
 * @author yiwang
 */
public class CutItems_Transaction implements jTPS_Transaction{
    GologoloApp app;
    GoLogoItemPrototype itemsToCut;
    GoLogoData data;
    int cutItemLocation;
    
    public CutItems_Transaction(GologoloApp initApp,GoLogoData idata, GoLogoItemPrototype initItemToCut) {
        app = initApp;
        itemsToCut = initItemToCut;
        data = idata;
        cutItemLocation = ((StackPane)((StackPane)app.getWorkspaceComponent().getWorkspace().getChildren().get(0)).getChildren().get(0)).getChildren().indexOf((Node)itemsToCut.getObject());
        
    }

    @Override
    public void doTransaction() {
        
        data.removeItem(itemsToCut);
        data.removeNodeinPane(itemsToCut);
        data.resetOrder();
        data.clearSelected();
        app.getFoolproofModule().updateControls(APP_CLIPBOARD_FOOLPROOF_SETTINGS);
    }

    @Override
    public void undoTransaction() {
        
        data.addItemAt(itemsToCut, cutItemLocation);
        data.addNodeinPaneatIndex(itemsToCut, cutItemLocation);
        data.resetOrder();
        data.selectItem(itemsToCut);
        data.hightlightOneNodeInPane(itemsToCut);
        app.getFoolproofModule().updateControls(APP_CLIPBOARD_FOOLPROOF_SETTINGS);
    }   
}
