package esir2.omd.tp2.v2.patterns.commands;

import java.util.Stack;

public class CommandsInvoker {
	private Stack<Command> undoStack;
	private Stack<Command> redoStack;

	// Constructeur de la classe
	public CommandsInvoker() {
		undoStack = new Stack<Command>();
		redoStack = new Stack<Command>();
	}

	/*
	* Exécute la commande et la stock
	* @param  cmd  Commande à exécuter
	*/
	public void execute(Command cmd) {

		cmd.execute();
		if (cmd.isReversible())
			undoStack.push(cmd);

	}


	/*
	* Méthode prenant la dernière commande de la pile et l'annulant
	* Puis la stock dans la pile des redo
	*/
	public void undo() {
		
		if (!undoStack.isEmpty()) {
			Command cmd = undoStack.pop();
			redoStack.push(cmd);
			cmd.undo();
		}

	}


	/*
	* Méthode prenant la dernière commande de la pile des redo et l'exécute
	* Puis la stock dans la pile des undo
	*/
	public void redo() {
		if (!redoStack.isEmpty()) {
			Command cmd = redoStack.pop();
			undoStack.push(cmd);
			cmd.execute();
		}

	}

}
