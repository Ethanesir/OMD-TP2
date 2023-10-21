package esir2.omd.tp2.v1.patterns.commands;

import esir2.omd.tp2.v1.patterns.Glyph;

import java.util.*;
public class CopyCommand implements Command {

	private Glyph document;
	private int insertB;
	private int insertE;
	private List<Glyph>copie;

	public CopyCommand(Glyph document, int indexBegin, int indexEnd) {
		System.out.println(indexBegin + " :: "+ indexEnd);
		this.document = document;
		this.insertB = indexBegin;
		this.insertE = indexEnd;
		copie=new ArrayList<Glyph>();
	}

	public void execute() {
		
		System.out.println("Command Copy");
		for(int i=this.insertB;i<this.insertE;i++){
			copie.add(this.document.getComponents().get(i));
		}
	}

	public List<Glyph> getPresseP(){
		return this.copie;
	}


}
