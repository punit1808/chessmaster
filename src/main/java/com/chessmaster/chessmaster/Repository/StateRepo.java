package com.chessmaster.chessmaster.Repository;
import com.chessmaster.chessmaster.Model.Piece;
import com.chessmaster.chessmaster.Model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

public interface StateRepo extends JpaRepository<State, Long> {
    @Modifying
    @Transactional
    @Query(value = "ALTER TABLE chess_state AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();
}
