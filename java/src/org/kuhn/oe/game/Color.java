package org.kuhn.oe.game;

public enum Color {
	NONE,
	BLACK,
	WHITE;
	
	public Color opponent() {
		return this == Color.BLACK ? Color.WHITE : Color.BLACK;
	}
}