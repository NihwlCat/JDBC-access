package pedro;

import db.DB;
import db.DBException;
import db.DbIntegrityException;

import java.sql.*;

public class Program {
    public static void main(String[] args) {

        Statement st = null;
        Connection conn = null;
        try {
            conn = DB.getConn();
            conn.setAutoCommit(false); // Desabilita a regra de update automático ao banco de dados.
            st = conn.createStatement();

            int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");

            /*
             int x = 1;
             if(x == 1){
                throw new SQLException("Fake error");
            } */

            int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");

            conn.commit(); // Cria o update no banco de dados.

            System.out.println("!rows1 " + rows1);
            System.out.println("!rows2 " + rows2);


        } catch (SQLException e){
            try {
                // O método .rollback só pode ser chamado quando o método .setAutoCommit estiver false
                conn.rollback();
                throw new DBException("Transaction rolled back! " + e.getMessage());
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        } finally {
            DB.closeStatement(st);
            DB.closeConn();
        }
    }
}
