
package controller;

//importacion
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.JobLogger;
import model.JobLoggerPrototype;
import view.ViewLog;

//escucha eventos de la IU
public class ControllerLog  implements ActionListener {

       private ViewLog view;
       private JobLoggerPrototype jobLoggerPrototype;
      
       
       //constructor
       public ControllerLog(ViewLog view, JobLoggerPrototype jobLoggerPrototype) {
        
               //asigno parametros
               this.view = view;
               this.jobLoggerPrototype = jobLoggerPrototype;
        
               //escucho evento sobre boton
               this.view.btnOK.addActionListener(this);
       }
       
       //inicio y parametrizo la vista
       public void InitLog(){
           
              this.view.setTitle("MIDAS Loggin send message");
              this.view.setLocationRelativeTo(null);
       }
       
       @Override
       
       //al producirse el evento lo comunico entre vista y modelo
       public void actionPerformed(ActionEvent e) {
           
           //envio parametrización al prototipo
           jobLoggerPrototype.AddPrototype(view.check_conMessage.isSelected(), view.check_conWarning.isSelected(), view.check_conError.isSelected(), 
                                           view.check_filMessage.isSelected(), view.check_filWarning.isSelected(), view.check_filError.isSelected(), 
                                           view.check_DBMessage.isSelected(),  view.check_DBWarning.isSelected(),  view.check_DBError.isSelected(),
                                           view.jtf_userName.getText(),view.jtf_password.getText(),view.jtf_Dbms.getText(),view.jtf_server.getText(),
                                           view.jtf_portNumber.getText(),view.jtf_FileFolder.getText()
           );
           
           
           try {
               
               //asigno al prototipo el item selecciondo en la vista
               JobLogger jobLogger = (JobLogger) jobLoggerPrototype.Prototype((String) view.cmb_imput.getSelectedItem());
               
               jobLogger.LogFilter(view.jtf_Message.getText(), view.check_ImpMessage.isSelected(),view.check_ImpWarning.isSelected(),view.check_ImpError.isSelected());
               jobLogger.logMessage();
           } catch (Exception ex) {
               Logger.getLogger(ControllerLog.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
    
}
