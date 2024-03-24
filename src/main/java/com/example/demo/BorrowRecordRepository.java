package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
    // 查找特定用户的所有借书记录
    List<BorrowRecord> findByUserId(Long userId);

    // 查找特定书籍的所有借书记录
    List<BorrowRecord> findByBookId(Long bookId);

    // 查找特定用户尚未归还的借书记录
    List<BorrowRecord> findByUserIdAndReturnDateIsNull(Long userId);

    // 查找特定书籍尚未归还的借书记录
    List<BorrowRecord> findByBookIdAndReturnDateIsNull(Long bookId);

    // 使用JPQL进行更复杂的查询
    @Query("SELECT br FROM BorrowRecord br WHERE br.userId = ?1 AND br.returnDate IS NULL")
    List<BorrowRecord> findActiveBorrowingsByUserId(Long userId);
}
