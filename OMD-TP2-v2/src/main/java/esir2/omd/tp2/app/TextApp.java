package esir2.omd.tp2.app;

import esir2.omd.tp2.patterns.commands.CommandsInvoker;
import esir2.omd.tp2.patterns.commands.CopyCommand;
import esir2.omd.tp2.patterns.commands.PasteCommand;
import esir2.omd.tp2.patterns.commands.CutCommand;
import esir2.omd.tp2.patterns.commands.DeleteCommand;
import esir2.omd.tp2.patterns.commands.InsertCommand;

import esir2.omd.tp2.patterns.Glyph;
import esir2.omd.tp2.patterns.composite.Point;
import esir2.omd.tp2.patterns.strategies.Compositor;
import esir2.omd.tp2.view.Window;

import java.util.*;
public class TextApp implements AppInterface {
	private Window view;
	private Glyph document;
	private CommandsInvoker cmdInvoker;
	private List <Glyph> presseP;


	public TextApp(Glyph g) {
		this.document = g;
		view = new Window(g, this);
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

}