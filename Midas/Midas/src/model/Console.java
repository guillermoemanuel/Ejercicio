package model;

//importaciones
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;

//clase que muestra por consola los log hereda de JobLogger
public class Console extends JobLogger {

    //constructor
    public Console(String logTo, boolean logMessage, boolean logWarning, boolean logError) {
        
        //asigno parametros
        super(logTo,logMessage,logWarning,logError);
    }
    
    //metodo sobrescrito para mostrar log por consola
    public void logMessage(){
         
                //creo un objeto consoleHander manipulador para consola
                ConsoleHandler ch = new ConsoleHandler();
                //asigno el manipulador al logger
                logger.addHandler(ch);
                ////muestro por consola un mensaje de tipo informacion con la variable l como texto
                logger.log(Level.INFO, this.getL());
    }
}
