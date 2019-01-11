/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.workspace.controllers;

import djf.AppPropertyType;
import static djf.AppPropertyType.LOAD_IMAGE_ERROR_CONTENT;
import static djf.AppPropertyType.LOAD_IMAGE_ERROR_TITLE;
import static djf.AppPropertyType.LOAD_IMAGE_TITLE;
import djf.ui.dialogs.AppDialogsFacade;
import gologolo.GologoloApp;
import gologolo.data.GoLogoData;
import gologolo.data.GoLogoItemPrototype;
import gologolo.transactions.Add_Transaction;
import gologolo.transactions.ChangeBorderColor_Tran;
import gologolo.transactions.ChangeBorderRadius_Tran;
import gologolo.transactions.ChangeBorder_Tran;
import gologolo.transactions.ChangeDimension_Transaction;
import gologolo.transactions.ChangeTextColo_Tran;
import gologolo.transactions.ChangeText_Transaction;
import gologolo.transactions.ColorGradiant_Tran;
import gologolo.transactions.Delete_Transaction;
import gologolo.transactions.EditText_Transaction;
import gologolo.transactions.MoveDown_Transaction;
import gologolo.transactions.MoveUp_Transaction;
import gologolo.transactions.Move_Transaction;
import gologolo.transactions.Rename_Transaction;
import gologolo.workspace.dialogs.AddTextDialog;
import gologolo.workspace.dialogs.RenameDialog;
import gologolo.workspace.dialogs.ResizeLogoDialog;
import static gologolo.workspace.style.GLLStyle.CLASS_GLL_ADD_TEXT;
import java.io.File;
import java.util.Arrays;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.math.BigDecimal;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.RadialGradient;
import javafx.scene.shape.Circle;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import properties_manager.PropertiesManager;

/**
 *
 * @author yiwang
 */
public class Controller {
    GologoloApp app;
    ResizeLogoDialog itemDialog;
    AddTextDialog editDialog;
    RenameDialog renameDialog;
    static boolean nodeDragged;
    public static boolean isnotLoad;

    public GologoloApp getApp() {
        return app;
    }
    
    public GoLogoData getData() {
        return (GoLogoData)app.getDataComponent();
    }
    
    

    public void setNodeDragged(boolean nodeDragged) {
        this.nodeDragged = nodeDragged;
    }
    
    
    public static double oriThick,newThick;
    public static RadialGradient oriR; 
    public static RadialGradient newR;
    
    static double orgSceneX,orgSceneX1, orgSceneY,orgSceneY1,orgTranslateX,orgTranslateX1, orgTranslateY,orgTranslateY1,newTranslateX,newTranslateX1,newTranslateY,newTranslateY1;
    
    public Controller(GologoloApp initApp) {
        app = initApp;
        itemDialog = new ResizeLogoDialog(app);
        editDialog = new AddTextDialog(app);
        renameDialog = new RenameDialog(app);
    }
    
    public void processChangeDimension() {
        GoLogoData data = (GoLogoData)app.getDataComponent();
        
        itemDialog.showChangeDialog(((StackPane)((StackPane)((BorderPane)app.getWorkspaceComponent().getWorkspace()).getCenter()).getChildren().get(0)));
        
        double orgH,orgW,newH,newW;
        newH = itemDialog.getNewH();
        newW = itemDialog.getNewW();
        orgH = itemDialog.getOrgH();
        orgW = itemDialog.getOrgW();

        if(!itemDialog.isCanceled())
        {
            ChangeDimension_Transaction t = new ChangeDimension_Transaction(app,data,orgH,orgW,newH,newW);
            app.processTransaction(t);
            app.getFileModule().markAsEdited(true);
            app.getFoolproofModule().updateAll();
        }

        
 
    }
    
