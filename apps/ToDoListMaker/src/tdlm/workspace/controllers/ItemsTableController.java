package tdlm.workspace.controllers;

import djf.AppTemplate;
import djf.modules.AppGUIModule;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import tdlm.ToDoListMakerApp;

import static tdlm.ToDoPropertyType.TDLM_ITEMS_TABLE_VIEW;
import tdlm.data.ToDoData;
import tdlm.data.ToDoItemPrototype;

import tdlm.transactions.MoveDown_Transaction;
import tdlm.transactions.MoveUp_Transaction;


/**
 *
 * @author McKillaGorilla
 */
public class ItemsTableController {
    ToDoListMakerApp app;

    public ItemsTableController(AppTemplate initApp) {
        app = (ToDoListMakerApp)initApp;
    }

    public void processChangeTableSize() {
        AppGUIModule gui = app.getGUIModule();
        TableView<ToDoItemPrototype> itemsTable = (TableView)gui.getGUINode(TDLM_ITEMS_TABLE_VIEW);
        ObservableList columns = itemsTable.getColumns();
        for (int i = 0; i < columns.size(); i++) {
            TableColumn column = (TableColumn)columns.get(i);
            column.setMinWidth(itemsTable.widthProperty().getValue()/columns.size());
            column.setMaxWidth(itemsTable.widthProperty().getValue()/columns.size());
        }
    }   
    public void processMoveUpColumn() {
        ToDoData data = (ToDoData)app.getDataComponent();
        if (data.isItemSelected()) 
        {
            
            ToDoItemPrototype itemsToMoveUp = new ToDoItemPrototype();

            itemsToMoveUp = data.getSelectedItem();
            
            if(data.getItemIndex(itemsToMoveUp)>0){
                MoveUp_Transaction transaction = new MoveUp_Transaction(data, itemsToMoveUp);
                app.processTransaction(transaction);
                app.getFileModule().markAsEdited(true);
            }
  
            //System.out.println("位置在"+data.getItemIndex(itemsToMoveUp));

        }
        
        
    }  

    public void processMoveDownColumn() {
        ToDoData data = (ToDoData)app.getDataComponent();
        if (data.isItemSelected()) 
        {
            
            ToDoItemPrototype itemsToMoveDown = new ToDoItemPrototype();

            itemsToMoveDown = data.getSelectedItem();
            
            
            
            if(data.getItemIndex(itemsToMoveDown)<data.getNumItems()-1){
                MoveDown_Transaction transaction = new MoveDown_Transaction(data, itemsToMoveDown);
                app.processTransaction(transaction);
                app.getFileModule().markAsEdited(true);
                
            }
  
            //System.out.println("位置在"+data.getItemIndex(itemsToMoveDown));

        }
    }
    
    
    
}
