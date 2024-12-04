package service.order;

import model.Book;
import model.EmployeeSales;
import model.Order;

import java.util.List;

public interface OrderService {
    boolean save(Order order);
   // boolean delete(Book book);
    List<EmployeeSales> getEmployeeSalesReport();
}
