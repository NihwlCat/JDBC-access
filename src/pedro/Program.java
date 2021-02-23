package pedro;

import db.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Program {
    public static void main(String[] args) {
        Connection conn = null;
        Statement statement = null; // Preparar consulta SQL
        ResultSet rs = null; // Guardar resultado da consulta

        try {
            conn = DB.getConn();
            statement = conn.createStatement();
            rs = statement.executeQuery("select * from department"); // Entrar com comando SQL

            while(rs.next()){
                System.out.println(rs.getInt("Id") + ", " + rs.getString("name"));
            }
        } catch (SQLException e){
            e.printStackTrace();

        } finally {
            DB.closeConn();
            DB.closeResultSet(rs);
            DB.closeStatement(statement);
        }





    }
}
