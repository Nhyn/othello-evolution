package org.kuhn.oe.game;

import java.io.PrintStream;


public class Board {
	public Board() {
		reset();
	}
	
	public void reset() {
		index = 0;
		for (int i = 0; i < 64; ++i) {
			data1[i] = 0;
			data2[i] = 0;
		}
		setColor(3, 4, Color.BLACK);
		setColor(4, 3, Color.BLACK);
		setColor(4, 4, Color.WHITE);
		setColor(3, 3, Color.WHITE);	
	}
	
	public boolean play(Color color, int col, int row) {
		assert index < 63;
		
		if (getColor(col, row) != Color.NONE) {
			return false;
		}
		
		this.data1[index + 1] = this.data1[index];
		this.data2[index + 1] = this.data2[index];
		++index;
		setColor(col, row, color);
		
		boolean v0 = playVector(color, col, row, 0, -1);	// north
		boolean v1 = playVector(color, col, row, 1, -1);	// northeast
		boolean v2 = playVector(color, col, row, 1, 0);		// east
		boolean v3 = playVector(color, col, row, 1, 1);		// southeast
		boolean v4 = playVector(color, col, row, 0, 1);		// south
		boolean v5 = playVector(color, col, row, -1, 1);	// southwest
		boolean v6 = playVector(color, col, row, -1, 0);	// west
		boolean v7 = playVector(color, col, row, -1, -1);	// northwest

		if (v0 || v1 || v2 || v3 || v4 || v5 || v6 || v7)
			return true;
		else {
			--index;
			return false;
		}
	}
	public void undo() {
		assert index > 0;
		--index;
	}
	
	private boolean playVector(Color color, int col, int row, int dx, int dy) {
		boolean valid = false;
		int x = col + dx;
		int y = row + dy;
		Color c = getColor(x, y);
		if (x >= 0 && x < 8 && y >= 0 && y < 8 && c != color) {
			while (x >= 0 && x < 8 && y >= 0 && y < 8) {
				if (c == Color.NONE) {
					break;
				} else if (c == color) {
					valid = true;
					while (x != col || y != row) {
						x -= dx;
						y -= dy;
						setColor(x, y, color);
					}
					break;
				}
				x += dx;
				y += dy;
				c = getColor(x, y);
			}
		}
		return valid;
	}
	
	public boolean test(Color color, int col, int row) {
		if (getColor(col, row) != Color.NONE) {
			return false;
		}
		return testVector(color, col, row, 0, -1)	// north
		|| testVector(color, col, row, 1, -1)		// northeast
		|| testVector(color, col, row, 1, 0)		// east
		|| testVector(color, col, row, 1, 1)		// southeast
		|| testVector(color, col, row, 0, 1)		// south
		|| testVector(color, col, row, -1, 1)		// southwest
		|| testVector(color, col, row, -1, 0)		// west
		|| testVector(color, col, row, -1, -1);		// northwest
	}
	
	private boolean testVector(Color color, int col, int row, int dx, int dy) {
		int x = col + dx;
		int y = row + dy;
		Color c = getColor(x, y);
		if (x >= 0 && x < 8 && y >= 0 && y < 8 && c != color) {
			while (x >= 0 && x < 8 && y >= 0 && y < 8) {
				if (c == Color.NONE) {
					break;
				} else if (c == color) {
					return true;
				}
				x += dx;
				y += dy;
				c = getColor(x, y);
			}
		}
		return false;
	}
	
	private Color getColor(int col, int row) {
		int offset = col + row * 8;
		long bit1 = (data1[index] >> offset) & 0x1;
		long bit2 = (data2[index] >> offset) & 0x1;
		long bit = bit1 | (bit2 << 1);
		return COLORS[(int)bit];
	}
	private void setColor(int col, int row, Color color) {
		int offset = col + row * 8;
		long bit = 0x1L << offset;
		if (color == Color.BLACK) {
			data1[index] |= bit;
			data2[index] &= ~bit;
		} else if (color == Color.WHITE) {
			data1[index] &= ~bit;
			data2[index] |= bit;
		} else {
			data1[index] &= ~bit;
			data2[index] &= ~bit;
		}
	}
	
	private Score score = new Score();
	
	public Score getScore() {
		short black = 0;
		short white = 0;
		for (int i = 0; i < 64; ++i) {
			black += (data1[index] >> i) & 0x1;
			white += (data2[index] >> i) & 0x1;
		}
		score.setBlack(black);
		score.setWhite(white);
		return score;
	}
		
	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder();
		buf.append(index + ":");
		for (int i = 0; i < 8; ++i) {
			for (int j = 0; j < 8; ++j) {
				Color c = getColor(j, i);
				if (c == Color.BLACK) {
					buf.append("B");
				} else if (c == Color.WHITE) {
					buf.append("W");
				} else {
					buf.append("-");
				}
			}
		}
		Score score = getScore();
		buf.append(score.getBlack() + "/" + score.getWhite() + "/" + score.getNone());
		return buf.toString();
	}
	
	public void print(PrintStream out) {
		out.println(" 01234567");
		for (int i = 0; i < 8; ++i) {
			out.print(String.valueOf(i));
			for (int j = 0; j < 8; ++j) {
				Color c = getColor(j, i);
				if (c == Color.BLACK) {
					out.print("B");
				} else if (c == Color.WHITE) {
					out.print("W");
				} else {
					out.print("-");
				}
			}
			out.println();
		}
		Score score = getScore();
		out.print("black: " + score.getBlack() + ", white: " + score.getWhite() + ", blank: " + score.getNone() + "\r\n");
	}
	
	private short index = 0;
	private long[] data1 = new long[64];
	private long[] data2 = new long[64];
	private static final Color[] COLORS = new Color[] { Color.NONE, Color.BLACK, Color.WHITE };
}
