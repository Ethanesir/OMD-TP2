package esir2.omd.tp2.v2.patterns.composite;

import esir2.omd.tp2.v2.patterns.Glyph;

import java.awt.Color;
import java.awt.Graphics;

public class CharacterModel extends Glyph {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private char character;
	private Rect bounds;
	private boolean toUnderline;
	public CharacterModel() {
		super();
	}

	// Constructeur de la classe
	public CharacterModel(char c) {
		super();
		this.character = c;
		toUnderline = false;

	}
	// Méthode affichant le caractère
	@Override
	public void draw(Graphics g, Point position, int width, int height) {
		g.drawString(Character.toString(this.character), position.getX(),
				position.getY());
		bounds = new Rect(position.getX(), position.getY() - getHeigth(), 10,
				20);
		position.incrementXBy(getWidth());
		if (toUnderline) {
			g.setColor(Color.RED);
			g.drawLine(bounds.getLeft(),
					bounds.getBottom() + bounds.getHeight(), bounds.getLeft()
							+ bounds.getWidth(),
					bounds.getBottom() + bounds.getHeight());
			g.setColor(Color.BLACK);
		}

	}

	@Override
	public int getWidth() {
		return 10;
	}

	@Override
	public int getHeigth() {
		return 20;
	}

	@Override
	public Rect getBounds() {
		return this.bounds;
	}

	@Override
	public void setBounds(Rect rectangle) {
		this.bounds = rectangle;
	}

	public char getCharacter() {
		return character;

	}

	@Override
	public String toString() {
		return "[" + character + "]";
	}

	public void setCharacter(char c) {
		this.character = c;

	}

}
