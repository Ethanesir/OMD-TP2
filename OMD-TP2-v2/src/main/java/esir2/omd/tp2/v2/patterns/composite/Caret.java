package esir2.omd.tp2.v2.patterns.composite;

import esir2.omd.tp2.v2.patterns.Glyph;

import java.util.List;
import java.awt.Graphics;

public class Caret {
	private Composition document;
	private int rowIndex;
	private int columnIndex;
	private int selectB;
	private int selectE;
	private int glyphIndex;

	public Caret() {
		this.rowIndex = 0;
		this.columnIndex = 0;
		this.glyphIndex = 0;
	}

	public Caret(Composition document, int rowIndex, int columnIndex,
			int glyphIndex) {
		this.document = document;
		this.rowIndex = rowIndex;
		this.columnIndex = columnIndex;
		this.glyphIndex = glyphIndex;

	}

	public void setGlyphIndex(int glyphIndex) {
		this.glyphIndex = glyphIndex;
	}

	public Composition getDocument() {
		return document;
	}

	public void setDocument(Composition document) {
		this.document = document;
	}

	public int getRowIndex() {
		return rowIndex;
	}

	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}

	public int getColumnIndex() {
		return columnIndex;
	}

	public void setColumnIndex(int columnIndex) {
		this.columnIndex = columnIndex;
	}

	// public void mapPointToRowAndColumn(Point p) {
	// 	this.rowIndex = -1;
	// 	this.columnIndex = -1;
	// 	Column doc = this.document.getFormattedDocument();

	// 	if (doc.intersects(p)) {
	// 		for (int i = 0; i < doc.getRowsCount(); i++) {

	// 			Row row = (Row) doc.getChild(i);

	// 			if (row.intersects(p)) {
	// 				for (int j = 0; j < row.getGlyphsCount(); j++) {
	// 					Glyph g = row.getChild(j);
	// 					if (g.intersects(p)) {
	// 						this.rowIndex = i;
	// 						this.columnIndex = j;
	// 						System.out.println("Row is " + this.rowIndex
	// 								+ "  and colIndex iis " + this.columnIndex);
	// 						break;
	// 					}

	// 				}

	// 			}

	// 		}

	// 	}
	// }
	public void mapPointToRowAndColumn(Point p) {
		int totChar=0;
		this.rowIndex = -1;
		this.columnIndex = -1;
		Column doc = this.document.getFormattedDocument();

		if (doc.intersects(p)) {
			for (int i = 0; i < doc.getRowsCount(); i++) {
				totChar=totChar+ ((Row) doc.getChild(i)).getGlyphsCount();
			}
			int ligne=(p.getY()-25)/30;
			int colonne = (p.getX()-20)/10;
			if (totChar<(ligne*48)+colonne){
				this.rowIndex=totChar/48;
				this.columnIndex = totChar%48;
				this.glyphIndex=totChar;
						}
			else{
				this.rowIndex=ligne;
				this.columnIndex = colonne;
				this.glyphIndex=(ligne*48)+colonne;
			}
			System.out.println("Row is " + this.rowIndex
									+ "  and colIndex iis " + this.columnIndex);

			}

		}

	public void moveLeft() {

	}

	public void moveRight() {

	}

	public void mapToGlyphIndex() {
		this.glyphIndex = 0;
		Column doc = this.document.getFormattedDocument();
		if (rowIndex == -1 && columnIndex == -1) {
			this.glyphIndex = this.document.getComponents().size();

		} else {

			for (int i = 0; i < this.rowIndex ; i++) {
				Row row = (Row) doc.getChild(i);
				glyphIndex += row.getGlyphsCount();
			}
			glyphIndex += this.columnIndex;

			System.out.println("Glyph id =" + glyphIndex);
		}
	}

	public void incrementGlyphIndex() {
		
		if (this.glyphIndex < this.document.getComponents().size()) {
			this.glyphIndex++;
			selectB=this.glyphIndex;
			selectE=this.glyphIndex;
			System.out.println(this.glyphIndex);
		}

	}

	public int getGlyphIndex() {
		return glyphIndex;
	}

	public void decrementGlyphIndex() {
		if (this.glyphIndex > 0) {
			this.glyphIndex--;
			selectB=this.glyphIndex;
			selectE=this.glyphIndex;

		}

	}

	public void updateCursor() {
		deActivateCursor();
		if (this.glyphIndex == 0) {

			if (this.document.getComponents().size() > 0) {
				Glyph lastGlyph = this.document.getComponents().get(
						this.glyphIndex);

				lastGlyph.activateHasCursor(true);
			} else {
				// draw the initial cursor
			}

		}
		if (this.glyphIndex > 0) {
			Glyph lastGlyph = this.document.getComponents().get(
					this.glyphIndex - 1);
			if (lastGlyph != null) {
				lastGlyph.activateHasCursor(false);

			}
		}
	}

	public void deActivateCursor() {
		List<Glyph> components = this.document.getComponents();
		for (Glyph g : components) {
			g.deActivateHasCursor();
		}

	}
	public int getSelectB(){
		return this.selectB;
	}
	public int getSelectE(){
		return this.selectE;
	}
	public void setSelectBegin(int index){
		this.selectB=index;
	}
	public void setSelectEnd(int index){
		

		this.selectE=index;
		if(this.selectE<this.selectB){
			int temp= this.selectB;
			this.selectB=this.selectE;
			this.selectE=temp;
		}
	}
	public void draw(Graphics g) {
		
		if (this.selectB!=this.selectE){
			int ligneB=(this.selectB-(this.selectB%48))/48;
			int ligneE=(this.selectE-(this.selectE%48))/48;
			int colonneB=this.selectB%48;
			int colonneE=this.selectE%48;
			g.setColor(java.awt.Color.CYAN);
			if(ligneB!=ligneE){
				
			for(int i =0; i<=ligneE-ligneB;i++){
				if(i==0){
					g.fillRect(10*this.selectB+20,35+30*ligneB,10*(48-colonneB),22);
				}
				else if(i==((this.selectE-(this.selectE%48))/48)-((this.selectB-(this.selectB%48))/48)){
					g.fillRect(20,35+30*ligneE,10*(colonneE),22);
					System.out.println("AAA:" + colonneE);
				}
				else{
					g.fillRect(20,35+30*(ligneB+i),10*(48),22);
										System.out.println(111);
				}
			}
		}
		else{
			g.fillRect(10*this.selectB+20,35+30*ligneE,10*(this.selectE-this.selectB),22);
		}
			g.setColor(java.awt.Color.BLACK);
			System.out.println(selectB + " " + selectE);
		};
		g.fillRect(20+10*(glyphIndex % 48), 35+30*(glyphIndex/48), 2,17);
	}

}
