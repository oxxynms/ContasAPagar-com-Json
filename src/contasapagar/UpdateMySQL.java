package contasapagar;
/*
* @author Bruno Sampaio
*
*********** UpdateMySQL my SQL ****************
*
*
*/
import java.sql.SQLException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;



public class UpdateMySQL extends ConnectMySQL{
    
    public void updateData(String forn, String dataEmissao, String dataVenc, float valor, java.sql.Connection conn){
        try {
            Statement st = conn.createStatement();
            st.execute("use financeiro");
            st.execute("insert into contasAPagar (fornecedor, dataVencimento, emissao, valor )" + "value ('" + forn + "','" + dataVenc + "', '" + dataEmissao + "', '" + valor + "')");
            System.out.println("Conta adicionada com sucesso!");
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("erro ao instanciar Statement");
            try {
                conn.close();
            } catch (SQLException ex1) {
                System.out.println("erro ao fechar conexão");
            }
        }
        
        
    }
    public void deleteData(int idAP, java.sql.Connection conn){
        try {
            Statement st = conn.createStatement();
            
            st.execute("use financeiro");
            st.execute("delete from contasapagar where idAP = '" + idAP + "'");
            conn.close();
        } catch (SQLException ex) {
            System.out.println("erro ao instanciar Statement");
            try {
                conn.close();
            } catch (SQLException ex1) {
                System.out.println("erro ao fechar conexão");
            }
        }

        }
        
    }
    
    

