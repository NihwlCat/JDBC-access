package pedro;

import db.DB;
import java.sql.*;

public class Program {
    public static void main(String[] args) {
        Connection conn;
        PreparedStatement st = null;
        try {
            conn = DB.getConn();
            st = conn.prepareStatement(
                    "UPDATE seller "
                    + "SET BaseSalary = BaseSalary + ?"
                    + "WHERE"
                    + "(DepartmentId = ?)");
            // Banco de dados precisa de restrição para não atualizar todos os dados

            st.setDouble(1, 200.0);
            st.setInt(2, 2);

            int ra = st.executeUpdate();

            System.out.println("Done: " + ra);

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            DB.closeStatement(st);
            DB.closeConn();
        }
        
    }
}
