package launcher;

import controller.AdminController;
import controller.BookController;
import controller.UserController;
import database.DatabaseConnectionFactory;
import javafx.stage.Stage;
import mapper.RoleMapper;
import mapper.UserMapper;
import repository.book.BookRepository;
import repository.order.OrderRepository;
import repository.order.OrderRepositoryMySQL;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySQL;
import service.book.BookService;
import service.order.OrderService;
import service.order.OrderServiceImpl;
import service.user.UserService;
import service.user.UserServiceImpl;
import view.UserView;
import view.model.RoleDTO;
import view.model.UserDTO;
import java.sql.Connection;
import java.util.List;

public class UserComponentFactory {

    private final UserView userView;
    private final UserController userController;
;
    private final OrderService orderService;
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final RightsRolesRepository rightsRolesRepository;
    private final UserRepository userRepository;
    private static UserComponentFactory instance;

    public static UserComponentFactory getInstance(Boolean componentsForTest, Stage stage){
        if (instance == null){
            instance = new UserComponentFactory(componentsForTest,stage);
        }
        return instance;
    }

    public UserComponentFactory(Boolean componentsForTest, Stage stage){
        Connection connection = DatabaseConnectionFactory.getConnectionWrapper(componentsForTest).getConnection();

        //order
        this.orderRepository = new OrderRepositoryMySQL(connection);
        this.orderService = new OrderServiceImpl(orderRepository);

        //users
        this.rightsRolesRepository = new RightsRolesRepositoryMySQL(connection);
        this.userRepository = new UserRepositoryMySQL(connection,rightsRolesRepository);
        this.userService = new UserServiceImpl(userRepository);


        // view & controller
        List<UserDTO> userDTOs = UserMapper.convertUserListToUserDTOList(this.userService.findAll());
        List<RoleDTO> rolesDTOs = RoleMapper.convertRoleListToRoleDTOList(this.userService.findAllRoles());
        this.userView = new UserView(stage,userDTOs, rolesDTOs);
        this.userController = new UserController(userView, userService,orderService);
    }

    public UserView getUserView() {
        return userView;
    }

    public UserController getUserController() {
        return userController;
    }

    public static UserComponentFactory getInstance() {
        return instance;
    }
}