package com.chessmaster.chessmaster.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chessmaster.chessmaster.Model.Board;
import com.chessmaster.chessmaster.Model.Piece;
import com.chessmaster.chessmaster.Model.User;
import com.chessmaster.chessmaster.Service.ChessService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/chess")
public class chessController {

    @Autowired
    private ChessService chessService;
    
    @GetMapping("/start/{player1}/{player2}")
    public ResponseEntity<String> startGame(@PathVariable String player1, @PathVariable String player2) {
        Boolean ck=this.chessService.initializeGame(player1, player2);

        if(ck){
            return ResponseEntity.ok("Game started between " + player1 + " and " + player2);
        }
        else{
            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUser() {
        if(this.chessService.getUser()!=null){
            return ResponseEntity.ok(this.chessService.getUser());
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getPiece/{row}/{col}")
    public Piece getPiece(@PathVariable int row, @PathVariable int col) {
        return this.chessService.getPiece(row, col);
    }

    @PostMapping("/move/{or}/{oc}/{nr}/{nc}")
    public String makeMove( @PathVariable int or, @PathVariable int oc,@PathVariable int nr, @PathVariable int nc) {
        

        Boolean ck=this.chessService.makeMove( or, oc, nr, nc);
        if(ck==false){
            return "Invalid Move!";
        }
        else{
            return "Move made"   + " from " + or+ "," + oc + " to " + nr + "," + nc;
        }  
    }

    @GetMapping("/board")
    public Board getCurrentState() {
        return this.chessService.getCurrentState();
    }

    @GetMapping("/turn")
    public String getTurn(){
        return this.chessService.getTurn();
    }

}
