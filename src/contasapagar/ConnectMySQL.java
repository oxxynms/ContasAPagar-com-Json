package contasapagar;

/*
* @author Bruno Sampaio
*
*********** Connector my SQL ****************
*
*
*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConnectMySQL {
    private String status = "não concetou";

    // Other fields and calculations

    //////////////////////////////
    public ConnectMySQL(){

}
    public static java.sql.Connection getConnectionSQL(){
        Connection connection = null;
       
        
        try{
            String driveName = "com.mysql.cj.jdbc.Driver"; 
            
            Class.forName(driveName);

            
            String url = "jdbc:mysql://localhost:3306/mySQL?useTimezone=true&serverTimezone=UTC";
            String login = "root";
            String pwd = "";
            connection = DriverManager.getConnection(url, login, pwd);
            
            if (connection != null){
                //System.out.println("Conectado com Sucesso!");
            }
            
            else{
                System.out.println("não conseguiu conectar");
            }
            
            return connection;
            
            
            
            
            
            
        }catch(ClassNotFoundException e){
            System.out.println("erro de conexão ClassNotFoundExeption, Driver não encontrado");
            return null;
            
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Erro de conexão SQLException, não foi possivel conectar ao banco de dados");
            return null;
        }
        
        
       
        
        

        
    }
    
    public String statusConnection(){
        return this.status;
    }
}