package esir2.omd.tp2.v1.app;

import esir2.omd.tp2.v1.patterns.commands.CopyCommand;
import esir2.omd.tp2.v1.patterns.commands.PasteCommand;
import esir2.omd.tp2.v1.patterns.commands.CutCommand;
import esir2.omd.tp2.v1.patterns.commands.DeleteCommand;
import esir2.omd.tp2.v1.patterns.commands.InsertCommand;

import esir2.omd.tp2.v1.patterns.Glyph;
import esir2.omd.tp2.v1.patterns.composite.Point;
import esir2.omd.tp2.v1.view.Window;

import java.util.*;
public class TextApp implements AppInterface {
	private Window view;
	private Glyph document;
	private List <Glyph> presseP;

	// Constructeur de la classe
	public TextApp(Glyph g) {
		this.document = g;
		view = new Window(g, this);
		view.createView();
		presseP= new ArrayList<Glyph>();
	}


	/*
	* Supprime et met dans le presse papier la sélection
	* @param  indexBegin  début de la sélection
	* @param  indexEnd fin de la sélection
	*/
	public void cut(int indexBegin, int indexEnd) {
		if (indexBegin!=indexEnd){
			System.out.println("test2");
			CutCommand copyCommand = new CutCommand(this.document, indexBegin, indexEnd);
			this.presseP=copyCommand.getPresseP();
			copyCommand.execute();

		}
	}


	/*
	* Met dans le presse papier la sélection
	* @param  indexBegin  début de la sélection
	* @param  indexEnd fin de la sélection
	*/
	public void copy(int indexBegin, int indexEnd) {
		if (indexBegin!=indexEnd){
			System.out.println("test2");
			CopyCommand copyCommand = new CopyCommand(this.document, indexBegin, indexEnd);
			this.presseP=copyCommand.getPresseP();
			copyCommand.execute();

		}
	}


	/*
	* Colle le presse au niveau de la sélection et remplace les éléments sélectionnés
	* @param  indexBegin  début de la sélection
	* @param  indexEnd fin de la sélection
	*/
	public void paste(int indexBegin, int indexEnd) {
		if (!this.presseP.isEmpty()){
			PasteCommand pasteCommand = new PasteCommand(this.document, indexBegin, indexEnd,this.presseP);
			pasteCommand.execute();
		}

	}


	/*
	* Ajoute le caractère à l'index précisé
	* @param  glyph  début de la sélection
	* @param  indexBegin début de la sélection
	* @param  indexEnd fin de la sélection
	*/
	public void insert(Glyph glyph, int indexBegin, int indexEnd) {
		System.out.println("Controller" + indexBegin);
		InsertCommand insertCmd = new InsertCommand(this.document, glyph,indexBegin,indexEnd);
		insertCmd.execute();

	}


	/*
	* Place le curseur en fonction de l'endroit cliqué
	* @param  point  emplacement du clic de la souris
	*/
	public void locateGlyph(Point point) {

		this.document.getCursor().mapPointToRowAndColumn(point);
		this.document.getCursor().mapToGlyphIndex();

		this.document.getCursor().updateCursor();
		this.view.update();
	}

	/*
	* Renvoie l'emplacement de la souris
	* @return  int  renvoie l'emplacement de la souris
	*/
	public int getGlyphIndex2(){
		return this.document.getCursor().getGlyphIndex();
	}


	/*
	* Supprime les éléments sélectionnés ou le caractère précédent si aucun élément n'est sélectionné
	* @param  indexBegin  début de la sélection
	* @param  indexEnd fin de la sélection
	*/
	public void delete(int indexBegin, int indexEnd) {
		DeleteCommand deleteCmd = new DeleteCommand(this.document,  indexBegin,  indexEnd);
		deleteCmd.execute();
	}

}
