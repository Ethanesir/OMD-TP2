import esir2.omd.tp2.v2.app.TextApp;
import esir2.omd.tp2.v2.patterns.composite.Composition;
import esir2.omd.tp2.v2.patterns.strategies.Compositor;
import esir2.omd.tp2.v2.patterns.strategies.SimpleCompositor;

public class App {
	public static void main(String[] args) {
		Compositor strategy = new SimpleCompositor();
		Composition glyph = new Composition(strategy);
		TextApp textApp = new TextApp(glyph);

	}
}