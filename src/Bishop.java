public class Bishop extends ChessPiece {
    public Bishop(String color) {
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

        // Проверка, является ли ход диагональным
        if (Math.abs(toLine - line) != Math.abs(toColumn - column)) {
            return false;
        }

        // Определение направления движения
        int stepLine = (toLine - line) > 0 ? 1 : -1;
        int stepColumn = (toColumn - column) > 0 ? 1 : -1;

        // Проверка, свободен ли путь
        int currentLine = line + stepLine;
        int currentColumn = column + stepColumn;
        while (currentLine != toLine && currentColumn != toColumn) {
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
        return "B";
    }
}
