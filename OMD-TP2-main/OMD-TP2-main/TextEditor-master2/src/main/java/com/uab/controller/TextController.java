package com.uab.controller;

import javax.swing.JFrame;

import com.uab.controller.commands.CommandsInvoker;
import com.uab.controller.commands.CopyCommand;
import com.uab.controller.commands.PasteCommand;
import com.uab.controller.commands.CutCommand;
import com.uab.controller.commands.DeleteCommand;
import com.uab.controller.commands.InsertCommand;

import com.uab.model.Glyph;
import com.uab.model.Composition;
import com.uab.model.Point;
import com.uab.model.decorator.ScrollDecorator;
import com.uab.model.strategies.Compositor;
import com.uab.view.TextPanel;
import java.util.*;
public class TextController implements ControllerInterface {
	private TextPanel view;
	private Glyph document;
	private CommandsInvoker cmdInvoker;
	private List <Glyph> presseP;


	public TextController(Glyph g) {
		this.document = g;
		view = new TextPanel(g, this);
		view.createView();
		cmdInvoker = new CommandsInvoker();
		presseP= new ArrayList<Glyph>();
	}

	public void setLineBreakingAlgorithm(Compositor compositor) {

	}

	public void scrollUp() {

		this.document.scrollUp(view);

	}
	

	public void scrollDown() {
		this.document.scrollDown(view);

	}



	public void undo() {
		cmdInvoker.undo();
	}

	public void redo() {
		cmdInvoker.redo();

	}

	public void cut(int indexBegin, int indexEnd) {
		// TODO Auto-generated method stub
		if (indexBegin!=indexEnd){
			System.out.println("test2");
			CutCommand copyCommand = new CutCommand(this.document, indexBegin, indexEnd);
			this.presseP=copyCommand.getPresseP();
			cmdInvoker.execute(copyCommand);

		}
	}

	public void copy(int indexBegin, int indexEnd) {
		if (indexBegin!=indexEnd){
			System.out.println("test2");
			CopyCommand copyCommand = new CopyCommand(this.document, indexBegin, indexEnd);
			this.presseP=copyCommand.getPresseP();
			cmdInvoker.execute(copyCommand);

		}
		System.out.println("test3");
	}

	public void paste(int indexBegin, int indexEnd) {
		if (!this.presseP.isEmpty()){
			PasteCommand pasteCommand = new PasteCommand(this.document, indexBegin, indexEnd,this.presseP);
			cmdInvoker.execute(pasteCommand);
		}

	}

	public void insert(Glyph glyph, int insertIndex) {
		System.out.println("Controller" + insertIndex);
		InsertCommand insertCmd = new InsertCommand(this.document, glyph,
				insertIndex);
		cmdInvoker.execute(insertCmd);

	}

	public void locateGlyph(Point point) {

		this.document.getCursor().mapPointToRowAndColumn(point);
		this.document.getCursor().mapToGlyphIndex();

		this.document.getCursor().updateCursor();
		this.view.update();
	}
	public int getGlyphIndex2(){
		return this.document.getCursor().getGlyphIndex();
	}

	public void delete(int indexBegin, int indexEnd) {
		DeleteCommand deleteCmd = new DeleteCommand(this.document,  indexBegin,  indexEnd);
		cmdInvoker.execute(deleteCmd);
	}


	public void decorateWithScrollBar() {
		this.document =  new ScrollDecorator(document, 1);
			//view.setGlyph(document);
			view.update();
		
	}

}
