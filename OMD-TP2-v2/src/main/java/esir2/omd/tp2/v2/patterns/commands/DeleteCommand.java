package esir2.omd.tp2.v2.patterns.commands;

import java.util.*;

import esir2.omd.tp2.v2.patterns.Glyph;

public class DeleteCommand implements Command {
	private Glyph document;
	private  List<Glyph> save;
	private int deleteIndex;
	private int indexB;
	private int indexE;
	private Boolean use;

	// Constructeur de la classe
	public DeleteCommand(Glyph document, int indexBegin, int indexEnd) {

		this.document = document;
		this.indexB = indexBegin;
		this.indexE = indexEnd;
		this.save= new ArrayList<Glyph>();
		this.use=false;
	}

	/*
	* Méthode exécutant la commande
	*/
	public void execute() {
		if (indexB==indexE){
			this.deleteIndex = this.indexB - 1;

			if (deleteIndex >= 0) {
				if(!this.use){
				this.save.add( this.document.getChild(deleteIndex));
				}
				this.document.remove(deleteIndex);
			}
		}
		else {
			this.document.getCursor().setGlyphIndex(this.indexE);
			for(int i=0;i<(this.indexE-this.indexB);i++){
				if(!this.use){
					this.save.add( this.document.getChild(deleteIndex));
				}
				this.document.remove(indexB);
			}
	
		}
		
		use=true;
	}


	/*
	* Méthode permettant le retour en arrière
	*/
	public void undo() {
		if (indexB==indexE){
			this.deleteIndex = this.indexB - 1;

		if (deleteIndex >= 0) {
			this.document.add(save.get(0), deleteIndex);
		}
		}
		else {

			
			for(int i=0;i<this.save.size();i++){
				this.document.add(save.get(i), indexB+i);
			}
	
		}

	}

	/*
	* Méthode précisant si la commande est réversible ou pas
	*/
	public boolean isReversible() {
		return true;
	}

}
