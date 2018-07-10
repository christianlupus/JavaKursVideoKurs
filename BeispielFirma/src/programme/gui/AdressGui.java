package programme.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import klassen.AdressModel;
import klassen.KontaktPrivat;
import klassen.gui.FMFrame;

public class AdressGui extends JApplet {

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
				AdressModel<KontaktPrivat> model = new AdressModel<KontaktPrivat>(
						1);

				JFrame frame = new FMFrame(model);
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
