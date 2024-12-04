package launcher;

import controller.AdminController;
import controller.BookController;
import database.DatabaseConnectionFactory;
import javafx.stage.Stage;
import mapper.BookMapper;
import mapper.UserMapper;
import repository.book.BookRepository;
import repository.book.BookRepositoryCacheDecorator;
import repository.book.BookRepositoryMySQL;
import repository.book.Cache;
import repository.order.OrderRepository;
import repository.order.OrderRepositoryMySQL;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySQL;
import service.book.BookService;
import service.book.BookServiceImpl;
import service.order.OrderService;
import service.order.OrderServiceImpl;
import service.user.UserService;
import service.user.UserServiceImpl;
import view.AdminView;
import view.BookView;
import view.model.BookDTO;
import service.user.AuthenticationService;
import view.model.UserDTO;

import java.sql.Connection;
import java.util.List;

public class AdminComponentFactory {

    private final AdminView adminView;
    private final AdminController adminController;


    private static AdminComponentFactory instance;
    public static AdminComponentFactory getInstance(Boolean componentsForTest, Stage stage){
        if (instance == null){
            instance = new AdminComponentFactory(componentsForTest, stage);
        }
        return instance;
    }

    public static void resetInstance(){
        instance = null;
    }

    public AdminComponentFactory(Boolean componentsForTest, Stage stage){
        // view & controller
        this.adminView = new AdminView(stage);
        this.adminController = new AdminController(adminView);
    }

    public AdminView getBookView() {
        return adminView;
    }

    public AdminController getBookController() {
        return adminController;
    }


    public static AdminComponentFactory getInstance() {
        return instance;
    }
}