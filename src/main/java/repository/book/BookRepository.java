package repository.book;

import model.Book;

import java.util.*;
//Repository - Se ocupa doar cu citirea din baza de date
public interface BookRepository {
    List<Book> findAll(); //returneaza o lista de carti
    Optional<Book> findById(Long id); //se poate sa avem o carte sau sa lipseasca (optional)
    boolean save(Book book);
    boolean delete(Book book);
    void removeAll();
    boolean deleteById(Long bookId);
    boolean updateStock(Long bookId, int quantity);
}
