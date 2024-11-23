public class Rook extends ChessPiece {
    public Rook(String color) {
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

        // Проверка, является ли движение горизонтальным или вертикальным.
        if (line != toLine && column != toColumn) {
            return false;
        }

        // Определение направления движения
        int stepLine = Integer.compare(toLine, line);   // 1, -1, или 0
        int stepColumn = Integer.compare(toColumn, column); // 1, -1, или 0

        // Проверка, свободен ли путь
        int currentLine = line + stepLine;
        int currentColumn = column + stepColumn;
        while (currentLine != toLine || currentColumn != toColumn) {
            if (chessBoard.board[currentLine][currentColumn] != null) {
                return false;
            }
            currentLine += stepLine;
            currentColumn += stepColumn;
        }

        // Проверка целевой ячейки
        ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
        return targetPiece == null || !targetPiece.getColor().equals(this.getColor());
    }


    @Override
    public String getSymbol() {
        return "R";
    }

}
