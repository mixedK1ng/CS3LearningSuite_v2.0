/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.workspace.foolproof;

import static djf.AppPropertyType.EXPORT_BUTTON;
import djf.modules.AppFileModule;
import djf.modules.AppGUIModule;
import djf.ui.foolproof.FoolproofDesign;
import static gologolo.GoLogoPropertyType.GL_A_BUTTON;
import static gologolo.GoLogoPropertyType.GL_BORDERCOLOR_BOX;
import static gologolo.GoLogoPropertyType.GL_BORDERRADIUS_SLIDER;
import static gologolo.GoLogoPropertyType.GL_BORDERTHICK_SLIDER;
import static gologolo.GoLogoPropertyType.GL_B_BUTTON;
import static gologolo.GoLogoPropertyType.GL_CENTERX_SLIDER;
import static gologolo.GoLogoPropertyType.GL_CENTERY_SLIDER;
import static gologolo.GoLogoPropertyType.GL_CHOOSEFONT_BOX;
import static gologolo.GoLogoPropertyType.GL_CYCLEMETHOD_BOX;
import static gologolo.GoLogoPropertyType.GL_DELETE_BUTTON;
import static gologolo.GoLogoPropertyType.GL_FOCUSANGLE_SLIDER;
import static gologolo.GoLogoPropertyType.GL_FOCUSDIS_SLIDER;
import static gologolo.GoLogoPropertyType.GL_FONTSIZE_BOX;
import static gologolo.GoLogoPropertyType.GL_I_BUTTON;
import static gologolo.GoLogoPropertyType.GL_RADIUS_SLIDER;
import static gologolo.GoLogoPropertyType.GL_STOP0_BOX;
import static gologolo.GoLogoPropertyType.GL_STOP1_BOX;
import static gologolo.GoLogoPropertyType.GL_Tt_BUTTON;
import static gologolo.GoLogoPropertyType.GL_tT_BUTTON;
import gologolo.GologoloApp;
import gologolo.data.GoLogoData;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.paint.RadialGradient;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 *
 * @author yiwang
 */

public class TextComponentFoolproofDesign implements FoolproofDesign {
    GologoloApp app;
    
    public TextComponentFoolproofDesign(GologoloApp initApp) {
        app = initApp;
    }

