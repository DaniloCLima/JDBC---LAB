package br.mack.ps2;
import java.sql.*;

public class App
{
    public static void main( String[] args )
    {
        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String db="sistema_chamada";
            String url="jdbc:mysql://localhost:32775/"+db;
            String user="root";
            String psw="root";

            conn=DriverManager.getConnection(url,user,psw);

            String sql1 = "SELECT tia, TIME_FORMAT(hora_entrada, '%h:%i %p') hora_entrada, TIME_FORMAT(hora_saida, '%h:%i %p') hora_saida FROM regist_frequencia;\n";
            PreparedStatement pstm = conn.prepareStatement(sql1);
            ResultSet rs = pstm.executeQuery();

            System.out.println("BASE DE DADOS ATUAL:");

            while (rs.next()){
                int tia = rs.getInt("tia");
                String hora_entrada = rs.getString("hora_entrada");
                String hora_saida = rs.getString("hora_saida");

                System.out.println("TIA: "+tia+" - Hora de Entrada: "+hora_entrada+" - Hora de Saída: "+hora_saida);
            }


            String update = "INSERT INTO `sistema_chamada`.`regist_frequencia`(`tia`,`hora_entrada`,`hora_saida`) VALUES(31809999,080000,100000);";
            PreparedStatement pstm2 = conn.prepareStatement(update);
            pstm2.executeUpdate();

            System.out.println("------------------------------------------------------------------");
            System.out.println("BASE DE DADOS DEPOIS DO UPDATE: ");

            PreparedStatement pstm3 = conn.prepareStatement(sql1);
            ResultSet rs2 = pstm3.executeQuery();

            while (rs2.next()){
                int tia = rs2.getInt("tia");
                String hora_entrada = rs2.getString("hora_entrada");
                String hora_saida = rs2.getString("hora_saida");

                System.out.println("TIA: "+tia+" - Hora de Entrada: "+hora_entrada+" - Hora de Saída: "+hora_saida);
            }

            String delete = "DELETE FROM regist_frequencia WHERE tia = '31809999'";
            PreparedStatement pstm4 = conn.prepareStatement(delete);
            pstm4.execute(delete);

            System.out.println("------------------------------------------------------------------");
            System.out.println("BASE DE DADOS DEPOIS DO DELETE:");

            PreparedStatement pstmq = conn.prepareStatement(sql1);
            ResultSet rsq = pstmq.executeQuery();

            while (rsq.next()){
                int tia = rsq.getInt("tia");
                String hora_entrada = rsq.getString("hora_entrada");
                String hora_saida = rsq.getString("hora_saida");

                System.out.println("TIA: "+tia+" - Hora de Entrada: "+hora_entrada+" - Hora de Saída: "+hora_saida);
            }
            rs.close();
            conn.close();

        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
