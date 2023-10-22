package esir2.omd.tp2.v2.patterns.commands;

import java.util.ArrayList;
import java.util.List;

import esir2.omd.tp2.v2.patterns.Glyph;

public class InsertCommand implements Command {

	private Glyph document;
	private Glyph glyph;
	private List<Glyph>save;
	private int insertB;
	private int insertE;
	private Boolean use;


	// Constructeur de la classe
	public InsertCommand(Glyph document, Glyph g, int indexBegin,int indexEnd) {

		this.document = document;
		this.glyph = g;
		this.insertB = indexBegin;
		this.save= new ArrayList<Glyph>();
		this.insertE = indexEnd;
		this.use=false;
		System.out.println(indexBegin);
	}

	/*
	* Méthode exécutant la commande
	*/
	public void execute() {
		System.out.println("Command " + insertB);
		for(int i=insertB;i<insertE;i++){
			if(!this.use){
				this.save.add( this.document.getChild(insertB));
			}
			this.document.remove(insertB);
		}
			this.document.add(glyph, insertB);		

	}


	/*
	* Méthode permettant le retour en arrière
	*/
	public void undo() {
		this.document.remove(insertB);
		for(int i=0;i<this.save.size();i++){
			this.document.add(this.save.get(i),insertB+i);
		}
		
		

	}

	/*
	* Méthode précisant si la commande est réversible ou pas
	*/
	public boolean isReversible() {
		return true;
	}

}
