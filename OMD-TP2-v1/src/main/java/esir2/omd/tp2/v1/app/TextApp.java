package esir2.omd.tp2.v1.app;

import esir2.omd.tp2.v1.patterns.commands.CopyCommand;
import esir2.omd.tp2.v1.patterns.commands.PasteCommand;
import esir2.omd.tp2.v1.patterns.commands.CutCommand;
import esir2.omd.tp2.v1.patterns.commands.DeleteCommand;
import esir2.omd.tp2.v1.patterns.commands.InsertCommand;

import esir2.omd.tp2.v1.patterns.Glyph;
import esir2.omd.tp2.v1.patterns.composite.Point;
import esir2.omd.tp2.v1.patterns.strategies.Compositor;
import esir2.omd.tp2.v1.view.Window;

import java.util.*;
public class TextApp implements AppInterface {
	private Window view;
	private Glyph document;
	private List <Glyph> presseP;


	public TextApp(Glyph g) {
		this.document = g;
		view = new Window(g, this);
		view.createView();
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



	public void cut(int indexBegin, int indexEnd) {
		// TODO Auto-generated method stub
		if (indexBegin!=indexEnd){
			System.out.println("test2");
			CutCommand copyCommand = new CutCommand(this.document, indexBegin, indexEnd);
			this.presseP=copyCommand.getPresseP();
			copyCommand.execute();

		}
	}

	public void copy(int indexBegin, int indexEnd) {
		if (indexBegin!=indexEnd){
			System.out.println("test2");
			CopyCommand copyCommand = new CopyCommand(this.document, indexBegin, indexEnd);
			this.presseP=copyCommand.getPresseP();
			copyCommand.execute();

		}
		System.out.println("test3");
	}

	public void paste(int indexBegin, int indexEnd) {
		if (!this.presseP.isEmpty()){
			PasteCommand pasteCommand = new PasteCommand(this.document, indexBegin, indexEnd,this.presseP);
			pasteCommand.execute();
		}

	}

	public void insert(Glyph glyph, int insertIndex) {
		System.out.println("Controller" + insertIndex);
		InsertCommand insertCmd = new InsertCommand(this.document, glyph,
				insertIndex);
		insertCmd.execute();

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
		deleteCmd.execute();
	}

}
