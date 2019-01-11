/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.data;

import djf.components.AppDataComponent;
import djf.modules.AppGUIModule;
import static djf.modules.AppGUIModule.ENABLED;
import static djf.modules.AppGUIModule.FOCUS_TRAVERSABLE;
import static djf.modules.AppGUIModule.HAS_KEY_HANDLER;
import static gologolo.GoLogoPropertyType.GL_DRAW_PANE;
import static gologolo.GoLogoPropertyType.GL_LAYES_ITEMSTABLE;
import gologolo.GologoloApp;
import gologolo.workspace.GoLogoWorkspace;
import gologolo.workspace.controllers.Controller;
import static gologolo.workspace.style.GLLStyle.CLASS_GLL_PANE;
import static gologolo.workspace.style.GLLStyle.CLASS_GLL_WORKPANE;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 *
 * @author yiwang
 */
public class GoLogoData implements AppDataComponent{
    
    GologoloApp app;
    ObservableList<GoLogoItemPrototype> items;
    TableViewSelectionModel itemsSelectionModel;
    Controller controller;
    StackPane workarea;
    boolean isnotblank;
    
    public GoLogoData(GologoloApp initapp){
        app = initapp;
        //constructPane();
        TableView tableView = (TableView) app.getGUIModule().getGUINode(GL_LAYES_ITEMSTABLE);
        items = tableView.getItems();
        itemsSelectionModel = tableView.getSelectionModel();
        itemsSelectionModel.setSelectionMode(SelectionMode.SINGLE);
        controller = ((GoLogoWorkspace)app.getWorkspaceComponent()).getController();
        workarea = (StackPane)((StackPane)((BorderPane)app.getWorkspaceComponent().getWorkspace()).getCenter()).getChildren().get(0);
        
        
        
    }
    
    public void setBlank(){
        isnotblank = true;
    }

    public boolean isnotBlank(){
        return isnotblank;
    }
    
    public StackPane getWorkPane(){
        return workarea;
    }
    
    public void setWorkPane(StackPane pane){
        ((StackPane)((BorderPane)app.getWorkspaceComponent().getWorkspace()).getCenter()).getChildren().add(pane);
        workarea = pane;
    }
    
    public Iterator<GoLogoItemPrototype> itemsIterator() {
        return this.items.iterator();
    }
    
    public boolean isItemSelected() {
        ObservableList<GoLogoItemPrototype> selectedItems = this.getSelectedItems();
        return (selectedItems != null) && (selectedItems.size() == 1);
    }
    
    public boolean areItemsSelected() {
        ObservableList<GoLogoItemPrototype> selectedItems = this.getSelectedItems();
        return (selectedItems != null) && (selectedItems.size() > 1);        
    }
    
    public void addItem(GoLogoItemPrototype itemToAdd) {
        items.add(itemToAdd);
    }

    public void removeItem(GoLogoItemPrototype itemToAdd) {
        items.remove(itemToAdd);
    }
    
    public GoLogoItemPrototype getSelectedItem() {
        if (!isItemSelected()) {
            return null;
        }
        return getSelectedItems().get(0);
    }
    public ObservableList<GoLogoItemPrototype> getSelectedItems() {
        return (ObservableList<GoLogoItemPrototype>)this.itemsSelectionModel.getSelectedItems();
    }
    public int getItemIndex(GoLogoItemPrototype item) {
        return items.indexOf(item);
    }

    public void addItemAt(GoLogoItemPrototype item, int itemIndex) {
        items.add(itemIndex, item);
    }

    public void moveItem(int oldIndex, int newIndex) {
        GoLogoItemPrototype itemToMove = items.remove(oldIndex);
        items.add(newIndex, itemToMove);
    }

    public int getNumItems() {
        return items.size();
    }

    public void selectItem(GoLogoItemPrototype itemToSelect) {
        this.itemsSelectionModel.select(itemToSelect);
    }
    
    public void selectItemAtIndex(int index) {
        this.itemsSelectionModel.select(index);
    }

