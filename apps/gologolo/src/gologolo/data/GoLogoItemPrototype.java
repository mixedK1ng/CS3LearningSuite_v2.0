/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo.data;

import java.time.LocalDate;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author yiwang
 */
public class GoLogoItemPrototype implements Cloneable{
    public static final Integer DEFAULT_ORDER = -1;
    public static final String DEFAULT_NAME = "?";
    public static final String DEFAULT_TYPE = "?";
    public static final String DEFAULT_OBJECT = "";
    
    final IntegerProperty order;
    final StringProperty name;
    final StringProperty type; 
    final ObjectProperty obj;
    String url=null;

    public String getUrl() {
        return url;
    }
    
    
    public GoLogoItemPrototype() {
        order = new SimpleIntegerProperty(DEFAULT_ORDER);
        name = new SimpleStringProperty(DEFAULT_NAME);
        type = new SimpleStringProperty(DEFAULT_TYPE);
        obj = new SimpleObjectProperty(DEFAULT_OBJECT);
    }
    
    public GoLogoItemPrototype(int initOrder, String initName, String initType,Object object) {
        this();
        order.set(initOrder);
        name.set(initName);
        type.set(initType);
        obj.set(object);
        
        ((Node)obj.get()).setUserData(this);
        
       
        
    }
    
    public GoLogoItemPrototype(int initOrder, String initName, String initType,Object object,String path) {
        this();
        order.set(initOrder);
        name.set(initName);
        type.set(initType);
        obj.set(object);
        url = path;
        ((Node)obj.get()).setUserData(this);
        
       
        
    }
    
    public StringProperty nameProperty(){
        return name;
    }
    
    public int getOrder() {
        return order.get();
    }

    public void setOrder(int value) {
        order.set(value);
    }
    
    public String getName() {
        return name.get();
    }

    public void setName(String value) {
        name.set(value);
    }
    
    public String getType() {
        return type.get();
    }

    public void setType(String value) {
        type.set(value);
    }
    
    public Object getObject() {
        return obj.get();
    }

    public void setObject(Object value) {
        obj.set(value);
    }
    
    public void reset() {
        setOrder(DEFAULT_ORDER);
        setName(DEFAULT_NAME);
        setType(DEFAULT_TYPE);

    }

    public Object clone() {
        if(obj.get() instanceof Rectangle)
        {
            Rectangle o = (Rectangle)obj.get();
            System.out.println(o.toString());
            
            Rectangle r = new Rectangle();
            
            r.setHeight(o.getHeight());
            r.setWidth(o.getWidth());
            r.setArcHeight(o.getArcHeight());
            r.setArcWidth(o.getArcWidth());
            r.setStroke(o.getStroke());
            r.setStrokeWidth(o.getStrokeWidth());
            r.setFill(o.getFill());
            
            
            
            GoLogoItemPrototype newItem= new GoLogoItemPrototype(-1,"rec","Rectangle",r);
            return newItem;
        }
        
        else if(obj.get() instanceof Text)
        {
            Text o = ((Text)obj.get());
            System.out.println(o.toString());
            Text t = new Text(o.getText());
            String fam = o.getFont().getFamily();
            String name = o.getFont().getName();
            
            double size = o.getFont().getSize();
            String style = o.getFont().getStyle();
            
            if(style.contains("Bold")&&style.contains("Italic"))
            {
                t.setFont(Font.font(fam, FontWeight.BOLD, FontPosture.ITALIC, size));
                //System.out.println("1:");
            }
            else if(style.contains("Bold")&&(!style.contains("Italic")))
            {
                t.setFont(Font.font(fam, FontWeight.BOLD, FontPosture.REGULAR, size));
                //System.out.println("2:");
            }
            else if(!(style.contains("Bold"))&&(style.contains("Italic")))
            {
                t.setFont(Font.font(fam, FontWeight.NORMAL, FontPosture.ITALIC, size));
                //System.out.println("3:");
            }

            else{
                t.setFont(Font.font(fam, FontWeight.NORMAL, FontPosture.REGULAR, size));

            }
            
            t.setFill(o.getFill());

            GoLogoItemPrototype newItem= new GoLogoItemPrototype(-1,"text","Text",t);
            return newItem;
        }
        else if(obj.get() instanceof Circle)
        {
            
            
            
            Circle o = ((Circle)obj.get());
            System.out.println(o.toString());
            
            Circle t = new Circle();
            t.setRadius(o.getRadius());
            t.setStrokeWidth(o.getStrokeWidth());
            t.setFill(o.getFill());
            t.setStroke(o.getStroke());
            
            GoLogoItemPrototype newItem= new GoLogoItemPrototype(-1,"cir","Circle",t);
            return newItem;
        }
        
        else if(obj.get() instanceof ImageView)
        {
            
            
            
            ImageView o = ((ImageView)obj.get());
            System.out.println(o.toString());
            
            ImageView t = new ImageView(new Image(url));
            
            
            GoLogoItemPrototype newItem= new GoLogoItemPrototype(-1,"image","Image",t,url);
            return newItem;
        }
        
        
        
        else{
            System.out.println("cut paste wrong");
            return new GoLogoItemPrototype();
        }
        

        
        
        
    }
    
    public boolean equals(Object obj) {
        return this == obj;
    }


}
