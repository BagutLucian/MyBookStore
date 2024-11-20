package repository.book;

import model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookRepositoryMock implements BookRepository{
    private final List<Book>books; //final pentru a face variabila books IMMUTABLE(daca ii setam o valoare nu
                                   // putem reseta in alta valoare)
                                   //Functional Programming (pure function) - 1) pentru acelasi input => acelasi output
                                   //                                       - 2) no side effects (nu se modifica nicio alta variabila globala/statica
    public BookRepositoryMock()
    {
        books = new ArrayList<>();
    }
    @Override
    public List<Book> findAll() {
        return books;
    }
    @Override
    public Optional<Book> findById(Long id) {
        return books.parallelStream()
                .filter(it -> it.getId().equals(id)) //lambda function
                .findFirst();
    }

    @Override
    public boolean save(Book book) {
        return books.add(book);
    }

    @Override
    public boolean delete(Book book) {
        return books.remove(book);
    }

    @Override
    public void removeAll() {
        books.clear();
    }
}
