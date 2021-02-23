package pedro;

import db.DB;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Connection conn = null;
        PreparedStatement statement = null; // Preparar consulta SQL e inserir dados posteriormente.
        try {
            conn = DB.getConn();

            statement = conn.prepareStatement(
                    "INSERT INTO seller "
                    + "(Name, Email, BirthDate, BaseSalary, DepartmentId)"
                    + "VALUES "
                    + "(?, ?, ?, ?, ?)"); // Comandos SQL

            // Sobrecarga de preparedStatement (comando_SQL,Statement RETURN_GENERATED_KEY)
            // Retornar a chave gerada pela inserção

            statement.setString(1,"Matheus Bichuette");
            statement.setString(2,"bichuette@gmail.com");
            statement.setDate(3, new Date(sdf.parse("22/04/1985").getTime()));
            statement.setDouble(4,3000.97);
            statement.setInt(5,4);

            int rowsAffected = statement.executeUpdate(); // Executar atualização da tabela que retorna o número de linhas alteradas.
            System.out.println("Done, " + rowsAffected);

            /*
            * ResultSet rs = sr.getGeneratedKeys();
            * while (rs.next()){ ind id = rs.getInt(1) }
            * Uma tabela auxiliar contendo uma coluna com os Ids
            * */


        } catch (SQLException e){
            e.printStackTrace();
        } catch (ParseException e){
            e.printStackTrace();
        } finally {
            DB.closeStatement(statement);
            DB.closeConn();
        }

    }
}
