package br.edu.ifsuldeminas.sd.chat.view;

import javax.swing.*;
import java.awt.*;

public class ConnectionView extends JFrame {

	private JTextField txtLocalPort;
	private JTextField txtRemotePort;
	private JTextField txtRemoteIp;
	private JTextField txtName;

	private JButton btnConnect;

	private JRadioButton udpButton;
	private JRadioButton tcpButton;

	private JRadioButton p2pButton;
	private JRadioButton groupButton;

	private JPanel protocolPanel;

	public ConnectionView() {

		setTitle("Conectar ao Chat");
		setSize(420, 550);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		getContentPane().setBackground(
				new Color(30,30,30)
				);

		initializeComponents();

		setVisible(true);
	}

	private JTextField createField(
			JPanel panel,
			String labelText,
			Font font
			) {

		JLabel label = new JLabel(labelText);

		label.setForeground(Color.WHITE);

		label.setFont(font);

		JTextField field = new JTextField();

		field.setMaximumSize(
				new Dimension(
						Integer.MAX_VALUE,
						35
						)
				);

		field.setFont(font);

		field.setBackground(
				new Color(40,40,40)
				);

		field.setForeground(
				Color.WHITE
				);

		field.setCaretColor(
				Color.WHITE
				);

		field.setAlignmentX(
				Component.LEFT_ALIGNMENT
				);

		label.setAlignmentX(
				Component.LEFT_ALIGNMENT
				);

		panel.add(label);
		panel.add(Box.createVerticalStrut(5));
		panel.add(field);
		panel.add(Box.createVerticalStrut(10));

		return field;
	}

	private void initializeComponents() {

		JPanel panel = new JPanel();

		panel.setBackground(
				new Color(24,24,24)
				);

		panel.setLayout(
				new BoxLayout(
						panel,
						BoxLayout.Y_AXIS
						)
				);

		panel.setBorder(
				BorderFactory.createEmptyBorder(
						20,20,20,80
						)
				);

		Font font =
				new Font(
						"Arial",
						Font.PLAIN,
						14
						);

		JLabel title =
				new JLabel(
						"CHAT DISTRIBUÍDO"
						);

		title.setForeground(
				Color.WHITE
				);

		title.setFont(
				new Font(
						"Arial",
						Font.BOLD,
						22
						)
				);

		title.setAlignmentX(
				Component.LEFT_ALIGNMENT
				);

		panel.add(title);

		panel.add(
				Box.createVerticalStrut(20)
				);

		JLabel lblMode =
				new JLabel("Modo:");

		lblMode.setForeground(
				Color.WHITE
				);

		p2pButton =
				new JRadioButton("P2P");

		groupButton =
				new JRadioButton("Grupo");

		p2pButton.setSelected(true);

		p2pButton.setBackground(
				new Color(24,24,24)
				);

		groupButton.setBackground(
				new Color(24,24,24)
				);

		p2pButton.setForeground(
				Color.WHITE
				);

		groupButton.setForeground(
				Color.WHITE
				);

		ButtonGroup modeGroup =
				new ButtonGroup();

		modeGroup.add(p2pButton);
		modeGroup.add(groupButton);

		JPanel modePanel =
				new JPanel(
						new FlowLayout(
								FlowLayout.LEFT
								)
						);

		modePanel.setBackground(
				new Color(24,24,24)
				);

		modePanel.setAlignmentX(
				Component.LEFT_ALIGNMENT
				);

		modePanel.add(lblMode);
		modePanel.add(p2pButton);
		modePanel.add(groupButton);

		panel.add(modePanel);

		JLabel lblProtocol =
				new JLabel("Protocolo:");

		lblProtocol.setForeground(
				Color.WHITE
				);

		udpButton =
				new JRadioButton("UDP");

		tcpButton =
				new JRadioButton("TCP");

		udpButton.setSelected(true);

		udpButton.setBackground(
				new Color(24,24,24)
				);

		tcpButton.setBackground(
				new Color(24,24,24)
				);

		udpButton.setForeground(
				Color.WHITE
				);

		tcpButton.setForeground(
				Color.WHITE
				);

		ButtonGroup protocolGroup =
				new ButtonGroup();

		protocolGroup.add(udpButton);
		protocolGroup.add(tcpButton);

		protocolPanel =
				new JPanel(
						new FlowLayout(
								FlowLayout.LEFT
								)
						);

		protocolPanel.setBackground(
				new Color(24,24,24)
				);

		protocolPanel.setAlignmentX(
				Component.LEFT_ALIGNMENT
				);

		protocolPanel.add(lblProtocol);
		protocolPanel.add(udpButton);
		protocolPanel.add(tcpButton);

		panel.add(protocolPanel);

		txtLocalPort =
				createField(
						panel,
						"Porta Local:",
						font
						);

		txtRemoteIp =
				createField(
						panel,
						"IP / Grupo:",
						font
						);

		txtRemotePort =
				createField(
						panel,
						"Porta Remota:",
						font
						);

		txtName =
				createField(
						panel,
						"Nome:",
						font
						);

		btnConnect =
				new JButton(
						"Conectar"
						);

		styleButton(
				btnConnect
				);

		btnConnect.setAlignmentX(
				Component.CENTER_ALIGNMENT
				);

		btnConnect.setMaximumSize(
				new Dimension(
						140,
						40
						)
				);

		panel.add(
				Box.createVerticalStrut(15)
				);

		panel.add(
				btnConnect
				);

		add(panel);

		btnConnect.addActionListener(
				e -> connect()
				);

		p2pButton.addActionListener(
				e -> updateFields()
				);

		groupButton.addActionListener(
				e -> updateFields()
				);

		updateFields();
	}

