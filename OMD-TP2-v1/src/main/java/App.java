import esir2.omd.tp2.v1.app.TextApp;
import esir2.omd.tp2.v1.patterns.composite.Composition;
import esir2.omd.tp2.v1.patterns.strategies.Compositor;
import esir2.omd.tp2.v1.patterns.strategies.SimpleCompositor;

public class App {
	public static void main(String[] args) {
		Compositor strategy = new SimpleCompositor();
		Composition glyph = new Composition(strategy);
		TextApp textApp = new TextApp(glyph);

	}
}