    public void processRename() {
        
        GoLogoData data = (GoLogoData)app.getDataComponent();
        
        if(data.isItemSelected())
        {
            
            String oldname = data.getSelectedItem().getName();
            renameDialog.showRenameDialog(oldname);
            
            if(!renameDialog.isCanceled())
            {
                String newname = renameDialog.getNew_name();
                Rename_Transaction t = new Rename_Transaction(app,data,oldname,newname);
                app.processTransaction(t);
                app.getFileModule().markAsEdited(true);
                //app.getFoolproofModule().updateAll();
            }
        }
        
          
    }
    
    public void processEnlarge() {
        
        GoLogoData data = (GoLogoData)app.getDataComponent();
        double orgX = data.getWorkPane().getScaleX();
        double orgY = data.getWorkPane().getScaleY();
        data.getWorkPane().setScaleX(orgX+orgX);
        data.getWorkPane().setScaleY(orgY+orgY);
        
    }
    
    public void processShrink() {
        
        GoLogoData data = (GoLogoData)app.getDataComponent();
        double orgX = data.getWorkPane().getScaleX();
        double orgY = data.getWorkPane().getScaleY();
        
        
        data.getWorkPane().setScaleX(0.5*(orgX));
        data.getWorkPane().setScaleY(0.5*(orgY));
        
    }
    
    public void resetView() {
        
        GoLogoData data = (GoLogoData)app.getDataComponent();
        StackPane pane = data.getWorkPane();
        pane.setScaleX(1);
        pane.setScaleY(1);

        pane.setTranslateX(0);
        pane.setTranslateY(0);
        
        
    }
    
    public boolean isCircle() {
        GoLogoData data = (GoLogoData)app.getDataComponent();
        if(data.isItemSelected())
        {
            if(data.getSelectedItem().getType()=="Circle")
                return true;
            else return false;
        }
        else return false;
    }
    
    public Circle getCircle() {
        GoLogoData data = (GoLogoData)app.getDataComponent();
        Circle c = null;
        if(data.isItemSelected())
        {
            if(data.getSelectedItem().getType()=="Circle")
                return (Circle)data.getSelectedItem().getObject();
            else return c;
        }
        else return c;
    }
    
    public void enlargeCircle() {
        GoLogoData data = (GoLogoData)app.getDataComponent();
        ((Circle)data.getSelectedItem().getObject()).setRadius(((Circle)data.getSelectedItem().getObject()).getRadius()*1.1);

        
    }
    
    public void shrinkCircle() {
        GoLogoData data = (GoLogoData)app.getDataComponent();
        ((Circle)data.getSelectedItem().getObject()).setRadius(((Circle)data.getSelectedItem().getObject()).getRadius()*0.9);

        
    }
    
    public void processDelete() {
        GoLogoData data = (GoLogoData)app.getDataComponent();

        if(data.isItemSelected())
        {
            GoLogoItemPrototype delete = data.getSelectedItem();
            int index = ((StackPane)((Pane)app.getWorkspaceComponent().getWorkspace().getChildren().get(0)).getChildren().get(0)).getChildren().indexOf(delete.getObject());
            Delete_Transaction transaction = new Delete_Transaction(data,delete,app,index);
            app.processTransaction(transaction);
            app.getFileModule().markAsEdited(true);
            app.getFoolproofModule().updateAll();
            
            data.clearSelected();
        }
    }
    
    public void processAddCircle() {
        GoLogoData data = (GoLogoData)app.getDataComponent();

        Circle c = new Circle();
        
        c.setRadius(30);
        c.setStrokeWidth(3.0);
        c.setStroke(Color.BLACK);
        c.setFill(RadialGradient.valueOf("radial-gradient(focus-angle 180.0deg, focus-distance 50.0% , center 50.0% 50.0%, radius 50.0%, reflect, 0xffffffff 0.0%, 0xffffffff 100.0%)"));
        
        GoLogoItemPrototype newItem= new GoLogoItemPrototype(1,"cir","Circle",c);
        Add_Transaction transaction = new Add_Transaction(data, newItem,app);
        app.processTransaction(transaction);
        app.getFileModule().markAsEdited(true);
        app.getFoolproofModule().updateAll();

        setNodeActionOnOff(c,newItem);
        
        
    }
    
