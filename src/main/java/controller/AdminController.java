package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import launcher.AdminComponentFactory;
import launcher.EmployeeComponentFactory;
import launcher.LoginComponentFactory;
import launcher.UserComponentFactory;
import mapper.BookMapper;
import model.Book;
import model.Order;
import service.book.BookService;
import service.order.OrderService;
import view.AdminView;
import view.BookView;
import view.model.BookDTO;
import view.model.builder.BookDTOBuilder;
import service.user.AuthenticationService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AdminController {
    private final AdminView adminView;

    public AdminController(AdminView adminView)
    {
        this.adminView=adminView;
        this.adminView.addViewUsersButtonListener(new AdminController.ViewUsersButtonListener());
        this.adminView.addViewBooksButtonListener(new AdminController.ViewBooksButtonListener());


    }
    private class ViewBooksButtonListener implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event)
        {
            EmployeeComponentFactory.getInstance(LoginComponentFactory.getComponentsForTests(), LoginComponentFactory.getStage());
        }
    }

    private class ViewUsersButtonListener implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event)
        {
            UserComponentFactory.getInstance(LoginComponentFactory.getComponentsForTests(), LoginComponentFactory.getStage());
        }
    }

}