package db;

import java.io.FileInputStream;
import java.util.Properties;

public class DB {

    public static Properties loadProperties() {
        try (FileInputStream fs = new FileInputStream("db.properties")) {
            Properties props = new Properties();

            props.load(fs);

            return props;

        }catch (IDException e) {
            throw new DbException(e.getMessage());
        }
    }
}

