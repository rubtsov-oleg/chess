public class Pawn extends ChessPiece {
    public Pawn(String color) {
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

        //Определение направления и начальной линии на основе цвета
        int direction = this.getColor().equals("White") ? 1 : -1;
        int initialLine = this.getColor().equals("White") ? 1 : 6;

        // Продвижение вперед на одну клетку
        if (toColumn == column && toLine == line + direction) {
            return chessBoard.board[toLine][toColumn] == null;
        }

        //Продвижение вперед на две клетки от исходного положения
        if (toColumn == column && line == initialLine && toLine == line + 2 * direction) {
            return chessBoard.board[line + direction][column] == null && chessBoard.board[toLine][toColumn] == null;
        }

        // Захват по диагонали
        if (Math.abs(toColumn - column) == 1 && toLine == line + direction) {
            ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
            return targetPiece != null && !targetPiece.getColor().equals(this.getColor());
        }

        return false;
    }

    @Override
    public String getSymbol() {
        return "P";
    }
}