    public ArrayList<Integer> removeAll(ArrayList<GoLogoItemPrototype> itemsToRemove) {
        ArrayList<Integer> itemIndices = new ArrayList();
        for (GoLogoItemPrototype item: itemsToRemove) {
            itemIndices.add(items.indexOf(item));
        }
        for (GoLogoItemPrototype item: itemsToRemove) {
            items.remove(item);
        }
        return itemIndices;
    }

    public void addAll(ArrayList<GoLogoItemPrototype> itemsToAdd, ArrayList<Integer> addItemLocations) {
        for (int i = 0; i < itemsToAdd.size(); i++) {
            GoLogoItemPrototype itemToAdd = itemsToAdd.get(i);
            Integer location = addItemLocations.get(i);
            items.add(location, itemToAdd);
        }
    }

    public ArrayList<GoLogoItemPrototype> getCurrentItemsOrder() {
        ArrayList<GoLogoItemPrototype> orderedItems = new ArrayList();
        for (GoLogoItemPrototype item : items) {
            orderedItems.add(item);
        }
        return orderedItems;
    }

    public void clearSelected() {
        this.itemsSelectionModel.clearSelection();
    }

    public void sortItems(Comparator sortComparator) {
        Collections.sort(items, sortComparator);
    }

    public void rearrangeItems(ArrayList<GoLogoItemPrototype> oldListOrder) {
        items.clear();
        for (GoLogoItemPrototype item : oldListOrder) {
            items.add(item);
        }
    }
    
    public void resetOrder() {
        for (GoLogoItemPrototype item : items) {
            item.setOrder(items.indexOf(item)+1);
        }
    }
    
    public int getNodeIndexInPane(GoLogoItemPrototype item) {
        Node node = (Node)item.getObject();
        int index = this.getWorkPane().getChildren().indexOf(node);
        return index;
    }
    
    public Node getNodeInPaneAtIndex(int index) {
        Node node = this.getWorkPane().getChildren().get(index);
        
        return node;
    }
    
    public void moveItemInPane(int oldIndex, int newIndex) {
        Node node = this.getWorkPane().getChildren().remove(oldIndex);
        this.getWorkPane().getChildren().add(newIndex, node);
    }
    
    
    
    
    public void hightlightOneNodeInPane(GoLogoItemPrototype item){
        clearAllhighlight();
        Node node = (Node)item.getObject();

        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0);
        ds.setOffsetX(3.0);
        ds.setColor(Color.GRAY);
        node.setEffect(ds);

        node.requestFocus();
        node.setVisible(true);
    }
    
    public void hightlightOneNodeInPaneAtIndex(int index){
        clearAllhighlight();
        Node node = (Node)items.get(index).getObject();

        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0);
        ds.setOffsetX(3.0);
        ds.setColor(Color.GRAY);
        node.setEffect(ds);

        node.requestFocus();
        node.setVisible(true);
    }
    
    public void clearAllhighlight(){

        for (GoLogoItemPrototype item : items) {
            ((Node)item.getObject()).setEffect(null);
        }
    }
    
    public void removeNodeinPane(GoLogoItemPrototype item){
        
        this.getWorkPane().getChildren().remove((Node)item.getObject());
    }
    public void removeNodeinPaneAtIndex(int index){
        
        this.getWorkPane().getChildren().remove(index);
    }
    
    public void addNodeinPane(GoLogoItemPrototype item){
        
        this.getWorkPane().getChildren().add((Node)item.getObject());
 
    }
    
    public void addNodeinPaneatIndex(GoLogoItemPrototype item,int index){
        
        this.getWorkPane().getChildren().add(index, (Node)item.getObject());
        
        
    }

    public void activateNodeAction(GoLogoItemPrototype item){
        
        controller.setNodeActionOnOff((Node)item.getObject(), item);
    }

    @Override
    public void reset() {
        AppGUIModule gui = app.getGUIModule();
        
        
        
        TableView tableView = (TableView)gui.getGUINode(GL_LAYES_ITEMSTABLE);
        items = tableView.getItems();
        
        for(GoLogoItemPrototype item: items)
        {
            removeNodeinPane(item);
        } 
        items.clear();
        
    }
    
    public void removePane() {
        AppGUIModule gui = app.getGUIModule();
        
        ((StackPane)app.getWorkspaceComponent().getWorkspace().getChildren().get(0)).getChildren().clear();
        
    }

    
    
}
