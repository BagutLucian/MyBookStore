package repository.order;
import model.Book;
import model.EmployeeSales;
import model.Order;
import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<EmployeeSales> getEmployeeSalesReport() {
        List<EmployeeSales> employeeSalesList = new ArrayList<>();
        String sql = "SELECT u.username AS employee_name, COUNT(o.id) AS books_sold, SUM(b.price) AS total_sales " +
                "FROM `order` o " +
                "JOIN user u ON o.user_id = u.id " +
                "JOIN book b ON o.book_id = b.id " +
                "GROUP BY u.id";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String employeeName = resultSet.getString("employee_name");
                int booksSold = resultSet.getInt("books_sold");
                double totalSales = resultSet.getDouble("total_sales");

                EmployeeSales employeeSales = new EmployeeSales(employeeName, booksSold, totalSales);
                employeeSalesList.add(employeeSales);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeSalesList;
    }
}
