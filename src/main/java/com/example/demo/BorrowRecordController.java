package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/borrowRecords")
public class BorrowRecordController {

    @Autowired
    private BorrowRecordRepository borrowRecordRepository;

    @Autowired
    private BookRepository bookRepository;

    @PostMapping("/borrow")
    public ResponseEntity<String> borrowBook(@RequestBody BorrowRecord record) {
        Optional<Book> bookOptional = bookRepository.findById(record.getBookId());
        if (!bookOptional.isPresent()) {
            return ResponseEntity.badRequest().body("Book not found");
        }

        Book book = bookOptional.get();
        if (book.getQuantity() <= 0) {
            return ResponseEntity.badRequest().body("Book is not available for borrowing");
        }

        // Decrease book quantity
        book.setQuantity(book.getQuantity() - 1);
        bookRepository.save(book);

        // Set borrow date to today
        record.setBorrowDate(LocalDate.now());
        borrowRecordRepository.save(record);

        return ResponseEntity.ok("Book borrowed successfully");
    }

    @PostMapping("/return")
    public ResponseEntity<String> returnBook(@RequestBody BorrowRecord record) {
        Optional<Book> bookOptional = bookRepository.findById(record.getBookId());
        if (!bookOptional.isPresent()) {
            return ResponseEntity.badRequest().body("Book not found");
        }

        Book book = bookOptional.get();
        // Increase book quantity
        book.setQuantity(book.getQuantity() + 1);
        bookRepository.save(book);

        // Optionally, you could update the return date for the record
        // record.setReturnDate(LocalDate.now());
        // borrowRecordRepository.save(record);

        return ResponseEntity.ok("Book returned successfully");
    }
}
