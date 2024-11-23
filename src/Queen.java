public class Queen extends ChessPiece {

    public Queen(String color) {
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

        // Определение, похож ли ход на ладью (горизонтальный или вертикальный).
        if (line == toLine || column == toColumn) {
            return canMoveLikeRook(chessBoard, line, column, toLine, toColumn);
        }

        //Определение, похож ли ход на слона (диагональ).
        if (Math.abs(toLine - line) == Math.abs(toColumn - column)) {
            return canMoveLikeBishop(chessBoard, line, column, toLine, toColumn);
        }

        return false;
    }

    private boolean canMoveLikeRook(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        int stepLine = Integer.compare(toLine, line);
        int stepColumn = Integer.compare(toColumn, column);

        int currentLine = line + stepLine;
        int currentColumn = column + stepColumn;

        while (currentLine != toLine || currentColumn != toColumn) {
            if (chessBoard.board[currentLine][currentColumn] != null) {
                return false;
            }
            currentLine += stepLine;
            currentColumn += stepColumn;
        }

        ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
        return targetPiece == null || !targetPiece.getColor().equals(this.getColor());
    }

    private boolean canMoveLikeBishop(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        int stepLine = Integer.compare(toLine, line);
        int stepColumn = Integer.compare(toColumn, column);

        int currentLine = line + stepLine;
        int currentColumn = column + stepColumn;

        while (currentLine != toLine && currentColumn != toColumn) {
            if (chessBoard.board[currentLine][currentColumn] != null) {
                return false;
            }
            currentLine += stepLine;
            currentColumn += stepColumn;
        }

        ChessPiece targetPiece = chessBoard.board[toLine][toColumn];
        return targetPiece == null || !targetPiece.getColor().equals(this.getColor());
    }

    @Override
    public String getSymbol() {
        return "Q";
    }
}
