package application;

import db.DB;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class Program {

    public static void main(String[] args) {

        Connection conn = null; //conn; st;rs; nome de uma variavel

        Statement st = null; // criar a conecção
        ResultSet rs = null;// armazenar os dados

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
        }

        System.out.println("-----------------------------------------------------------------------------------------");
        PreparedStatement ps = null;
        SimpleDateFormat aniversario = new SimpleDateFormat("dd/MM/yyyy");

        try {
            ps = conn.prepareStatement( "INSERT INTO seller" +("Name, Email, BirthDate, BaseSalary, DepartmentId") + "VALUES" + "(?,?,?,?,?"); //

            ps.setString(1,  "Carol");
            ps.setString(2, "carol@gmail");
            ps.setDate(3, new java.sql.Date(aniversario.parse("22/06/2007").getTime()));
            ps.setDouble(4, Double.parseDouble("10000.0"));
            ps.setInt(5, 5);
            ps.executeQuery();

            int linhasAfetadas = ps.executeUpdate();
            System.out.println("Finalizado");


        } catch (SQLException | ParseException e) {
            e.printStackTrace();

        }finally {
            DB.closeStatement();

        try {
            ps = conn.prepareStatement("UPDATE seller"+"SET BaseSalary = BaseSalary + ?" + "WHERE" + "DepartmentID" )
        }

            //consultar dados

        }
    }
}