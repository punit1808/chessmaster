package com.chessmaster.chessmaster.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chessmaster.chessmaster.Model.State;
import com.chessmaster.chessmaster.Model.Board;
import com.chessmaster.chessmaster.Model.Piece;
import com.chessmaster.chessmaster.Model.User;
import com.chessmaster.chessmaster.Repository.StateRepo;

@Service
public class ChessService {

    private User user1;
    private User user2;
    private Board board;
    @Autowired
    private StateRepo stateRepo;

    public Boolean initializeGame(String player1, String player2) {
        user1 = new User(player1, "white", true);
        user2 = new User(player2, "black", false);
        board = new Board();
        stateRepo.deleteAll();
        stateRepo.resetAutoIncrement();

        if(user1!=null && user2!=null && board!=null){
            return true;
        }
        else{
            return false;
        }   
    }

    public User getUser() {
        return user1;
    }

    public Piece getPiece(int row, int col) {
        return board.getPiece(row, col);
    }

    public Boolean whiteRook(int or, int oc, int nr, int nc) {
        if(or==nr){
            if(nc> oc){
                // check in right
                for(int i=oc+1;i<=nc;i++) {
                    if(board.getPiece(or, i)!=null && board.getPiece(or, i).getColor().equals(user1.getColor())) {
                        return false;
                    }
                    else if(board.getPiece(or,i)!=null && board.getPiece(or,i).getColor().equals(user2.getColor()) && i!=nc) {
                        return false;
                    }
                }
                // return true;
            }
            else{
                // check in left
                for(int i=nc;i<oc;i++) {
                    if(board.getPiece(or, i)!=null && board.getPiece(or, i).getColor().equals(user1.getColor())) {
                        return false;
                    }
                    else if(board.getPiece(or,i)!=null && board.getPiece(or,i).getColor().equals(user2.getColor()) && i!=nc) {
                        return false;
                    }
                }
                // return true;
            }
        }
        else if(oc==nc){
            if(nr> or){
                // check in down
                for(int i=or+1;i<=nr;i++) {
                    if(board.getPiece(i, oc)!=null && board.getPiece(i, oc).getColor().equals(user1.getColor())) {
                        return false;
                    }
                    else if(board.getPiece(i,oc)!=null && board.getPiece(i,oc).getColor().equals(user2.getColor()) && i!=nr) {
                        return false;
                    }
                }
                // return true;
            }
            else{
                // check in up
                for(int i=nr;i<or;i++) {
                    if(board.getPiece(i, oc)!=null && board.getPiece(i, oc).getColor().equals(user1.getColor())) {
                        return false;
                    }
                    else if(board.getPiece(i,oc)!=null && board.getPiece(i,oc).getColor().equals(user2.getColor()) && i!=nr) {
                        return false;
                    }
                }
                // return true;
            }
        }
        else{
            return false;
        }
        return true;
    }

    public Boolean whiteKnight(int or, int oc, int nr, int nc) {
        if((Math.abs(or-nr)==2 && Math.abs(oc-nc)==1) || (Math.abs(or-nr)==1 && Math.abs(oc-nc)==2)) {
            // check if the destination cell is empty or occupied by opponent's piece
            Piece destPiece = board.getPiece(nr, nc);
            if(destPiece!=null && destPiece.getColor().equals(user1.getColor())) {
                return false;
            }
        }
        else{
            return false;
        }
        return true;
    } 

    public Boolean whiteBishop(int or, int oc, int nr, int nc) {
        if(Math.abs(or-nr)==Math.abs(oc-nc)) {
            // check in diagonal
            int rowStep = (nr > or) ? 1 : -1;
            int colStep = (nc > oc) ? 1 : -1;
            for(int i=or+rowStep, j=oc+colStep; i!=nr && j!=nc; i+=rowStep, j+=colStep) {
                if(board.getPiece(i, j)!=null) {
                    return false;
                }
            }
        }
        else{
            return false;
        }
        return true;
    }

