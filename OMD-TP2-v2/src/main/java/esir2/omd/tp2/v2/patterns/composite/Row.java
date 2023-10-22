package esir2.omd.tp2.v2.patterns.composite;

import esir2.omd.tp2.v2.patterns.Glyph;

import java.awt.Graphics;
import java.util.ArrayList;

//import com.uab.patterns.visitor.Visitor;

public class Row extends Glyph {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Glyph> glyphs = new ArrayList<Glyph>();
	private Rect bounds;


	// Constructeur de la classe
	public Row() {
		bounds = Rect.ZERO;
	}

	public int getGlyphsCount() {
		return glyphs.size();
	}


	// ajoute un caractère
	@Override
	public void add(Glyph glyph) {
		glyphs.add(glyph);

	}

	// supprime un caractère à l'index i
	@Override
	public void remove(int index) {

		glyphs.remove(index);
	}

	// supprime un caractère en particulier
	@Override
	public void remove(Glyph glyph) {
		glyphs.remove(glyph);

	}

	// Renvoie le caractère de l'index i
	@Override
	public Glyph getChild(int i) {
		return glyphs.get(i);
	}

	@Override
	public Glyph parent() {
		return super.parent();
	}

	// Méthode qui dessine tous les caractères d'une ligne 
	@Override
	public void draw(Graphics g, Point position, int width, int height) {
		bounds.setLeft(position.getX());
		bounds.setBottom(position.getY() - getHeigth());

		for (Glyph glyph : glyphs) {
			glyph.draw(g, position, width, height);

		}
		position.setX(20);

		bounds.setExtent(new Point(getWidth(), getHeigth()));
	}

	@Override
	public int getWidth() {
		int totalWidth = 0;
		for (Glyph glyph : glyphs) {
			totalWidth += glyph.getWidth();
		}
		return totalWidth;
	}

	public int getCummulativeHeight() {
		int totalHeight = 0;
		for (Glyph glyph : glyphs) {
			totalHeight += glyph.getHeigth();
		}
		return totalHeight;
	}

	@Override
	public int getHeigth() {
		int maxHeight = 0;
		for (Glyph glyph : glyphs) {

			if (maxHeight <= glyph.getHeigth()) {
				maxHeight = glyph.getHeigth();
			}
		}
		return maxHeight;
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
