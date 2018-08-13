package programme.gui;

import java.awt.Frame;


import javax.swing.SwingUtilities;
import javax.swing.UIManager;


public class Test {
	
	public static void main(String[] args) {
	
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					e.printStackTrace();
				}

				TestMainFrame frame = new TestMainFrame();
				//model.setNotifier(frame);
				frame.setVisible(true);
				frame.setExtendedState(Frame.MAXIMIZED_BOTH);

			}
		});

	}

}
	