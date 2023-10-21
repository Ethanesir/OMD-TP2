package esir2.omd.tp2.patterns.commands;

import esir2.omd.tp2.patterns.Glyph;

public class InsertCommand implements Command {

	private Glyph document;
	private Glyph glyph;
	private int insertIndex;

	public InsertCommand(Glyph document, Glyph g, int index) {

		this.document = document;
		this.glyph = g;
		this.insertIndex = index;
	}

	public void execute() {
		System.out.println("Command " + insertIndex);
		this.document.add(glyph, insertIndex);

	}

	public void undo() {
		this.document.remove(insertIndex);

	}

	public boolean isReversible() {
		return true;
	}

}
