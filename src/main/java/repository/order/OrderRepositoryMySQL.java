package repository.order;
import model.Book;
import model.Order;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderRepositoryMySQL implements OrderRepository{
    private Connection connection;
    public OrderRepositoryMySQL(Connection connection)
    {
        this.connection = connection;
    }

    @Override
    public boolean save(Order order) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = order.getTransactionDate().format(formatter);
        String newSql = "INSERT INTO `order` (book_id, user_id, transactionDate) VALUES (" + order.getBookId() + "," + order.getUserId() + ",'" + formattedDateTime + "');";

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(newSql);
        } catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
