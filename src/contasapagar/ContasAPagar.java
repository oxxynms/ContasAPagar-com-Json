package contasapagar;

import java.util.Scanner;
import java.sql.*;
public class ContasAPagar {

    public static void main(String[] args) {
        Scanner s1 = new Scanner(System.in);
        java.sql.Connection conn;
        int select;
       
        
        ////////////////// Input de dados
        System.out.println("=====================================");
        System.out.println("=           Contas a Pagar          =");
        System.out.println("=====================================");
        System.out.println("Escolha a operação desejada");
        System.out.println("1- Ad3icionar uma conta");
        System.out.println("2- excluir uma conta");
        System.out.println("3- Consultar");
        select = s1.nextInt();
       
        //////////////////
        if (select == 1) {
            String fornecedor;
            String dataEmissao;
            String dataVencimento;
            float valor;
            
            System.out.print("Digite o Fornecedor: ");
            fornecedor = s1.next();
            System.out.print("Digite a data de emissão: ");
            dataEmissao = s1.next();
            System.out.print("Digite a data de vencimento: ");
            dataVencimento = s1.next();
            System.out.println("Digite o valor da conta: ");
        

            ////////////////// conexão com o servidor
            UpdateMySQL up = new UpdateMySQL();
            valor = s1.nextFloat();
            conn = up.getConnectionSQL();
            up.updateData(fornecedor, dataEmissao, dataVencimento, valor, conn);
        }
        if(select == 2){
            int idAP;
            System.out.print("Digite a idAP para excluir-lá: ");
            /////////////////// conexão com o servidor
            idAP = s1.nextInt();
            UpdateMySQL up = new UpdateMySQL();
            conn = up.getConnectionSQL();
            up.deleteData(idAP, conn);
        }
        if(select == 3){
            int opcao;
            float resp;
            String respd;
            System.out.println("Selecione as opções de consulta");
            System.out.println("1 - total de contas a pagar");
            System.out.println("2 - Contas a pagar por fornecedor");
            System.out.println("3 - Contas por data");
            System.out.println("4 - Contas Vencidas");
            opcao = s1.nextInt();
            if(opcao == 1){
                SelectMySQL sel = new SelectMySQL();
                sel.selectTotal();
                
            }
            if(opcao == 2){
              SelectMySQL sel = new SelectMySQL();
              sel.selectFornecedor();

            }
            if(opcao == 3){
                 SelectMySQL sel = new SelectMySQL();
                String data1;
                String data2;
                System.out.print("digite a primeira data: ");
                data1 = s1.next();
                System.out.print("digite a segunda data: ");
                data2 = s1.next();
                sel.selectData(data1,data2);
             
            }
            if(opcao == 4){
                
                SelectMySQL sel = new SelectMySQL();
                sel.selectVencido();
            }
        }
            
        }
                
    }
    

