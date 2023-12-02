import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;


public class DBHelper {

    //Получение данных из системы и из app.props, пыталась передать url и port из app.props
    private static String url = System.getProperty("db.url");
    private static String appURL = System.getProperty("app.url");
    private static String appPORT = System.getProperty("app.port");
    private static String userDB = System.getProperty("app.userDB");
    private static String password = System.getProperty("app.password");

    //Удаляем данные из таблицы БД
    public static void clearData() {
        try (Connection conn = DriverManager.getConnection(url, userDB, password)) {
            QueryRunner runner = new QueryRunner();
            runner.update(conn, "DELETE FROM credit_request_entity;");
            runner.update(conn, "DELETE FROM payment_entity;");
            runner.update(conn, "DELETE FROM order_entity;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //проверяем статус платежа в таблице БД
    public static String checkPaymentStatus() {
        try (Connection conn = DriverManager.getConnection(url, userDB, password)) {
            QueryRunner runner = new QueryRunner();
            String paymentDataSQL = "SELECT status FROM payment_entity;";
            String status = runner.query(conn, paymentDataSQL, new ScalarHandler<>());
            return status;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Проверяем статус кредита в таблице БД
    public static String checkCreditStatus() {
        try (Connection conn = DriverManager.getConnection(url, userDB, password)) {
            QueryRunner runner = new QueryRunner();
            String creditDataSQL = "SELECT status FROM credit_request_entity;";
            String status = runner.query(conn, creditDataSQL, new ScalarHandler<>());
            return status;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
