package com.chess.engine.pieces;


import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

import java.util.Collection;

public abstract class Piece {

    //create the piecePosition and an enumerator alliance(White or Black)
    protected final int piecePosition;
    protected final Alliance pieceAlliance;
    protected final boolean isFirstMove;

    //Constructor for the piece class, granting it a position and alliance
    Piece(final int piecePosition, final Alliance pieceAlliance){
        this.pieceAlliance = pieceAlliance;
        this.piecePosition = piecePosition;
        //TODO more work here!!!
        this.isFirstMove = false;
    }

    public Alliance getPieceAlliance(){
        return this.pieceAlliance;
    }

    public boolean isFirstMove(){
        return this.isFirstMove;
    }

    //calculate the legal moves of a piece
    //ex. knights will be an extension of a piece, so it will override this method
    public abstract Collection<Move> calculateLegalMoves(final Board board);

}
