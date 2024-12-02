package service.book;

import model.Book;

import java.util.*;

//in aceasta interfata avem metodele pe care le poate folosi presentation
//Service - parte de procesare logica
public interface BookService {
    List<Book> findAll();
    Book findById(Long id);
    boolean save(Book book);
    boolean delete(Book book);
    boolean deleteByID(Long bookID);
    int getAgeOfBook(Long id);
    boolean updateStock(Long bookID);
}