    public Boolean whiteQueen(int or,int oc, int nr, int nc) {
        if(or==nr){
            if(nc> oc){
                // check in right
                for(int i=oc+1;i<=nc;i++) {
                    if(board.getPiece(or, i)!=null && board.getPiece(or, i).getColor().equals(user1.getColor())) {
                        return false;
                    }
                    else if(board.getPiece(or,i)!=null && board.getPiece(or,i).getColor().equals(user2.getColor()) && i!=nc) {
                        return false;
                    }
                }
                // return true;
            }
            else{
                // check in left
                for(int i=nc;i<oc;i++) {
                    if(board.getPiece(or, i)!=null && board.getPiece(or, i).getColor().equals(user1.getColor())) {
                        return false;
                    }
                    else if(board.getPiece(or,i)!=null && board.getPiece(or,i).getColor().equals(user2.getColor()) && i!=nc) {
                        return false;
                    }
                }
                // return true;
            }
        }
        else if(oc==nc){
            if(nr> or){
                // check in down
                for(int i=or+1;i<=nr;i++) {
                    if(board.getPiece(i, oc)!=null && board.getPiece(i, oc).getColor().equals(user1.getColor())) {
                        return false;
                    }
                    else if(board.getPiece(i,oc)!=null && board.getPiece(i,oc).getColor().equals(user2.getColor()) && i!=nr) {
                        return false;
                    }
                }
                // return true;
            }
            else{
                // check in up
                for(int i=nr;i<or;i++) {
                    if(board.getPiece(i, oc)!=null && board.getPiece(i, oc).getColor().equals(user1.getColor())) {
                        return false;
                    }
                    else if(board.getPiece(i,oc)!=null && board.getPiece(i,oc).getColor().equals(user2.getColor()) && i!=nr) {
                        return false;
                    }
                }
                // return true;
            }
        }

        else if(Math.abs(or-nr)==Math.abs(oc-nc)) {
            // check in diagonal
            int rowStep = (nr > or) ? 1 : -1;
            int colStep = (nc > oc) ? 1 : -1;
            for(int i=or+rowStep, j=oc+colStep; i!=nr && j!=nc; i+=rowStep, j+=colStep) {
                if(board.getPiece(i, j)!=null) {
                    return false;
                } 
            }
        }
        else{
            return false;
        }
        return true;
    }

    public Boolean whiteKing(int or, int oc, int nr, int nc) {
        String pieceType = "king";
                    
        if(Math.abs(or-nr)<=1 && Math.abs(oc-nc)<=1) {
            Piece destPiece = board.getPiece(nr, nc);
            if(destPiece!=null && destPiece.getColor().equals(user1.getColor())) {
                return false;
            }
            else if(destPiece!=null && destPiece.getName().equals(pieceType)) {
                return false;
            }
        }
        else{
            return false;
        }
        return true;
    }       

    public Boolean whitePawn(int or, int oc, int nr, int nc) {
        if(or==1 && nr==3 && nc==oc) {
            // check if the destination cell is empty or occupied by opponent's piece
            Piece destPiece = board.getPiece(nr, nc);
            Piece destPiece0 = board.getPiece(or+1, nc);
            if(destPiece0!=null || destPiece!=null) {
                return false;
            }
        }
        else if(nr==or+1 && nc==oc) {
            // check if the destination cell is empty or occupied by opponent's piece
            Piece destPiece = board.getPiece(nr, nc);
            if(destPiece!=null) {
                return false;
            }
        }
        else if(nr==or+1 && Math.abs(oc-nc)==1) {
            // check if the destination cell is empty or occupied by opponent's piece
            Piece destPiece = board.getPiece(nr, nc);
            if(destPiece==null) {
                return false;
            }
            else if(destPiece!=null && destPiece.getColor().equals(user1.getColor())) {
                return false;
            }
        }
        else{
            return false;
        }
        return true;
    }

    public Boolean blackRook(int or,int oc, int nr, int nc) {
        if(or==nr){
            if(nc> oc){
                for(int i=oc+1;i<=nc;i++) {
                    if(board.getPiece(or, i)!=null && board.getPiece(or, i).getColor().equals(user2.getColor())) {
                        return false;
                    }
                    else if(board.getPiece(or,i)!=null && board.getPiece(or,i).getColor().equals(user1.getColor()) && i!=nc) {
                        return false;
                    }
                }
            } else {
                for(int i=nc;i<oc;i++) {
                    if(board.getPiece(or, i)!=null && board.getPiece(or, i).getColor().equals(user2.getColor())) {
                        return false;
                    }
                    else if(board.getPiece(or,i)!=null && board.getPiece(or,i).getColor().equals(user1.getColor()) && i!=nc) {
                        return false;
                    }
                }
            }
        }
        else if(oc==nc){
            if(nr> or){
                for(int i=or+1;i<=nr;i++) {
                    if(board.getPiece(i, oc)!=null && board.getPiece(i, oc).getColor().equals(user2.getColor())) {
                        return false;
                    }
                    else if(board.getPiece(i,oc)!=null && board.getPiece(i,oc).getColor().equals(user1.getColor()) && i!=nr) {
                        return false;
                    }
                }
            } else {
                for(int i=nr;i<or;i++) {
                    if(board.getPiece(i, oc)!=null && board.getPiece(i, oc).getColor().equals(user2.getColor())) {
                        return false;
                    }
                    else if(board.getPiece(i,oc)!=null && board.getPiece(i,oc).getColor().equals(user1.getColor()) && i!=nr) {
                        return false;
                    }
                }
            }
        }
        else{
            return false;
        }
        return true;
    }

