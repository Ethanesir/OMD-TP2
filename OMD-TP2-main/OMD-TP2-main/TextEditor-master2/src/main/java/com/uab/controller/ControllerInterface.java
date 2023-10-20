package com.uab.controller;

import java.util.List;

import javax.swing.JFrame;

import com.uab.model.Glyph;
import com.uab.model.Point;
import com.uab.model.strategies.Compositor;

public interface ControllerInterface {

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

	public void decorateWithScrollBar();
/* 
	public void setSelectBegin(int index);

	public void setSelectEnd(int index);

 */


}
