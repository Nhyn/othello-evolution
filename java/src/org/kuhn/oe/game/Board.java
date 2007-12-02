package org.kuhn.oe.game;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;


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
		if (getColor(col, row) != Color.NONE) {
			return false;
		}
		
		this.data1[index + 1] = this.data1[index];
		this.data2[index + 1] = this.data2[index];
		++index;
		setColor(col, row, color);
		
		boolean valid = false;
		int x, y;
		
		// south
		x = col;
		y = row + 1;
		if (y < 8 && getColor(x, y) != color) {
			while (y < 8) {
				if (getColor(x, y) == Color.NONE) {
					break;
				} else if (getColor(x, y) == color) {
					valid = true;
					while (--y > row) {
						setColor(x, y, color);
					}
					break;
				}
				++y;
			}
		}

		// southeast
		x = col + 1;
		y = row + 1;
		if (x < 8 && y < 8 && getColor(x, y) != color) {
			while (x < 8 && y < 8) {
				if (getColor(x, y) == Color.NONE) {
					break;
				}
				else if (getColor(x, y) == color) {
					valid = true;
					while (--x > col && --y > row) {
						setColor(x, y, color);
					}
					break;
				}
				++x;
				++y;
			}
		}

		// east
		x = col + 1;
		y = row;
		if (x < 8 && getColor(x, y) != color) {
			while (x < 8) {
				if (getColor(x, y) == Color.NONE) {
					break;
				}
				else if (getColor(x, y) == color) {
					valid = true;
					while (--x > col) {
						setColor(x, y, color);
					}
					break;
				}
				++x;
			}
		}

		// northeast
		x = col + 1;
		y = row - 1;
		if (x < 8 && y >= 0 && getColor(x, y) != color) {
			while (x < 8 && y >= 0) {
				if (getColor(x, y) == Color.NONE) {
					break;
				}
				else if (getColor(x, y) == color) {
					valid = true;
					while (--x > col && ++y < row) {
						setColor(x, y, color);
					}
					break;
				}
				++x;
				--y;
			}
		}

		// north
		x = col;
		y = row - 1;
		if (y >= 0 && getColor(x, y) != color) {
			while (y >= 0) {
				if (getColor(x, y) == Color.NONE) {
					break;
				}
				else if (getColor(x, y) == color) {
					valid = true;
					while (++y < row) {
						setColor(x, y, color);
					}
					break;
				}
				--y;
			}
		}

		// northwest
		x = col - 1;
		y = row - 1;
		if (x >= 0 && y >= 0 && getColor(x, y) != color) {
			while (x >= 0 && y >= 0) {
				if (getColor(x, y) == Color.NONE) {
					break;
				}
				else if (getColor(x, y) == color) {
					valid = true;
					while (++x < col && ++y < row) {
						setColor(x, y, color);
					}
					break;
				}
				--x;
				--y;
			}
		}

		// west
		x = col - 1;
		y = row;
		if (x >= 0 && getColor(x, y) != color) {
			while (x >= 0) {
				if (getColor(x, y) == Color.NONE) {
					break;
				}
				else if (getColor(x, y) == color) {
					valid = true;
					while (++x < col) {
						setColor(x, y, color);
					}
					break;
				}
				--x;
			}
		}

		// southwest
		x = col - 1;
		y = row + 1;
		if (x >= 0 && y < 8 && getColor(x, y) != color) {
			while (x >= 0 && y < 8) {
				if (getColor(x, y) == Color.NONE) {
					break;
				}
				else if (getColor(x, y) == color) {
					valid = true;
					while (++x < col && --y > row) {
						setColor(x, y, color);
					}
					break;
				}
				--x;
				++y;
			}
		}
		if (!valid)
			--index;
		return valid;
	}
	public void undo() {
		assert index > 0;
		--index;
	}
	public boolean test(Color color, int col, int row) {
		boolean result = play(color, col, row);
		if (result) undo();
		return result;
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
		return getScore(index);
	}
	private Score getScore(short index) {
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
	
	public void print(OutputStream out) throws IOException {
		OutputStreamWriter writer = new OutputStreamWriter(out);
		writer.append(" 01234567\r\n");
		for (int i = 0; i < 8; ++i) {
			writer.append(String.valueOf(i));
			for (int j = 0; j < 8; ++j) {
				Color c = getColor(j, i);
				if (c == Color.BLACK) {
					writer.append("B");
				} else if (c == Color.WHITE) {
					writer.append("W");
				} else {
					writer.append("-");
				}
			}
			writer.append("\r\n");
		}
		Score score = getScore();
		writer.append("black: " + score.getBlack() + ", white: " + score.getWhite() + ", blank: " + score.getNone() + "\r\n");
		writer.flush();
	}
	
	private short index = 0;
	private long[] data1 = new long[64];
	private long[] data2 = new long[64];
	private static final Color[] COLORS = new Color[] { Color.NONE, Color.BLACK, Color.WHITE };
}
