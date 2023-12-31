package esir2.omd.tp2.v1.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import esir2.omd.tp2.v1.app.AppInterface;
import esir2.omd.tp2.v1.patterns.observer.Observer;
import esir2.omd.tp2.v1.patterns.composite.CharacterModel;
import esir2.omd.tp2.v1.patterns.Glyph;
import esir2.omd.tp2.v1.patterns.composite.Composition;
import esir2.omd.tp2.v1.patterns.composite.Point;

public class Window extends JPanel implements Observer, MouseListener {

	private static final long serialVersionUID = 1L;
	private Glyph glyph;
	private Point startPosition;

	private AppInterface controller;
	private JFrame frame;
	private JMenuBar menubar;
	private JMenu edit;

	// Edit menu items
	private JMenuItem cut;
	private JMenuItem copy;
	private JMenuItem paste;

	// Preferences menu items

	public Window(Glyph g, AppInterface controller) {
		this.glyph = g;
		this.controller = controller;
		glyph.registerObservers((Observer) this); // On sauvegarde chaque saisie dans notre observeur
		startPosition = new Point(20, 50);

	}
	
	public void setGlyph(Composition document) {
		this.glyph = document;
	}

	public Point getStartPosition() {
		return startPosition;
	}

	public void setStartPosition(Point startPosition) {
		this.startPosition = startPosition;
	}

	public void createView() {
		frame = new JFrame("OMD Text Editor");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		creatControls();
		this.setFocusable(true);
		this.addKeyListener(new TypeListener());
		addMouseListener(this);
		frame.add(this);

		frame.setSize(new Dimension(500, 500));
		frame.setVisible(true);
	}

	// Créer les boutons dans la barre
	public void creatControls() {
		menubar = new JMenuBar();

		edit = new JMenu("Edit");

		edit.addSeparator();
		cut = new JMenuItem("Cut");
		edit.add(cut);
		cut.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				controller.cut(glyph.getCursor().getSelectB(),glyph.getCursor().getSelectE());

			}

		});

		copy = new JMenuItem("Copy");
		edit.add(copy);
		copy.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.out.println("test");
				controller.copy(glyph.getCursor().getSelectB(),glyph.getCursor().getSelectE());

			}

		});

		paste = new JMenuItem("Paste");
		edit.add(paste);
		paste.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				controller.paste(glyph.getCursor().getSelectB(),glyph.getCursor().getSelectE());

			}

		});

		edit.setMnemonic('E');

		cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				ActionEvent.CTRL_MASK));
		copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				ActionEvent.CTRL_MASK));
		paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
				ActionEvent.CTRL_MASK));

		menubar.add(edit);
		frame.setJMenuBar(menubar);

	}

	// Affiche l'éditeur et les éléments
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int width = frame.getWidth();
		int height = frame.getHeight();
		Point p = new Point(startPosition.getX(), startPosition.getY());

		glyph.draw(g, p, width, height);

	}

	// Met à jour l'affichage
	public void update() {

		repaint();

	}

	// Classe associant des touches à des actions
	private class TypeListener implements KeyListener {

		public void keyTyped(KeyEvent e) {

		}

		public void keyPressed(KeyEvent e) {

			if (e.getKeyCode() == KeyEvent.VK_DELETE || e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
				controller.delete(glyph.getCursor().getSelectB(),glyph.getCursor().getSelectE());
			}
			 else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

				System.out.println("Right");
				glyph.getCursor().incrementGlyphIndex();
				repaint();
			 } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {

				System.out.println("Left");
				glyph.getCursor().decrementGlyphIndex();
				repaint();
			 }

			else if (!e.isControlDown() && !e.isAltDown()  && ! (e.getKeyCode()== KeyEvent.VK_SHIFT)) {
				System.out.println(e.getKeyChar() + "   called ");
				CharacterModel c = new CharacterModel(e.getKeyChar());
				System.out.println(glyph.getCursor().getGlyphIndex());
				controller.insert(c, glyph.getCursor().getSelectB(),glyph.getCursor().getSelectE());
			}

		}

		public void keyReleased(KeyEvent e) {

		}

	}
	// Place le curseur lorsque l'on clique
	public void mouseClicked(MouseEvent e) {
		System.out.println("Point x" + e.getX() + "Point y" + e.getY());
		controller.locateGlyph(new Point(e.getX(), e.getY()));

	}

	// Place le curseur lorsque le clique gauche est pressé
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		controller.locateGlyph(new Point(e.getX(), e.getY()));
		this.glyph.getCursor().setSelectBegin(controller.getGlyphIndex2());
		this.glyph.getCursor().setSelectEnd(controller.getGlyphIndex2());
	}


	// Place le curseur lorsque le clique gauche est lâché
	public void mouseReleased(MouseEvent e) {
		controller.locateGlyph(new Point(e.getX(), e.getY()));
		this.glyph.getCursor().setSelectEnd(controller.getGlyphIndex2());
		update();
	}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

}
