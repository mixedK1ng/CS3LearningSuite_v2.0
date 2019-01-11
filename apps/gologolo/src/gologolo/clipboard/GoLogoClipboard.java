package gologolo.clipboard;

import djf.components.AppClipboardComponent;
import gologolo.GologoloApp;
import gologolo.data.GoLogoData;
import gologolo.data.GoLogoItemPrototype;
import gologolo.transactions.CutItems_Transaction;
import gologolo.transactions.PasteItems_Transaction;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.scene.Node;


/**
 *
 * @author McKillaGorilla
 */
public class GoLogoClipboard implements AppClipboardComponent {
    GologoloApp app;
    GoLogoItemPrototype clipboardCutItem;
    GoLogoItemPrototype clipboardCopiedItem;
    
    public GoLogoClipboard(GologoloApp initApp) {
        app = initApp;
        clipboardCutItem = null;
        clipboardCopiedItem = null;
    }
    
    @Override
    public void cut() {
        GoLogoData data = (GoLogoData)app.getDataComponent();
        if (data.isItemSelected()) {
            clipboardCutItem = data.getSelectedItem();
            clipboardCopiedItem = null;
            CutItems_Transaction transaction = new CutItems_Transaction((GologoloApp)app,data, clipboardCutItem);
            app.processTransaction(transaction);
            
        }
    }

    @Override
    public void copy() {
        GoLogoData data = (GoLogoData)app.getDataComponent();
        if (data.isItemSelected()) {
            GoLogoItemPrototype tempItem = data.getSelectedItem();
            copyToCopiedClipboard(tempItem);
        }
    }
    
    private void copyToCutClipboard(GoLogoItemPrototype itemsToCopy) {
        //clipboardCutItem = new GoLogoItemPrototype();
        //Node node = (Node)itemsToCopy.getObject();
        //Node newNode = node.get
        clipboardCutItem = (GoLogoItemPrototype)itemsToCopy.clone();
        clipboardCopiedItem = null;        
        app.getFoolproofModule().updateAll();        
    }
    
    private void copyToCopiedClipboard(GoLogoItemPrototype itemToCopy) {
        clipboardCutItem = null;
        clipboardCopiedItem = (GoLogoItemPrototype)itemToCopy.clone();
        app.getFoolproofModule().updateAll();        
    }
    
//    private ArrayList<GoLogoItemPrototype> copyItems(ArrayList<GoLogoItemPrototype> itemsToCopy) {
//        ArrayList<GoLogoItemPrototype> tempCopy = new ArrayList();         
//        for (GoLogoItemPrototype itemToCopy : itemsToCopy) {
//            //GoLogoItemPrototype copiedItem = (GoLogoItemPrototype)itemToCopy.clone();
//            //tempCopy.add(copiedItem);
//        }        
//        return tempCopy;
//    }

    @Override
    public void paste() {
        GoLogoData data = (GoLogoData)app.getDataComponent();
        if (data.isItemSelected()) {
            int selectedIndex = data.getItemIndex(data.getSelectedItem());  
            //GoLogoItemPrototype pasteItem = clipboardCutItem;
            if (clipboardCutItem != null) {
                PasteItems_Transaction transaction = new PasteItems_Transaction((GologoloApp)app,data, clipboardCutItem, selectedIndex);
                app.processTransaction(transaction);
                
                // NOW WE HAVE TO RE-COPY THE CUT ITEMS TO MAKE
                // SURE IF WE PASTE THEM AGAIN THEY ARE BRAND NEW OBJECTS
                copyToCutClipboard(clipboardCutItem);
            }
            else if (clipboardCopiedItem != null) {
                PasteItems_Transaction transaction = new PasteItems_Transaction((GologoloApp)app, data,clipboardCopiedItem, selectedIndex);
                app.processTransaction(transaction);
            
                // NOW WE HAVE TO RE-COPY THE COPIED ITEMS TO MAKE
                // SURE IF WE PASTE THEM AGAIN THEY ARE BRAND NEW OBJECTS
                copyToCopiedClipboard(clipboardCopiedItem);
            }
        }
        else{
            if (clipboardCutItem != null) {
                PasteItems_Transaction transaction = new PasteItems_Transaction((GologoloApp)app, clipboardCutItem);
                app.processTransaction(transaction);
                
                // NOW WE HAVE TO RE-COPY THE CUT ITEMS TO MAKE
                // SURE IF WE PASTE THEM AGAIN THEY ARE BRAND NEW OBJECTS
                copyToCutClipboard(clipboardCutItem);
                System.out.println("ggggggg");
            }
            else if (clipboardCopiedItem != null) {
                PasteItems_Transaction transaction = new PasteItems_Transaction((GologoloApp)app, clipboardCopiedItem);
                app.processTransaction(transaction);
            
                // NOW WE HAVE TO RE-COPY THE COPIED ITEMS TO MAKE
                // SURE IF WE PASTE THEM AGAIN THEY ARE BRAND NEW OBJECTS
                copyToCopiedClipboard(clipboardCopiedItem);
            }
        }
        
        
        
    }    


    @Override
    public boolean hasSomethingToCut() {
        return ((GoLogoData)app.getDataComponent()).isItemSelected();
                
    }

    @Override
    public boolean hasSomethingToCopy() {
        return ((GoLogoData)app.getDataComponent()).isItemSelected();
                
    }

    @Override
    public boolean hasSomethingToPaste() {
        if (clipboardCutItem != null)
            return true;
        else if (clipboardCopiedItem != null)
            return true;
        else
            return false;
    }
}