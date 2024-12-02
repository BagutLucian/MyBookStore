package launcher;

import controller.BookController;
import database.DatabaseConnectionFactory;
import javafx.stage.Stage;
import mapper.BookMapper;
import repository.book.BookRepository;
import repository.book.BookRepositoryMySQL;
import repository.order.OrderRepository;
import repository.order.OrderRepositoryMySQL;
import service.book.BookService;
import service.book.BookServiceImpl;
import service.order.OrderService;
import service.order.OrderServiceImpl;
import service.user.AuthenticationService;
import service.user.AuthenticationServiceImpl;
import view.BookView;
import view.model.BookDTO;

import java.sql.Connection;
import java.util.List;

//clasa singleton
public class ComponentFactory {
    private final BookView bookView;
    private final BookController bookController;
    private final BookRepository bookRepository;
    private  final OrderRepository orderRepository;
    private final BookService bookService;
    private final OrderService orderService;
    private static ComponentFactory instance;
    //implementare lazy load thread save singleton
    public static ComponentFactory getInstance(Boolean componentsForTest, Stage primaryStage)
    {
      if (instance == null)
      {
          instance = new ComponentFactory(componentsForTest, primaryStage);
      }
      return instance;
    }

    //NO BLL in Controller
    public ComponentFactory(Boolean componentsForTest, Stage primaryStage)
    {
        Connection connection = DatabaseConnectionFactory.getConnectionWrapper(componentsForTest).getConnection();
        this.bookRepository=new BookRepositoryMySQL(connection);
        this.orderRepository=new OrderRepositoryMySQL(connection);
        this.bookService=new BookServiceImpl(bookRepository);
        this.orderService=new OrderServiceImpl(this.orderRepository);
        List<BookDTO> bookDTOS = BookMapper.convertBookListToBookDTOList(bookService.findAll());
        this.bookView = new BookView(primaryStage,bookDTOS);


        AuthenticationService authserv=LoginComponentFactory.getInstance(componentsForTest,primaryStage).getAuthenticationService();

        this.bookController= new BookController(bookView,bookService,orderService,authserv);
    }

    public BookView getBookView() {
        return bookView;
    }

    public BookController getBookController() {
        return bookController;
    }

    public BookRepository getBookRepository() {
        return bookRepository;
    }

    public BookService getBookService() {
        return bookService;
    }
}
