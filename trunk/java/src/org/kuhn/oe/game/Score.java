package org.kuhn.oe.game;

public class Score {
	private short black;
	private short white;
	public short getNone() {
		return (short)(64 - black - white);
	}
	public short getBlack() {
		return black;
	}
	public void setBlack(short black) {
		this.black = black;
	}
	public short getWhite() {
		return white;
	}
	public void setWhite(short white) {
		this.white = white;
	}
	
	public short get(Color color) {
		if (color == Color.BLACK)
			return black;
		else if (color == Color.WHITE)
			return white;
		else
			return getNone();
	}
}