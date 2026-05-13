package application;

import db.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class Program {

    public static void main(String[] args) {

        Connection conn = null; //conn; st;rs; nome de uma variavel

        Statement st = null;
        ResultSet rs = null;

        try {
            conn = DB.getConnection();//criar conecçaõ

            st = conn.createStatement();//metodo dentro da classe conection, vai ter como cunsulta no banco

            rs = st.executeQuery("select * from seller"); // armazem o resultado da consulta, ele tá resebendo o resultado da onsulta. ele retorna os departamentos no banco

            while (rs.next()) {
                System.out.println(rs.getInt("id") + "-" +
                        rs.getString("Name") + "-" +
                        rs.getString("Email")+ "-" +
                        rs.getDate("BirthDate") + "-" +
                        rs.getDouble("BaseSalary") + "-" +
                        rs.getInt("DepartmentId"));//ele ta imprimindo

            }


        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            DB.closeStatement(st);// vai executar tudo que tiver certo no try
            DB.closeResultSet(rs);
            DB.closeConnection();

            //consultar dados

        }
    }
}