    public Boolean blackKnight(int or, int oc, int nr, int nc) {
        if((Math.abs(or-nr)==2 && Math.abs(oc-nc)==1) || (Math.abs(or-nr)==1 && Math.abs(oc-nc)==2)) {
            Piece destPiece = board.getPiece(nr, nc);
            if(destPiece!=null && destPiece.getColor().equals(user2.getColor())) {
                return false;
            }
        }
        else {
            return false;
        }
        return true;
    }

    public Boolean blackBishop(int or, int oc, int nr, int nc) {
        if(Math.abs(or-nr)==Math.abs(oc-nc)) {
            int rowStep = (nr > or) ? 1 : -1;
            int colStep = (nc > oc) ? 1 : -1;
            for(int i=or+rowStep, j=oc+colStep; i!=nr && j!=nc; i+=rowStep, j+=colStep) {
                if(board.getPiece(i, j)!=null && board.getPiece(i, j).getColor().equals(user2.getColor())) {
                    return false;
                }
                else if(board.getPiece(i,j)!=null && board.getPiece(i,j).getColor().equals(user1.getColor()) && i!=nr) {
                    return false;
                }
            }
        }
        else{
            return false;
        }
        return true;
    }

    public Boolean blackQueen(int or,int oc, int nr, int nc) {
        if(or==nr){
            if(nc> oc){
                for(int i=oc+1;i<=nc;i++) {
                    if(board.getPiece(or, i)!=null && board.getPiece(or, i).getColor().equals(user2.getColor())) {
                        return false;
                    }
                    else if(board.getPiece(or,i)!=null && board.getPiece(or,i).getColor().equals(user1.getColor()) && i!=nc) {
                        return false;
                    }
                }
            } else {
                for(int i=nc;i<oc;i++) {
                    if(board.getPiece(or, i)!=null && board.getPiece(or, i).getColor().equals(user2.getColor())) {
                        return false;
                    }
                    else if(board.getPiece(or,i)!=null && board.getPiece(or,i).getColor().equals(user1.getColor()) && i!=nc) {
                        return false;
                    }
                }
            }
        }
        else if(oc==nc){
            if(nr> or){
                for(int i=or+1;i<=nr;i++) {
                    if(board.getPiece(i, oc)!=null && board.getPiece(i, oc).getColor().equals(user2.getColor())) {
                        return false;
                    }
                    else if(board.getPiece(i,oc)!=null && board.getPiece(i,oc).getColor().equals(user1.getColor()) && i!=nr) {
                        return false;
                    }
                }
            } else {
                for(int i=nr;i<or;i++) {
                    if(board.getPiece(i, oc)!=null && board.getPiece(i, oc).getColor().equals(user2.getColor())) {
                        return false;
                    }
                    else if(board.getPiece(i,oc)!=null && board.getPiece(i,oc).getColor().equals(user1.getColor()) && i!=nr) {
                        return false;
                    }
                }
            }
        }
        else if(Math.abs(or-nr)==Math.abs(oc-nc)) {
            int rowStep = (nr > or) ? 1 : -1;
            int colStep = (nc > oc) ? 1 : -1;
            for(int i=or+rowStep, j=oc+colStep; i!=nr && j!=nc; i+=rowStep, j+=colStep) {
                if(board.getPiece(i, j)!=null && board.getPiece(i, j).getColor().equals(user2.getColor())) {
                    return false;
                }
                else if(board.getPiece(i,j)!=null && board.getPiece(i,j).getColor().equals(user1.getColor()) && i!=nr) {
                    return false;
                }
            }
        }
        else{
            return false;
        }
        return true;
    }

    public Boolean blackKing(int or, int oc, int nr, int nc) {
        String pieceType = "king";
        if(Math.abs(or-nr)<=1 && Math.abs(oc-nc)<=1) {
            Piece destPiece = board.getPiece(nr, nc);
            if(destPiece!=null && destPiece.getColor().equals(user2.getColor())) {
                return false;
            }
            else if(destPiece!=null && destPiece.getName().equals(pieceType)) {
                return false;
            }
        }
        else{
            return false;
        }
        return true;
    }   

    public Boolean blackPawn(int or, int oc, int nr, int nc) {
        if(or==6 && nr==4 && nc==oc) {
            Piece destPiece = board.getPiece(nr, nc);
            Piece destPiece0 = board.getPiece(or-1, nc);
            if(destPiece0!=null) {
                return false;
            }
            else if(destPiece!=null && destPiece.getColor().equals(user2.getColor())) {
                return false;
            } 
        }
        else if(nr==or-1 && nc==oc) {
            Piece destPiece = board.getPiece(nr, nc);
            if(destPiece!=null) {
                return false;
            }
        }
        else if(nr==or-1 && Math.abs(oc-nc)==1) {
            Piece destPiece = board.getPiece(nr, nc);
            if(destPiece==null) {
                return false;
            }
            else if(destPiece!=null && destPiece.getColor().equals(user2.getColor())) {
                return false;
            }
        }
        else {
            return false;
        }
        return true;
    }