    public void processAddImage() {
        GoLogoData data = (GoLogoData)app.getDataComponent();
        PropertiesManager props = PropertiesManager.getPropertiesManager();

        File select = AppDialogsFacade.showLoadImageDialog(app.getGUIModule().getWindow(), LOAD_IMAGE_TITLE);
        
        if(select!=null)
        {
            try{
                String path = "file:"+select.getPath();
                ImageView iv = new ImageView(new Image(path));
                //System.out.println(iv.getImage().);
                
                GoLogoItemPrototype newItem = new GoLogoItemPrototype(1,"image","Image",iv,path);
                Add_Transaction transaction = new Add_Transaction(data, newItem,app);
                app.processTransaction(transaction);
                app.getFileModule().markAsEdited(true);
                app.getFoolproofModule().updateAll();
                setNodeActionOnOff(iv,newItem);
                
            }
            catch(Exception o){
                AppDialogsFacade.showMessageDialog(app.getGUIModule().getWindow(), LOAD_IMAGE_ERROR_TITLE, LOAD_IMAGE_ERROR_CONTENT);
            }
        }
        
    }
    
    
    
    

    
    public void processAddRectangle() 
    {
        GoLogoData data = (GoLogoData)app.getDataComponent();
        Rectangle rec = new Rectangle();
        rec.setHeight(100);
        rec.setWidth(200);
        rec.setStroke(Color.BLACK);
        rec.setStrokeWidth(3.0);
        rec.setFill(RadialGradient.valueOf("radial-gradient(focus-angle 180.0deg, focus-distance 50.0% , center 50.0% 50.0%, radius 50.0%, reflect, 0xffffffff 0.0%, 0xffffffff 100.0%)"));
        
        
        
        int order = data.getNumItems();
        
        GoLogoItemPrototype newItem= new GoLogoItemPrototype(order+1,"rec","Rectangle",rec);
        Add_Transaction transaction = new Add_Transaction(data, newItem,app);
        Controller.isnotLoad = false;
        app.processTransaction(transaction);
        app.getFileModule().markAsEdited(true);
        ;
        app.getFoolproofModule().updateAll();

        

        setNodeActionOnOff(rec,newItem);
        
        
    }
    
    
    public void processAddText() 
    {
        GoLogoData data = (GoLogoData)app.getDataComponent();
        
        editDialog.setEditting(false);
        editDialog.setCancelClicked(false);
        editDialog.showAddDialog();
        
        if(editDialog.getAddText()!="")
        {
            
            Text newText = new Text(editDialog.getAddText());
            newText.setFont(Font.font ("Times New Roman", 30));

            int order = data.getNumItems();
            
            GoLogoItemPrototype newItem= new GoLogoItemPrototype(order+1,"text","Text",newText);
            Add_Transaction transaction = new Add_Transaction(data, newItem,app);
            Controller.isnotLoad = false;
            app.processTransaction(transaction);
            app.getFileModule().markAsEdited(true);

            
            setNodeActionOnOff(newText,newItem);
        }

    }
    
    public void saveOriColor(RadialGradient r) {

        oriR = r;

    }
    
    public void saveNewColor(RadialGradient r) {

        newR = r;

    }
    
    public void changeColorGradient() {
        GoLogoData data = (GoLogoData)app.getDataComponent();

        ColorGradiant_Tran tran = new ColorGradiant_Tran(data,app,oriR,newR);
        app.processTransaction(tran);
        app.getFileModule().markAsEdited(true);
        
        
        resetColor();

    }
    
    public void processChangeText(Text text,String newF,double size,String style) 
    {
        GoLogoData data = (GoLogoData)app.getDataComponent();
        
        ChangeText_Transaction tran = new ChangeText_Transaction(app,data,text,newF,size,style);
        Controller.isnotLoad = false;
        app.processTransaction(tran);
        app.getFileModule().markAsEdited(true);


    }
    
