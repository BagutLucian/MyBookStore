package repository.order;
import model.Book;
import model.Order;

public interface OrderRepository {
    boolean save(Order order);
}
