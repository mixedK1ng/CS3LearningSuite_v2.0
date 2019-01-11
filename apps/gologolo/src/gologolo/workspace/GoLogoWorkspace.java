/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.workspace;

import static djf.AppPropertyType.COPY_BUTTON;
import static djf.AppPropertyType.CUT_BUTTON;
import static djf.AppPropertyType.LANGUAGE_OPTIONS;
import static djf.AppPropertyType.PASTE_BUTTON;
import djf.AppTemplate;
import djf.components.AppWorkspaceComponent;
import djf.modules.AppFoolproofModule;
import djf.modules.AppGUIModule;
import static djf.modules.AppGUIModule.DISABLED;
import static djf.modules.AppGUIModule.ENABLED;
import static djf.modules.AppGUIModule.FOCUS_TRAVERSABLE;
import static djf.modules.AppGUIModule.HAS_KEY_HANDLER;
import djf.ui.AppNodesBuilder;
import static djf.ui.style.DJFStyle.CLASS_DJF_ICON_BUTTON;
import static djf.ui.style.DJFStyle.CLASS_DJF_TOOLBAR_PANE;
import static gologolo.GoLogoPropertyType.COLOR_OPTIONS;
import static gologolo.GoLogoPropertyType.DEFAULT_COLOR;
import static gologolo.GoLogoPropertyType.DEFAULT_FONT;
import static gologolo.GoLogoPropertyType.DEFAULT_FONTSIZE;
import static gologolo.GoLogoPropertyType.FONTSIZE_OPTIONS;
import static gologolo.GoLogoPropertyType.FONT_OPTIONS;
import static gologolo.GoLogoPropertyType.GL_ADDCIR_BUTTON;
import static gologolo.GoLogoPropertyType.GL_ADDIMAGE_BUTTON;
import static gologolo.GoLogoPropertyType.GL_ADDREC_BUTTON;
import static gologolo.GoLogoPropertyType.GL_ADDTEXT_BUTTON;
import static gologolo.GoLogoPropertyType.GL_ADDTRI_BUTTON;
import static gologolo.GoLogoPropertyType.GL_A_BUTTON;
import static gologolo.GoLogoPropertyType.GL_BORDERCOLOR_BOX;
import static gologolo.GoLogoPropertyType.GL_BORDERCOLOR_LABEL;
import static gologolo.GoLogoPropertyType.GL_BORDERRADIUS_LABEL;
import static gologolo.GoLogoPropertyType.GL_BORDERRADIUS_SLIDER;
import static gologolo.GoLogoPropertyType.GL_BORDERTHICK_LABEL;
import static gologolo.GoLogoPropertyType.GL_BORDERTHICK_SLIDER;
import static gologolo.GoLogoPropertyType.GL_BORDER_TOOLBAR;
import static gologolo.GoLogoPropertyType.GL_B_BUTTON;
import static gologolo.GoLogoPropertyType.GL_CENTERX_LABEL;
import static gologolo.GoLogoPropertyType.GL_CENTERX_SLIDER;
import static gologolo.GoLogoPropertyType.GL_CENTERY_LABEL;
import static gologolo.GoLogoPropertyType.GL_CENTERY_SLIDER;
import static gologolo.GoLogoPropertyType.GL_CHOOSEFONT_BOX;
import static gologolo.GoLogoPropertyType.GL_COLORGRADIENT_LABEL;
import static gologolo.GoLogoPropertyType.GL_COMPONENT_TOOLBAR;
import static gologolo.GoLogoPropertyType.GL_CYCLEMETHOD_BOX;
import static gologolo.GoLogoPropertyType.GL_CYCLEMETHOD_LABEL;
import static gologolo.GoLogoPropertyType.GL_DELETE_BUTTON;
import static gologolo.GoLogoPropertyType.GL_DRAW_PANE;
import static gologolo.GoLogoPropertyType.GL_ENLARGE_BUTTON;
import static gologolo.GoLogoPropertyType.GL_FOCUSANGLE_LABEL;
import static gologolo.GoLogoPropertyType.GL_FOCUSANGLE_SLIDER;
import static gologolo.GoLogoPropertyType.GL_FOCUSDIS_LABEL;
import static gologolo.GoLogoPropertyType.GL_FOCUSDIS_SLIDER;
import static gologolo.GoLogoPropertyType.GL_FONTBOX1_TOOLBAR;
import static gologolo.GoLogoPropertyType.GL_FONTBOX2_TOOLBAR;
import static gologolo.GoLogoPropertyType.GL_FONTSIZE_BOX;
import static gologolo.GoLogoPropertyType.GL_FONT_TOOLBAR;
import static gologolo.GoLogoPropertyType.GL_FOOLPROOF_SETTINGS;
import static gologolo.GoLogoPropertyType.GL_GRADIENT_TOOLBAR;
import static gologolo.GoLogoPropertyType.GL_HOME_BUTTON;
import static gologolo.GoLogoPropertyType.GL_I_BUTTON;
import static gologolo.GoLogoPropertyType.GL_LAYERS_EDIT_BUTTON;
import static gologolo.GoLogoPropertyType.GL_LAYERS_MOVEDOWN_BUTTON;
import static gologolo.GoLogoPropertyType.GL_LAYERS_MOVEUP_BUTTON;
import static gologolo.GoLogoPropertyType.GL_LAYERS_PANE;
import static gologolo.GoLogoPropertyType.GL_LAYES_BUTTONS_PANE;
import static gologolo.GoLogoPropertyType.GL_LAYES_ITEMSTABLE;
import static gologolo.GoLogoPropertyType.GL_NAME_COLUMN;
import static gologolo.GoLogoPropertyType.GL_NAVIGATION_TOOLBAR;
import static gologolo.GoLogoPropertyType.GL_ORDER_COLUMN;
import static gologolo.GoLogoPropertyType.GL_PANE;
import static gologolo.GoLogoPropertyType.GL_RADIUS_LABEL;
import static gologolo.GoLogoPropertyType.GL_RADIUS_SLIDER;
import static gologolo.GoLogoPropertyType.GL_RESIZE_BUTTON;
import static gologolo.GoLogoPropertyType.GL_SHRINK_BUTTON;
import static gologolo.GoLogoPropertyType.GL_SNAP_BUTTON;
import static gologolo.GoLogoPropertyType.GL_SNAP_LABEL;
import static gologolo.GoLogoPropertyType.GL_STOP0_BOX;
import static gologolo.GoLogoPropertyType.GL_STOP0_LABEL;
import static gologolo.GoLogoPropertyType.GL_STOP1_BOX;
import static gologolo.GoLogoPropertyType.GL_STOP1_LABEL;
import static gologolo.GoLogoPropertyType.GL_TEST_COLUMN;
import static gologolo.GoLogoPropertyType.GL_TOOL_PANE;
import static gologolo.GoLogoPropertyType.GL_TYPE_COLUMN;
import static gologolo.GoLogoPropertyType.GL_Tt_BUTTON;
import static gologolo.GoLogoPropertyType.GL_tT_BUTTON;
import static gologolo.GoLogoPropertyType.STOP0_OPTIONS;
import static gologolo.GoLogoPropertyType.STOP1_OPTIONS;
import gologolo.GologoloApp;
import gologolo.data.GoLogoData;
import gologolo.data.GoLogoItemPrototype;
import gologolo.transactions.ChangeBorder_Tran;
import gologolo.transactions.ResizeCircle_Transaction;
import gologolo.workspace.controllers.Controller;
import static gologolo.workspace.controllers.Controller.newR;
import gologolo.workspace.controllers.TableController;
import gologolo.workspace.foolproof.TextComponentFoolproofDesign;
import static gologolo.workspace.style.GLLStyle.CLASS_GLL_BOX;
import static gologolo.workspace.style.GLLStyle.CLASS_GLL_BUTTON;
import static gologolo.workspace.style.GLLStyle.CLASS_GLL_COLUMN;
import static gologolo.workspace.style.GLLStyle.CLASS_GLL_DIALOG_DATE_PICKER;
import static gologolo.workspace.style.GLLStyle.CLASS_GLL_PANE;
import static gologolo.workspace.style.GLLStyle.CLASS_GLL_TABLE;
import static gologolo.workspace.style.GLLStyle.CLASS_GLL_WORKPANE;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.effect.DropShadow;
import static javafx.scene.input.KeyCode.S;
import static javafx.scene.input.KeyCode.T;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;

