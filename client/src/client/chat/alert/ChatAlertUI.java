package client.chat.alert;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import client.chat.context.ChatContext;

public class ChatAlertUI extends JDialog implements ActionListener, KeyListener {

	/**
		 * 
		 */
	private static final long serialVersionUID = 3460756226221759514L;

	public ChatAlertUI(JFrame frame, String title, String context) {
		super(frame, title, false);

		JPanel alertPanel = new JPanel();
		alertPanel.setLayout(null);
		alertPanel.setBackground(Color.decode(ChatContext.UI_BACKGROUND));
		alertPanel.setPreferredSize(new Dimension(ChatContext.UI_ALERT_WIDTH,
				ChatContext.UI_ALERT_HEIGHT));

		JLabel logoContainer = new JLabel("");
		logoContainer.setBounds(132, 26, 81, 81);
		ImageIcon logo = new ImageIcon(ChatContext.RES_IMG_ALERT);

		logoContainer.setIcon(logo);

		JLabel text = new JLabel("<HTML>" + context + "</HTML>");
		text.setHorizontalAlignment(SwingConstants.CENTER);
		text.setBounds(36, 99, 281, 122);
		text.setBackground(Color.decode(ChatContext.UI_BACKGROUND));
		text.setFont(new Font("바탕", Font.BOLD, 14));

		JButton btn = new JButton("OK");
		btn.setForeground(Color.decode(ChatContext.UI_TEXT_WHITE));
		btn.setFont(new Font("바탕", Font.BOLD, 29));
		btn.setBounds(17, 236, 316, 49);
		btn.setBackground(Color.decode(ChatContext.UI_BUTTON));
		btn.addActionListener(this);
		btn.addKeyListener(this);
		alertPanel.add(logoContainer);
		alertPanel.add(text);
		alertPanel.add(btn);

		this.getContentPane().addKeyListener(this);
		this.setContentPane(alertPanel);
		this.pack();

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE) {
			this.setVisible(false);
		} else if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
			this.setVisible(false);
		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		this.setVisible(false);
	}

}
