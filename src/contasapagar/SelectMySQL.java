package contasapagar;
/*
* @author Bruno Sampaio
*
*********** Select my SQL ****************
*
* Faz Consultas ao MySQL e salva em um Json
*
*/

import java.sql.*;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class SelectMySQL extends ConnectMySQL {
    private float ret;
    private String result;
    private SelectMySQL conn;
    private java.sql.Connection con;

    // getters and setters
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    
    public float getRet() {
        return ret;
    }

    public void setRet(float ret) {
        this.ret = ret;

    }
    
    // *************** selecionar total de contas a pagar *********************
    public void selectTotal(){
        this.con = this.conn.getConnectionSQL();
        
        try {
            Statement st = this.con.createStatement();
            st.execute("use financeiro");
            ResultSet rs = st.executeQuery("select sum(valor) from contasapagar");
            while(rs.next()){
                this.setResult("[\r\n	 {\"valorTotal\": \"" + rs.getString("sum(valor)") + "\"}\r\n]");                 
            }
            
        //Cria arquivo json
        
        do{
        File file = new File("C:\\Users\\bruno\\Desktop\\faculdade\\JAVA\\AprendendoAjax\\web\\resultSet.json");
        try {
            file.createNewFile();
            FileWriter fw = new FileWriter("C:\\Users\\bruno\\Desktop\\faculdade\\JAVA\\AprendendoAjax\\web\\resultSet.json");
            fw.write(this.getResult());
            fw.close();
            break;
             } catch (IOException ex) {
                 file.delete();

            }            
        }while(true);    
           
            
            this.con.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("erro de sintax sql");
        }try {
            this.con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("erro ao fechar conexão");
        }
        
    }
        // ************************** selecionar total por fornecedores **************
        public void selectFornecedor(){
        this.con = this.conn.getConnectionSQL();
        
        try {
            Statement st = this.con.createStatement();
            st.execute("use financeiro");
            ResultSet rs = st.executeQuery("select fornecedor, sum(valor) from contasapagar group by fornecedor");
            this.setResult("[\r\n");
            while(rs.next()){
                
                this.setResult(this.getResult() + "       {\"fornecedor\": \"" + rs.getNString("fornecedor") + "\",\r\n       \"valor\": \"" + rs.getString("sum(valor)") + "\"\r\n       },\r\n");
                
            }
            
            
            
            //BackSpace, remove os ultimos 3 chars do arquivo
            StringBuilder sb = new StringBuilder(this.getResult());
            int comp = sb.length();
            
            for(int count = 1; count < 4; count++){
            comp -= 1;
            sb.deleteCharAt(comp);
            }
            this.setResult(sb.toString());
            ///////////
            
            this.setResult(this.getResult() + "\r\n]");
          //Cria Json
        do{
        File file = new File("C:\\Users\\bruno\\Desktop\\faculdade\\JAVA\\AprendendoAjax\\web\\resultSet.json");
        try {
            file.createNewFile();
            FileWriter fw = new FileWriter("C:\\Users\\bruno\\Desktop\\faculdade\\JAVA\\AprendendoAjax\\web\\resultSet.json");
            fw.write(this.getResult());
            fw.close();
            break;
             } catch (IOException ex) {
                 file.delete();

            }            
        }while(true);    
          
          
          
          
          
          
            this.con.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("erro de sintax sql");
        }try {
            this.con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("erro ao fechar conexão");
        }
        
    }
        // ************** Selecionar por intervalo de datas ************************
        public void selectData(String data1, String data2){
            this.con = this.conn.getConnectionSQL();
            try{
                Statement st = this.con.createStatement();
                st.execute("use financeiro");
                ResultSet rs = st.executeQuery("select sum(valor), fornecedor, valor, emissao from contasapagar where emissao between '" + data1 + "' and '" + data2 + "' group by fornecedor");
                this.setResult("[\r\n");     
                while(rs.next()){
                     
                    this.setResult(this.getResult() + "    {\"fornecedor\": \"" +rs.getString("fornecedor")+ "\",\r\n    \"emissao\": \"" +rs.getString("emissao")+ "\",\r\n    \"valor\": \"" +rs.getString("valor")+ "\"},\r\n" ); 
                 
                 }
            if(rs.last()){
            this.setResult(this.getResult() + "    {\r\n    \"valorTotal\": \"" +rs.getString("sum(valor)")+ "\"}\r\n]" );
            }   
            //Cria Json
            
        do{
        File file = new File("C:\\Users\\bruno\\Desktop\\faculdade\\JAVA\\AprendendoAjax\\web\\resultSet.json");
        try {
            file.createNewFile();
            FileWriter fw = new FileWriter("C:\\Users\\bruno\\Desktop\\faculdade\\JAVA\\AprendendoAjax\\web\\resultSet.json");
            fw.write(this.getResult());
            fw.close();
            break;
             } catch (IOException ex) {
                 file.delete();

            }            
        }while(true);   
                 
                 
                 
            }catch(SQLException e ){
                e.printStackTrace();
                System.out.println("erro ao criar estancia");
                try {
                    this.con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();

                }
            }
                   
            

        }
        //************************** Select Contas Vencidas ************************
        public void selectVencido(){
            // formatando a data atual para SQL
            Calendar cl = Calendar.getInstance();
            cl.getTime();
            java.util.Date date = cl.getTime();
            DateFormat df =  DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.CHINA);
            String data1 = df.format(date);
            ///////////////
            this.con = this.conn.getConnectionSQL();
        try {
            Statement st = this.con.createStatement();
            st.execute("use financeiro");
            ResultSet rs = st.executeQuery("select fornecedor, valor, sum(valor), dataVencimento from contasapagar where dataVencimento < '" + data1 + "' group by dataVencimento order by fornecedor");
            this.setResult("[\r\n");
            while(rs.next()){
                this.setResult(this.getResult() + "    {\"fornecedor\" :\"" +rs.getString("fornecedor")+ "\",\r\n    \"dataVencimento\": \"" +rs.getString("dataVencimento")+ "\",\r\n    \"valor\": \"" +rs.getString("valor")+ "\"\r\n    }," );
    
            }
            if(rs.last()){
            this.setResult(this.getResult() + "\r\n    {\"valorTotal\": \"" +rs.getString("sum(valor)")+ "\"\r\n    }\r\n]" );
            }  
                   
            //Cria Json
           
            do{
        File file = new File("C:\\Users\\bruno\\Desktop\\faculdade\\JAVA\\AprendendoAjax\\web\\resultSet.json");
        try {
            file.createNewFile();
            FileWriter fw = new FileWriter("C:\\Users\\bruno\\Desktop\\faculdade\\JAVA\\AprendendoAjax\\web\\resultSet.json");
            fw.write(this.getResult());
            fw.close();
            break;
             } catch (IOException ex) {
                 file.delete();

            }            
        }while(true);   

            this.con.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
            
            

        }
        
    
}
