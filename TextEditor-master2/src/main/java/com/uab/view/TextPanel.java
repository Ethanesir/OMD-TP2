package com.uab.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.uab.controller.ControllerInterface;
import com.uab.listeners.Observer;
import com.uab.model.ArrowModel;
import com.uab.model.CharacterModel;
import com.uab.model.Glyph;
import com.uab.model.ImageModel;
import com.uab.model.Point;

public class TextPanel extends JPanel implements Observer, MouseListener {

	private static final long serialVersionUID = 1L;
	private Glyph glyph;
	private Point startPosition;

	private ControllerInterface controller;
	private JFrame frame;
	private JMenuBar menubar;
	private JMenu file;
	private JMenu edit;
	private JMenu preferences;

	private JMenu help;


	// Edit menu items
	private JMenuItem undo;
	private JMenuItem redo;
	private JMenuItem cut;
	private JMenuItem copy;
	private JMenuItem paste;

	// Preferences menu items
	private JMenuItem spellCheck;
	private JMenuItem border;
	private JMenuItem scroll;

	public TextPanel(Glyph g, ControllerInterface controller) {
		this.glyph = g;
		this.controller = controller;
		glyph.registerObservers((Observer) this);
		startPosition = new Point(20, 50);

	}

	public void setGlyph(Glyph document) {
		this.glyph = document;
	}

	public Point getStartPosition() {
		return startPosition;
	}

	public void setStartPosition(Point startPosition) {
		this.startPosition = startPosition;
	}

	public void createView() {
		frame = new JFrame("Suraj's Text Editor");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		creatControls();
		this.setFocusable(true);
		this.addKeyListener(new TypeListener());
		addMouseListener(this);
		frame.add(this);

		frame.setSize(new Dimension(500, 500));
		frame.setVisible(true);
	}

	public void creatControls() {
		menubar = new JMenuBar();

		edit = new JMenu("Edit");
		undo = new JMenuItem("Undo Typing");
		edit.add(undo);
		undo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				controller.undo();

			}

		});

		redo = new JMenuItem("Redo");
		edit.add(redo);
		redo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				controller.redo();

			}

		});

		edit.addSeparator();
		cut = new JMenuItem("Cut");
		edit.add(cut);
		cut.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				controller.cut();

			}

		});

		copy = new JMenuItem("Copy");
		edit.add(copy);
		copy.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				controller.copy();

			}

		});

		paste = new JMenuItem("Paste");
		edit.add(paste);
		paste.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				controller.paste();

			}

		});

		// preferences bar
		 controller.decorateWithScrollBar();


		edit.setMnemonic('E');

		redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,
				ActionEvent.CTRL_MASK));
		undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
				ActionEvent.CTRL_MASK));
		cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				ActionEvent.CTRL_MASK));
		copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				ActionEvent.CTRL_MASK));
		paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
				ActionEvent.CTRL_MASK));

		menubar.add(edit);
		frame.setJMenuBar(menubar);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int width = frame.getWidth();
		int height = frame.getHeight();
		Point p = new Point(startPosition.getX(), startPosition.getY());

		glyph.draw(g, p, width, height);

	}

	public void update() {

		repaint();

	}

	private class TypeListener implements KeyListener {

		public void keyTyped(KeyEvent e) {

		}

		public void keyPressed(KeyEvent e) {

			if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_I) {
				JFileChooser chooser = new JFileChooser();

				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"JPG & GIF Images", "jpg", "jpeg", "gif", "png");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(frame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {

					File imageFile = chooser.getSelectedFile();
					BufferedImage img = null;
					try {
						img = ImageIO.read(imageFile);
						ImageModel p = new ImageModel(img, chooser
								.getSelectedFile().getAbsolutePath());
						controller.insert(p, glyph.getCursor().getGlyphIndex());
					} catch (IOException exception) {
						System.out.println("No file found");
					}

				}

			} else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Z) {
				controller.undo();
			} else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Y) {
				controller.redo();
			} else if (e.getKeyCode() == KeyEvent.VK_DELETE) {
				controller.delete(glyph.getCursor().getGlyphIndex());
			}

			else if (e.getKeyCode() == KeyEvent.VK_UP) {
				System.out.println("UPPP");
				controller.scrollUp();
			} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {

				System.out.println("Down");
				controller.scrollDown();
			} else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_A) {
				ArrowModel arrow = new ArrowModel();
				controller.insert(arrow, glyph.getCursor().getGlyphIndex());
			}

			else if (!e.isControlDown() && !e.isAltDown()) {
				System.out.println(e.getKeyChar() + "   callled ");
				CharacterModel c = new CharacterModel(e.getKeyChar());
				System.out.println(glyph.getCursor().getGlyphIndex());
				controller.insert(c, glyph.getCursor().getGlyphIndex());
			}

		}

		public void keyReleased(KeyEvent e) {

		}

	}

	public void mouseClicked(MouseEvent e) {
		System.out.println("Point x" + e.getX() + "Point y" + e.getY());
		controller.locateGlyph(new Point(e.getX(), e.getY()));

	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