	private void updateFields() {

		boolean multicast =
				groupButton.isSelected();

		protocolPanel.setVisible(
				!multicast
				);

		txtLocalPort.setEnabled(
				!multicast
				);

		if(multicast) {

			txtRemoteIp.setText(
					"224.0.0.1"
					);

			txtRemotePort.setText(
					"1027"
					);

		} else {

			if(txtRemoteIp.getText().equals("230.0.0.1")) {
				txtRemoteIp.setText("");
			}

			if(txtRemotePort.getText().equals("5000")) {
				txtRemotePort.setText("");
			}
		}
	}

	private void styleButton(
			JButton button
			) {

		button.setBackground(
				new Color(70,130,180)
				);

		button.setForeground(
				Color.WHITE
				);

		button.setFocusPainted(false);

		button.setBorderPainted(false);

		button.setCursor(
				new Cursor(
						Cursor.HAND_CURSOR
						)
				);
	}

	private void connect() {

		try {

			String protocol;

			int localPort;

			int remotePort;

			String remoteIp;

			String name =
					txtName.getText().trim();

			if(name.isEmpty()) {

				JOptionPane.showMessageDialog(
						this,
						"Digite um nome."
						);

				return;
			}

			if(groupButton.isSelected()) {

				protocol = "MULTICAST";

				remoteIp =
						txtRemoteIp.getText().trim();

				remotePort =
						Integer.parseInt(
								txtRemotePort.getText()
								);

				localPort = remotePort;

			} else {

				protocol =
						udpButton.isSelected()
						? "UDP"
								: "TCP";

				localPort =
						Integer.parseInt(
								txtLocalPort.getText()
								);

				remoteIp =
						txtRemoteIp.getText().trim();

				remotePort =
						Integer.parseInt(
								txtRemotePort.getText()
								);
			}

			new ChatView(
					protocol,
					localPort,
					remoteIp,
					remotePort,
					name
					);

			dispose();

		} catch (Exception e) {

			JOptionPane.showMessageDialog(
					this,
					"Dados inválidos."
					);
		}
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(
				ConnectionView::new
				);
	}
}
