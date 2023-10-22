package esir2.omd.tp2.v2.patterns.commands;

import esir2.omd.tp2.v2.patterns.Glyph;

import java.util.*;
public class CopyCommand implements Command {

	private Glyph document;
	private int insertB;
	private int insertE;
	private List<Glyph>copie;


	// Constructeur de la classe
	public CopyCommand(Glyph document, int indexBegin, int indexEnd) {
		System.out.println(indexBegin + " :: "+ indexEnd);
		this.document = document;
		this.insertB = indexBegin;
		this.insertE = indexEnd;
		copie=new ArrayList<Glyph>();
	}

	/*
	* Méthode exécutant la commande
	*/
	public void execute() {
		
		System.out.println("Command Copy");
		for(int i=this.insertB;i<this.insertE;i++){
			copie.add(this.document.getComponents().get(i));
		}
	}

	/*
	* Méthode renvoyant le chaîne de caractère copié
	*/
	public List<Glyph> getPresseP(){
		return this.copie;
	}



}
