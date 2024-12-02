package model;

import java.time.LocalDateTime;

public class Order {
    private int id;
    private Long bookId;
    private Long userId;
    private LocalDateTime transactionDate;

    public Order(Long bookId, Long userId, LocalDateTime transactionDate) {
        this.bookId = bookId;
        this.userId = userId;
        this.transactionDate = transactionDate;
    }
    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }
    @Override
    public String toString()
    {
        return "Order: Id: " + id + " Book: " + bookId + " User: " + userId + " Transaction Date: " + transactionDate;
    }
}
