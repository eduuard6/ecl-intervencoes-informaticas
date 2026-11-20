package pt.ecl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** Ligação à base de dados. As credenciais são configuradas no servidor. */
public final class BaseDados {
    private BaseDados() { }

    public static Connection ligar() throws SQLException {
        String url = System.getenv("ECL_DB_URL");
        String utilizador = System.getenv("ECL_DB_USER");
        String password = System.getenv("ECL_DB_PASSWORD");

        if (url == null || url.isBlank()) {
            throw new SQLException("Falta configurar a variável ECL_DB_URL.");
        }
        if (utilizador == null) utilizador = "";
        if (password == null) password = "";

        return DriverManager.getConnection(url, utilizador, password);
    }
}
