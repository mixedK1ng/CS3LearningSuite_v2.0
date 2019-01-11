package gologolo.files;

import static djf.AppPropertyType.APP_EXPORT_PAGE;
import static djf.AppPropertyType.APP_PATH_EXPORT;
import djf.components.AppDataComponent;
import djf.components.AppFileComponent;
import static gologolo.GoLogoPropertyType.GL_EXPORT_TEMPLATE_FILE_NAME;
import gologolo.GologoloApp;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;
import javax.swing.text.html.HTML;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import properties_manager.PropertiesManager;
//import static tdlm.ToDoPropertyType.TDLM_EXPORT_TEMPLATE_FILE_NAME;
import gologolo.data.GoLogoData;
import gologolo.data.GoLogoItemPrototype;
import static gologolo.workspace.style.GLLStyle.CLASS_GLL_WORKPANE;
import java.awt.image.RenderedImage;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.paint.RadialGradient;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextBoundsType;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author McKillaGorilla
 */
public class ToDoFiles implements AppFileComponent {
    // FOR JSON SAVING AND LOADING

    static final String JSON_ORDER = "order";
    static final String JSON_NAME = "name";
    static final String JSON_TYPE = "type";
    
    static final String JSON_NODE = "node";
    static final String JSON_ITEMS = "items";
    
    // FOR EXPORTING TO HTML
    static final String TITLE_TAG = "title";

    static final String TABLE_DATA_TAG = "go_logo_list_table_data";
    
    public JsonObject saveSingleNode(Object node){
        if(node instanceof Text)
        {
            System.out.println("node:\n"+((Text) node).toString()+"\n");
            System.out.println(((Text) node).getFont().toString());
            //System.out.println(((Text) node).getFont().toString());
            JsonObject itemJson = Json.createObjectBuilder()
                    .add("translateX",((Text) node).getTranslateX())
                    .add("translateY",((Text) node).getTranslateY())
                    .add("text", ((Text) node).getText())
                    .add("x", ((Text) node).getX())
                    .add("y", ((Text) node).getY())
                    .add("alignment",((Text) node).getTextAlignment().toString())
                    .add("origin",((Text) node).getTextOrigin().toString())
                    .add("boundsType",((Text) node).getBoundsType().toString())
                    .add("fontSmoothingType",((Text) node).getFontSmoothingType().toString())
                    .add("fill",((Text) node).getFill().toString())
                    .add("fontname",((Text) node).getFont().getName())
                    .add("fontfam",((Text) node).getFont().getFamily())
                    .add("fontstyle",((Text) node).getFont().getStyle())
                    .add("fontsize",((Text) node).getFont().getSize()).build();
            return itemJson;
        }
        else if(node instanceof Rectangle)
        {
            System.out.println("node:\n"+((Rectangle) node).toString()+"\n");
            JsonObject itemJson = Json.createObjectBuilder()
                    .add("translateX",((Rectangle) node).getTranslateX())
                    .add("translateY",((Rectangle) node).getTranslateY())
                    .add("x", ((Rectangle) node).getX())
                    .add("y", ((Rectangle) node).getY())
                    .add("width",((Rectangle) node).getWidth())
                    .add("height",((Rectangle) node).getHeight())
                    .add("fill",((Rectangle) node).getFill().toString())
                    .add("stroke", ((Rectangle) node).getStroke().toString())
                    .add("strokeWidth", ((Rectangle) node).getStrokeWidth()).build();
            return itemJson;
        }
        
        else if(node instanceof Circle)
        {
            System.out.println("node:\n"+((Circle) node).toString()+"\n");
            JsonObject itemJson = Json.createObjectBuilder()
                    .add("translateX",((Circle) node).getTranslateX())
                    .add("translateY",((Circle) node).getTranslateY())
                    .add("radius",((Circle) node).getRadius())
                    .add("fill",((Circle) node).getFill().toString())
                    .add("stroke", ((Circle) node).getStroke().toString())
                    .add("strokeWidth", ((Circle) node).getStrokeWidth()).build();
            return itemJson;
        }
        else if(node instanceof ImageView)
        {
            System.out.println("node:\n"+((ImageView) node).toString()+"\n");
            JsonObject itemJson = Json.createObjectBuilder()
                    .add("translateX",((ImageView) node).getTranslateX())
                    .add("translateY",((ImageView) node).getTranslateY()).build();
            return itemJson;
        }
        
        
        else{
            System.out.println("node:\n"+((Text) node).toString()+"\n");
            JsonObject itemJson = Json.createObjectBuilder().build();
            return itemJson;
            
        }


            
            
    }
    
