package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import mapper.BookMapper;
import model.Book;
import model.Order;
import service.book.BookService;
import service.order.OrderService;
import view.BookView;
import view.model.BookDTO;
import view.model.builder.BookDTOBuilder;
import service.user.AuthenticationService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BookController {
    private final BookView bookView;
    private final BookService bookService;
    private final OrderService orderService;
    private final AuthenticationService authenticationService;
    public BookController(BookView bookView, BookService bookService,OrderService orderService,AuthenticationService authenticationService)
    {
        this.bookView=bookView;
        this.bookService=bookService;
        this.orderService=orderService;
        this.authenticationService=authenticationService;

        this.bookView.addSaveButtonListener(new SaveButtonListener());
        this.bookView.addDeleteButtonListener(new DeleteButtonListener());
        this.bookView.addOrderButtonListener(new OrderButtonListener());
    }
    private class SaveButtonListener implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event)
        {
            String title = bookView.getTitle();
            String author = bookView.getAuthor();
            Integer stock = bookView.getStock();
            Integer price = bookView.getPrice();

            if(title.isEmpty() || author.isEmpty())
            {
                bookView.addDisplayAlertMessage("Save Error","Problem at Author or Title fields","Can not have an empty Title or Author field.");
            } else{
                BookDTO bookDTO = new BookDTOBuilder()
                        .setTitle(title)
                        .setAuthor(author)
                        .setPrice(price)
                        .setStock(stock)
                        .build();
                boolean savedBook = bookService.save(BookMapper.convertBookDTOToBook(bookDTO));

                if(savedBook)
                {
                    List<Book> newBooks = bookService.findAll();
                    // reload list
                    bookView.reloadBooks(BookMapper.convertBookListToBookDTOList(newBooks));

                    bookView.addDisplayAlertMessage("Save Successful","Book Added","Book was successfully added to the database.");


                } else{
                    bookView.addDisplayAlertMessage("Save Error","Problem at adding book","There was a problem at adding the book to the database. Please try again.");
                }
            }
        }
    }

    private class DeleteButtonListener implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event)
        {
            BookDTO bookDTO = (BookDTO) bookView.getBookTableView().getSelectionModel().getSelectedItem();
            if(bookDTO != null)
            {
                boolean deletionSuccessful = bookService.delete(BookMapper.convertBookDTOToBook(bookDTO));
                if(deletionSuccessful)
                {
                    bookView.addDisplayAlertMessage("Delete Successful","Book Deleted","Book was successfully deleted from the database.");
                    bookView.removeBookFromObservableList(bookDTO);
                } else
                {
                    bookView.addDisplayAlertMessage("Delete Error","Problem at deleting the book","There was a problem with the database. Please try again.");


                }
            } else {
                bookView.addDisplayAlertMessage("Delete Error","Problem at deleting book","You must select a book before pressing the delete button.");

            }
        }
    }
    private class OrderButtonListener implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event)
        {
            BookDTO selectedBook = (BookDTO) bookView.getBookTableView().getSelectionModel().getSelectedItem();

            if (selectedBook == null) {
                bookView.addDisplayAlertMessage(
                        "Order Error",
                        "No Book Selected",
                        "You must select a book before pressing the Order button."
                );
            } else if(selectedBook.getStock()<=0)
            {
                bookView.addDisplayAlertMessage(
                        "Order Error",
                        "Out of Stock",
                        "Return Later."
                );
            }
            else
            {
                Order o = new Order(selectedBook.getId(),authenticationService.getLoggedUser().getId(), LocalDateTime.now());
                boolean deletionSuccessful = orderService.save(o);

                if (deletionSuccessful) {
                    bookService.updateStock(selectedBook.getId());

                    selectedBook.setStock(selectedBook.getStock()-1);

                    bookView.addDisplayAlertMessage(
                            "Order Successful",
                            "Book Ordered",
                            "The book '" + selectedBook.gettitle() + "' by " + selectedBook.getAuthor() + " has been successfully ordered."
                    );

                } else {
                    // Dacă ștergerea a eșuat, afișează un mesaj de eroare
                    bookView.addDisplayAlertMessage(
                            "Order Error",
                            "Database Issue",
                            "There was a problem processing your order. Please try again."
                    );
                }
            }
        }
    }
}



