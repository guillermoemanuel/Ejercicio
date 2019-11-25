
package midas;

import controller.ControllerLog;
import model.JobLoggerPrototype;
import view.ViewLog;


public class Midas {

    //clase Main Principal
    public static void main(String[] args) throws Exception{
        
        //instancio objetos  de tipo vista y modelo
        ViewLog view = new ViewLog();
        JobLoggerPrototype jobLoggerPrototype = new JobLoggerPrototype();
        
        //instancio objeto controlador y paso como parametros vista y modelo
        ControllerLog controllerLog = new ControllerLog(view,jobLoggerPrototype);
        controllerLog.InitLog();
        view.setVisible(true);
        
    }
    
}
