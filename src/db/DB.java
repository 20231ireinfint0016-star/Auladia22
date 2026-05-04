package db;

import java.io.FileInputStream;
import java.util.Properties;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DB {
    public static Connection conn = null; // Criar variavel para a conec, armazena conec


    public static Connection getConnection(){ // esse comando serve para conectar com o banco de daDOS
        if(conn == null){// para verificar se estar conectado, null nao esta
            try {

                Properties props = loadProperties();

                String url = props.getProperty("dburl");

                conn = DriverManager.getConnection(url,props); // parte principal que coneecta com o banco
                //variavel conn recebe uma conexao
                // criado 2 variaveis pq? pra nao consumir recurso desnecessario

            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
        return conn; //
    }

    public static void  closeConnection(){
        if(conn != null){
            try {
                conn.close();
            }catch (SQLException e){
                throw  new DbException(e.getMessage());
            }
        }
    }
    public static Properties loadProperties() {
        try (FileInputStream fs = new FileInputStream("db.properties")) {
            Properties props = new Properties();

            props.load(fs);

            return props;

        }catch (IOException e) {
            throw new DbException(e.getMessage());
        }
    }
}

//essa classe cria uma conecção com o banco de dados