    public void changeBorderThick(Node node) {
        GoLogoData data = (GoLogoData)app.getDataComponent();
        
        ChangeBorder_Tran tran = new ChangeBorder_Tran(data,app,node,Controller.oriThick,Controller.newThick);
        app.processTransaction(tran);
        app.getFileModule().markAsEdited(true);
        resetBorder();
    }
    
    public void changeBorderRadius(Node node) {
        GoLogoData data = (GoLogoData)app.getDataComponent();
        
        ChangeBorderRadius_Tran tran = new ChangeBorderRadius_Tran(data,app,node,Controller.oriThick,Controller.newThick);
        app.processTransaction(tran);
        app.getFileModule().markAsEdited(true);
        resetBorder();
    }
    public void changeBorderColor(Color color,Node node) {
        GoLogoData data = (GoLogoData)app.getDataComponent();
        
        ChangeBorderColor_Tran tran = new ChangeBorderColor_Tran(data,app,node,color);
        app.processTransaction(tran);
        app.getFileModule().markAsEdited(true);
    }
    
    
    public void changeTextColor(Color color,Text text) {
        GoLogoData data = (GoLogoData)app.getDataComponent();
        
        ChangeTextColo_Tran tran = new ChangeTextColo_Tran(data,app,text,color);
        app.processTransaction(tran);
        app.getFileModule().markAsEdited(true);
    }
    
    public void setInitBorderThick(double d) {

        oriThick = d;

    }
    public void setFinalBorderThick(double d) {

        newThick = d;

    }
    
    
    
    public boolean isBold(Text text) 
    {
        if(text.getFont().getStyle().contains("Bold"))
            return true;
        else return false;

    }
    
    public boolean isItalic(Text text) 
    {
        if(text.getFont().getStyle().contains("Italic"))
            return true;
        else return false;
    }
    
    
    
    public void processMoveUp() {
        GoLogoData data = (GoLogoData)app.getDataComponent();
        if (data.isItemSelected()) 
        {
            
            GoLogoItemPrototype itemsToMoveUp = new GoLogoItemPrototype();

            itemsToMoveUp = data.getSelectedItem();
            int index = data.getItemIndex(itemsToMoveUp);
            
            if(index>0){
                MoveUp_Transaction transaction = new MoveUp_Transaction(data, itemsToMoveUp,app,index);
                app.processTransaction(transaction);
                app.getFileModule().markAsEdited(true);
                app.getFoolproofModule().updateAll();
            }
  
            //System.out.println("位置在"+data.getItemIndex(itemsToMoveUp));

        }
        
        
    }  

