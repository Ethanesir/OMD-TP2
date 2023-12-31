package esir2.omd.tp2.v1.patterns.strategies;

import java.util.List;

import esir2.omd.tp2.v1.patterns.composite.Column;
import esir2.omd.tp2.v1.patterns.composite.Composition;
import esir2.omd.tp2.v1.patterns.Glyph;
import esir2.omd.tp2.v1.patterns.composite.Row;

public class SimpleCompositor implements Compositor {

	private Composition composition;

	public void setComposition(Composition composition) {

		this.composition = composition;

	}
	// Met à jour les éléments en fonction des dimensions
	public void compose(int lineWidth, int height) {

		List<Glyph> components = composition.getComponents();

		Column column = new Column();
		int remainingWidth = lineWidth - 20;
		int i = 0;
		while (i < components.size()) {
			Row line = new Row();
			for (; i < components.size(); i++) {
				Glyph currentGlyph = components.get(i);
				if (remainingWidth < currentGlyph.getWidth())
					break;
				else {
					line.add(currentGlyph);
					remainingWidth = remainingWidth - currentGlyph.getWidth();

				}
			}
			remainingWidth = lineWidth - 20;
			column.add(line);
		}

		composition.setFormattedDocument(column);
	}

}
