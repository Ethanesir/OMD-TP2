package esir2.omd.tp2.v1.patterns.strategies;

import esir2.omd.tp2.v1.patterns.composite.Composition;

public interface Compositor {

	public void setComposition(Composition composition);

	public void compose(int lineWidth, int height);
}