    @Override
    public void updateControls() {
        AppGUIModule gui = app.getGUIModule();
       
        // CHECK AND SEE IF A TABLE ITEM IS SELECTED
        GoLogoData data = (GoLogoData)app.getDataComponent();
        boolean itemIsSelected = data.isItemSelected();
        
        
        if(!itemIsSelected)
        {
            disableAll();
            enableColor();
            RadialGradient p;
            if(!data.isnotBlank()){
                p = ((RadialGradient)data.getWorkPane().getBackground().getFills().get(0).getFill());
            
                ((Slider)gui.getGUINode(GL_FOCUSANGLE_SLIDER)).setValue(((RadialGradient)p).getFocusAngle()*0.28);
                ((Slider)gui.getGUINode(GL_FOCUSDIS_SLIDER)).setValue(((RadialGradient)p).getFocusDistance()*100);
                ((Slider)gui.getGUINode(GL_CENTERX_SLIDER)).setValue(((RadialGradient)p).getCenterX()*100);
                ((Slider) gui.getGUINode(GL_CENTERY_SLIDER)).setValue(((RadialGradient)p).getCenterY()*100);
                ((Slider)gui.getGUINode(GL_RADIUS_SLIDER)).setValue(((RadialGradient)p).getRadius()*100);
                ((ComboBox)gui.getGUINode(GL_CYCLEMETHOD_BOX)).setValue(((RadialGradient)p).getCycleMethod());
                ((ColorPicker)gui.getGUINode(GL_STOP0_BOX)).setValue(((RadialGradient)p).getStops().get(0).getColor());
                ((ColorPicker)gui.getGUINode(GL_STOP1_BOX)).setValue(((RadialGradient)p).getStops().get(1).getColor());
            }

            
            
        }
        
        if(itemIsSelected&&(data.getSelectedItem().getObject() instanceof Text))
        {
            disableAll();
            
            gui.getGUINode(GL_DELETE_BUTTON).setDisable(false);
            gui.getGUINode(GL_CHOOSEFONT_BOX).setDisable(false);
            gui.getGUINode(GL_FONTSIZE_BOX).setDisable(false);
            gui.getGUINode(GL_B_BUTTON).setDisable(false);
            gui.getGUINode(GL_I_BUTTON).setDisable(false);
            gui.getGUINode(GL_Tt_BUTTON).setDisable(false);
            gui.getGUINode(GL_tT_BUTTON).setDisable(false);
            gui.getGUINode(GL_A_BUTTON).setDisable(false);
            ((ComboBox)gui.getGUINode(GL_CHOOSEFONT_BOX)).setValue(((Text)data.getSelectedItem().getObject()).getFont().getFamily());
            ((ComboBox)gui.getGUINode(GL_FONTSIZE_BOX)).setValue(Double.toString(((Text)data.getSelectedItem().getObject()).getFont().getSize()));
            ((ColorPicker)gui.getGUINode(GL_A_BUTTON)).setValue((Color)Paint.valueOf(((Text)data.getSelectedItem().getObject()).getFill().toString()));
            
            
            if(((Text)data.getSelectedItem().getObject()).getFont().getSize()<=2)
                gui.getGUINode(GL_Tt_BUTTON).setDisable(true);
            
            //gui.getGUINode(GL_B_BUTTON).
        }
        
        else if(itemIsSelected&&(data.getSelectedItem().getObject() instanceof Rectangle))
        {
            Rectangle r = (Rectangle)data.getSelectedItem().getObject();
            
            disableAll();
            enableColor();
            gui.getGUINode(GL_DELETE_BUTTON).setDisable(false);

            gui.getGUINode(GL_BORDERTHICK_SLIDER).setDisable(false);
            gui.getGUINode(GL_BORDERCOLOR_BOX).setDisable(false);
            gui.getGUINode(GL_BORDERRADIUS_SLIDER).setDisable(false);
            
            
            ((Slider)gui.getGUINode(GL_BORDERTHICK_SLIDER)).setValue(((Rectangle)data.getSelectedItem().getObject()).getStrokeWidth());
            ((Slider)gui.getGUINode(GL_BORDERRADIUS_SLIDER)).setValue(((Rectangle)data.getSelectedItem().getObject()).getArcHeight());
            ((ColorPicker)gui.getGUINode(GL_BORDERCOLOR_BOX)).setValue((Color)Paint.valueOf(((Rectangle)data.getSelectedItem().getObject()).getStroke().toString()));
            
            RadialGradient p;
            if(!data.isnotBlank()){
                p = ((RadialGradient)r.getFill());
            
                ((Slider)gui.getGUINode(GL_FOCUSANGLE_SLIDER)).setValue(((RadialGradient)p).getFocusAngle()*0.28);
                ((Slider)gui.getGUINode(GL_FOCUSDIS_SLIDER)).setValue(((RadialGradient)p).getFocusDistance()*100);
                ((Slider)gui.getGUINode(GL_CENTERX_SLIDER)).setValue(((RadialGradient)p).getCenterX()*100);
                ((Slider) gui.getGUINode(GL_CENTERY_SLIDER)).setValue(((RadialGradient)p).getCenterY()*100);
                ((Slider)gui.getGUINode(GL_RADIUS_SLIDER)).setValue(((RadialGradient)p).getRadius()*100);
                ((ComboBox)gui.getGUINode(GL_CYCLEMETHOD_BOX)).setValue(((RadialGradient)p).getCycleMethod());
                ((ColorPicker)gui.getGUINode(GL_STOP0_BOX)).setValue(((RadialGradient)p).getStops().get(0).getColor());
                ((ColorPicker)gui.getGUINode(GL_STOP1_BOX)).setValue(((RadialGradient)p).getStops().get(1).getColor());
            }
            
            

        }
        
        else if(itemIsSelected&&(data.getSelectedItem().getObject() instanceof Circle))
        {
            Circle r = (Circle)data.getSelectedItem().getObject();
            
            disableAll();
            
            //then enable
            
            gui.getGUINode(GL_DELETE_BUTTON).setDisable(false);

            gui.getGUINode(GL_BORDERTHICK_SLIDER).setDisable(false);
            gui.getGUINode(GL_BORDERCOLOR_BOX).setDisable(false);
            
            
            ((Slider)gui.getGUINode(GL_BORDERTHICK_SLIDER)).setValue(((Circle)data.getSelectedItem().getObject()).getStrokeWidth());
            ((ColorPicker)gui.getGUINode(GL_BORDERCOLOR_BOX)).setValue((Color)Paint.valueOf(((Circle)data.getSelectedItem().getObject()).getStroke().toString()));

            enableColor();
            
            RadialGradient p;
            if(!data.isnotBlank()){
                p = ((RadialGradient)r.getFill());
            
                ((Slider)gui.getGUINode(GL_FOCUSANGLE_SLIDER)).setValue(((RadialGradient)p).getFocusAngle()*0.28);
                ((Slider)gui.getGUINode(GL_FOCUSDIS_SLIDER)).setValue(((RadialGradient)p).getFocusDistance()*100);
                ((Slider)gui.getGUINode(GL_CENTERX_SLIDER)).setValue(((RadialGradient)p).getCenterX()*100);
                ((Slider) gui.getGUINode(GL_CENTERY_SLIDER)).setValue(((RadialGradient)p).getCenterY()*100);
                ((Slider)gui.getGUINode(GL_RADIUS_SLIDER)).setValue(((RadialGradient)p).getRadius()*100);
                ((ComboBox)gui.getGUINode(GL_CYCLEMETHOD_BOX)).setValue(((RadialGradient)p).getCycleMethod());
                ((ColorPicker)gui.getGUINode(GL_STOP0_BOX)).setValue(((RadialGradient)p).getStops().get(0).getColor());
                ((ColorPicker)gui.getGUINode(GL_STOP1_BOX)).setValue(((RadialGradient)p).getStops().get(1).getColor());
            }
            
            
            
            
        }
        
        else if(itemIsSelected&&(data.getSelectedItem().getObject() instanceof ImageView))
        {
            disableAll();
            
            //then enable

            
            
            
            
        }
        
        
        
        
    }
    private void disableAll(){
        AppGUIModule gui = app.getGUIModule();
        
        //first disable
        //delete
        gui.getGUINode(GL_DELETE_BUTTON).setDisable(true);
        
        //for text
        gui.getGUINode(GL_CHOOSEFONT_BOX).setDisable(true);
        gui.getGUINode(GL_FONTSIZE_BOX).setDisable(true);
        gui.getGUINode(GL_B_BUTTON).setDisable(true);
        gui.getGUINode(GL_I_BUTTON).setDisable(true);
        gui.getGUINode(GL_Tt_BUTTON).setDisable(true);
        gui.getGUINode(GL_tT_BUTTON).setDisable(true);
        gui.getGUINode(GL_A_BUTTON).setDisable(true);
        //for rectangle and circle
        gui.getGUINode(GL_BORDERTHICK_SLIDER).setDisable(true);
        gui.getGUINode(GL_BORDERCOLOR_BOX).setDisable(true);
        gui.getGUINode(GL_BORDERRADIUS_SLIDER).setDisable(true);
        
        //for color gradient
        gui.getGUINode(GL_FOCUSANGLE_SLIDER).setDisable(true);
        gui.getGUINode(GL_FOCUSDIS_SLIDER).setDisable(true);
        gui.getGUINode(GL_CENTERX_SLIDER).setDisable(true);
        gui.getGUINode(GL_CENTERY_SLIDER).setDisable(true);
        gui.getGUINode(GL_RADIUS_SLIDER).setDisable(true);
        gui.getGUINode(GL_CYCLEMETHOD_BOX).setDisable(true);
        gui.getGUINode(GL_STOP0_BOX).setDisable(true);
        gui.getGUINode(GL_STOP1_BOX).setDisable(true);
        
        
    }
    
    private void enableColor(){
        AppGUIModule gui = app.getGUIModule();
        

        
        //for color gradient
        gui.getGUINode(GL_FOCUSANGLE_SLIDER).setDisable(false);
        gui.getGUINode(GL_FOCUSDIS_SLIDER).setDisable(false);
        gui.getGUINode(GL_CENTERX_SLIDER).setDisable(false);
        gui.getGUINode(GL_CENTERY_SLIDER).setDisable(false);
        gui.getGUINode(GL_RADIUS_SLIDER).setDisable(false);
        gui.getGUINode(GL_CYCLEMETHOD_BOX).setDisable(false);
        gui.getGUINode(GL_STOP0_BOX).setDisable(false);
        gui.getGUINode(GL_STOP1_BOX).setDisable(false);
        
        
    }
    
    
}
