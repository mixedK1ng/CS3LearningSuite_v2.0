/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gologolo;

import djf.AppTemplate;
import djf.components.AppClipboardComponent;
import djf.components.AppDataComponent;
import djf.components.AppFileComponent;
import djf.components.AppWorkspaceComponent;
import gologolo.clipboard.GoLogoClipboard;
import gologolo.data.GoLogoData;
import gologolo.files.ToDoFiles;
import gologolo.workspace.GoLogoWorkspace;
import java.util.Locale;
import static javafx.application.Application.launch;

/**
 *
 * @author yiwang
 */
public class GologoloApp extends AppTemplate{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
	launch(args);
    }

    @Override
    public AppClipboardComponent buildClipboardComponent(AppTemplate app) {
        return new GoLogoClipboard(this); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AppDataComponent buildDataComponent(AppTemplate app) {
        return new GoLogoData(this); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AppFileComponent buildFileComponent() {
        return new ToDoFiles(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AppWorkspaceComponent buildWorkspaceComponent(AppTemplate app) {
        return new GoLogoWorkspace(this);  //To change body of generated methods, choose Tools | Templates.
    }
    
}
