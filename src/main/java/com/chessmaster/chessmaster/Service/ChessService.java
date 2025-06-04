package com.chessmaster.chessmaster.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chessmaster.chessmaster.Model.Board;
import com.chessmaster.chessmaster.Model.BoolResponse;
import com.chessmaster.chessmaster.Model.Piece;
import com.chessmaster.chessmaster.Model.State;
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

    private Boolean whiteRook(int or, int oc, int nr, int nc) {
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

    private Boolean whiteKnight(int or, int oc, int nr, int nc) {
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

    private Boolean whiteBishop(int or, int oc, int nr, int nc) {
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

    private Boolean whiteQueen(int or,int oc, int nr, int nc) {
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

    private Boolean whiteKing(int or, int oc, int nr, int nc) {
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

    private Boolean whitePawn(int or, int oc, int nr, int nc) {
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

    private Boolean blackRook(int or,int oc, int nr, int nc) {
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

    private Boolean blackKnight(int or, int oc, int nr, int nc) {
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

    private Boolean blackBishop(int or, int oc, int nr, int nc) {
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

    private Boolean blackQueen(int or,int oc, int nr, int nc) {
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

    private Boolean blackKing(int or, int oc, int nr, int nc) {
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

    private Boolean blackPawn(int or, int oc, int nr, int nc) {
        if(or==6 && nr==4 && nc==oc) {
            Piece destPiece = board.getPiece(nr, nc);
            Piece destPiece0 = board.getPiece(or-1, nc);
            if(destPiece0!=null || destPiece!=null) {
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
            if(oldPiece.getName().equals("pawn")){
                if(oldPiece.getColor().equals("white") && nr==7) {
                    oldPiece.setName("queen");
                }
                else if(oldPiece.getColor().equals("black") && nr==0) {
                    oldPiece.setName("queen");
                }
            }
            BoolResponse res= isSafe(board.getBoard());
            board.setPiece(nr, nc, oldPiece);
            if(res.getRes()==false && res.getPieceColor().equals(user1.getColor()) && user1.getIsTurn()) {
                // white in check and its white turn
                res = isSafe(board.getBoard());
                if(!res.getRes()) {
                    board.setPiece(or, oc, oldPiece); // revert the move
                    board.setPiece(nr, nc, newPiece);
                    return false; 
                }    
            }
            else if(res.getRes()==false && res.getPieceColor().equals(user2.getColor()) && user2.getIsTurn()) {
                // black in check and its black turn
                res = isSafe(board.getBoard());
                if(!res.getRes()) {
                    board.setPiece(or, oc, oldPiece); // revert the move
                    board.setPiece(nr, nc, newPiece);
                    return false; 
                }
            }

            res = isSafe(board.getBoard());
            if(!res.getRes() && res.getPieceColor().equals(oldPiece.getColor())) {
                board.setPiece(or, oc, oldPiece); // revert the move
                board.setPiece(nr, nc, newPiece);
                return false; 
            }
            
            this.stateRepo.save(new State(oldPiece.getName(), or, nr, oc, nc, oldPiece.getColor()));
        }
        else if(newPiece!=null){
            if(oldPiece.getName().equals("pawn")){
                if(oldPiece.getColor().equals("white") && nr==7) {
                    oldPiece.setName("queen");
                }
                else if(oldPiece.getColor().equals("black") && nr==0) {
                    oldPiece.setName("queen");
                }
            }
            BoolResponse res= isSafe(board.getBoard());
            board.setPiece(nr, nc, oldPiece);
            if(res.getRes()==false && res.getPieceColor().equals(user1.getColor()) && user1.getIsTurn()) {
                // white in check and its white turn
                res = isSafe(board.getBoard());
                if(!res.getRes()) {
                    board.setPiece(or, oc, oldPiece); // revert the move
                    board.setPiece(nr, nc, newPiece);
                    return false; 
                }    
            }
            else if(res.getRes()==false && res.getPieceColor().equals(user2.getColor()) && user2.getIsTurn()) {
                // black in check and its black turn
                res = isSafe(board.getBoard());
                if(!res.getRes()) {
                    board.setPiece(or, oc, oldPiece); // revert the move
                    board.setPiece(nr, nc, newPiece);
                    return false; 
                }
            }

            res = isSafe(board.getBoard());
            if(!res.getRes() && res.getPieceColor().equals(oldPiece.getColor())) {
                board.setPiece(or, oc, oldPiece); // revert the move
                board.setPiece(nr, nc, newPiece);
                return false; 
            }
            this.stateRepo.save(new State(oldPiece.getName(),or, oc,nr, nc, oldPiece.getColor()));
            this.stateRepo.save(new State(newPiece.getName(),nr,nc, -1, -1, newPiece.getColor()));
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

    // Working properly
    public BoolResponse isSafe(Piece [][] currBoard) {

        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                Piece piece=currBoard[i][j];
                if(piece!=null && piece.getName().equals("king") && piece.getColor().equals("black")){
                    
                    BoolResponse mainRes=checkBlackKing(i, j, currBoard);
                    if(mainRes.getRes()){
                        mainRes.setRes(false);
                        return mainRes; // black king is in check
                    }
                }
                else if( piece!=null && piece.getName().equals("king") && piece.getColor().equals("white")){
                    BoolResponse mainRes=checkWhiteKing(i,j, currBoard);
                    if(mainRes.getRes()){
                        mainRes.setRes(false);
                        return mainRes; // white king is in check
                    }
                }
            }
        }
        
        
        BoolResponse res=new BoolResponse();
        res.setPieceColor("none");
        res.setRes(true); // no king is in check
        return res;
    }
 
    // Working
    private BoolResponse checkWhiteKing(int i,int j,Piece[][] currBoard){
        // 5x5 matrix pawns and knight
        for(int strow=i-2;strow<=i+2;strow++){
            for(int stcol=j-2;stcol<=j+2;stcol++){
                if(strow<0 || strow>7 || stcol<0 || stcol>7) {
                    continue;
                }
                Piece checkPiece=currBoard[strow][stcol];
                if(checkPiece!=null) {
                    if(strow==i+1 && ( stcol==j-1 || stcol==j+1) ){
                        if(checkPiece.getName().equals("pawn") && checkPiece.getColor().equals("black")){
                            BoolResponse res=new BoolResponse();
                            res.setPieceColor("white");
                            res.setRes(true);
                            res.setRow(i);
                            res.setCol(j);
                            res.setFromRow(strow);
                            res.setFromCol(stcol);
                            return res; 
                        }
                    }
                    if((Math.abs(strow-i)==1 && Math.abs(stcol-j)==2) || (Math.abs(strow-i)==2 && Math.abs(stcol-j)==1)){
                        if(checkPiece.getName().equals("knight") && checkPiece.getColor().equals("black")){
                            
                            BoolResponse res=new BoolResponse();
                            res.setPieceColor("white");
                            res.setRes(true);
                            res.setRow(i);
                            res.setCol(j);
                            res.setFromRow(strow);
                            res.setFromCol(stcol);
                            return res; 
                        }
                    }
                }   
            }
        }
        
        // check in up down right left
        // left
        for(int k=j-1;k>=0;k--){
            Piece checkPiece=currBoard[i][k];
            if(checkPiece!=null ){
                if((checkPiece.getName().equals("rook") || checkPiece.getName().equals("queen")) && checkPiece.getColor().equals("black")){
                    BoolResponse res=new BoolResponse();
                    res.setPieceColor("white");
                    res.setRes(true);
                    res.setRow(i);
                    res.setCol(j);
                    res.setFromRow(i);
                    res.setFromCol(k);
                    return res; 
                }
                break;
            }
        }
        // right
        for(int k=j+1;k<8;k++){
            Piece checkPiece=currBoard[i][k];
            if(checkPiece!=null ){
                if((checkPiece.getName().equals("rook") || checkPiece.getName().equals("queen")) && checkPiece.getColor().equals("black")){
                    BoolResponse res=new BoolResponse();
                    res.setPieceColor("white");
                    res.setRes(true);
                    res.setRow(i);
                    res.setCol(j);
                    res.setFromRow(i);
                    res.setFromCol(k);
                    return res;
                }
                break;
            }
        }
        // up
        for(int k=i-1;k>=0;k--){
            Piece checkPiece=currBoard[k][j];
            if(checkPiece!=null ){
                if((checkPiece.getName().equals("rook") || checkPiece.getName().equals("queen")) && checkPiece.getColor().equals("black")){
                    BoolResponse res=new BoolResponse();
                    res.setPieceColor("white");
                    res.setRes(true);
                    res.setRow(i);
                    res.setCol(j);
                    res.setFromRow(k);
                    res.setFromCol(j);
                    return res;
                }
                break;
            }
        }
        // down
        for(int k=i+1;k<8;k++){
            Piece checkPiece=currBoard[k][j];
            if(checkPiece!=null ){
                if(checkPiece.getName().equals("rook") || checkPiece.getName().equals("queen") && checkPiece.getColor().equals("black")){
                    BoolResponse res=new BoolResponse();
                    res.setPieceColor("white");
                    res.setRes(true);
                    res.setRow(i);
                    res.setCol(j);
                    res.setFromRow(k);
                    res.setFromCol(j);
                    return res;
                }
                break;
            }
        }
        
        
        // check in cross up down right left
        // up left
        for(int k=1;k<8;k++){
            if(i-k<0 || j-k<0) {
                break;
            }
            Piece checkPiece=currBoard[i-k][j-k];
            if(checkPiece!=null ){
                if((checkPiece.getName().equals("bishop") || checkPiece.getName().equals("queen")) && checkPiece.getColor().equals("black")){
                    BoolResponse res=new BoolResponse();
                    res.setPieceColor("white");
                    res.setRes(true);
                    res.setRow(i);
                    res.setCol(j);
                    res.setFromRow(i-k);
                    res.setFromCol(j-k);
                    return res;
                }
                break;
            }
        }
        // up right
        for(int k=1;k<8;k++){
            if(i-k<0 || j+k>7) {
                break;
            }
            Piece checkPiece=currBoard[i-k][j+k];
            if(checkPiece!=null ){
                if((checkPiece.getName().equals("bishop") || checkPiece.getName().equals("queen") )&& checkPiece.getColor().equals("black")){
                    BoolResponse res=new BoolResponse();
                    res.setPieceColor("white");
                    res.setRes(true);
                    res.setRow(i);
                    res.setCol(j);
                    res.setFromRow(i-k);
                    res.setFromCol(j+k);
                    return res;
                }
                break;
            }
        }
        // down left
        for(int k=1;k<8;k++){
            if(i+k>7 || j-k<0) {
                break;
            }
            Piece checkPiece=currBoard[i+k][j-k];
            if(checkPiece!=null ){
                if((checkPiece.getName().equals("bishop") || checkPiece.getName().equals("queen") )&& checkPiece.getColor().equals("black")){
                    BoolResponse res=new BoolResponse();
                    res.setPieceColor("white");
                    res.setRes(true);
                    res.setRow(i);
                    res.setCol(j);
                    res.setFromRow(i+k);
                    res.setFromCol(j-k);
                    return res;
                }
                break;
            }
        }

        // down right
        for(int k=1;k<8;k++){
            if(i+k>7 || j+k>7) {
                break;
            }
            Piece checkPiece=currBoard[i+k][j+k];
            if(checkPiece!=null ){
                if((checkPiece.getName().equals("bishop") || checkPiece.getName().equals("queen")) && checkPiece.getColor().equals("black")){
                    BoolResponse res=new BoolResponse();
                    res.setPieceColor("white");
                    res.setRes(true);
                    res.setRow(i);
                    res.setCol(j);
                    res.setFromRow(i+k);
                    res.setFromCol(j+k);
                    return res;
                }
                break;
            }
        }
        BoolResponse res=new BoolResponse();
        res.setPieceColor("white");
        res.setRes(false); // white king is safe
        return res;

    }
    // Working 
    private BoolResponse checkBlackKing(int i, int j, Piece[][] currBoard) {
        // 5x5 matrix pawns and knight
        for(int strow=i-2;strow<=i+2;strow++){
            for(int stcol=j-2;stcol<=j+2;stcol++){
                if(strow<0 || strow>7 || stcol<0 || stcol>7) {
                    continue;
                }
                Piece checkPiece=currBoard[strow][stcol];
                if(checkPiece!=null) {
                    if(strow==i-1 &&  (stcol==j-1 || stcol==j+1) ){
                        if((checkPiece.getName().equals("pawn") || checkPiece.getName().equals("bishop")) && checkPiece.getColor().equals("white")){
                            BoolResponse res=new BoolResponse();
                            res.setPieceColor("black");
                            res.setRes(true);
                            res.setRow(i);
                            res.setCol(j);
                            res.setFromRow(strow);
                            res.setFromCol(stcol);
                            return res;
                        }
                    }
                    if((Math.abs(strow-i)==1 && Math.abs(stcol-j)==2) || (Math.abs(strow-i)==2 && Math.abs(stcol-j)==1)){
                        if(checkPiece.getName().equals("knight") && checkPiece.getColor().equals("white")){
                            BoolResponse res=new BoolResponse();
                            res.setPieceColor("black");
                            res.setRes(true);
                            res.setRow(i);
                            res.setCol(j);
                            res.setFromRow(strow);
                            res.setFromCol(stcol);
                            return res; // black king is in check
                        }
                    }  
                }
            }
        }
        
        // check in up down right left
        // left
        for(int k=j-1;k>=0;k--){
            Piece checkPiece=currBoard[i][k];
            if(checkPiece!=null ){
                if((checkPiece.getName().equals("rook") || checkPiece.getName().equals("queen")) && checkPiece.getColor().equals("white")){
                    BoolResponse res=new BoolResponse();
                    res.setPieceColor("black");
                    res.setRes(true);
                    res.setRow(i);
                    res.setCol(j);
                    res.setFromRow(i);
                    res.setFromCol(k);
                    return res; // black king is in check
                }
                break;
            }
        }
        // right
        for(int k=j+1;k<8;k++){
            Piece checkPiece=currBoard[i][k];
            if(checkPiece!=null ){
                if((checkPiece.getName().equals("rook") || checkPiece.getName().equals("queen")) && checkPiece.getColor().equals("white")){
                    BoolResponse res=new BoolResponse();
                    res.setPieceColor("black");
                    res.setRes(true);
                    res.setRow(i);
                    res.setCol(j);
                    res.setFromRow(i);
                    res.setFromCol(k);
                    return res; // black king is in check
                }
                break;
            }
        }
        // up
        for(int k=i-1;k>=0;k--){
            Piece checkPiece=currBoard[k][j];
            if(checkPiece!=null ){
                if((checkPiece.getName().equals("rook") || checkPiece.getName().equals("queen")) && checkPiece.getColor().equals("white")){
                    BoolResponse res=new BoolResponse();
                    res.setPieceColor("black");
                    res.setRes(true);
                    res.setRow(i);
                    res.setCol(j);
                    res.setFromRow(k);
                    res.setFromCol(j);
                    return res; // black king is in check
                }
                break;
            }
        }
        // down
        for(int k=i+1;k<8;k++){
            Piece checkPiece=currBoard[k][j];
            if(checkPiece!=null ){
                if((checkPiece.getName().equals("rook") || checkPiece.getName().equals("queen")) && checkPiece.getColor().equals("white")){
                    BoolResponse res=new BoolResponse();
                    res.setPieceColor("black");
                    res.setRes(true);
                    res.setRow(i);
                    res.setCol(j);
                    res.setFromRow(k);
                    res.setFromCol(j);
                    return res; // black king is in check
                }
                break;
            }
        }
        
        
        // check in cross up down right left
        // up left
        for(int k=1;k<8;k++){
            if(i-k<0 || j-k<0) {
                break;
            }
            Piece checkPiece=currBoard[i-k][j-k];
            if(checkPiece!=null ){
                if((checkPiece.getName().equals("bishop") || checkPiece.getName().equals("queen")) && checkPiece.getColor().equals("white")){
                    BoolResponse res=new BoolResponse();
                    res.setPieceColor("black");
                    res.setRes(true);
                    res.setRow(i);
                    res.setCol(j);
                    res.setFromRow(i-k);
                    res.setFromCol(j-k);
                    return res; // black king is in check
                }
                break;
            }
        }
        // up right
        for(int k=1;k<8;k++){
            if(i-k<0 || j+k>7) {
                break;
            }
            Piece checkPiece=currBoard[i-k][j+k];
            if(checkPiece!=null ){
                if((checkPiece.getName().equals("bishop") || checkPiece.getName().equals("queen")) && checkPiece.getColor().equals("white")){
                    BoolResponse res=new BoolResponse();
                    res.setPieceColor("black");
                    res.setRes(true);
                    res.setRow(i);
                    res.setCol(j);
                    res.setFromRow(i-k);
                    res.setFromCol(j+k);
                    return res; // black king is in check
                }
                break;
            }
        }
        // down left
        for(int k=1;k<8;k++){
            if(i+k>7 || j-k<0) {
                break;
            }
            Piece checkPiece=currBoard[i+k][j-k];
            if(checkPiece!=null ){
                if((checkPiece.getName().equals("bishop") || checkPiece.getName().equals("queen")) && checkPiece.getColor().equals("white")){
                    BoolResponse res=new BoolResponse();
                    res.setPieceColor("black");
                    res.setRes(true);
                    res.setRow(i);
                    res.setCol(j);
                    res.setFromRow(i+k);
                    res.setFromCol(j-k);
                    return res; // black king is in check
                }
                break;
            }
        }

        // down right
        for(int k=1;k<8;k++){
            if(i+k>7 || j+k>7) {
                break;
            }
            Piece checkPiece=currBoard[i+k][j+k];
            if(checkPiece!=null ){
                if((checkPiece.getName().equals("bishop") || checkPiece.getName().equals("queen")) && checkPiece.getColor().equals("white")){
                    BoolResponse res=new BoolResponse();
                    res.setPieceColor("black");
                    res.setRes(true);
                    res.setRow(i);
                    res.setCol(j);
                    res.setFromRow(i+k);
                    res.setFromCol(j+k);
                    return res; // black king is in check

                }
                break;
            }
        }
    BoolResponse res=new BoolResponse();
        res.setPieceColor("black");
        res.setRes(false); // black king is safe
        return res;
}

//    Working
    public BoolResponse isWin(){
        // Board tpBoard = new Board(board);
        Board tpBoard = board;
        BoolResponse res = isSafe(tpBoard.getBoard());
        // king is in check
        if(!res.getRes()){
            int row = res.getRow();
            int col = res.getCol();

            // king piece
            Piece oldPiece = board.getPiece(row, col);
            
            // check for all pieces if possible to move to remove check
            Piece checkFromPiece=tpBoard.getPiece(res.getFromRow(), res.getFromCol());
            
            for(int i=0;i<8;i++){
                for(int j=0;j<8;j++){
                    Piece piece=tpBoard.getPiece(i, j);
                    if(piece==null || piece.getColor().equals(checkFromPiece.getColor())){
                        continue;
                    }
                        
                    if(checkFromPiece.getName().equals("queen") || checkFromPiece.getName().equals("bishop")){
                        // movement is diagonal
                        if(checkFromPiece.getRow()<row && col<checkFromPiece.getCol()){
                            // top right
                            for(int k=1;k<8;k++){
                            if(row-k<0 || col+k>7) {
                                break;
                            }
                            // currnt position is i-k and j+k
                            Boolean ck=false;
                            if(piece.getName().equals("pawn")){
                                if(piece.getColor().equals("white")){
                                    ck=whitePawn(piece.getRow(),piece.getCol(),row-k,col+k);
                                }
                                else{
                                    ck=blackPawn(piece.getRow(),piece.getCol(),row-k,col+k);
                                }
                            }
                            else if(piece.getName().equals("bishop")){
                                if(piece.getColor().equals("white")){
                                    ck=whiteBishop(piece.getRow(),piece.getCol(),row-k,col+k);
                                }
                                else{
                                    ck=blackBishop(piece.getRow(),piece.getCol(),row-k,col+k);
                                }
                            }
                            else if(piece.getName().equals("rook")){
                                if(piece.getColor().equals("white")){
                                    ck=whiteRook(piece.getRow(),piece.getCol(),row-k,col+k);
                                }
                                else{
                                    ck=blackRook(piece.getRow(),piece.getCol(),row-k,col+k);
                                }
                            }
                            else if(piece.getName().equals("queen")){
                                if(piece.getColor().equals("white")){
                                    ck=whiteQueen(piece.getRow(),piece.getCol(),row-k,col+k);
                                }
                                else{
                                    ck=blackQueen(piece.getRow(),piece.getCol(),row-k,col+k);
                                }
                            }
                            else if(piece.getName().equals("king")){
                                if(piece.getColor().equals("white")){
                                    ck=whiteKing(piece.getRow(),piece.getCol(),row-k,col+k);
                                }
                                else{
                                    ck=blackKing(piece.getRow(),piece.getCol(),row-k,col+k);
                                }
                            }
                            else if(piece.getName().equals("knight")){
                                if(piece.getColor().equals("white")){
                                    ck=whiteKnight(piece.getRow(),piece.getCol(),row-k,col+k);
                                }
                                else{
                                    ck=blackKnight(piece.getRow(),piece.getCol(),row-k,col+k);
                                }
                            }
                            else{
                                ck=false;
                            }
                            
                            if(ck){
                                tpBoard.setPiece(row-k,col+k,piece);
                                BoolResponse currRes = isSafe(tpBoard.getBoard());
                                if(currRes.getRes()){
                                    // check can be removed by this move
                                    tpBoard.setPiece(i,j,piece);
                                    BoolResponse bs=new BoolResponse();
                                    bs.setPieceColor("removed by placing in top right");
                                    bs.setRes(false);
                                    return bs;
                                }
                                else{
                                    // revert changes
                                    tpBoard.setPiece(i,j,piece);
                                }
                            }
                        }
                        
                        }

                        else if(checkFromPiece.getRow()<row && checkFromPiece.getCol()<col){
                                // top left
                            for(int k=1;k<8;k++){
                                if(row-k<0 || col-k<0) {
                                    break;
                                }
                                // currnt position is i-k and j-k
                                Boolean ck=false;
                                if(piece.getName().equals("pawn")){
                                    if(piece.getColor().equals("white")){
                                        ck=whitePawn(piece.getRow(),piece.getCol(),row-k,col-k);
                                    }
                                    else{
                                        ck=blackPawn(piece.getRow(),piece.getCol(),row-k,col-k);
                                    }
                                }
                                else if(piece.getName().equals("bishop")){
                                    if(piece.getColor().equals("white")){
                                        ck=whiteBishop(piece.getRow(),piece.getCol(),row-k,col-k);
                                    }
                                    else{
                                        ck=blackBishop(piece.getRow(),piece.getCol(),row-k,col-k);
                                    }
                                }
                                else if(piece.getName().equals("rook")){
                                    if(piece.getColor().equals("white")){
                                        ck=whiteRook(piece.getRow(),piece.getCol(),row-k,col-k);
                                    }
                                    else{
                                        ck=blackRook(piece.getRow(),piece.getCol(),row-k,col-k);
                                    }
                                }
                                else if(piece.getName().equals("queen")){
                                    if(piece.getColor().equals("white")){
                                        ck=whiteQueen(piece.getRow(),piece.getCol(),row-k,col-k);
                                    }
                                    else{
                                        ck=blackQueen(piece.getRow(),piece.getCol(),row-k,col-k);
                                    }
                                }
                                else if(piece.getName().equals("king")){
                                    if(piece.getColor().equals("white")){
                                        ck=whiteKing(piece.getRow(),piece.getCol(),row-k,col-k);
                                    }
                                    else{
                                        ck=blackKing(piece.getRow(),piece.getCol(),row-k,col-k);
                                    }
                                }
                                else if(piece.getName().equals("knight")){
                                    if(piece.getColor().equals("white")){
                                        ck=whiteKnight(piece.getRow(),piece.getCol(),row-k,col-k);
                                    }
                                    else{
                                        ck=blackKnight(piece.getRow(),piece.getCol(),row-k,col-k);
                                    }
                                }
                                else{
                                    ck=false;
                                }

                                
                                if(ck){
                                    tpBoard.setPiece(row-k,col-k,piece);
                                    BoolResponse currRes = isSafe(tpBoard.getBoard());
                                    if(currRes.getRes()){
                                        // check can be removed by this move
                                        tpBoard.setPiece(i,j,piece);
                                        BoolResponse bs=new BoolResponse();
                                        bs.setPieceColor("removed by placing in top left");
                                        bs.setRes(false);
                                        return bs;
                                    }
                                    else{
                                        // revert changes
                                        tpBoard.setPiece(i,j,piece);
                                    }
                                }
                            }

                        }
                            
                        else if(checkFromPiece.getRow()>row && checkFromPiece.getCol()>col){
                            // bottom right
                            for(int k=1;k<8;k++){
                                if(row+k>7 || col+k>7) {
                                    break;
                                }
                                // currnt position is i+k and j+k
                                Boolean ck=false;
                                if(piece.getName().equals("pawn")){
                                    if(piece.getColor().equals("white")){
                                        ck=whitePawn(piece.getRow(),piece.getCol(),row+k,col+k);
                                    }
                                    else{
                                        ck=blackPawn(piece.getRow(),piece.getCol(),row+k,col+k);
                                    }
                                }
                                else if(piece.getName().equals("bishop")){
                                    if(piece.getColor().equals("white")){
                                        ck=whiteBishop(piece.getRow(),piece.getCol(),row+k,col+k);
                                    }
                                    else{
                                        ck=blackBishop(piece.getRow(),piece.getCol(),row+k,col+k);
                                    }
                                }
                                else if(piece.getName().equals("rook")){
                                    if(piece.getColor().equals("white")){
                                        ck=whiteRook(piece.getRow(),piece.getCol(),row+k,col+k);
                                    }
                                    else{
                                        ck=blackRook(piece.getRow(),piece.getCol(),row+k,col+k);
                                    }
                                }
                                else if(piece.getName().equals("queen")){
                                    if(piece.getColor().equals("white")){
                                        ck=whiteQueen(piece.getRow(),piece.getCol(),row+k,col+k);
                                    }
                                    else{
                                        ck=blackQueen(piece.getRow(),piece.getCol(),row+k,col+k);
                                    }
                                }
                                else if(piece.getName().equals("king")){
                                    if(piece.getColor().equals("white")){
                                        ck=whiteKing(piece.getRow(),piece.getCol(),row+k,col+k);
                                    }
                                    else{
                                        ck=blackKing(piece.getRow(),piece.getCol(),row+k,col+k);
                                    }
                                }
                                else if(piece.getName().equals("knight")){
                                    if(piece.getColor().equals("white")){
                                        ck=whiteKnight(piece.getRow(),piece.getCol(),row+k,col+k);
                                    }
                                    else{
                                        ck=blackKnight(piece.getRow(),piece.getCol(),row+k,col+k);
                                    }
                                }
                                else{
                                    ck=false;
                                }

                                
                                if(ck){
                                    tpBoard.setPiece(row+k,col+k,piece);
                                    BoolResponse currRes = isSafe(tpBoard.getBoard());
                                    if(currRes.getRes()){
                                        // check can be removed by this move
                                        tpBoard.setPiece(i,j,piece);
                                        BoolResponse bs=new BoolResponse();
                                        bs.setPieceColor("removed by placing in bottom right");
                                        bs.setRes(false);
                                        return bs;
                                    }
                                    else{
                                        // revert changes
                                        tpBoard.setPiece(i,j,piece);
                                    }
                                }
                            }

                        }
                        
                        else if(checkFromPiece.getRow()>row && checkFromPiece.getCol()<col){
                            // bottom left
                            for(int k=1;k<8;k++){
                                if(row+k>7 || col-k<0) {
                                    break;
                                }
                                // currnt position is i+k and j-k
                                Boolean ck=false;
                                if(piece.getName().equals("pawn")){
                                    if(piece.getColor().equals("white")){
                                        ck=whitePawn(piece.getRow(),piece.getCol(),row+k,col-k);
                                    }
                                    else{
                                        ck=blackPawn(piece.getRow(),piece.getCol(),row+k,col-k);
                                    }
                                }
                                else if(piece.getName().equals("bishop")){
                                    if(piece.getColor().equals("white")){
                                        ck=whiteBishop(piece.getRow(),piece.getCol(),row+k,col-k);
                                    }
                                    else{
                                        ck=blackBishop(piece.getRow(),piece.getCol(),row+k,col-k);
                                    }
                                }
                                else if(piece.getName().equals("rook")){
                                    if(piece.getColor().equals("white")){
                                        ck=whiteRook(piece.getRow(),piece.getCol(),row+k,col-k);
                                    }
                                    else{
                                        ck=blackRook(piece.getRow(),piece.getCol(),row+k,col-k);
                                    }
                                }
                                else if(piece.getName().equals("queen")){
                                    if(piece.getColor().equals("white")){
                                        ck=whiteQueen(piece.getRow(),piece.getCol(),row+k,col-k);
                                    }
                                    else{
                                        ck=blackQueen(piece.getRow(),piece.getCol(),row+k,col-k);
                                    }
                                }
                                else if(piece.getName().equals("king")){
                                    if(piece.getColor().equals("white")){
                                        ck=whiteKing(piece.getRow(),piece.getCol(),row+k,col-k);
                                    }
                                    else{
                                        ck=blackKing(piece.getRow(),piece.getCol(),row+k,col-k);
                                    }
                                }
                                else if(piece.getName().equals("knight")){
                                    if(piece.getColor().equals("white")){
                                        ck=whiteKnight(piece.getRow(),piece.getCol(),row+k,col-k);
                                    }
                                    else{
                                        ck=blackKnight(piece.getRow(),piece.getCol(),row+k,col-k);
                                    }
                                }
                                else{
                                    ck=false;
                                }

                                
                                if(ck){
                                    tpBoard.setPiece(row+k,col-k,piece);
                                    BoolResponse currRes = isSafe(tpBoard.getBoard());
                                    if(currRes.getRes()){
                                        // check can be removed by this move
                                        tpBoard.setPiece(i,j,piece);
                                        BoolResponse bs=new BoolResponse();
                                        bs.setPieceColor("removed by placing in bottom left");
                                        bs.setRes(false);
                                        return bs;
                                    }
                                    else{
                                        // revert changes
                                        tpBoard.setPiece(i,j,piece);
                                    }
                                }
                            }

                        }  
                        
                    }
                    if(checkFromPiece.getName().equals("rook") ||  checkFromPiece.getName().equals("queen")){
                        // movement is either up down left right
                        if(row==checkFromPiece.getRow()){
                            if(checkFromPiece.getCol()<col){
                                // left
                                for(int k=col-1;k>=0;k--){
                                    Boolean ck=false;
                                    
                                    if((piece.getName().equals("pawn")) ){
                                        if(piece.getColor().equals("white")){
                                            ck=whitePawn(piece.getRow(),piece.getCol(),row,k);
                                        }
                                        else{
                                            ck=blackPawn(piece.getRow(),piece.getCol(),row,k);
                                        }
                                    }
                                    else if(piece.getName().equals("rook")){
                                        if(piece.getColor().equals("white")){
                                            ck=whiteRook(piece.getRow(),piece.getCol(),row,k);
                                        }
                                        else{
                                            ck=blackRook(piece.getRow(),piece.getCol(),row,k);
                                        }
                                    }
                                    else if(piece.getName().equals("queen")){
                                        if(piece.getColor().equals("white")){
                                            ck=whiteQueen(piece.getRow(),piece.getCol(),row,k);
                                        }
                                        else{
                                            ck=blackQueen(piece.getRow(),piece.getCol(),row,k);
                                        }
                                    }
                                    else if(piece.getName().equals("king")){
                                        if(piece.getColor().equals("white")){
                                            ck=whiteKing(piece.getRow(),piece.getCol(),row,k);
                                        }
                                        else{
                                            ck=blackKing(piece.getRow(),piece.getCol(),row,k);
                                        }
                                    }
                                    else if(piece.getName().equals("bishop")){
                                        if(piece.getColor().equals("white")){
                                            ck=whiteBishop(piece.getRow(),piece.getCol(),row,k);
                                        }
                                        else{
                                            ck=blackBishop(piece.getRow(),piece.getCol(),row,k);
                                        }
                                    }
                                    else if(piece.getName().equals("knight")){
                                        if(piece.getColor().equals("white")){
                                            ck=whiteKnight(piece.getRow(),piece.getCol(),row,k);
                                        }
                                        else{
                                            ck=blackKnight(piece.getRow(),piece.getCol(),row,k);
                                        }
                                    }
                                    else{
                                        ck=false;
                                    }

                                    
                                    if(ck){
                                        tpBoard.setPiece(row, k, piece);
                                        BoolResponse currRes = isSafe(tpBoard.getBoard());
                                        if(currRes.getRes()){
                                            // check can be removed by this move
                                            tpBoard.setPiece(i,j,piece);
                                            BoolResponse bs=new BoolResponse();
                                            bs.setPieceColor("removed by placing in left");
                                            bs.setRes(false);   
                                            return bs;
                                        }
                                        else{
                                            // revert changes
                                            tpBoard.setPiece(i,j,piece);
                                        }
                                    }

                                }

                            }
                            else{
                                // right
                                for(int k=col+1;k<8;k++){
                                    Boolean ck=false;
                                    
                                    if((piece.getName().equals("pawn")) ){
                                        if(piece.getColor().equals("white")){
                                            ck=whitePawn(piece.getRow(),piece.getCol(),row,k);
                                        }
                                        else{
                                            ck=blackPawn(piece.getRow(),piece.getCol(),row,k);
                                        }
                                    }
                                    else if(piece.getName().equals("rook")){
                                        if(piece.getColor().equals("white")){
                                            ck=whiteRook(piece.getRow(),piece.getCol(),row,k);
                                        }
                                        else{
                                            ck=blackRook(piece.getRow(),piece.getCol(),row,k);
                                        }
                                    }
                                    else if(piece.getName().equals("queen")){
                                        if(piece.getColor().equals("white")){
                                            ck=whiteQueen(piece.getRow(),piece.getCol(),row,k);
                                        }
                                        else{
                                            ck=blackQueen(piece.getRow(),piece.getCol(),row,k);
                                        }
                                    }
                                    else if(piece.getName().equals("king")){
                                        if(piece.getColor().equals("white")){
                                            ck=whiteKing(piece.getRow(),piece.getCol(),row,k);
                                        }
                                        else{
                                            ck=blackKing(piece.getRow(),piece.getCol(),row,k);
                                        }
                                    }
                                    else if(piece.getName().equals("bishop")){
                                        if(piece.getColor().equals("white")){
                                            ck=whiteBishop(piece.getRow(),piece.getCol(),row,k);
                                        }
                                        else{
                                            ck=blackBishop(piece.getRow(),piece.getCol(),row,k);
                                        }
                                    }
                                    else if(piece.getName().equals("knight")){
                                        if(piece.getColor().equals("white")){
                                            ck=whiteKnight(piece.getRow(),piece.getCol(),row,k);
                                        }
                                        else{
                                            ck=blackKnight(piece.getRow(),piece.getCol(),row,k);
                                        }
                                    }
                                    else{
                                        ck=false;
                                    }

                                    if(ck){
                                        tpBoard.setPiece(row, k, piece);
                                        BoolResponse currRes = isSafe(tpBoard.getBoard());
                                        if(currRes.getRes()){
                                            // check can be removed by this move
                                            tpBoard.setPiece(i,j,piece);
                                            BoolResponse bs=new BoolResponse();
                                            bs.setPieceColor("removed by placing in right");
                                            bs.setRes(false);   
                                            return bs;
                                        }
                                        else{
                                            // revert changes
                                            tpBoard.setPiece(i,j,piece);
                                        }
                                    }
                                    
                                }

                            }
                        }
                        else if(col==checkFromPiece.getCol()){
                            if(checkFromPiece.getRow()<row){
                                    // top
                                for(int k=row-1;k>=0;k--){
                                    Boolean ck=false;
                                    
                                    if((piece.getName().equals("pawn")) ){
                                        if(piece.getColor().equals("white")){
                                            ck=whitePawn(piece.getRow(),piece.getCol(),k,col);
                                        }
                                        else{
                                            ck=blackPawn(piece.getRow(),piece.getCol(),k,col);
                                        }
                                    }
                                    else if(piece.getName().equals("rook")) {
                                        if(piece.getColor().equals("white")){
                                            ck=whiteRook(piece.getRow(),piece.getCol(),k,col);
                                        }
                                        else{
                                            ck=blackRook(piece.getRow(),piece.getCol(),k,col);
                                        }
                                    }
                                    else if(piece.getName().equals("queen")){
                                        if(piece.getColor().equals("white")){
                                            ck=whiteQueen(piece.getRow(),piece.getCol(),k,col);
                                        }
                                        else{
                                            ck=blackQueen(piece.getRow(),piece.getCol(),k,col);
                                        }
                                    }
                                    else if(piece.getName().equals("king")){
                                        if(piece.getColor().equals("white")){
                                            ck=whiteKing(piece.getRow(),piece.getCol(),k,col);
                                        }
                                        else{
                                            ck=blackKing(piece.getRow(),piece.getCol(),k,col);
                                        }
                                    }
                                    else if(piece.getName().equals("bishop")){
                                        if(piece.getColor().equals("white")){
                                            ck=whiteBishop(piece.getRow(),piece.getCol(),k,col);
                                        }
                                        else{
                                            ck=blackBishop(piece.getRow(),piece.getCol(),k,col);
                                        }
                                    }
                                    else if(piece.getName().equals("knight")){
                                        if(piece.getColor().equals("white")){
                                            ck=whiteKnight(piece.getRow(),piece.getCol(),k,col);
                                        }
                                        else{
                                            ck=blackKnight(piece.getRow(),piece.getCol(),k,col);
                                        }
                                    }
                                    else{
                                        ck=false;
                                    }

                                    
                                    if(ck){
                                        tpBoard.setPiece(k, col, piece);
                                        BoolResponse currRes = isSafe(tpBoard.getBoard());
                                        if(currRes.getRes()){
                                            // check can be removed by this move
                                            tpBoard.setPiece(i,j,piece);
                                            BoolResponse bs=new BoolResponse();
                                            bs.setPieceColor("removed by placing in top");
                                            bs.setRes(false);   
                                            return bs;
                                        }
                                        else{
                                            // revert changes
                                            tpBoard.setPiece(i,j,piece);
                                        }
                                    }

                                }
                            }
                            else{
                                // bottom
                                for(int k=row+1;k<8;k++){
                                    Boolean ck=false;
                                    
                                    if((piece.getName().equals("pawn")) ){
                                        if(piece.getColor().equals("white")){
                                            ck=whitePawn(piece.getRow(),piece.getCol(),k,col);
                                        }
                                        else{
                                            ck=blackPawn(piece.getRow(),piece.getCol(),k,col);
                                        }
                                    }
                                    else if(piece.getName().equals("rook")){
                                        if(piece.getColor().equals("white")){
                                            ck=whiteRook(piece.getRow(),piece.getCol(),k,col);
                                        }
                                        else{
                                            ck=blackRook(piece.getRow(),piece.getCol(),k,col);
                                        }
                                    }
                                    else if(piece.getName().equals("queen")){
                                        if(piece.getColor().equals("white")){
                                            ck=whiteQueen(piece.getRow(),piece.getCol(),k,col);
                                        }
                                        else{
                                            ck=blackQueen(piece.getRow(),piece.getCol(),k,col);
                                        }
                                    }
                                    else if(piece.getName().equals("king")){
                                        if(piece.getColor().equals("white")){
                                            ck=whiteKing(piece.getRow(),piece.getCol(),k,col);
                                        }
                                        else{
                                            ck=blackKing(piece.getRow(),piece.getCol(),k,col);
                                        }
                                    }
                                    else if(piece.getName().equals("bishop")){
                                        if(piece.getColor().equals("white")){
                                            ck=whiteBishop(piece.getRow(),piece.getCol(),k,col);
                                        }
                                        else{
                                            ck=blackBishop(piece.getRow(),piece.getCol(),k,col);
                                        }
                                    }
                                    else if(piece.getName().equals("knight")){
                                        if(piece.getColor().equals("white")){
                                            ck=whiteKnight(piece.getRow(),piece.getCol(),k,col);
                                        }
                                        else{
                                            ck=blackKnight(piece.getRow(),piece.getCol(),k,col);
                                        }
                                    }
                                    else{
                                        ck=false;
                                    }

                                    
                                    if(ck){
                                        tpBoard.setPiece(k, col, piece);
                                        BoolResponse currRes = isSafe(tpBoard.getBoard());
                                        if(currRes.getRes()){
                                            // check can be removed by this move
                                            tpBoard.setPiece(i,j,piece);
                                            BoolResponse bs=new BoolResponse();
                                            bs.setPieceColor("removed by placing in bottom");
                                            bs.setRes(false);   
                                            return bs;
                                        }
                                        else{
                                            // revert changes
                                            tpBoard.setPiece(i,j,piece);
                                        }
                                    }

                                }

                            }
                        }
                        }  
                        // check for elementaion of the checkFromPiece
                        // Working fine
                           Boolean moveCheck = false;
                           if(piece.getName().equals("pawn")){
                               if(piece.getColor().equals("white")){
                                   moveCheck=whitePawn(piece.getRow(),piece.getCol(),checkFromPiece.getRow(),checkFromPiece.getCol());
                               
                               }
                               else{
                                   moveCheck=blackPawn(piece.getRow(),piece.getCol(),checkFromPiece.getRow(),checkFromPiece.getCol());
                               }
                           }
                           else if(piece.getName().equals("bishop")){
                               if(piece.getColor().equals("white")){
                                   moveCheck=whiteBishop(piece.getRow(),piece.getCol(),checkFromPiece.getRow(),checkFromPiece.getCol());
                               }
                               else{
                                   moveCheck=blackBishop(piece.getRow(),piece.getCol(),checkFromPiece.getRow(),checkFromPiece.getCol());
                               }
                           }
                           else if(piece.getName().equals("rook")){
                               if(piece.getColor().equals("white")){
                                   moveCheck=whiteRook(piece.getRow(),piece.getCol(),checkFromPiece.getRow(),checkFromPiece.getCol());
                               }
                               else{
                                   moveCheck=blackRook(piece.getRow(),piece.getCol(),checkFromPiece.getRow(),checkFromPiece.getCol());
                               }
                           }
                           else if(piece.getName().equals("queen")){
                               if(piece.getColor().equals("white")){
                                   moveCheck=whiteQueen(piece.getRow(),piece.getCol(),checkFromPiece.getRow(),checkFromPiece.getCol());
                               }
                               else{
                                   moveCheck=blackQueen(piece.getRow(),piece.getCol(),checkFromPiece.getRow(),checkFromPiece.getCol());
                               }
                           }
                           else if(piece.getName().equals("king")){
                               if(piece.getColor().equals("white")){
                                   moveCheck=whiteKing(piece.getRow(),piece.getCol(),checkFromPiece.getRow(),checkFromPiece.getCol());
                               }
                               else{
                                   moveCheck=blackKing(piece.getRow(),piece.getCol(),checkFromPiece.getRow(),checkFromPiece.getCol());
                               }
                           }
                           else if(piece.getName().equals("knight")){
                               if(piece.getColor().equals("white")){
                                   moveCheck=whiteKnight(piece.getRow(),piece.getCol(),checkFromPiece.getRow(),checkFromPiece.getCol());
                               }
                               else{
                                   moveCheck=blackKnight(piece.getRow(),piece.getCol(),checkFromPiece.getRow(),checkFromPiece.getCol());
                               }
                           }
                           else{
                               moveCheck=false;
                           }
                           if(moveCheck){
                               tpBoard.setPiece(checkFromPiece.getRow(), checkFromPiece.getCol(), piece);
                               BoolResponse bs=isSafe(tpBoard.getBoard());
                               if(bs.getRes()){
                                   // check can be removed by this move
                                   tpBoard.setPiece(i,j,piece);
                                   tpBoard.setPiece(res.getFromRow(), res.getFromCol(), checkFromPiece);
                                   BoolResponse response=new BoolResponse();
                                   response.setPieceColor("removed by elimination from :"+piece.getName()+" at "+piece.getRow()+ "," +piece.getCol());
                                   response.setRes(false);
                                   return response;
                               }
                               else{
                                   // revert changes
                                   tpBoard.setPiece(i, j, piece);
                                   tpBoard.setPiece(res.getFromRow(), res.getFromCol(), checkFromPiece);
                               }
                           }
                       
                    }
                
                }
            
            
            // No moves possible its a checkmate
            BoolResponse bs=new BoolResponse();
            if(oldPiece.getColor().equals("white")){
                bs.setPieceColor("black");
            }
            else{
                bs.setPieceColor("white");
            }
            bs.setRes(true);
            return bs;
        }
        BoolResponse bs=new BoolResponse();
        bs.setPieceColor("no check");
        bs.setRes(false);
        return bs;
    }

}
