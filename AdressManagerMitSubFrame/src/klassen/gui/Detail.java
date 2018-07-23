package klassen.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import klassen.Kontakt;


public class Detail  extends JFrame  implements GUINotification{
	private static final long serialVersionUID = -3853211640762160090L;
	private JLabel lblHeader;
	private JLabel lblPos;
	private JLabel lblVorname;
	private JLabel lblNachname;
	private JTextField fldPos;
	private JTextField fldVorname;
	private JTextField fldNachname;
	private JPanel pnlAdd;
	private JPanel pnlLeft;
	private JButton btnDetail;

	private Kontakt kontact;

	public Detail(KontaktFrame kontaktFrame, Kontakt k) {
		kontact = k;
		
		setTitle("Adress Details");
		createWidgets();
		setupInteractions();
		addWidgets();				
		pack();
		setVisible(true);
		setLocation(550, 300);
		setSize(300, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	private void setupInteractions() {
		btnDetail.addActionListener(new DetailAction());
	}

	
	private void createWidgets() {
		lblHeader = new JLabel("AdressDetails");
		lblHeader.setFont(lblHeader.getFont().deriveFont(
				Font.BOLD + Font.ITALIC, 30));
		lblHeader.setForeground(Color.WHITE);
		lblHeader.setOpaque(true);
		lblHeader.setBackground(Color.RED);
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);

		pnlAdd = new JPanel();
		pnlAdd.setLayout(new GridLayout(0, 2, 5, 5));

		lblPos = new JLabel("Pos");
		lblPos.setEnabled(false);

		lblVorname = new JLabel("Vorname");
		lblNachname = new JLabel("Nachname");

		fldPos = new JTextField();
		fldPos.setEnabled(false);

		fldVorname = new JTextField(kontact.getVorname());
		fldNachname = new JTextField(kontact.getNachname());

		btnDetail = new JButton("Detail");
		btnDetail.setEnabled(true);


		pnlLeft = new JPanel();
		pnlLeft.setLayout(new BoxLayout(pnlLeft, BoxLayout.PAGE_AXIS));

	}
	
	private void addWidgets() {
		getContentPane().setLayout(new BorderLayout(5, 5));
		getContentPane().add(BorderLayout.PAGE_START, lblHeader);
		getContentPane().add(BorderLayout.LINE_START, pnlLeft);

		pnlAdd.add(lblPos);
		pnlAdd.add(fldPos);

		pnlAdd.add(lblVorname);
		pnlAdd.add(fldVorname);

		pnlAdd.add(lblNachname);
		pnlAdd.add(fldNachname);

		pnlAdd.add(Box.createHorizontalGlue());
		pnlAdd.add(btnDetail);

		
		pnlAdd.setMaximumSize(pnlAdd.getPreferredSize());
		pnlAdd.setAlignmentX(LEFT_ALIGNMENT);

		pnlLeft.add(pnlAdd);
		pnlLeft.add(Box.createVerticalGlue());

		pnlLeft.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
	}

	
	
	public class DetailAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			updateGUI();
		}
	}

	
	@Override
	public void updateGUI() {
		fldPos.setText("111");
		fldVorname.setText("Max");
		fldNachname.setText("Mustermann");
	}



}
