package esir2.omd.tp2.v2.patterns.commands;

import esir2.omd.tp2.v2.patterns.Glyph;

import java.util.*;
public class CutCommand implements Command {

	private Glyph document;
	private Boolean use;
	private int insertB;
	private int insertE;
	private List<Glyph>copie;

	// Constructeur de la classe
	public CutCommand(Glyph document, int indexBegin, int indexEnd) {
		System.out.println(indexBegin + " :: "+ indexEnd);
		this.document = document;
		this.insertB = indexBegin;
		this.insertE = indexEnd;
		this.copie=new ArrayList<Glyph>();
		this.use=false;
	}


	/*
	* Méthode exécutant la commande
	*/
	public void execute() {
		System.out.println("Command Cut");
		this.document.getCursor().setGlyphIndex(this.insertE);
		for(int i=this.insertB;i<this.insertE;i++){
			if(!use){
			copie.add(this.document.getComponents().get(insertB));
			}
			this.document.remove(insertB);
		}
		use=true;
	}

	/*
	* Méthode renvoyant le chaîne de caractère copié
	*/
	public List<Glyph> getPresseP(){
		return this.copie;
	}

	/*
	* Méthode permettant le retour en arrière
	*/
	public void undo() {
		System.out.println("AAAAAAAA");
		if (insertB==insertE){
			this.insertB = this.insertB - 1;

		if (insertB >= 0) {
			this.document.add(copie.get(0), insertB);
		}
		}
		else {			
			for(int i=0;i<this.copie.size();i++){
				this.document.add(copie.get(i), insertB+i);
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
