package esir2.omd.tp2.v2.patterns.commands;

public interface Command {

	/*
	* Méthode exécutant la commande
	*/
	public void execute();


	/*
	* Méthode permettant le retour en arrière
	*/
	public void undo();

	/*
	* Méthode précisant si la commande est réversible ou pas
	*/
	public boolean isReversible();
}
