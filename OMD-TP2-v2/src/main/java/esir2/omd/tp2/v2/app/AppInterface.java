package esir2.omd.tp2.v2.app;

import esir2.omd.tp2.v2.patterns.Glyph;
import esir2.omd.tp2.v2.patterns.composite.Point;

public interface AppInterface {

	/*
	* Méthode permettant de revenir en arrière, annuler la dernière action de la pile
	*/
	public void undo();

	/*
	* Méthode permettant de rejouer la dernière action annulée 
	*/
	public void redo();

	/*
	* Supprime et met dans le presse papier la sélection
	* @param  indexBegin  début de la sélection
	* @param  indexEnd fin de la sélection
	*/
	public void cut(int indexBegin, int indexEnd);

	/*
	* Met dans le presse papier la sélection
	* @param  indexBegin  début de la sélection
	* @param  indexEnd fin de la sélection
	*/
	public void copy(int indexBegin, int indexEnd);

	/*
	* Colle le presse au niveau de la sélection et remplace les éléments sélectionnés
	* @param  indexBegin  début de la sélection
	* @param  indexEnd fin de la sélection
	*/
	public void paste(int indexBegin, int indexEnd);


	/*
	* Ajoute le caractère à l'index précisé
	* @param  glyph  début de la sélection
	* @param  indexBegin début de la sélection
	* @param  indexEnd fin de la sélection
	*/
	public void insert(Glyph glyph, int indexBegin, int indexEnd);

	/*
	* Supprime les éléments sélectionnés ou le caractère précédent si aucun élément n'est sélectionné
	* @param  indexBegin  début de la sélection
	* @param  indexEnd fin de la sélection
	*/
	public void delete(int indexBegin, int indexEnd);

	/*
	* Place le curseur en fonction de l'endroit cliqué
	* @param  point  emplacement du clic de la souris
	*/
	public void locateGlyph(Point point);

	/*
	* Renvoie l'emplacement de la souris
	* @return  int  renvoie l'emplacement de la souris
	*/
	public int getGlyphIndex2();


}
