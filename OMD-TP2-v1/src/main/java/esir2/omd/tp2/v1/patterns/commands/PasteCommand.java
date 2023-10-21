package esir2.omd.tp2.v1.patterns.commands;

import java.util.List;

import esir2.omd.tp2.v1.patterns.Glyph;

public class PasteCommand implements Command {



	private Glyph document;
	private Boolean use;
	private int indexB;
	private int indexE;
	private List<Glyph>copie;

	public PasteCommand(Glyph document, int indexBegin, int indexEnd,List<Glyph> pressep) {
		this.document = document;
		this.indexB = indexBegin;
		this.indexE = indexEnd;
		this.copie=pressep;
		this.use=false;
	}
	public void execute() {
		System.out.println("OOOOO");
		int deleteIndex=indexB;
		for(int i=indexB;i<indexE;i++){
			this.document.remove(deleteIndex);
		}
		

			//this.glyph = this.document.getChild(deleteIndex);
			System.out.println(this.copie.size());
		for(int i=0;i<this.copie.size();i++){
			this.document.add(copie.get(i),deleteIndex+i);
		}
		this.use=true;

	}

	public void undo() {
		// TODO Auto-generated method stub
		for(int i=0;i<this.copie.size();i++){
			this.document.remove(indexB);
		}

	}

	public boolean isReversible() {

		return true;
	}

}
