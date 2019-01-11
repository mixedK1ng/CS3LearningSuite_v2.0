package tdlm.workspace.foolproof;

import djf.modules.AppGUIModule;
import djf.ui.foolproof.FoolproofDesign;
import javafx.scene.control.TextField;
import tdlm.ToDoListMakerApp;
import static tdlm.ToDoPropertyType.TDLM_DOWN_ITEM_BUTTON;
import static tdlm.ToDoPropertyType.TDLM_EDIT_ITEM_BUTTON;
import static tdlm.ToDoPropertyType.TDLM_NAME_TEXT_FIELD;
import static tdlm.ToDoPropertyType.TDLM_OWNER_TEXT_FIELD;
import static tdlm.ToDoPropertyType.TDLM_REMOVE_ITEM_BUTTON;
import static tdlm.ToDoPropertyType.TDLM_UP_ITEM_BUTTON;
import tdlm.data.ToDoData;

/**
 *
 * @author McKillaGorilla
 */
public class ToDoSelectionFoolproofDesign implements FoolproofDesign {
    ToDoListMakerApp app;
    
    public ToDoSelectionFoolproofDesign(ToDoListMakerApp initApp) {
        app = initApp;
    }

    @Override
    public void updateControls() {
        AppGUIModule gui = app.getGUIModule();
       
        // CHECK AND SEE IF A TABLE ITEM IS SELECTED
        ToDoData data = (ToDoData)app.getDataComponent();
        boolean itemIsSelected = data.isItemSelected();
        boolean itemsAreSelected = data.areItemsSelected();
        gui.getGUINode(TDLM_REMOVE_ITEM_BUTTON).setDisable(!(itemIsSelected || itemsAreSelected));
        gui.getGUINode(TDLM_EDIT_ITEM_BUTTON).setDisable(!(itemIsSelected));
        
        //System.out.println("现在所选"+data.getItemIndex(data.getSelectedItem()));
        if(data.getItemIndex(data.getSelectedItem())==0)
        {
            gui.getGUINode(TDLM_UP_ITEM_BUTTON).setDisable(true);
        }
        else{
            gui.getGUINode(TDLM_UP_ITEM_BUTTON).setDisable(!(itemIsSelected));
        }
        
        if(data.getItemIndex(data.getSelectedItem())==data.getNumItems()-1)
        {
            gui.getGUINode(TDLM_DOWN_ITEM_BUTTON).setDisable(true);
        }
        else{
            gui.getGUINode(TDLM_DOWN_ITEM_BUTTON).setDisable(!(itemIsSelected));
        }
        
        ((TextField)gui.getGUINode(TDLM_OWNER_TEXT_FIELD)).setEditable(true);
        ((TextField)gui.getGUINode(TDLM_NAME_TEXT_FIELD)).setEditable(true);
    }
}