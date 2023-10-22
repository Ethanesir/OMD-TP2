package esir2.omd.tp2.v1.patterns.composite;

import esir2.omd.tp2.v1.patterns.Glyph;

import java.awt.Color;
import java.awt.Graphics;

//import com.uab.patterns.visitor.Visitor;

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

	public CharacterModel(char c) {
		super();
		this.character = c;
		toUnderline = false;

	}

	@Override
	public void draw(Graphics g, Point position, int width, int height) {
		g.drawString(Character.toString(this.character), position.getX(),
				position.getY());

		// bounds = new Rect(position.getX(), position.getY(),
		// fm.charWidth(this.character), fm.getHeight());
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
		/*if (this.hasCursor) {
			if (before)
				g.fillRect(bounds.getLeft() - 3, bounds.getBottom(), 2,
						bounds.getHeight());
			else
				g.fillRect(bounds.getLeft() + bounds.getWidth() - 3,
						bounds.getBottom(), 2, bounds.getHeight());
		}
		*/

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

	// @Override
	// public void accept(Visitor visitor) {
	// 	visitor.visit(this);
	// }

	public void underlineWithRed() {
		toUnderline = true;
	}

	public void removeUnderline() {
		toUnderline = false;
	}


}