    public void processMoveDown() {
        GoLogoData data = (GoLogoData)app.getDataComponent();
        if (data.isItemSelected()) 
        {
            
            GoLogoItemPrototype itemsToMoveDown = new GoLogoItemPrototype();

            itemsToMoveDown = data.getSelectedItem();
            int index = data.getItemIndex(itemsToMoveDown);
            
            
            if(index<data.getNumItems()-1){
                MoveDown_Transaction transaction = new MoveDown_Transaction(data, itemsToMoveDown,app,index);
                app.processTransaction(transaction);
                app.getFileModule().markAsEdited(true);
                app.getFoolproofModule().updateAll();
                
            }
  
            //System.out.println("位置在"+data.getItemIndex(itemsToMoveDown));

        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void setNodeActionOnOff(Node node, GoLogoItemPrototype item) {
        GoLogoData data = (GoLogoData)app.getDataComponent();
        StackPane pane = ((StackPane)app.getWorkspaceComponent().getWorkspace().getChildren().get(0));
        StackPane stackpane = ((StackPane)((StackPane)app.getWorkspaceComponent().getWorkspace().getChildren().get(0)).getChildren().get(0));
        
        node.setOnMousePressed((MouseEvent event) -> 
        {
            
            hightlightNode(node);
            
            data.selectItem(item);
            Controller.isnotLoad = false;
            app.getFoolproofModule().updateAll();

            if(node instanceof Text){
                if (event.isPrimaryButtonDown() && event.getClickCount() == 2)
                {
                    String oringinal = ((Text)node).getText();
                    int index = ((StackPane)((StackPane)app.getWorkspaceComponent().getWorkspace().getChildren().get(0)).getChildren().get(0)).getChildren().indexOf(node);
                    editDialog.setEditting(true);
                    editDialog.showEditDialog(oringinal);

                    //有编辑内容的时候
                    if(!editDialog.getEditTextField().getText().isEmpty()&&!editDialog.getCancelClicked())
                    {
                        //System.out.println("这个"+editDialog.getAfterEditText()+"sha");
                        EditText_Transaction transaction1= new EditText_Transaction(data, item,app,index,editDialog.getAfterEditText(),oringinal);
                        app.processTransaction(transaction1);
                        app.getFileModule().markAsEdited(true);
                        Controller.isnotLoad = false;
                        app.getFoolproofModule().updateAll();

                    }
                    //没有编辑内容，直接删除文本框
                    else if(editDialog.getEditTextField().getText().isEmpty()&&!editDialog.getCancelClicked()){
                        //System.out.println("无编辑内容");
                        EditText_Transaction transaction2= new EditText_Transaction(data, item,app,index,"empty",oringinal);
                        app.processTransaction(transaction2);
                        app.getFileModule().markAsEdited(true);
                        Controller.isnotLoad = false;
                        app.getFoolproofModule().updateAll();

                    }
                    editDialog.setCancelClicked(false);
                }
            }
//            
//            System.out.println("sceneX:"+event.getSceneX());
//            System.out.println("sceneY:"+event.getSceneY());
//            System.out.println("translateX:"+((Node)(event.getSource())).getTranslateX());
//            System.out.println("translateY:"+((Node)(event.getSource())).getTranslateY());
//            System.out.println("X:"+event.getX());
//            System.out.println("Y:"+event.getY());
//            System.out.println("screenX:"+event.getScreenX());
//            System.out.println("screenY:"+event.getScreenY());

        

            
            Controller.orgSceneX = event.getSceneX();
            Controller.orgSceneY = event.getSceneY();
            
//            System.out.println("SceneX:"+event.getSceneX());
//            System.out.println("SceneY:"+event.getSceneY());
            
            Controller.orgTranslateX = ((Node)(event.getSource())).getTranslateX();
            Controller.orgTranslateY = ((Node)(event.getSource())).getTranslateY();
        });
            
        node.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                nodeDragged = true;
                
                double offsetX = t.getSceneX() - orgSceneX;
                double offsetY = t.getSceneY() - orgSceneY;
                
                BigDecimal c1 = new BigDecimal(offsetX);
                BigDecimal c2 = new BigDecimal(node.getParent().getScaleX());
                BigDecimal d1 = new BigDecimal(offsetY);
                BigDecimal d2 = new BigDecimal(node.getParent().getScaleY());

                Controller.newTranslateX = orgTranslateX + c1.divide(c2).doubleValue();
                Controller.newTranslateY = orgTranslateY + d1.divide(d2).doubleValue();

                
                ((Node)(t.getSource())).setTranslateX(newTranslateX);
                ((Node)(t.getSource())).setTranslateY(newTranslateY);
 
            }
        });
        
