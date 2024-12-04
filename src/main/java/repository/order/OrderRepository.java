package repository.order;
import model.Book;
import model.EmployeeSales;
import model.Order;

import java.util.List;

public interface OrderRepository {
    boolean save(Order order);
    public List<EmployeeSales> getEmployeeSalesReport();
}
