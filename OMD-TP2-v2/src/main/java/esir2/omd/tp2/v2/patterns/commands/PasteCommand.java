package esir2.omd.tp2.v2.patterns.commands;

import java.util.*;

import esir2.omd.tp2.v2.patterns.Glyph;

public class PasteCommand implements Command {



	private Glyph document;
	private List<Glyph>save;
	private int indexB;
	private int indexE;
	private List<Glyph>copie;
	private Boolean use;

	// Constructeur de la classe
	public PasteCommand(Glyph document, int indexBegin, int indexEnd,List<Glyph> pressep) {
		this.document = document;
		this.indexB = indexBegin;
		this.indexE = indexEnd;
		this.copie=pressep;
		this.save= new ArrayList<Glyph>();
		this.use=false;
	}

	/*
	* Méthode exécutant la commande
	*/
	public void execute() {
		int deleteIndex=indexB;
		this.document.getCursor().setGlyphIndex(this.indexE);
		for(int i=indexB;i<indexE;i++){
			if(!this.use){
				this.save.add( this.document.getChild(deleteIndex));
			}
			this.document.remove(deleteIndex);
		}
		for(int i=0;i<this.copie.size();i++){
			this.document.add(copie.get(i),deleteIndex+i);
		}
		this.use=true;

	}


	/*
	* Méthode permettant le retour en arrière
	*/
	public void undo() {
		// TODO Auto-generated method stub
		for(int i=0;i<this.copie.size();i++){
			this.document.remove(indexB);
		}
		for(int i=0;i<this.save.size();i++){
			this.document.add(this.save.get(i),indexB+i);
		}

	}

	/*
	* Méthode précisant si la commande est réversible ou pas
	*/
	public boolean isReversible() {

		return true;
	}

}
