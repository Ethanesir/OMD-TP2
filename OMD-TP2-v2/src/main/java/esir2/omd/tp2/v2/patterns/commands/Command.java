package esir2.omd.tp2.v2.patterns.commands;

public interface Command {

	public void execute();

	public void undo();

	public boolean isReversible();
}
