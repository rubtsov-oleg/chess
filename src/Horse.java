public class Horse extends ChessPiece {

    public Horse(String color) {
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

        // Определение всех возможных ходов «L» для коня.
        int[][] possibleMoves = {
                {2, 1}, {2, -1}, {-2, 1}, {-2, -1},
                {1, 2}, {1, -2}, {-1, 2}, {-1, -2}
        };

        // Проверка, соответствует ли целевая позиция какому-либо допустимому ходу коня.
        for (int[] move : possibleMoves) {
            if (line + move[0] == toLine && column + move[1] == toColumn) {
                // Проверка, пуста ли целевая ячейка или содержит ли она фигуру противника.
                ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
                if (targetPiece == null || !targetPiece.getColor().equals(this.getColor())) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public String getSymbol() {
        return "H";
    }
}
