package com.uab.controller;

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

	public void cut();

	public void copy();

	public void paste();

	public void insert(Glyph glyph, int insertIndex);

	public void delete(int glyphIndex);

	void locateGlyph(Point point);


	public void decorateWithScrollBar();






}
