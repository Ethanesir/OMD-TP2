package esir2.omd.tp2.v1.patterns.commands;

import esir2.omd.tp2.v1.patterns.Glyph;

public class DeleteCommand implements Command {
	private Glyph document;
	private int deleteIndex;
	private int indexB;
	private int indexE;

	// Constructeur de la classe
	public DeleteCommand(Glyph document, int indexBegin, int indexEnd) {

		this.document = document;
		this.indexB = indexBegin;
		this.indexE = indexEnd;
	}

	/*
	* Méthode exécutant la commande
	*/
	public void execute() {
		if (indexB==indexE){
			this.deleteIndex = this.indexB - 1;

			if (deleteIndex >= 0) {
				this.document.remove(deleteIndex);
			}
		}
		else {

			for(int i=0;i<(this.indexE-this.indexB);i++){
				this.document.remove(indexB);
			}
	
		}
	}


	

}
