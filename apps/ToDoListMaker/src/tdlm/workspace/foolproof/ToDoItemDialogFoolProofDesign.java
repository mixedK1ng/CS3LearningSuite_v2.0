/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdlm.workspace.foolproof;

import djf.modules.AppGUIModule;
import djf.ui.foolproof.FoolproofDesign;

import tdlm.ToDoListMakerApp;
import static tdlm.ToDoPropertyType.TDLM_ITEM_DIALOG_OK_BUTTON;

import tdlm.workspace.dialogs.ToDoListItemDialog;

/**
 *
 * @author yiwang
 */
public class ToDoItemDialogFoolProofDesign implements FoolproofDesign 
{
    ToDoListMakerApp app;
    ToDoListItemDialog dialog;
    
    public ToDoItemDialogFoolProofDesign(ToDoListMakerApp initApp,ToDoListItemDialog initDialog) {
        app = initApp;
        dialog = initDialog;
    }

     @Override
    public void updateControls() {
        AppGUIModule gui = app.getGUIModule();
        //ToDoData data = (ToDoData)app.getDataComponent();
        
        boolean nullCategory = (dialog.getCategory().getText().trim().length()==0 || dialog.getDescription().getText().trim().length()==0 || dialog.getEndDate().isBefore(dialog.getStartDate()));
        
        gui.getGUINode(TDLM_ITEM_DIALOG_OK_BUTTON).setDisable(nullCategory);

    }
    
}
