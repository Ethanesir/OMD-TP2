package esir2.omd.tp2.v1.patterns.composite;

import esir2.omd.tp2.v1.patterns.Glyph;

import java.awt.Graphics;
import java.util.ArrayList;

public class Column extends Glyph {
	private ArrayList<Glyph> rows = new ArrayList<Glyph>();
	private Rect bounds;
	private int lineSpacing;

	// Constructeur de la classe 
	public Column() {
		bounds = Rect.ZERO;
		lineSpacing = 10;
	}

	public int getRowsCount() {
		return rows.size();
	}

	@Override
	public void add(Glyph glyph) {
		rows.add(glyph);
		notifyObservers();

	}

	@Override
	public void remove(int index) {

		rows.remove(index);
	}

	@Override
	public void remove(Glyph glyph) {
		rows.remove(glyph);
		notifyObservers();
	}

	@Override
	public Glyph getChild(int i) {
		return rows.get(i);
	}

	@Override
	public Glyph parent() {
		return super.parent();
	}
	
	// Méthode dessinant les lignes
	@Override
	public void draw(Graphics g, Point position, int width, int height) {
		bounds.setLeft(position.getX());

		for (Glyph row : rows) {
			row.draw(g, position, width, height);
			position.incrementYBy(row.getHeigth() + lineSpacing);

		}

		bounds.setBottom(position.getY() - getHeigth() - 2 * lineSpacing);
		bounds.setExtent(new Point(getWidth(), getHeigth()));
	}

	@Override
	public int getWidth() {
		int maxWidth = 0;
		for (Glyph row : rows) {

			if (maxWidth <= row.getWidth()) {
				maxWidth = row.getWidth();
			}

		}
		return maxWidth;
	}

	@Override
	public int getHeigth() {
		int totalHeight = 0;
		for (Glyph row : rows) {

			totalHeight += row.getHeigth() + lineSpacing;

		}
		return totalHeight;
	}

	@Override
	public Rect getBounds() {
		return this.bounds;
	}

	@Override
	public void setBounds(Rect rectangle) {
		this.bounds = rectangle;
	}

}
