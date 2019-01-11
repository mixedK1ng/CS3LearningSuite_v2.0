package tdlm.workspace.controllers;

import static djf.AppPropertyType.ADD_DIALOG_ERROR_CONTENT;
import static djf.AppPropertyType.ADD_DIALOG_ERROR_TITLE;
import static djf.AppPropertyType.EDIT_DIALOG_ERROR_CONTENT;
import static djf.AppPropertyType.EDIT_DIALOG_ERROR_TITLE;
import java.util.ArrayList;
import tdlm.ToDoListMakerApp;
import tdlm.data.ToDoData;
import tdlm.data.ToDoItemPrototype;
import tdlm.workspace.dialogs.ToDoListItemDialog;
import tdlm.transactions.AddItem_Transaction;
import tdlm.transactions.EditItems_Transaction;
import tdlm.transactions.RemoveItems_Transaction;

/**
 *
 * @author McKillaGorilla
 */
public class ItemsController {
    ToDoListMakerApp app;
    ToDoListItemDialog itemDialog;
    
    public ItemsController(ToDoListMakerApp initApp) {
        app = initApp;
        
        itemDialog = new ToDoListItemDialog(app);
    }
    
    public void processAddItem() {
        itemDialog.showAddDialog();
        
        
        ToDoItemPrototype newItem = itemDialog.getNewItem();
        //System.out.println("processing "+newItem.assigntoProperty());
        if (newItem != null) {
            // IF IT HAS A UNIQUE NAME AND COLOR
            // THEN CREATE A TRANSACTION FOR IT
            // AND ADD IT
            ToDoData data = (ToDoData)app.getDataComponent();
            
            System.out.println(newItem.getStartDate());
            AddItem_Transaction transaction = new AddItem_Transaction(data, newItem);
            app.processTransaction(transaction);
            app.getFileModule().markAsEdited(true);
            
        }    
        // OTHERWISE TELL THE USER WHAT THEY
        // HAVE DONE WRONG
        else {
            System.out.println("显示窗口");
            //djf.ui.dialogs.AppDialogsFacade.showMessageDialog(app.getGUIModule().getWindow(), "Invalid Line", "Invalid data for a new line");
            djf.ui.dialogs.AppDialogsFacade.showMessageDialog(app.getGUIModule().getWindow(), ADD_DIALOG_ERROR_TITLE, ADD_DIALOG_ERROR_CONTENT);

        }
    }
    
    public void processRemoveItems() {
        ToDoData data = (ToDoData)app.getDataComponent();
        if (data.isItemSelected() || data.areItemsSelected()) {
            ArrayList<ToDoItemPrototype> itemsToRemove = new ArrayList(data.getSelectedItems());
            RemoveItems_Transaction transaction = new RemoveItems_Transaction(app, itemsToRemove);
            app.processTransaction(transaction);
            app.getFileModule().markAsEdited(true);
        }
    }
    
    public void processEditItems() {
        ToDoData data = (ToDoData)app.getDataComponent();
        
        
        if(data.isItemSelected())
        {
            ToDoItemPrototype editItem = data.getSelectedItem();
            ToDoItemPrototype originalItem = (ToDoItemPrototype) data.getSelectedItem().clone();
            int index = data.getItemIndex(data.getSelectedItem());
            
            itemDialog.showEditDialog(editItem);
            
            //this if statement prevent user hit "cancel" button, if user hit, then do nothing no transaction
            if(itemDialog.getEditItem()==null){
                djf.ui.dialogs.AppDialogsFacade.showMessageDialog(app.getGUIModule().getWindow(), EDIT_DIALOG_ERROR_TITLE, EDIT_DIALOG_ERROR_CONTENT);

            }
            else{
                EditItems_Transaction transaction = new EditItems_Transaction(data, itemDialog.getEditItem(),editItem,index);
                app.processTransaction(transaction);    
                app.getFileModule().markAsEdited(true);
            }
            
        }

    }
}
