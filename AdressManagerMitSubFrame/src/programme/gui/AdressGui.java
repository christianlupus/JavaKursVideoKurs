package programme.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JApplet;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import klassen.AdressModel;
import klassen.Kontakt;
import klassen.gui.KontaktFrame;

public class AdressGui extends JApplet {

	private static final long serialVersionUID = 8025367283047896679L;

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		// ...
	}

	@Override
	public void init() {
		System.out.println("init");

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager
							.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					e.printStackTrace();
				}
				List<Kontakt> data = new LinkedList<Kontakt>();
				AdressModel model = new AdressModel(data);
				KontaktFrame frame = new KontaktFrame(model);
				model.setNotifier(frame);
				frame.setVisible(true);

			}
		});

		pack();
	}

	private void pack() {
		Container c = getContentPane();
		Dimension prefSize = c.getLayout().preferredLayoutSize(c);
		setSize(prefSize.width, prefSize.height);
	}

	@Override
	public void start() {
		System.out.println("start");
	}

	@Override
	public void stop() {
		System.out.println("stop");
	}

	@Override
	public void destroy() {
		System.out.println("destroy");
	}

}
