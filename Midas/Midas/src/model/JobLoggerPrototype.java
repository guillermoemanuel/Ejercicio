package model;

//importación
import java.util.HashMap;

//clase que crea nuevos objetos pidiendo al prototipo que se clone
public class JobLoggerPrototype {
    
    //declaracion de objeto ded la clase Map
    private HashMap<String,JobLogger> prototypes = new HashMap<String,JobLogger>();

    //constructor de la clase
    public JobLoggerPrototype() {
        
        /*//creo objetos
        Console  console   = new Console("console",true,true,true);
        Search   search    = new Search("search",true,true,true,"c:/logMidas");
        DataBase dataBase  = new DataBase("dataBase",true,true,true,"sa","123456","sqlserver","desktop2","1433");
        
        //asigno objetos al maps
        this.prototypes.put("console",console);
        this.prototypes.put("search", search);
        this.prototypes.put("dataBase",dataBase);*/
    }
    
    
    public void AddPrototype(boolean consoleMsg, boolean consoleWar, boolean consoleErr, 
                             boolean searchMsg, boolean searchWar, boolean searchErr, 
                             boolean dataBaseMsg, boolean dataBaseWar, boolean dataBaseErr,
                             String userName, String password, String dbms, String serverName, 
                             String portNumber, String fileFolder){
        
        //creo objetos
        Console  console   = new Console("console",consoleMsg,consoleWar,consoleErr);
        Search   search    = new Search("search",searchMsg,searchWar,searchErr,fileFolder);
        DataBase dataBase  = new DataBase("dataBase",dataBaseMsg,dataBaseWar,dataBaseErr,userName,password,dbms,serverName,portNumber);
        
        //asigno objetos al maps
        this.prototypes.put("console",console);
        this.prototypes.put("search", search);
        this.prototypes.put("dataBase",dataBase);
    }
    
    //clase que crea prototipos a traves de la clonacion
    public Object Prototype(String logTo) throws CloneNotSupportedException{
        
        return prototypes.get(logTo).Clone();
    }
}
