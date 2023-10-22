package esir2.omd.tp2.v1.patterns.commands;

import esir2.omd.tp2.v1.patterns.Glyph;

public class InsertCommand implements Command {

	private Glyph document;
	private Glyph glyph;
	private int insertB;
	private int insertE;


	// Constructeur de la classe
	public InsertCommand(Glyph document, Glyph g, int indexBegin,int indexEnd) {

		this.document = document;
		this.glyph = g;
		this.insertB = indexBegin;
		this.insertE = indexEnd;
	}

	/*
	* Méthode exécutant la commande
	*/
	public void execute() {
		System.out.println("Command " + insertB);
		for(int i=insertB;i<insertE;i++){
			this.document.remove(insertB);
		}
			this.document.add(glyph, insertB);		

	}




}
