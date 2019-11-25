package model;

//importaciones
import java.io.File;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import static model.JobLogger.logger;

//clase que graba en archivo los log (hereda de jobLogger)
public class Search extends JobLogger {

    //declaracion de variables
    private String logFileFolder;

    //constructor de la clase
    public Search(String logTo, boolean logMessage, boolean logWarning, boolean logError, String logFileFolder) {
        
        //asigno parametros
         super(logTo,logMessage,logWarning,logError);
         this.setLogFileFolder(logFileFolder);
    }
    
    //metodo sobreescrito modificado para insertar datos en archivo c:/midas/logFile.txt
    public void logMessage(){
        
        
            try {
                        //creo un objeto fileHandler que es un mmanipuador de tipo archivo al que le paso como parametro la ruta del archivo
                        FileHandler fh = new FileHandler(this.getLogFileFolder() + "/logFile.txt");
                        //asigno el manipuladr al logger
                        logger.addHandler(fh);
                        //grabo un mensaje de tipo informacion con la variable l como texto en un archivo
                        logger.log(Level.INFO, this.getL());
                        
            } catch (IOException ex) {
                        Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                        Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }

    //mettodos SET y GET
    public String getLogFileFolder() {
        return logFileFolder;
    }

    public void setLogFileFolder(String logFileFolder) {
        this.logFileFolder = logFileFolder;
    }
}
