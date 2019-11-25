package model;

//importacion
import java.text.DateFormat;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

//clase abstracta de la que heredan console, FileLog y DataBase  implementa la interface Cloneable
public abstract class JobLogger implements Cloneable {

    //declaracion de variables
    protected String  logTo;        //tipo de medio (consola,archivo,db)
    protected boolean logMessage;   //mensaje
    protected boolean logWarning;   //warning
    protected boolean logError;     //error
    protected String  l = "";       //texto del mensaje a mostrar ó grabar
    protected static Logger logger; // objeto creador de mensajes

    //constructor
    public JobLogger(String logTo, boolean logMessage, boolean logWarning, boolean logError) {
        
        //setting de parametros
        logger = Logger.getLogger("MyLog");
        setLogTo(logTo);
        setLogMessage(logMessage);
        setLogWarning(logWarning);
        setLogError(logError);
    }

    //metdo que recibe parametros, los filtra y concatena el mensaje en la variable l
    public void LogFilter(String messageText, boolean message, boolean warning, boolean error) throws Exception {
        
        //filtro que messageText no este vacio
        if (messageText == null || messageText.length() == 0) {

                       throw new Exception("Empty or NULL message");
        }
        
        //filtro que LogTO no corresponda a ningún medio
        if (logTo != "console" && logTo != "search" && logTo != "dataBase") {

                       throw new Exception("Invalid configuration");
        }

        //valido que se expecifique un error,alerta o mensaje
        if ((!logError && !logMessage && !logWarning) || (!message && !warning && !error)) {

                       throw new Exception("Error or Warning or Message must be specified");
        }
        
        //obtengo fecha de sistema y le doy formato
        String dateFormatMsg = DateFormat.getDateInstance(DateFormat.LONG).format(new Date());
        
        //si se recibe error
        if (error && logError) {

               l = l + "error  - " + dateFormatMsg + " - " + messageText + " -- "; //acumulo mjes en l, separo paramtros por -, separo tipo de mensajes por --
       }

       //si se recibe warning
       if (warning && logWarning) {

               l = l + "warning - " + dateFormatMsg + " - " + messageText + " -- ";
       }

       //si se recibe mensaje
       if (message && logMessage) {

               l = l + "message - " + dateFormatMsg + " - " + messageText + " -- ";
      }
      
      //asigno mensaje a la variable l
      this.setL(l);
    }
    
    //metodo encargado de enviar el mensaje al medio
    public void logMessage (){
         
    }

    //metodo que clona objetos (clonar objetos es más rápido que crearlos y luego setear cada valor en particular)
    public Object Clone() throws CloneNotSupportedException{
        
        return super.clone();
    }

    
    //metodos SET y GET
    public String getLogTo() {
        return logTo;
    }

    public void setLogTo(String logTo) {
        this.logTo = logTo;
    }

    public boolean getLogMessage() {
        return logMessage;
    }

    public void setLogMessage(boolean logMessage) {
        this.logMessage = logMessage;
    }

    public boolean getLogWarning() {
        return logWarning;
    }

    public void setLogWarning(boolean logWarning) {
        this.logWarning = logWarning;
    }

    public boolean getLogError() {
        return logError;
    }

    public void setLogError(boolean logError) {
        this.logError = logError;
    }

    public String getL() {
        return l;
    }

    public void setL(String l) {
        this.l = l;
    }
}
