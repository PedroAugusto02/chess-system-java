package boardgame;

public class Board {

	private int rows;
	private int columns;
	private Piece[][] pieces;
	
	public Board(int rows, int columns) {
		if (rows < 1 || columns < 1) {
			throw new BoardException("Erro crindo o tabuleiro: precisa pelo menos 1 coluna e uma linha");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	public Piece piece(int row, int column) {
		return pieces[row][column];
	}
	
	public Piece piece(Position position) {
		if (!positionExist(position)) {
			throw new BoardException("Posição não está no tabuleiro");
		}
		return pieces[position.getRow()][position.getColumn()];
	}

	public void placePiece(Piece piece, Position position) {
		if (thereIsAPiece(position)) {
			throw new BoardException("Ja existe uma peça na posião");
		}
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}
	
	public Piece removePiece(Position position) {
		if (!positionExist(position)) {
			throw new BoardException("Posição não está no tabuleiro");
		}
		if (piece(position) == null) {
			return null;
		}
		Piece aux = piece(position);
		aux.position = null;
		pieces[position.getRow()][position.getColumn()] = null;
		return aux;
	}
	
	private boolean positionExist(int row,int column) {
		return row >=0 && row < rows && column >= 0 && column < columns;
	}
	
	public boolean positionExist(Position position) {
		return positionExist(position.getRow(),position.getColumn());
	}

	public boolean thereIsAPiece(Position position) {
		if (!positionExist(position)) {
			throw new BoardException("Posição não está no tabuleiro");
		}
		return piece(position) != null;
	}
}