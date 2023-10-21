package esir2.omd.tp2.app;

import esir2.omd.tp2.patterns.Glyph;
import esir2.omd.tp2.patterns.composite.Point;
import esir2.omd.tp2.patterns.strategies.Compositor;

public interface AppInterface {

	public void setLineBreakingAlgorithm(Compositor compositor);

	public void scrollUp();

	public void scrollDown();

	public void undo();

	public void redo();

	public void cut(int indexBegin, int indexEnd);

	public void copy(int indexBegin, int indexEnd);

	public void paste(int indexBegin, int indexEnd);

	public void insert(Glyph glyph, int insertIndex);

	public void delete(int indexBegin, int indexEnd);

	void locateGlyph(Point point);

	public int getGlyphIndex2();


}
