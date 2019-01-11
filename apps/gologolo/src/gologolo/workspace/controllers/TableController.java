/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.workspace.controllers;

import djf.modules.AppGUIModule;
import static gologolo.GoLogoPropertyType.GL_LAYES_ITEMSTABLE;
import gologolo.GologoloApp;
import gologolo.data.GoLogoData;
import gologolo.data.GoLogoItemPrototype;
import static gologolo.workspace.controllers.Controller.inHierarchy;
import gologolo.workspace.dialogs.AddTextDialog;
import gologolo.workspace.dialogs.ResizeLogoDialog;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 *
 * @author yiwang
 */
public class TableController {
    
    GologoloApp app;
    
    public TableController(GologoloApp initApp) {
        app = initApp;

    }
    
//    public void processHighlightLayers() {
//        GoLogoData data = (GoLogoData)app.getDataComponent();
//        AppGUIModule gui = app.getGUIModule();
//        TableView<GoLogoItemPrototype> itemsTable = (TableView)gui.getGUINode(GL_LAYES_ITEMSTABLE);
//        
//        
//        
//        
//        
//        
//        
//    } 
    
    public void processChangeTableSize() {
        AppGUIModule gui = app.getGUIModule();
        TableView<GoLogoItemPrototype> itemsTable = (TableView)gui.getGUINode(GL_LAYES_ITEMSTABLE);
        ObservableList columns = itemsTable.getColumns();
        for (int i = 0; i < columns.size(); i++) {
            TableColumn column = (TableColumn)columns.get(i);
            column.setMinWidth(itemsTable.widthProperty().getValue()/columns.size());
            column.setMaxWidth(itemsTable.widthProperty().getValue()/columns.size());
        }
    } 
    
    
    
}
