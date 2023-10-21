package esir2.omd.tp2.v1.app;

import esir2.omd.tp2.v1.patterns.Glyph;
import esir2.omd.tp2.v1.patterns.composite.Point;
import esir2.omd.tp2.v1.patterns.strategies.Compositor;

public interface AppInterface {

	public void setLineBreakingAlgorithm(Compositor compositor);

	public void scrollUp();

	public void scrollDown();

	public void cut(int indexBegin, int indexEnd);

	public void copy(int indexBegin, int indexEnd);

	public void paste(int indexBegin, int indexEnd);

	public void insert(Glyph glyph, int insertIndex);

	public void delete(int indexBegin, int indexEnd);

	void locateGlyph(Point point);

	public int getGlyphIndex2();


}
