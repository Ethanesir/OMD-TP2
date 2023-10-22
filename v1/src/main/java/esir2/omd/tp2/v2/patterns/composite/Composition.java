package esir2.omd.tp2.v2.patterns.composite;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

import esir2.omd.tp2.v2.patterns.Glyph;
import esir2.omd.tp2.v2.patterns.strategies.Compositor;

@XmlRootElement
public class Composition extends Glyph {

	private static final long serialVersionUID = 1L;
	private Compositor compositor;

	private ArrayList<Glyph> components = new ArrayList<Glyph>();

	private Column formattedDocument;

	private Caret cursor;
	public Composition() {

	}

	// Constructeur de la classe
	public Composition(Compositor compositor) {
		this.compositor = compositor;
		this.compositor.setComposition(this);
		cursor = new Caret(this, 0, 0, 0);
	}

	public Caret getCursor() {
		return cursor;
	}


	public void setCursor(Caret cursor) {
		this.cursor = cursor;
	}

	public void setCompositor(Compositor compositor) {
		this.compositor = compositor;
	}

	public List<Glyph> getComponents() {
		return components;
	}

	public void setComponents(List<Glyph> components) {
		int i = 0;
		for (Glyph g : components) {
			add(g, i);
			i++;
		}

	}

	// Renvoie le caractère à l'index i
	@Override
	public Glyph getChild(int i) {
		return this.components.get(i);
	}

	public Column getFormattedDocument() {
		return formattedDocument;
	}

	public void setFormattedDocument(Column column) {
		formattedDocument = column;
	}

	// Ajoute un caractère à l'index i
	@Override
	public void add(Glyph glyph, int index) {
		System.out.println("Composition" + index);
		this.components.add(index, glyph);
		this.cursor.incrementGlyphIndex();
		this.cursor.updateCursor();
		notifyObservers();

	}

	// Supprime le caractère de l'index i
	@Override
	public void remove(int index) {

		this.components.remove(index);
		this.cursor.decrementGlyphIndex();
		this.cursor.updateCursor();
		notifyObservers();
	}

	// Met à les éléments en fonction de la dimension
	@Override
	public void format(int width, int height) {
		this.compositor.compose(width, height);
	}
	
	// Méthode affichant les éléments de l'éditeur de texte
	@Override
	public void draw(Graphics g, Point position, int width, int height) {
		int startX = 9;
		int startY = position.getY() - 40;
		g.setColor(Color.WHITE);
		g.fillRect(startX, startY, width, height);
		g.setColor(Color.BLACK);
		format(width, height);
		this.cursor.draw(g);
		formattedDocument.draw(g, position, width, height);
		
	}
}