    public JsonObject saveWorkPane(Object node){
        
        JsonObject itemJson = Json.createObjectBuilder()
                .add("paneHeight", ((StackPane)node).getHeight())
                .add("paneWidth", ((StackPane)node).getWidth())
                .add("background",((StackPane)node).getBackground().getFills().get(0).getFill().toString()).build();

            
        return itemJson;   
    }
    
    /**
     * This method is for saving user work.
     * 
     * @param data The data management component for this application.
     * 
     * @param filePath Path (including file name/extension) to where
     * to save the data to.
     * 
     * @throws IOException Thrown should there be an error writing 
     * out data to the file.
     */
    @Override
    public void saveData(AppDataComponent data, String filePath) throws IOException {
	// GET THE DATA
	GoLogoData goLogoData = (GoLogoData)data;
	
        System.out.println(((Region)goLogoData.getWorkPane()).toString());
        
       
        
        
	// NOW BUILD THE JSON ARRAY FOR THE LIST
	JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        
        //save workpane first
        JsonObject wokpane = saveWorkPane(goLogoData.getWorkPane());
        
        
        //then save all items
        Iterator<GoLogoItemPrototype> itemsIt = goLogoData.itemsIterator();
	while (itemsIt.hasNext()) {	
            GoLogoItemPrototype item = itemsIt.next();
            
            if(item.getUrl()!=null)
            {
                JsonObject itemJson = Json.createObjectBuilder().add(JSON_ORDER, item.getOrder())
                .add(JSON_NAME, item.getName())
                .add(JSON_TYPE, item.getType())
                .add("path", item.getUrl())
                .add(JSON_NODE, saveSingleNode(item.getObject())).build();
                arrayBuilder.add(itemJson);
            }
            else{
                JsonObject itemJson = Json.createObjectBuilder().add(JSON_ORDER, item.getOrder())
                .add(JSON_NAME, item.getName())
                .add(JSON_TYPE, item.getType())
                .add(JSON_NODE, saveSingleNode(item.getObject())).build();
                arrayBuilder.add(itemJson);
            }
            

     
	    
	}
	JsonArray itemsArray = arrayBuilder.build();
	
	// THEN PUT IT ALL TOGETHER IN A JsonObject
	JsonObject toDoDataJSO = Json.createObjectBuilder()
                .add("workpane",wokpane)
		.add(JSON_ITEMS, itemsArray)
		.build();
	
	// AND NOW OUTPUT IT TO A JSON FILE WITH PRETTY PRINTING
	Map<String, Object> properties = new HashMap<>(1);
	properties.put(JsonGenerator.PRETTY_PRINTING, true);
	JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
	StringWriter sw = new StringWriter();
	JsonWriter jsonWriter = writerFactory.createWriter(sw);
	jsonWriter.writeObject(toDoDataJSO);
	jsonWriter.close();

	// INIT THE WRITER
	OutputStream os = new FileOutputStream(filePath);
	JsonWriter jsonFileWriter = Json.createWriter(os);
	jsonFileWriter.writeObject(toDoDataJSO);
	String prettyPrinted = sw.toString();
	PrintWriter pw = new PrintWriter(filePath);
	pw.write(prettyPrinted);
	pw.close();
    }
    
    
    
    /**
     * This method loads data from a JSON formatted file into the data 
     * management component and then forces the updating of the workspace
     * such that the user may edit the data.
     * 
     * @param data Data management component where we'll load the file into.
     * 
     * @param filePath Path (including file name/extension) to where
     * to load the data from.
     * 
     * @throws IOException Thrown should there be an error
     * reading
     * in data from the file.
     */

