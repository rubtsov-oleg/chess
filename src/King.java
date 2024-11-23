public class King extends ChessPiece {
    public King(String color) {
        super(color);
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверка, находится ли целевая позиция на доске
        if (!chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }

        // Проверка, является ли этот ход на самом деле ходом (не пребыванием на месте)
        if (line == toLine && column == toColumn) {
            return false;
        }

        //Проверка, находится ли ход в пределах одной ячейки в любом направлении
        if (Math.abs(toLine - line) > 1 || Math.abs(toColumn - column) > 1) {
            return false;
        }

        // Проверка целевой ячейки
        ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
        if (targetPiece != null && targetPiece.getColor().equals(this.getColor())) {
            return false;
        }

        return true;
    }

    public boolean isUnderAttack(ChessBoard board, int line, int column) {
        // Проверка, находится ли целевая позиция на доске
        if (!board.checkPos(line) || !board.checkPos(column)) {
            return false;
        }

        //Перебрать все ячейки доски
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessPiece piece = board.board[i][j];
                // Проверка, принадлежит ли фигура противнику
                if (piece != null && !piece.getColor().equals(this.getColor())) {
                    //Проверка, может ли фигура противника переместиться в указанную позицию
                    if (piece.canMoveToPosition(board, i, j, line, column)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }


    @Override
    public String getSymbol() {
        return "K";
    }
}
