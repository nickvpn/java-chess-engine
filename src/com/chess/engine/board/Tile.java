
package com.chess.engine.board;

import com.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

public abstract class Tile {

    // introduce a member field that represents the tile number
    protected final int tileCoordinate;

    // created every possible empty tile to exist, so I don't have to make it again
    // If I ever wanted the empty tile that represents the first one, just say emptyTiles.get(0);
    private static final Map<Integer, EmptyTile> EMPTY_TILES_CACHE = createAllPossibleEmptyTiles();

    private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {

        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();

        for(int i = 0; i < BoardUtils.NUM_TILES; i++){
            emptyTileMap.put(i, new EmptyTile(i));
        }

        // Need the map to be immutable, so it cant be changed by anyone.
        return ImmutableMap.copyOf(emptyTileMap);
    }

    // if statement that creates a OccupiedTile with a given tileCoordinate and piece whenever the piece is not null, meaning the tile is occupied by the piece.
    // otherwise it gets a EmptyTile from EMPTY_TILES using the tileCoordinate
    public static Tile createTile(final int tileCoordinate, final Piece piece){
        return piece != null ? new OccupiedTile(tileCoordinate, piece) : EMPTY_TILES_CACHE.get(tileCoordinate);
    }

    //Constructor that grants tiles tileCoordinates, basically creates individual tiles
    private Tile(final int tileCoordinate) {
        this.tileCoordinate = tileCoordinate;
    }


    // abstract since it is not defined in a class but subclass
    public abstract boolean isTileOccupied();

    public abstract Piece getPiece();

    // define empty tile
    public static final class EmptyTile extends Tile{

        private EmptyTile(final int coordinate){
            super(coordinate);
        }

        //tile is not occupied, print as hyphen
        @Override
        public String toString(){
            return "-";
        }

        @Override
        public boolean isTileOccupied() {
            return false;
        }

        @Override
        public Piece getPiece() {
            return null;
        }
    }

    // define occupied tile
    public static final class OccupiedTile extends Tile {

        private final Piece pieceOnTile;

        private OccupiedTile(final int tileCoordinate, final Piece pieceOnTile) {
            super(tileCoordinate);
            this.pieceOnTile = pieceOnTile;
        }

        //tile is occupied, print as piece
        //black piece = lowercase
        //white piece = uppercase
        @Override
        public String toString(){
            return getPiece().getPieceAlliance().isBlack() ? getPiece().toString().toLowerCase() :
                    getPiece().toString();
        }

        @Override
        public boolean isTileOccupied() {
            return true;
        }

        @Override
        public Piece getPiece() {
            return this.pieceOnTile;
        }
    }
}
