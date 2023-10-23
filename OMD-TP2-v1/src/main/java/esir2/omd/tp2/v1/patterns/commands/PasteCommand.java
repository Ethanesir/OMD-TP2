package esir2.omd.tp2.v1.patterns.commands;

import java.util.*;

import esir2.omd.tp2.v1.patterns.Glyph;

public class PasteCommand implements Command {



	private Glyph document;
	private int indexB;
	private int indexE;
	private List<Glyph>copie;

	// Constructeur de la classe
	public PasteCommand(Glyph document, int indexBegin, int indexEnd,List<Glyph> pressep) {
		this.document = document;
		this.indexB = indexBegin;
		this.indexE = indexEnd;
		this.copie=pressep;
	}

	/*
	* Méthode exécutant la commande
	*/
	public void execute() {
		int deleteIndex=indexB;
		this.document.getCursor().setGlyphIndex(this.indexE);
		for(int i=indexB;i<indexE;i++){
			this.document.remove(deleteIndex);
		}
		for(int i=0;i<this.copie.size();i++){
			this.document.add(copie.get(i),deleteIndex+i);
		}


	}




}