import javafx.scene.control.TableColumn;

import javafx.scene.control.TableView;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import properties_manager.PropertiesManager;

/**
 *
 * @author yiwang
 */
public class GoLogoWorkspace extends AppWorkspaceComponent{
    
    static boolean hasbeenSelect = false;
    static GoLogoItemPrototype temp;
    Controller controller;
    private double orgSceneX;
    private double orgSceneY;
    private double orgTranslateX;
    private double orgTranslateY;
    private double newTranslateX;
    private double newTranslateY;
    private double oldR;
    private double newR;
    
    
    
    

    public GoLogoWorkspace(AppTemplate initApp) {
        super(initApp);
        
        controller = new Controller((GologoloApp)app);
        initLayout();
        
        initFoolproofDesign();
        
    }

    

    private void initLayout() {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        // THIS WILL BUILD ALL OF OUR JavaFX COMPONENTS FOR US
        AppNodesBuilder tdlBuilder = app.getGUIModule().getNodesBuilder();
        
        //SPLIT THE SCENE TO THREE SPACE
        //HBox goLogoPane = tdlBuilder.buildHBox(GL_PANE,null,null,CLASS_GLL_BOX, HAS_KEY_HANDLER,             FOCUS_TRAVERSABLE,      ENABLED);
        VBox itemsPane = tdlBuilder.buildVBox(GL_LAYERS_PANE,workspace,null,   CLASS_GLL_BOX, HAS_KEY_HANDLER,     FOCUS_TRAVERSABLE,  ENABLED);
        StackPane drawPane = tdlBuilder.buildStackPane(GL_DRAW_PANE,workspace,null,   CLASS_GLL_PANE, HAS_KEY_HANDLER,     FOCUS_TRAVERSABLE,  ENABLED);
        
        
        VBox toolPane = tdlBuilder.buildVBox(GL_TOOL_PANE,workspace,null,   CLASS_GLL_BOX, HAS_KEY_HANDLER,     FOCUS_TRAVERSABLE,  ENABLED);

        StackPane workArea = tdlBuilder.buildStackPane(GL_DRAW_PANE,drawPane,null,   null, HAS_KEY_HANDLER,     FOCUS_TRAVERSABLE,  ENABLED);
        workArea.setMaxSize(1000, 700);
        
        //SET UP FOR 1ST PLACE---LAYERS PANE
        TableView<GoLogoItemPrototype> itemsTable  = tdlBuilder.buildTableView(GL_LAYES_ITEMSTABLE,itemsPane,null,CLASS_GLL_TABLE, HAS_KEY_HANDLER,    FOCUS_TRAVERSABLE,  true);
        TableColumn orderColumn      = tdlBuilder.buildTableColumn(  GL_ORDER_COLUMN,    itemsTable,         CLASS_GLL_COLUMN);
        TableColumn nameColumn   = tdlBuilder.buildTableColumn(  GL_NAME_COLUMN, itemsTable,         CLASS_GLL_COLUMN);
        TableColumn typeColumn   = tdlBuilder.buildTableColumn(  GL_TYPE_COLUMN, itemsTable,         CLASS_GLL_COLUMN);
        //TableColumn testColumn   = tdlBuilder.buildTableColumn(  GL_TEST_COLUMN, itemsTable,         CLASS_GLL_COLUMN);
        
        //itemsTable.setEditable(true);
        orderColumn.setCellValueFactory(     new PropertyValueFactory<String,    String>("order"));
        nameColumn.setCellValueFactory(     new PropertyValueFactory<String,    String>("name"));
        typeColumn.setCellValueFactory(     new PropertyValueFactory<String,    String>("type"));
        addButtonToTable(itemsTable);

        HBox itemButtonsPane = tdlBuilder.buildHBox(GL_LAYES_BUTTONS_PANE,itemsPane,null,CLASS_GLL_BOX, HAS_KEY_HANDLER,FOCUS_TRAVERSABLE,  ENABLED);
        Button upItemButton     = tdlBuilder.buildIconButton(GL_LAYERS_MOVEUP_BUTTON,   itemButtonsPane,    null,   CLASS_GLL_BUTTON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button downItemButton     = tdlBuilder.buildIconButton(GL_LAYERS_MOVEDOWN_BUTTON,   itemButtonsPane,    null,   CLASS_GLL_BUTTON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button editItemButton     = tdlBuilder.buildIconButton(GL_LAYERS_EDIT_BUTTON,   itemButtonsPane,    null,   CLASS_GLL_BUTTON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        
        //SET UP FOR 3RD PLACE----TOOL PANE
        HBox componentToolbar = tdlBuilder.buildHBox(GL_COMPONENT_TOOLBAR,toolPane,null,CLASS_GLL_BOX, HAS_KEY_HANDLER,FOCUS_TRAVERSABLE,  ENABLED);
        VBox fontToolbar = tdlBuilder.buildVBox(GL_FONT_TOOLBAR,toolPane,null,CLASS_GLL_BOX, HAS_KEY_HANDLER,FOCUS_TRAVERSABLE,  ENABLED);
        VBox boredrToolbar = tdlBuilder.buildVBox(GL_BORDER_TOOLBAR,toolPane,null,CLASS_GLL_BOX, HAS_KEY_HANDLER,FOCUS_TRAVERSABLE,  ENABLED);
        VBox gradientToolbar = tdlBuilder.buildVBox(GL_GRADIENT_TOOLBAR,toolPane,null,CLASS_GLL_BOX, HAS_KEY_HANDLER,FOCUS_TRAVERSABLE,  ENABLED);


        workspace = new BorderPane();
        
        //GoLogoData data = new GoLogoData(app);
        //data.constructPane();
	((BorderPane)workspace).setCenter(drawPane);
        ((BorderPane)workspace).setLeft(itemsPane);
        ((BorderPane)workspace).setRight(toolPane);
        
        //SET UP NAVIGATION TOOLBAR AND STYLE
        ToolBar navigationToolbar = new ToolBar();
        app.getGUIModule().getTopToolbarPane().getChildren().add(3, navigationToolbar);
        Button homeButton = app.getGUIModule().getNodesBuilder().buildIconButton(GL_HOME_BUTTON, null, navigationToolbar, CLASS_DJF_ICON_BUTTON, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Button enlargeButton = app.getGUIModule().getNodesBuilder().buildIconButton(GL_ENLARGE_BUTTON, null, navigationToolbar, CLASS_DJF_ICON_BUTTON, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Button shrinkButton = app.getGUIModule().getNodesBuilder().buildIconButton(GL_SHRINK_BUTTON, null, navigationToolbar, CLASS_DJF_ICON_BUTTON, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Button resizeButton = app.getGUIModule().getNodesBuilder().buildIconButton(GL_RESIZE_BUTTON, null, navigationToolbar, CLASS_DJF_ICON_BUTTON, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        CheckBox checkbox = app.getGUIModule().getNodesBuilder().buildCheckBox(GL_SNAP_BUTTON, null, navigationToolbar, null, HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Label snap = app.getGUIModule().getNodesBuilder().buildLabel(GL_SNAP_LABEL,null,navigationToolbar,null,HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);  
        navigationToolbar.getStyleClass().add(CLASS_DJF_TOOLBAR_PANE);
        app.getGUIModule().addGUINode(GL_NAVIGATION_TOOLBAR, navigationToolbar);
        
       //PUT EVERYTHING IN TOOLPANE
        Button addText     = tdlBuilder.buildIconButton(GL_ADDTEXT_BUTTON,   componentToolbar,    null,   CLASS_GLL_BUTTON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button addImage     = tdlBuilder.buildIconButton(GL_ADDIMAGE_BUTTON,   componentToolbar,    null,   CLASS_GLL_BUTTON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button addRec     = tdlBuilder.buildIconButton(GL_ADDREC_BUTTON,   componentToolbar,    null,   CLASS_GLL_BUTTON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button addCir     = tdlBuilder.buildIconButton(GL_ADDCIR_BUTTON,   componentToolbar,    null,   CLASS_GLL_BUTTON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button addTri     = tdlBuilder.buildIconButton(GL_ADDTRI_BUTTON,   componentToolbar,    null,   CLASS_GLL_BUTTON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button delete     = tdlBuilder.buildIconButton(GL_DELETE_BUTTON,   componentToolbar,    null,   CLASS_GLL_BUTTON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        //componentToolbar.getStyleClass().add(CLASS_DJF_TOOLBAR_PANE);
        
        HBox fontbar1 = tdlBuilder.buildHBox(GL_FONTBOX1_TOOLBAR,fontToolbar,null,CLASS_GLL_BOX, HAS_KEY_HANDLER,FOCUS_TRAVERSABLE,  ENABLED);
        HBox fontbar2 = tdlBuilder.buildHBox(GL_FONTBOX2_TOOLBAR,fontToolbar,null,CLASS_GLL_BOX, HAS_KEY_HANDLER,FOCUS_TRAVERSABLE,  ENABLED);
        ComboBox fontType = tdlBuilder.buildComboBox(GL_CHOOSEFONT_BOX,FONT_OPTIONS,DEFAULT_FONT, fontbar1, null,CLASS_GLL_DIALOG_DATE_PICKER, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        ComboBox fontSize = tdlBuilder.buildComboBox(GL_FONTSIZE_BOX,FONTSIZE_OPTIONS,DEFAULT_FONTSIZE, fontbar1, null,CLASS_GLL_DIALOG_DATE_PICKER, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button b     = tdlBuilder.buildIconButton(GL_B_BUTTON,   fontbar2,    null,   CLASS_GLL_BUTTON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button i     = tdlBuilder.buildIconButton(GL_I_BUTTON,   fontbar2,    null,   CLASS_GLL_BUTTON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button Tt     = tdlBuilder.buildIconButton(GL_Tt_BUTTON,   fontbar2,    null,   CLASS_GLL_BUTTON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Button tT     = tdlBuilder.buildIconButton(GL_tT_BUTTON,   fontbar2,    null,   CLASS_GLL_BUTTON, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        ColorPicker A = tdlBuilder.buildColorPicker(GL_A_BUTTON, fontbar2, null,CLASS_GLL_DIALOG_DATE_PICKER, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);


        
        Label borderThick = tdlBuilder.buildLabel(GL_BORDERTHICK_LABEL,boredrToolbar,null,null,HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);  
        Slider borderThickness = tdlBuilder.buildSlider(GL_BORDERTHICK_SLIDER, boredrToolbar,null,null,0,100,HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Label borderColor = tdlBuilder.buildLabel(GL_BORDERCOLOR_LABEL,boredrToolbar,null,null,HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        ColorPicker borderColorBox = tdlBuilder.buildColorPicker(GL_BORDERCOLOR_BOX, boredrToolbar, null,CLASS_GLL_DIALOG_DATE_PICKER, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Label borderRadius = tdlBuilder.buildLabel(GL_BORDERRADIUS_LABEL,boredrToolbar,null,null,HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);  
        Slider borderRadiusSlider = tdlBuilder.buildSlider(GL_BORDERRADIUS_SLIDER, boredrToolbar,null,null,0,100,HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        
        Label colorGradient = tdlBuilder.buildLabel(GL_COLORGRADIENT_LABEL,gradientToolbar,null,null,HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);  
        Label focusAngle = tdlBuilder.buildLabel(GL_FOCUSANGLE_LABEL,gradientToolbar,null,null,HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);  
        Slider focusAngleSlider = tdlBuilder.buildSlider(GL_FOCUSANGLE_SLIDER, gradientToolbar,null,null,0,100,HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Label focusDistance = tdlBuilder.buildLabel(GL_FOCUSDIS_LABEL,gradientToolbar,null,null,HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);  
        Slider focusDistanceSlider = tdlBuilder.buildSlider(GL_FOCUSDIS_SLIDER, gradientToolbar,null,null,0,100,HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Label centerX = tdlBuilder.buildLabel(GL_CENTERX_LABEL,gradientToolbar,null,null,HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);  
        Slider centerXSlider = tdlBuilder.buildSlider(GL_CENTERX_SLIDER, gradientToolbar,null,null,0,100,HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Label centerY = tdlBuilder.buildLabel(GL_CENTERY_LABEL,gradientToolbar,null,null,HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);  
        Slider centerYSlider = tdlBuilder.buildSlider(GL_CENTERY_SLIDER, gradientToolbar,null,null,0,100,HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        Label radius = tdlBuilder.buildLabel(GL_RADIUS_LABEL,gradientToolbar,null,null,HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);  
        Slider radiusSlider = tdlBuilder.buildSlider(GL_RADIUS_SLIDER, gradientToolbar,null,null,0,100,HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        
        Label cycleMethod = tdlBuilder.buildLabel(GL_CYCLEMETHOD_LABEL,gradientToolbar,null,null,HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        ComboBox cycleMethodBox = tdlBuilder.buildComboBox(GL_CYCLEMETHOD_BOX,COLOR_OPTIONS,DEFAULT_COLOR, gradientToolbar, null,CLASS_GLL_DIALOG_DATE_PICKER, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Label stop0 = tdlBuilder.buildLabel(GL_STOP0_LABEL,gradientToolbar,null,null,HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        ColorPicker stop0Box = tdlBuilder.buildColorPicker(GL_STOP0_BOX, gradientToolbar, null,CLASS_GLL_DIALOG_DATE_PICKER, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);
        Label stop1 = tdlBuilder.buildLabel(GL_STOP1_LABEL,gradientToolbar,null,null,HAS_KEY_HANDLER, FOCUS_TRAVERSABLE, ENABLED);
        ColorPicker stop1Box = tdlBuilder.buildColorPicker(GL_STOP1_BOX, gradientToolbar, null,CLASS_GLL_DIALOG_DATE_PICKER, HAS_KEY_HANDLER,   FOCUS_TRAVERSABLE,  ENABLED);

        colorGradient.setFont(Font.font(22));
        borderThick.setFont(Font.font(18));
        borderColor.setFont(Font.font(18));
        borderRadius.setFont(Font.font(18));
        
        
        //enable Color gradient
        forColorGradient(focusAngleSlider);
        forColorGradient(focusDistanceSlider);
        forColorGradient(centerXSlider);
        forColorGradient(centerYSlider);
        forColorGradient(radiusSlider);
        forColorGradient(cycleMethodBox);
        forColorGradient(stop0Box);
        forColorGradient(stop1Box);
           
        
        
        
        
        
        
        
        
        //Border thickness
        borderThickness.setOnMousePressed(e->{
            controller.setInitBorderThick(borderThickness.getValue());
        });
        
        borderThickness.setOnMouseDragged(e->{
            
            if(controller.getData().getSelectedItem().getType().equals("Rectangle"))
            {
                Rectangle r = (Rectangle)controller.getData().getSelectedItem().getObject();
                r.setStrokeWidth(borderThickness.getValue());
            }
            else if (controller.getData().getSelectedItem().getType().equals("Circle"))
            {
                Circle cir = (Circle)controller.getData().getSelectedItem().getObject();
                cir.setStrokeWidth(borderThickness.getValue());
            }
            
        }); 
        borderThickness.setOnMouseReleased(e->{
            
            Node node = (Node)controller.getData().getSelectedItem().getObject();
            controller.setFinalBorderThick(borderThickness.getValue());
            if(Controller.oriThick!=Controller.newThick)
            {
                controller.changeBorderThick(node);
            }

        });
        

        
        
        //Border radius
        borderRadiusSlider.setOnMousePressed(e->{
            controller.setInitBorderThick(borderRadiusSlider.getValue());
        });
        
        borderRadiusSlider.setOnMouseDragged(e->{
           
            Rectangle r = (Rectangle)controller.getData().getSelectedItem().getObject();
            r.setArcHeight(borderRadiusSlider.getValue());
            r.setArcWidth(borderRadiusSlider.getValue());
        }); 
        borderRadiusSlider.setOnMouseReleased(e->{
            
            Rectangle r = (Rectangle)controller.getData().getSelectedItem().getObject();
            controller.setFinalBorderThick(borderRadiusSlider.getValue());
            if(Controller.oriThick!=Controller.newThick)
            {
                controller.changeBorderRadius(r);
            }

        });
        
        //border color
        borderColorBox.setOnAction(e->{
            
            if(controller.getData().getSelectedItem().getType().equals("Rectangle"))
            {
                Rectangle r = (Rectangle)controller.getData().getSelectedItem().getObject();
                controller.changeBorderColor(borderColorBox.getValue(),r);
            }
            else if (controller.getData().getSelectedItem().getType().equals("Circle"))
            {
                Circle cir = (Circle)controller.getData().getSelectedItem().getObject();
                controller.changeBorderColor(borderColorBox.getValue(),cir);
            }
            
            

        });
        
        //font color
        A.setOnAction(e->{
            
            Text t = (Text)controller.getData().getSelectedItem().getObject();
            controller.changeTextColor(A.getValue(),t);

        });
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        //------for fonts
        
        fontType.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue newValue, String oldS, String newS) {
                
                if(Controller.isnotLoad)
                {
                    System.out.println();
                    Text text = (Text)controller.getData().getSelectedItem().getObject();
                    System.out.println(Controller.isnotLoad+"fonttype:"+text.toString());
                    controller.processChangeText(text,newS,text.getFont().getSize(),text.getFont().getStyle());
                }
                else{
                                    
                    System.out.println("ov:"+newValue);
                    System.out.println("oldS:"+oldS);
                    System.out.println("newS:"+newS); 
                }


   

            }
         });
        
        fontType.setOnMouseClicked(e->{
            Controller.isnotLoad = true;
        });  

        fontSize.setOnMouseClicked(e->{
            Controller.isnotLoad = true;
        });  
        
        
        fontSize.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue newValue, String oldS, String newS) {
                
                if(Controller.isnotLoad)
                {
                    Text text = (Text)controller.getData().getSelectedItem().getObject();
                    System.out.println("fontsize:"+text.toString());
                    controller.processChangeText(text,text.getFont().getFamily(),Double.parseDouble(newS),text.getFont().getStyle());
                }
                else{
                    System.out.println("ov:"+newValue);
                    System.out.println("oldS:"+oldS);
                    System.out.println("newS:"+newS);
                }
            }
         });
        
        
        b.setOnAction(e->{
            
            
            Text text = (Text)controller.getData().getSelectedItem().getObject();

            String style="";
            if(controller.isBold(text)&&controller.isItalic(text))
                style = "Normal Italic";
            else if(controller.isBold(text)&&(!controller.isItalic(text)))
                style = "Normal Regular";
            else if((!controller.isBold(text))&&controller.isItalic(text))
                style = "Bold Italic";
            else if((!controller.isBold(text))&&(!controller.isItalic(text)))
                style = "Bold Regular";
            else
                style = "Bold";

            controller.processChangeText(text,text.getFont().getFamily(),text.getFont().getSize(),style);  
        });
        
        i.setOnAction(e->{

            Text text = (Text)controller.getData().getSelectedItem().getObject();
            String style="";
            if(controller.isBold(text)&&controller.isItalic(text))
                style = "Bold Regular";
            else if(controller.isBold(text)&&(!controller.isItalic(text)))
                style = "Bold Italic";
            else if((!controller.isBold(text))&&controller.isItalic(text))
                style = "Normal Regular";
            else if((!controller.isBold(text))&&(!controller.isItalic(text)))
                style = "Normal Italic";
            else
                style = "Italic";
            controller.processChangeText(text,text.getFont().getFamily(),text.getFont().getSize(),style);
        });
        
        tT.setOnAction(e->{

            Text text = (Text)controller.getData().getSelectedItem().getObject();
            double size = text.getFont().getSize();

            controller.processChangeText(text,text.getFont().getFamily(),size+2,text.getFont().getStyle());     
        });
        
        Tt.setOnAction(e->{ 
            Text text = (Text)controller.getData().getSelectedItem().getObject();
            double size = text.getFont().getSize();
            
            if(size>2)
                controller.processChangeText(text,text.getFont().getFamily(),size-2,text.getFont().getStyle());
            else
                app.getFoolproofModule().updateAll();
                
            
        });
        

        
        
        
        
        
        
        
        
        
        TableController tableController = new TableController((GologoloApp)app);
        
        itemsTable.widthProperty().addListener(e->{
            tableController.processChangeTableSize();
        });
        
        

        
        itemsTable.setOnMouseClicked(e-> {
            GoLogoData data = (GoLogoData)app.getDataComponent();

            if(data.isItemSelected())
            {
                data.clearAllhighlight();
                DropShadow ds = new DropShadow();
                ds.setOffsetY(3.0);
                ds.setOffsetX(3.0);
                ds.setColor(Color.GRAY);
                ((Node)data.getSelectedItem().getObject()).setEffect(ds);
                Controller.isnotLoad = false;
                app.getFoolproofModule().updateAll();
            }

        });

        controller.setPaneActionDragScroll(drawPane,workArea,null);
        
        drawPane.setOnScrollStarted(e->{
            
           if(controller.isCircle()) 
           {
               System.out.println("setOnScrollStarted:"+newR);
               oldR = controller.getCircle().getRadius();
               System.out.println("oldR:"+oldR);
           }
           
            
        });
        
        
        drawPane.setOnScroll(e->{
            
           if(controller.isCircle()) 
           {
               System.out.println("setOnScroll:"+newR);

               //scroll down 
                if(e.getDeltaY()>0)
                {
                    System.out.println(e.getDeltaY());
                    controller.shrinkCircle();
                }

                //scroll up 
                else if(e.getDeltaY()<0)
                {
                    controller.enlargeCircle();
                }
           }
           else{
               //scroll down 
                if(e.getDeltaY()>0)
                {
                    System.out.println(e.getDeltaY());
                    controller.processEnlarge();
                }

                //scroll up 
                else if(e.getDeltaY()<0)
                {
                    controller.processShrink();
                }
           } 
        });
        
        drawPane.setOnScrollFinished(e->{
            
           if(controller.isCircle()) 
           {
                newR = controller.getCircle().getRadius();
                System.out.println("setOnScrollFinished:"+newR);
                
                ResizeCircle_Transaction t = new ResizeCircle_Transaction(controller.getApp(),(GoLogoData)controller.getApp().getDataComponent(),oldR,newR);
                app.processTransaction(t);
                app.getFileModule().markAsEdited(true);
           }
           
            
        });
        
        
        
        System.out.println("tranX:"+workArea.getTranslateX());
        System.out.println("tranY:"+workArea.getTranslateY());     
                
        
        homeButton.setOnAction(e->{
            
            controller.resetView();
            
        });
        
        enlargeButton.setOnAction(e->{
            controller.processEnlarge();
        });
        
        shrinkButton.setOnAction(e->{
            controller.processShrink();
        });
        
        

        
                
        upItemButton.setOnAction(e->{
            controller.processMoveUp();
        });   
        
        downItemButton.setOnAction(e->{
            controller.processMoveDown();
        }); 
        
        editItemButton.setOnAction(e->{
            controller.processRename();
        }); 
        
        
        resizeButton.setOnAction(e->{
            controller.processChangeDimension();
        });
        
        
        
        
        //add things!------------------------------------------------
        addText.setOnAction(e->{
            controller.processAddText();
        });
        
        addRec.setOnAction(e->{
            controller.processAddRectangle();
        });
        
        addCir.setOnAction(e->{
            controller.processAddCircle();
        });
        
        addImage.setOnAction(e->{
            controller.processAddImage();
        });
        
        
        
        
        
        
        delete.setOnAction(e->{
            controller.processDelete();
        });
        Rectangle recClip1 = new Rectangle(drawPane.getPrefWidth(),drawPane.getPrefHeight());
        recClip1.widthProperty().bind(drawPane.widthProperty());
        recClip1.heightProperty().bind(drawPane.heightProperty());
        drawPane.setClip(recClip1);
        
        Rectangle recClip = new Rectangle(workArea.getMaxWidth(),workArea.getMaxHeight());
        workArea.setClip(recClip);
        
        workArea.setBackground(new Background(new BackgroundFill(RadialGradient.valueOf("radial-gradient(focus-angle 180.0deg, focus-distance 50.0% , center 50.0% 50.0%, radius 50.0%, reflect, 0xffffffff 0.0%, 0xffffffff 100.0%)"), CornerRadii.EMPTY, Insets.EMPTY)));
                        System.out.println("最初"+workArea.getBackground().getFills().get(0).getFill().toString());
                System.out.println("final:"+RadialGradient.valueOf(workArea.getBackground().getFills().get(0).getFill().toString()).toString());
    }
    
    public Controller getController() {
        return controller;
    }

    public void initFoolproofDesign() {
        AppGUIModule gui = app.getGUIModule();
        AppFoolproofModule foolproofSettings = app.getFoolproofModule();
        foolproofSettings.registerModeSettings(GL_FOOLPROOF_SETTINGS, 
                new TextComponentFoolproofDesign((GologoloApp)app));
    }
    
    private RadialGradient getRadial() {
        AppGUIModule gui = app.getGUIModule();
        
        double angle = ((Slider)gui.getGUINode(GL_FOCUSANGLE_SLIDER)).getValue()*3.6;
        double distance = ((Slider)gui.getGUINode(GL_FOCUSDIS_SLIDER)).getValue()*0.01;
        double centerx = ((Slider)gui.getGUINode(GL_CENTERX_SLIDER)).getValue()*0.01;
        double centery = ((Slider)gui.getGUINode(GL_CENTERY_SLIDER)).getValue()*0.01;
        double radius = ((Slider)gui.getGUINode(GL_RADIUS_SLIDER)).getValue()*0.01;
        boolean proportional = true;
        CycleMethod cm = CycleMethod.valueOf(((ComboBox)gui.getGUINode(GL_CYCLEMETHOD_BOX)).getValue().toString());
        Stop s0 = new Stop(0,((ColorPicker)gui.getGUINode(GL_STOP0_BOX)).getValue());
        Stop s1 = new Stop(1,((ColorPicker)gui.getGUINode(GL_STOP1_BOX)).getValue());
        ArrayList<Stop> stops = new ArrayList<>();
        stops.add(s0);
        stops.add(s1);
        RadialGradient r = new RadialGradient(angle,distance,centerx,centery,radius,proportional,cm,stops);
        return r;
    }
    
     private void addButtonToTable(TableView table) {
        TableColumn<String, Void> colBtn = new TableColumn("Button");

        Callback<TableColumn<String, Void>, TableCell<String, Void>> cellFactory = new Callback<TableColumn<String, Void>, TableCell<String, Void>>() {
            @Override
            public TableCell<String, Void> call(final TableColumn<String, Void> param) {
                final TableCell<String, Void> cell = new TableCell<String, Void>() {

                    private final Button btn = new Button("Action");



                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        table.getColumns().add(colBtn);

    }
    
    private void resetDrag() {
        orgSceneX=0;
        orgSceneY=0;
        orgTranslateX=0;
        orgTranslateY=0;
        newTranslateX=0;
        newTranslateY=0;
    }
    private void forColorGradient(ComboBox box) {


        box.setOnMousePressed(e->{
            if(!controller.getData().isItemSelected())
            {
                StackPane pane = controller.getData().getWorkPane();
                //pane.setBackground(new Background(new BackgroundFill(getRadial(), CornerRadii.EMPTY, Insets.EMPTY)));
                controller.saveOriColor(RadialGradient.valueOf(pane.getBackground().getFills().get(0).getFill().toString()));
            }
            
            else if(controller.getData().getSelectedItem().getType().equals("Rectangle"))
            {
                Rectangle r = (Rectangle)controller.getData().getSelectedItem().getObject();
                controller.saveOriColor(RadialGradient.valueOf(r.getFill().toString()));
            }
            else if (controller.getData().getSelectedItem().getType().equals("Circle"))
            {
                Circle cir = (Circle)controller.getData().getSelectedItem().getObject();
                controller.saveOriColor(RadialGradient.valueOf(cir.getFill().toString()));
            }
        });
        
        box.setOnAction(e->{
            
            controller.saveNewColor(getRadial());
            if(!Controller.oriR.equals(Controller.newR))
            {
                controller.changeColorGradient();
            }

        });
        
        

    }
    
    private void forColorGradient(ColorPicker pick) {
        pick.setOnMousePressed(e->{
            if(!controller.getData().isItemSelected())
            {
                StackPane pane = controller.getData().getWorkPane();
                //System.out.println("beforeAAAA"+pane.getBackground().getFills().get(0).getFill().toString());
                //pane.setBackground(new Background(new BackgroundFill(getRadial(), CornerRadii.EMPTY, Insets.EMPTY)));
                //System.out.println("afterBBBBB"+pane.getBackground().getFills().get(0).getFill().toString());
                controller.saveOriColor(RadialGradient.valueOf(pane.getBackground().getFills().get(0).getFill().toString()));
            }
            
            else if(controller.getData().getSelectedItem().getType().equals("Rectangle"))
            {
                Rectangle r = (Rectangle)controller.getData().getSelectedItem().getObject();
                controller.saveOriColor(RadialGradient.valueOf(r.getFill().toString()));
            }
            else if (controller.getData().getSelectedItem().getType().equals("Circle"))
            {
                Circle cir = (Circle)controller.getData().getSelectedItem().getObject();
                controller.saveOriColor(RadialGradient.valueOf(cir.getFill().toString()));
            }
        });
        
        pick.setOnAction(e->{
            
            controller.saveNewColor(getRadial());
            if(!Controller.oriR.equals(Controller.newR))
            {
                controller.changeColorGradient();
            }

        });

    }
    
    
    private void forColorGradient(Slider slider) {
        
                //Color Gradient
        
        slider.setOnMousePressed(e->{
            
            //System.out.println("yessssssssssssss");
            
            if(!controller.getData().isItemSelected())
            {
                StackPane pane = controller.getData().getWorkPane();
                //pane.setBackground(new Background(new BackgroundFill(getRadial(), CornerRadii.EMPTY, Insets.EMPTY)));
                

                controller.saveOriColor(RadialGradient.valueOf(pane.getBackground().getFills().get(0).getFill().toString()));
            }
            
            else if(controller.getData().getSelectedItem().getType().equals("Rectangle"))
            {
                Rectangle r = (Rectangle)controller.getData().getSelectedItem().getObject();
                controller.saveOriColor(RadialGradient.valueOf(r.getFill().toString()));
            }
            else if (controller.getData().getSelectedItem().getType().equals("Circle"))
            {
                Circle cir = (Circle)controller.getData().getSelectedItem().getObject();
                controller.saveOriColor(RadialGradient.valueOf(cir.getFill().toString()));
            }
        });
        
        slider.setOnMouseDragged(e->{
            
            if(!controller.getData().isItemSelected())
            {
                StackPane pane = controller.getData().getWorkPane();
                //pane.setBackground(new Background(new BackgroundFill(getRadial(), CornerRadii.EMPTY, Insets.EMPTY)));
            }
            
            else if(controller.getData().getSelectedItem().getType().equals("Rectangle"))
            {
                Rectangle r = (Rectangle)controller.getData().getSelectedItem().getObject();
                r.setFill(getRadial());
            }
            else if (controller.getData().getSelectedItem().getType().equals("Circle"))
            {
                Circle cir = (Circle)controller.getData().getSelectedItem().getObject();
                cir.setFill(getRadial());
            }
            
        }); 
        slider.setOnMouseReleased(e->{
            
            controller.saveNewColor(getRadial());
            if(!Controller.oriR.equals(Controller.newR))
            {
                controller.changeColorGradient();
            }

        });
        
        
        
        
    }
    
    @Override
    public void processWorkspaceKeyEvent(KeyEvent ke) {
         //To change body of generated methods, choose Tools | Templates.
    }


}