    public Boolean makeMove( int or, int oc, int nr, int nc) {

        if(user1.getIsTurn()){
            // check if the move is valid
            Piece piece = board.getPiece(or, oc);
            if(piece==null || !piece.getColor().equals(user1.getColor())){
                return false;
            }
            else{

                if(nr < 0 || nr > 7 || nc < 0 || nc > 7 || (nr == or && nc == oc)) {
                    return false;
                }
                String pieceType = piece.getName();
                // Rook moves done
                if(pieceType.equals("rook")) {
                    Boolean ck = whiteRook(or, oc, nr, nc);
                    if(!ck) {
                        return false;
                    }
                } 
                
                // Knight moves done
                else if(pieceType.equals("knight")) {
                    Boolean ck = whiteKnight(or, oc, nr, nc);
                    if(!ck) {
                        return false;
                    }
                }
                
                // Bishop moves done
                else if(pieceType.equals("bishop")) {
                    Boolean ck = whiteBishop(or, oc, nr, nc);   
                    if(!ck) {
                        return false;
                    }
                } 
                
                // Queen moves done
                else if(pieceType.equals("queen")) {
                    Boolean ck=whiteQueen(or, oc, nr, nc);
                    if(!ck) {
                        return false;
                    }
                }

                // King moves done
                else if(pieceType.equals("king")) {
                    Boolean ck = whiteKing(or, oc, nr, nc);
                    if(!ck) {
                        return false;
                    }
                } 
                
                // Pawn moves done
                else if(pieceType.equals("pawn")) {
                    Boolean ck = whitePawn(or, oc, nr, nc);
                    if(!ck) {
                        return false;
                    }
                }
                
            }
        }
        
        else if(user2.getIsTurn()){
            // check if the move is valid
            Piece piece = board.getPiece(or, oc);
            if(piece==null || !piece.getColor().equals(user2.getColor())){
                return false;
            }
            else{
                if(nr < 0 || nr > 7 || nc < 0 || nc > 7 || (nr == or && nc == oc)) {
                    return false;
                }
                String pieceType = piece.getName();
                
                // Rook moves
                if(pieceType.equals("rook")) {
                    Boolean ck = blackRook(or, oc, nr, nc);
                    if(!ck) {
                        return false;
                    }
                }

                // Knight moves
                else if(pieceType.equals("knight")) {
                    Boolean ck = blackKnight(or, oc, nr, nc);
                    if(!ck) {
                        return false;
                    }
                }

                // Bishop moves
                else if(pieceType.equals("bishop")) {
                    Boolean ck = blackBishop(or, oc, nr, nc);
                    if(!ck) {
                        return false;
                    }
                }

                // Queen moves
                else if(pieceType.equals("queen")) {
                    Boolean ck = blackQueen(or, oc, nr, nc);
                    if(!ck) {
                        return false;
                    }
                }

                // King moves
                else if(pieceType.equals("king")) {
                    Boolean ck = blackKing(or, oc, nr, nc);
                    if(!ck) {
                        return false;
                    }
                }

                // Pawn moves
                else if(pieceType.equals("pawn")) {
                    Boolean ck = blackPawn(or, oc, nr, nc);
                    if(!ck) {
                        return false;
                    }
                }
            }
        }
        
        else{
            return false;
        }

        Piece oldPiece = board.getPiece(or, oc);
        Piece newPiece = board.getPiece(nr, nc);

        if(newPiece!=null &&  newPiece.getName().equals("king")) {
            return false;
        }
        else if(newPiece==null){
            board.setPiece(nr, nc, oldPiece);
            this.stateRepo.save(new State(oldPiece.getName(), or, nr, oc, nc, oldPiece.getColor()));
        }
        else if(newPiece!=null){
            this.stateRepo.save(new State(oldPiece.getName(),or, oc,nr, nc, oldPiece.getColor()));
            this.stateRepo.save(new State(newPiece.getName(),nr,nc, -1, -1, newPiece.getColor()));
            board.setPiece( nr, nc, oldPiece);

        }
        
        user1.setIsTurn(!user1.getIsTurn());
        user2.setIsTurn(!user2.getIsTurn());
        
        return true;
    }

    public Board getCurrentState() {
        return board;
    }


    public String getTurn(){
        if(user1.getIsTurn()){
            return user1.getUsername() + "'s turn";
        }
        else{
            return user2.getUsername() + "'s turn";
        }
    }
}
