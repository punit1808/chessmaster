package  com.chessmaster.chessmaster.Model;

public class Board {

    int row=8;
    int col=8;

    private Piece[][] board;

    public Board(){
        this.board=new Piece[row][col];
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                board[i][j]=null;
            }
        }

        Piece rookw1=new Piece("rook","white",0,0);
        board[0][0]=rookw1;
        Piece rookw2=new Piece("rook","white",0,7);
        board[0][7]=rookw2;
        Piece knightw1=new Piece("knight","white",0,1);
        board[0][1]=knightw1;
        
        Piece knightw2=new Piece("knight","white",0,6);
        board[0][6]=knightw2;
        Piece bishopw1=new Piece("bishop","white",0,2);
        board[0][2]=bishopw1;

        Piece bishopw2=new Piece("bishop","white",0,5);
        board[0][5]=bishopw2;
        Piece queenw=new Piece("queen","white",0,3);
        board[0][3]=queenw;
        Piece kingw=new Piece("king","white",0,4);
        board[0][4]=kingw;
        Piece pawnw1=new Piece("pawn","white",1,0);
        board[1][0]=pawnw1;
        Piece pawnw2=new Piece("pawn","white",1,1); 
        board[1][1]=pawnw2;
        Piece pawnw3=new Piece("pawn","white",1,2);
        board[1][2]=pawnw3;
        Piece pawnw4=new Piece("pawn","white",1,3);
        board[1][3]=pawnw4;
        Piece pawnw5=new Piece("pawn","white",1,4);
        board[1][4]=pawnw5;
        Piece pawnw6=new Piece("pawn","white",1,5);
        board[1][5]=pawnw6;
        Piece pawnw7=new Piece("pawn","white",1,6);
        board[1][6]=pawnw7; 
        Piece pawnw8=new Piece("pawn","white",1,7);
        board[1][7]=pawnw8;

        Piece rookb1=new Piece("rook","black",7,0);
        board[7][0]=rookb1;
        Piece rookb2=new Piece("rook","black",7,7);
        board[7][7]=rookb2;
        Piece knightb1=new Piece("knight","black",7,1);
        board[7][1]=knightb1;
        Piece knightb2=new Piece("knight","black",7,6);
        board[7][6]=knightb2;
        Piece bishopb1=new Piece("bishop","black",7,2);
        board[7][2]=bishopb1;
        Piece bishopb2=new Piece("bishop","black",7,5);
        board[7][5]=bishopb2;
        Piece queenb=new Piece("queen","black",7,3);
        board[7][3]=queenb;
        Piece kingb=new Piece("king","black",7,4);
        board[7][4]=kingb;
        Piece pawnb1=new Piece("pawn","black",6,0);
        board[6][0]=pawnb1;
        Piece pawnb2=new Piece("pawn","black",6,1);
        board[6][1]=pawnb2;
        Piece pawnb3=new Piece("pawn","black",6,2);
        board[6][2]=pawnb3;
        Piece pawnb4=new Piece("pawn","black",6,3);
        board[6][3]=pawnb4;
        Piece pawnb5=new Piece("pawn","black",6,4);
        board[6][4]=pawnb5;
        Piece pawnb6=new Piece("pawn","black",6,5);
        board[6][5]=pawnb6;
        Piece pawnb7=new Piece("pawn","black",6,6);
        board[6][6]=pawnb7;
        Piece pawnb8=new Piece("pawn","black",6,7);
        board[6][7]=pawnb8;

    }

    public Board(Board other) {
        this.row = other.row;
        this.col = other.col;
        this.board = new Piece[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Piece originalPiece = other.board[i][j];
                if (originalPiece != null) {
                    this.board[i][j] = new Piece(
                        originalPiece.getName(),
                        originalPiece.getColor(),
                        originalPiece.getRow(),
                        originalPiece.getCol()
                    );
                } else {
                    this.board[i][j] = null;
                }
            }
        }
    }
 
    public Piece[][] getBoard() {
        return board;
    }
    
    public Piece getPiece(int row, int col) {
        return board[row][col];
    }
    
    public void setPiece(int row, int col, Piece piece) {
        if(piece!=null){
            board[piece.getRow()][piece.getCol()] = null;
            piece.setRow(row);
            piece.setCol(col);
            board[row][col] = piece;
        }
    }
}

