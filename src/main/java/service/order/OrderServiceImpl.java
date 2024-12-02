package service.order;

import model.Book;
import model.Order;
import repository.book.BookRepository;
import repository.book.BookRepositoryMySQL;
import repository.order.OrderRepository;
import repository.security.RightsRolesRepository;
import repository.user.UserRepository;
import service.user.AuthenticationService;
import service.user.AuthenticationServiceImpl;

import java.time.LocalDateTime;

public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository)
    {
        this.orderRepository = orderRepository;
    }
    @Override
    public boolean save(Order order) {

        return orderRepository.save(order);
    }
}