    @Override
    public void loadData(AppDataComponent data, String filePath) throws IOException {
	// CLEAR THE OLD DATA OUT
        
	GoLogoData goLogoData = (GoLogoData)data;
	goLogoData.reset();
        goLogoData.removePane();
	
	// LOAD THE JSON FILE WITH ALL THE DATA
	JsonObject json = loadJSONFile(filePath);
	
	// LOAD workpane
        JsonObject workpane = json.getJsonObject("workpane");
	StackPane pane = loadPane(workpane);
        goLogoData.setWorkPane(pane);
        //System.out.println("styleclass"+goLogoData.getWorkPane().getStyleClass().get(0));
	
	// AND NOW LOAD ALL THE ITEMS
	JsonArray jsonItemArray = json.getJsonArray(JSON_ITEMS);
	for (int i = 0; i < jsonItemArray.size(); i++) {
	    JsonObject jsonItem = jsonItemArray.getJsonObject(i);
	    GoLogoItemPrototype item = loadItem(jsonItem);
	    goLogoData.addItem(item);
            goLogoData.addNodeinPane(item);
            goLogoData.activateNodeAction(item);
	}
    }
    
    public StackPane loadPane(JsonObject json) {
        
        String width = json.getJsonNumber("paneWidth").toString();
        String height = json.getJsonNumber("paneHeight").toString();
        String paint = json.getString("background");
        System.out.println("loading:"+paint);
        StackPane pane = new StackPane();
        pane.setMaxSize(Double.parseDouble(width), Double.parseDouble(height));
        pane.setBackground(new Background(new BackgroundFill(RadialGradient.valueOf(paint), CornerRadii.EMPTY, Insets.EMPTY)));

        
	return pane;	
    }
    
    public double getDataAsDouble(JsonObject json, String dataName) {
	JsonValue value = json.get(dataName);
	JsonNumber number = (JsonNumber)value;
	return number.bigDecimalValue().doubleValue();	
    }
    
