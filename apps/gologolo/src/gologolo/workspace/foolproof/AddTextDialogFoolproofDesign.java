/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.workspace.foolproof;

import djf.modules.AppGUIModule;
import djf.ui.foolproof.FoolproofDesign;
import static gologolo.GoLogoPropertyType.GL_DELETE_BUTTON;
import static gologolo.GoLogoPropertyType.GL_LAYERS_EDIT_BUTTON;
import static gologolo.GoLogoPropertyType.GL_LAYERS_MOVEDOWN_BUTTON;
import static gologolo.GoLogoPropertyType.GL_LAYERS_MOVEUP_BUTTON;
import gologolo.GologoloApp;
import gologolo.data.GoLogoData;
import gologolo.workspace.dialogs.AddTextDialog;

/**
 *
 * @author yiwang
 */

public class AddTextDialogFoolproofDesign implements FoolproofDesign {
    GologoloApp app;
    AddTextDialog dialog;
    
    public AddTextDialogFoolproofDesign(GologoloApp initApp,AddTextDialog d) {
        app = initApp;
        dialog = d;
    }

    @Override
    public void updateControls() {
        AppGUIModule gui = app.getGUIModule();
       
        // CHECK AND SEE IF A TABLE ITEM IS SELECTED
        GoLogoData data = (GoLogoData)app.getDataComponent();
        boolean itemIsSelected = data.isItemSelected();

        gui.getGUINode(GL_DELETE_BUTTON).setDisable(!(itemIsSelected));
        gui.getGUINode(GL_LAYERS_EDIT_BUTTON).setDisable(!(itemIsSelected));
        
        //System.out.println("现在所选"+data.getItemIndex(data.getSelectedItem()));
        if(data.getItemIndex(data.getSelectedItem())==0)
        {
            gui.getGUINode(GL_LAYERS_MOVEUP_BUTTON).setDisable(true);
        }
        else{
            gui.getGUINode(GL_LAYERS_MOVEUP_BUTTON).setDisable(!(itemIsSelected));
        }
        
        if(data.getItemIndex(data.getSelectedItem())==data.getNumItems()-1)
        {
            gui.getGUINode(GL_LAYERS_MOVEDOWN_BUTTON).setDisable(true);
        }
        else{
            gui.getGUINode(GL_LAYERS_MOVEDOWN_BUTTON).setDisable(!(itemIsSelected));
        }
        
        
        
        
    }
}
