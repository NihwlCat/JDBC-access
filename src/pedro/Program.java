package pedro;

import db.DB;
import db.DbIntegrityException;

import java.sql.*;

public class Program {
    public static void main(String[] args) {

        /*
        * Elementos com chave estrangeira associadas a uma outra chave primária não
        * podem ser excluidos. Exemplo: Departamentos que possuem vendedores não
        * podem ser excluidos pois estaria gerando uma falha de integridade referencial.
        */

        Connection conn;
        PreparedStatement st = null;
        try {
            conn = DB.getConn();
            st = conn.prepareStatement(
                    "DELETE FROM "
                    +"department WHERE Id = ?"
            );

             /*st = conn.prepareStatement(
                    "INSERT INTO department (Name) VALUES (?) "
            ); */


            st.setInt(1, 5);

            /*st.setString(1,"Teste1"); */

            int ra = st.executeUpdate();

            System.out.println("Done: " + ra);

        } catch (SQLException e){
            throw new DbIntegrityException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeConn();
        }

    }
}
