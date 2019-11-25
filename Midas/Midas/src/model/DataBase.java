package model;

//declaracion de importaciones
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Statement;

//clase que graba log en la DB  hereda de JobLogger
public class DataBase extends JobLogger {

    //declaración de variables
    private String userName;   //nombre de usuario
    private String password;   //contraseña
    private String dbms;       //gestor de bd
    private String serverName; //nombre del servidor
    private String portNumber; //numero de puerto

    //constructor de clase dataBase
    public DataBase(String logTo, boolean logMessage, boolean logWarning, boolean logError, String userName, String password, String dbms, String serverName, String portNumber) {
        
        //setting de parametros
        super(logTo,logMessage,logWarning,logError);
        this.setUserName(userName);
        this.setPassword(password);
        this.setPortNumber(portNumber);
        this.setServerName(serverName);
        this.setDbms(dbms);
        
    }
    
    //metodo que permite la conexión con la base de datos y ejecuta una consulta a la bd
    public void conexion(String sql){
    
            try {
                Connection connection = null;

                Properties connectionProps = new Properties();
                connectionProps.put("user", this.getUserName());
                connectionProps.put("password", this.getPassword());
                
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                
                connection = DriverManager.getConnection("jdbc:" + this.getDbms() + "://" + this.getServerName()+ ":" + this.getPortNumber() + "",connectionProps);
                
                Statement stmt = connection.createStatement();
                stmt.executeUpdate(sql);
                  

                } catch (SQLException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    
    //metodo que separa cadenas de texto para insertar en la base de datos por tipo,fecha y mensaje
    public void FilterlogMessage(String typeMsg){
                
                //si la cadena esta vacia no existe tipo de mensaje. Caso contrario separa la cadena e inserta en la DB
                if(!typeMsg.equalsIgnoreCase(" ")){
                
                           String[] parts = typeMsg.split("-", 3);
                    
                           String messageType  =  parts[0]; // message type
                           String messageDate  =  parts[1]; // message date log               
                           String messageLog   =  parts[2]; // message log
                
                           this.conexion("INSERT INTO [MIDAS].[dbo].[LOG_VALUES] (typeLog,dateLog,messageLog) VALUES ('" +messageType+ "','" +messageDate+ "','" +messageLog+ "')");
                }
                          
    }
    
    //Metedo sobrescrito que separa la cadena de texto por mensaje de error, warning y message
    public void logMessage(){
        
                //variable que contiene el mensaje                     
                String mje = this.getL();
                
                //separo cadena por tipo de mje
                String[] partsTypes     =  mje.split("--");
                String typeError        =  partsTypes[0]; // message type
                String typeWarning      =  partsTypes[1]; // message date log
                String typeMessage      =  " ";
                
                if(partsTypes.length==3) 
                         typeMessage      =  partsTypes[2]; // message date log
                
                //filtro cada tipo de mje e inserto en DB
                this.FilterlogMessage(typeError);
                this.FilterlogMessage(typeWarning);
                this.FilterlogMessage(typeMessage);
                   
    }

    
    //metodos SET y GET
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDbms() {
        return dbms;
    }

    public void setDbms(String dbms) {
        this.dbms = dbms;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(String portNumber) {
        this.portNumber = portNumber;
    }
}
