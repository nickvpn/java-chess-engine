package com.chess.engine;

public enum Alliance {
    WHITE {
        @Override
        public int getDirection() {
            return -1;
        }
    },
    BLACK {
        @Override
        public int getDirection() {
            return 1;
        }
    };

    // method that returns directionality (black/white pawns move accordingly, not the same exact way)
    public abstract int getDirection();
}