    public GoLogoItemPrototype loadItem(JsonObject jsonItem) {
        
        GoLogoItemPrototype loaditem = new GoLogoItemPrototype();
	// GET THE DATA
        int order = jsonItem.getInt(JSON_ORDER);
	//String order = jsonItem.getString(JSON_ORDER);
	String name = jsonItem.getString(JSON_NAME);
        String type = jsonItem.getString(JSON_TYPE);
        //Object obj = jsonItem.getString(JSON_OBJECT);
        String path=null;
        if(jsonItem.containsKey("path"))
            path = jsonItem.getString("path");
        
        if(type.equals("Text"))
        {
            JsonObject result = jsonItem.getJsonObject("node");
            
            String tranX = result.getJsonNumber("translateX").toString();
            String tranY = result.getJsonNumber("translateY").toString();
            String text = result.getString("text");
            String x = result.getJsonNumber("x").toString();
            String y = result.getJsonNumber("y").toString();
            String alignment = result.getString("alignment");
            String origin = result.getString("origin");
            String boundsType = result.getString("boundsType");
            String fontSmoothingType = result.getString("fontSmoothingType");
            String fontname = result.getString("fontname");
            String fam = result.getString("fontfam");
            String style = result.getString("fontstyle");
            String size = result.getJsonNumber("fontsize").toString();
            String fill = result.getString("fill");

            
            
            Text t = new Text();
            t.setText(text);
            t.setX(Double.parseDouble(x));
            t.setY(Double.parseDouble(y));
            t.setTextAlignment(TextAlignment.valueOf(alignment));
            t.setTextOrigin(VPos.valueOf(origin));
            t.setBoundsType(TextBoundsType.valueOf(boundsType));
            
            t.setFontSmoothingType(FontSmoothingType.valueOf(fontSmoothingType));
            t.setTranslateX(Double.parseDouble(tranX));
            t.setTranslateY(Double.parseDouble(tranY));
            t.setFill(Paint.valueOf(fill));

            if(style.contains("Bold")&&style.contains("Italic"))
            {
                t.setFont(Font.font(fam, FontWeight.BOLD, FontPosture.ITALIC, Double.parseDouble(size)));
                //System.out.println("1:");
            }
            else if(style.contains("Bold")&&(!style.contains("Italic")))
            {
                t.setFont(Font.font(fam, FontWeight.BOLD, FontPosture.REGULAR, Double.parseDouble(size)));
                //System.out.println("2:");
            }
            else if(!(style.contains("Bold"))&&(style.contains("Italic")))
            {
                t.setFont(Font.font(fam, FontWeight.NORMAL, FontPosture.ITALIC, Double.parseDouble(size)));
                //System.out.println("3:");
            }

            else{
                t.setFont(Font.font(fam, FontWeight.NORMAL, FontPosture.REGULAR, Double.parseDouble(size)));

            }
            
            
            
            
            
            GoLogoItemPrototype item = new GoLogoItemPrototype(order, name, type,t);
            loaditem = item;
        }
        else if(type.equals("Rectangle"))
        {
            JsonObject result = jsonItem.getJsonObject("node");
            String tranX = result.getJsonNumber("translateX").toString();
            String tranY = result.getJsonNumber("translateY").toString();
            String x = result.getJsonNumber("x").toString();
            String y = result.getJsonNumber("y").toString();
            String width = result.getJsonNumber("width").toString();
            String height = result.getJsonNumber("height").toString();
            String stroke = result.getString("stroke");
            String strokeWidth = result.getJsonNumber("strokeWidth").toString();
            String fill = result.getString("fill");
            
            
            
            
            Rectangle t = new Rectangle();
            
            t.setX(Double.parseDouble(x));
            t.setY(Double.parseDouble(y));
            t.setWidth(Double.parseDouble(width));
            t.setHeight(Double.parseDouble(height));
            t.setStroke(Paint.valueOf(stroke));
            t.setStrokeWidth(Double.parseDouble(strokeWidth));
            t.setFill(Paint.valueOf(fill));
            t.setTranslateX(Double.parseDouble(tranX));
            t.setTranslateY(Double.parseDouble(tranY));
            
            GoLogoItemPrototype item = new GoLogoItemPrototype(order, name, type,t);
            loaditem = item;
        }
        
        else if(type.equals("Circle"))
        {
            JsonObject result = jsonItem.getJsonObject("node");
            String tranX = result.getJsonNumber("translateX").toString();
            String tranY = result.getJsonNumber("translateY").toString();
            String radius = result.getJsonNumber("radius").toString();
            String stroke = result.getString("stroke");
            String strokeWidth = result.getJsonNumber("strokeWidth").toString();
            String fill = result.getString("fill");
            
            
            
            
            Circle t = new Circle();

            t.setRadius(Double.parseDouble(radius));
            t.setStroke(Paint.valueOf(stroke));
            t.setStrokeWidth(Double.parseDouble(strokeWidth));
            t.setFill(Paint.valueOf(fill));
            t.setTranslateX(Double.parseDouble(tranX));
            t.setTranslateY(Double.parseDouble(tranY));
            
            GoLogoItemPrototype item = new GoLogoItemPrototype(order, name, type,t);
            loaditem = item;
        }
        else if (type.equals("Image"))
        {
            JsonObject result = jsonItem.getJsonObject("node");
            String tranX = result.getJsonNumber("translateX").toString();
            String tranY = result.getJsonNumber("translateY").toString();
            
            ImageView iv = new ImageView(new Image(path));
            iv.setTranslateX(Double.parseDouble(tranX));
            iv.setTranslateY(Double.parseDouble(tranY));
            
            GoLogoItemPrototype item = new GoLogoItemPrototype(order, name, type,iv,path);
            loaditem = item;
        }
        
	// THEN USE THE DATA TO BUILD AN ITEM
        
	// ALL DONE, RETURN IT
	return loaditem;
    }

    // HELPER METHOD FOR LOADING DATA FROM A JSON FORMAT
    private JsonObject loadJSONFile(String jsonFilePath) throws IOException {
	InputStream is = new FileInputStream(jsonFilePath);
	JsonReader jsonReader = Json.createReader(is);
	JsonObject json = jsonReader.readObject();
	jsonReader.close();
	is.close();
	return json;
    }
    
    /**
     * This method would be used to export data to another format,
     * which we're not doing in this assignment.
     */
    @Override
    public void exportData(AppDataComponent data, String savedFileName) throws IOException {
        


        //Prompt user to select a file
        //File file = fileChooser.showSaveDialog(null);
        WritableImage image = ((GoLogoData)data).getWorkPane().snapshot(new SnapshotParameters(), null);

        
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("png files (*.png)", "*.png"));
        File file = fileChooser.showSaveDialog(null);

