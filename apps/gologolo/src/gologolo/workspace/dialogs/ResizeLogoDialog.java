/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.workspace.dialogs;

import djf.modules.AppLanguageModule;
import static gologolo.GoLogoPropertyType.GL_DIALOG_CANCEL_BUTTON;
import static gologolo.GoLogoPropertyType.GL_DIALOG_HEADER;
import static gologolo.GoLogoPropertyType.GL_DIALOG_HEADER_TEXT;
import static gologolo.GoLogoPropertyType.GL_DIALOG_HEIGHT_PROMPT;
import static gologolo.GoLogoPropertyType.GL_DIALOG_OK_BUTTON;
import static gologolo.GoLogoPropertyType.GL_DIALOG_WEIGHT_PROMPT;
import gologolo.GologoloApp;
import static gologolo.workspace.style.GLLStyle.CLASS_GLL_DIALOG_BUTTON;
import static gologolo.workspace.style.GLLStyle.CLASS_GLL_DIALOG_GRID;
import static gologolo.workspace.style.GLLStyle.CLASS_GLL_DIALOG_HEADER;
import static gologolo.workspace.style.GLLStyle.CLASS_GLL_DIALOG_PANE;
import static gologolo.workspace.style.GLLStyle.CLASS_GLL_DIALOG_PROMPT;
import static gologolo.workspace.style.GLLStyle.CLASS_GLL_DIALOG_TEXT_FIELD;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;

/**
 *
 * @author yiwang
 */
public class ResizeLogoDialog extends Stage {
    GologoloApp app;
    GridPane gridPane;
    
    Label headerLabel = new Label(); 
    Label heightLabel = new Label();
    Label widthLabel = new Label();
    
    TextField heightTextField = new TextField();
    TextField widthTextField = new TextField();
    
    HBox okCancelPane = new HBox();
    Button okButton = new Button();
    Button cancelButton = new Button();
    
    double orgH,orgW,newH,newW;
    boolean canceled;

    public boolean isCanceled() {
        return canceled;
    }
    
    

    public double getOrgH() {
        return orgH;
    }

    public double getOrgW() {
        return orgW;
    }

    public double getNewH() {
        return newH;
    }

    public double getNewW() {
        return newW;
    }
    
    
    
    
    public TextField getHeightTextField(){
        return heightTextField;
    }
    
    public TextField getWidthTextField(){
        return widthTextField;
    }
    
    public ResizeLogoDialog(GologoloApp initApp) {
        // KEEP THIS FOR WHEN THE WORK IS ENTERED
        app = initApp;

        // EVERYTHING GOES IN HERE
        gridPane = new GridPane();
        gridPane.getStyleClass().add(CLASS_GLL_DIALOG_GRID);
        initDialog();

        // NOW PUT THE GRID IN THE SCENE AND THE SCENE IN THE DIALOG
        Scene scene = new Scene(gridPane);
        this.setScene(scene);
        
        // SETUP THE STYLESHEET
        app.getGUIModule().initStylesheet(this);
        //scene.getStylesheets().add(CLASS_GLL_DIALOG_GRID);                             
        
        // MAKE IT MODAL
        this.initOwner(app.getGUIModule().getWindow());
        this.initModality(Modality.APPLICATION_MODAL);
        
        //initFoolproofDesign();
    }
    
    protected void initGridNode(Node node, Object nodeId, String styleClass, int col, int row, int colSpan, int rowSpan, boolean isLanguageDependent) {
        // GET THE LANGUAGE SETTINGS
        AppLanguageModule languageSettings = app.getLanguageModule();
        
        // TAKE CARE OF THE TEXT
        if (isLanguageDependent) {
            languageSettings.addLabeledControlProperty(nodeId + "_TEXT", ((Labeled)node).textProperty());
            ((Labeled)node).setTooltip(new Tooltip(""));
            languageSettings.addLabeledControlProperty(nodeId + "_TOOLTIP", ((Labeled)node).tooltipProperty().get().textProperty());
        }
        
        // PUT IT IN THE UI
        if (col >= 0)
            gridPane.add(node, col, row, colSpan, rowSpan);

        // SETUP IT'S STYLE SHEET
        node.getStyleClass().add(styleClass);
    }
    
    private void initDialog() {
        // THE NODES ABOVE GO DIRECTLY INSIDE THE GRID
        initGridNode(headerLabel,     GL_DIALOG_HEADER,          CLASS_GLL_DIALOG_HEADER,       0, 0, 3, 1, true);
        initGridNode(heightLabel,     GL_DIALOG_HEIGHT_PROMPT,   CLASS_GLL_DIALOG_PROMPT,       0, 1, 1, 1, true);
        initGridNode(heightTextField, null,                      CLASS_GLL_DIALOG_TEXT_FIELD,   1, 1, 1, 1, false);
        initGridNode(widthLabel,      GL_DIALOG_WEIGHT_PROMPT,   CLASS_GLL_DIALOG_PROMPT,       0, 2, 1, 1, true);
        initGridNode(widthTextField,  null,                      CLASS_GLL_DIALOG_TEXT_FIELD,   1, 2, 1, 1, false);
        initGridNode(okCancelPane,    null,                      CLASS_GLL_DIALOG_PANE,         0, 4, 3, 1, false);

        okButton = new Button();
        okButton.setDisable(false);
        cancelButton = new Button();
        app.getGUIModule().addGUINode(GL_DIALOG_OK_BUTTON, okButton);
        app.getGUIModule().addGUINode(GL_DIALOG_CANCEL_BUTTON, cancelButton);
        okButton.getStyleClass().add(CLASS_GLL_DIALOG_BUTTON);
        cancelButton.getStyleClass().add(CLASS_GLL_DIALOG_BUTTON);
        okCancelPane.getChildren().add(okButton);
        okCancelPane.getChildren().add(cancelButton);
        okCancelPane.setAlignment(Pos.CENTER);
        
//        heightTextField.setPromptText("(necessary)");
//        widthTextField.setPromptText("(necessary)");
        

        AppLanguageModule languageSettings = app.getLanguageModule();
        languageSettings.addLabeledControlProperty(GL_DIALOG_OK_BUTTON + "_TEXT",    okButton.textProperty());
        languageSettings.addLabeledControlProperty(GL_DIALOG_CANCEL_BUTTON + "_TEXT",    cancelButton.textProperty());
       
        // AND SETUP THE EVENT HANDLERS

        
        
        okButton.setOnAction(e->{
            processCompleteWork();
            
        });
        cancelButton.setOnAction(e->{
            canceled = true;
            this.hide();
        });   
       
        
    }
    public void showChangeDialog(StackPane pane) {        
        // USE THE TEXT IN THE HEADER FOR ADD
        canceled = false;
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String headerText = props.getProperty(GL_DIALOG_HEADER_TEXT);
        headerLabel.setText(headerText);
        setTitle(headerText);

        orgH = pane.getMaxHeight();
        orgW = pane.getMaxWidth();
        
        heightTextField.setText(Double.toString(orgH));
        widthTextField.setText(Double.toString(orgW));
        heightTextField.requestFocus();
        heightTextField.selectAll();
        // AND OPEN THE DIALOG
        showAndWait();
    }
    
    private void processCompleteWork()
    {
        newH = Double.parseDouble(heightTextField.getText());
        newW = Double.parseDouble(widthTextField.getText());
        this.hide();
    }
    
    
    
    
    
    
}