        node.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                if(!(Controller.newTranslateX==0 && Controller.newTranslateY==0))
                {
                    int index = ((StackPane)((StackPane)app.getWorkspaceComponent().getWorkspace().getChildren().get(0)).getChildren().get(0)).getChildren().indexOf(node);
                    Move_Transaction transaction = new Move_Transaction(app,data,node,index, Controller.orgTranslateX,Controller.orgTranslateY,Controller.newTranslateX,Controller.newTranslateY);
                    app.processTransaction(transaction);
                    app.getFileModule().markAsEdited(true);
                    //app.getFoolproofModule().updateAll();

                }
                resetLocation();
                nodeDragged = false;
            }
        });

        setPaneActionDragScroll(pane,stackpane,node);
    }
    
    public void setPaneActionDragScroll(StackPane pane,StackPane canvas,Node node){
        //StackPane stackpane = ((StackPane)((StackPane)app.getWorkspaceComponent().getWorkspace().getChildren().get(0)).getChildren().get(0));
        GoLogoData data = (GoLogoData)app.getDataComponent();
        
        
        canvas.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {


            if(node!=null)
            {
                if (!inHierarchy(event.getPickResult().getIntersectedNode(), node)) 
                {
                    node.setEffect(null);
                    

                }
                app.getFoolproofModule().updateAll();
            }

            if(event.getPickResult().getIntersectedNode().equals(canvas)){
                
                if(data!=null)
                {
                    data.clearSelected();
                    
                }
                app.getFoolproofModule().updateAll();
                Controller.orgSceneX1 = event.getSceneX();
                Controller.orgSceneY1 = event.getSceneY();
                Controller.orgTranslateX1 = ((StackPane)(event.getSource())).getTranslateX();
                Controller.orgTranslateY1 = ((StackPane)(event.getSource())).getTranslateY();
            }
            
        });
        
        canvas.addEventFilter(MouseEvent.MOUSE_DRAGGED, event -> {

            if(event.getPickResult().getIntersectedNode().equals(canvas) && !nodeDragged){
                double offsetX = event.getSceneX() - orgSceneX1;
                double offsetY = event.getSceneY() - orgSceneY1;
                Controller.newTranslateX1 = orgTranslateX1 + offsetX;
                Controller.newTranslateY1 = orgTranslateY1 + offsetY;

                ((StackPane)(event.getSource())).setTranslateX(newTranslateX1);
                ((StackPane)(event.getSource())).setTranslateY(newTranslateY1); 
            }
            
            
        });

        
        pane.addEventFilter(MouseEvent.MOUSE_PRESSED, evt -> 
        {
            if(node!=null)
            {
               if(evt.getPickResult().getIntersectedNode().equals(pane))
                {
                    if(data.isItemSelected())   
                        ((Node)data.getSelectedItem().getObject()).setEffect(null);
                    if(data!=null)
                        data.clearSelected();
                    app.getFoolproofModule().updateAll();
                } 
            }
            
            
        });
//        
//        
//        pane.addEventFilter(MouseEvent.MOUSE_PRESSED, evt -> 
//        {
//            if(node!=null)
//            {
//               if(evt.getPickResult().getIntersectedNode().equals(pane))
//                {
//                    if(data.isItemSelected())   
//                        ((Node)data.getSelectedItem().getObject()).setEffect(null);
//                    if(data!=null)
//                        data.clearSelected();
//                    app.getFoolproofModule().updateAll();
//                } 
//            }
//            
//            
//        });
    }
    
    public void hightlightNode(Node node){
        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0);
        ds.setOffsetX(3.0);
        ds.setColor(Color.GRAY);
        node.setEffect(ds);

        node.requestFocus();
        node.setVisible(true);
    }
    
    
    
    private static void resetLocation(){
        orgSceneX = 0;
        orgSceneY = 0;
        orgTranslateX = 0;
        orgTranslateY = 0;
        newTranslateX = 0;
        newTranslateY = 0;
        
    }
    private static void resetLocation1(){
        orgSceneX1 = 0;
        orgSceneY1 = 0;
        orgTranslateX1 = 0;
        orgTranslateY1 = 0;
        newTranslateX1 = 0;
        newTranslateY1 = 0;
        
    }
    private static void resetBorder(){
        oriThick = -1;
        newThick = -1;
        
    }
    private static void resetColor(){
        oriR = null;
        newR = null;
        
    }
    
    public static boolean inHierarchy(Node node, Node potentialHierarchyElement) {
        if (potentialHierarchyElement == null) {
            return true;
        }
        while (node != null) {
            if (node == potentialHierarchyElement) {
                return true;
            }
            node = node.getParent();
        }
        return false;
    }
    
    
    
    
    
}