        if(file != null){
//            try {
//                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
//            } catch (IOException e) {
//                // TODO: handle exception here
//            }

            try {
                 WritableImage writableImage = new WritableImage(Double.valueOf(((GoLogoData)data).getWorkPane().getWidth()).intValue(), Double.valueOf(((GoLogoData)data).getWorkPane().getHeight()).intValue());
                 ((GoLogoData)data).getWorkPane().snapshot(null, writableImage);
                 RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                 ImageIO.write(renderedImage, "png", file);
             } catch (IOException ex) {
                 //Logger.getLogger(JavaFX_DrawOnCanvas.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
        

        
        
        
        
//        String toDoListName = savedFileName.substring(savedFileName.indexOf("."));
//        String fileToExport = toDoListName + ".html";
//        try {
//            // GET THE ACTUAL DATA
//            GoLogoData goLogoData = (GoLogoData)data;
//            PropertiesManager props = PropertiesManager.getPropertiesManager();
//            String exportDirPath = props.getProperty(APP_PATH_EXPORT) + toDoListName + "/";
//            File exportDir = new File(exportDirPath);
//            if (!exportDir.exists()) {
//                exportDir.mkdir();
//            }
//
//            // NOW LOAD THE TEMPLATE DOCUMENT
//            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
//            String htmlTemplatePath = props.getPropertiesDataPath() + props.getProperty(GL_EXPORT_TEMPLATE_FILE_NAME);
//            File file = new File(htmlTemplatePath);
//            System.out.println(file.getPath() + " exists? " + file.exists());
//            URL templateURL = file.toURI().toURL();
//            Document exportDoc = docBuilder.parse(templateURL.getPath());
//
//            // SET THE WEB PAGE TITLE
//            Node titleNode = exportDoc.getElementsByTagName(TITLE_TAG).item(0);
//            titleNode.setTextContent("No Name Logo");
//
//            
//            
//            // ADD ALL THE ITEMS
//            Node tDataNode = getNodeWithId(exportDoc, "tdata", TABLE_DATA_TAG);
//            Iterator<GoLogoItemPrototype> itemsIt = goLogoData.itemsIterator();
//            while (itemsIt.hasNext()) {
//                GoLogoItemPrototype item = itemsIt.next();
//                Element trElement = exportDoc.createElement(HTML.Tag.TR.toString());
//                tDataNode.appendChild(trElement);
//                //addCellToRow(exportDoc, trElement, item.getOrder());
//                addCellToRow(exportDoc, trElement, item.getName());
//                addCellToRow(exportDoc, trElement, item.getType());
//                
//            }
//            
//            // CORRECT THE APP EXPORT PAGE
//            props.addProperty(APP_EXPORT_PAGE, exportDirPath + fileToExport);
//
//            // EXPORT THE WEB PAGE
//            saveDocument(exportDoc, props.getProperty(APP_EXPORT_PAGE));
//        }
//        catch(SAXException | ParserConfigurationException
//                | TransformerException exc) {
//            throw new IOException("Error loading " + fileToExport);
//        }
    }
    private void addCellToRow(Document doc, Node rowNode, String text) {
        Element tdElement = doc.createElement(HTML.Tag.TD.toString());
        tdElement.setTextContent(text);
        rowNode.appendChild(tdElement);
    }
    private Node getNodeWithId(Document doc, String tagType, String searchID) {
        NodeList testNodes = doc.getElementsByTagName(tagType);
        for (int i = 0; i < testNodes.getLength(); i++) {
            Node testNode = testNodes.item(i);
            Node testAttr = testNode.getAttributes().getNamedItem(HTML.Attribute.ID.toString());
            if ((testAttr != null) && testAttr.getNodeValue().equals(searchID)) {
                return testNode;
            }
        }
        return null;
    }
    private void saveDocument(Document doc, String outputFilePath)
            throws TransformerException, TransformerConfigurationException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        Result result = new StreamResult(new File(outputFilePath));
        Source source = new DOMSource(doc);
        transformer.transform(source, result);
    }

    /**
     * This method is provided to satisfy the compiler, but it
     * is not used by this application.
     */
    @Override
    public void importData(AppDataComponent data, String filePath) throws IOException {
        
    }
}
