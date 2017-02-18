package client.chat.room;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import net.sf.json.JSONObject;
import client.chat.context.ChatContext;
import client.chat.thread.ChatThread;
import java.awt.GridLayout;


public class ChatRoomUI extends JFrame implements KeyListener, ActionListener{


	private static final long serialVersionUID = 6025454848423640743L;

	public ChatThread thread = null;
	public JTextField textField;
	
	public JLabel pirateText = null;
	public JLabel moneyText = null;
	public JLabel idText = null;
	public JLabel dateText = null;
	
	public JButton makeWarBtn = null;
	public static JTable table;
	
	
	public ChatRoomUI(ChatThread thread){
		this.thread = thread;
		this.setTitle(ChatContext.TITLE);
		this.setPreferredSize(new Dimension(ChatContext.UI_ROOM_WIDTH, ChatContext.UI_ROOM_HEIGHT));
		this.addWindowListener(new WinEvent());
	
		
		JPanel allPanel = new JPanel();
		allPanel.setBackground(Color.decode(ChatContext.UI_BACKGROUND));
		allPanel.setLayout(null);

		
		JPanel sessionPanel = new JPanel();
		sessionPanel.setBackground(new Color(128, 0, 0));
		sessionPanel.setBounds(5, 5, 383, 139);
		sessionPanel.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		
		pirateText = new JLabel();
		pirateText.setForeground(new Color(255, 255, 255));
		idText = new JLabel();
		idText.setForeground(new Color(255, 255, 255));
		moneyText = new JLabel();
		moneyText.setForeground(new Color(255, 255, 255));
		dateText = new JLabel();
		dateText.setForeground(new Color(255, 255, 255));
		
		pirateText.setFont(new Font("바탕", Font.PLAIN, 24));
		idText.setFont(new Font("바탕", Font.PLAIN, 24));
		moneyText.setFont(new Font("바탕", Font.PLAIN, 24));
		dateText.setFont(new Font("바탕", Font.PLAIN, 24));
		sessionPanel.setLayout(new GridLayout(4, 1, 0, 0));
		
		sessionPanel.add(pirateText);
		sessionPanel.add(idText);
		sessionPanel.add(moneyText);
		sessionPanel.add(dateText);
		
		
		makeWarBtn = new JButton("Make a War!! Bastard!!");
		makeWarBtn.setFont(new Font("바탕", Font.PLAIN, 25));
		makeWarBtn.setBounds(0, 586, 394, 74);
		makeWarBtn.setForeground(Color.WHITE);
		makeWarBtn.setBackground(Color.decode(ChatContext.UI_BUTTON));
		makeWarBtn.addActionListener(this);
		
		JPanel centerPanel = new JPanel();
		centerPanel.setBounds(0, 150, 394, 437);
		centerPanel.setLayout(null);
		
		
		
		textField = new JTextField();
		textField.setBounds(0, 0, 397, 31);
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setFont(new Font("바탕", Font.PLAIN, 21));
		textField.setColumns(23);
		
		
		centerPanel.add(textField);
		
		
		allPanel.add(sessionPanel);
		allPanel.add(makeWarBtn);
		allPanel.add(centerPanel);
		
				
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setFont(new Font("바탕", Font.PLAIN, 21));
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 33, 394, 404);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		centerPanel.add(scrollPane);
		
		getContentPane().add(allPanel);
		this.pack();
		this.setVisible(true);
	}
	

	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		JButton btn = (JButton) arg0.getSource();
		if(btn==makeWarBtn){
			JSONObject packet = new JSONObject();
			packet.put("FUNC", ChatContext.FUNC_MAKE_ROOM);
			thread.send(packet,this);
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode()==KeyEvent.VK_ENTER){
			
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public class WinEvent extends WindowAdapter{
		@Override
		public void windowClosing(WindowEvent arg0) {
			// TODO Auto-generated method stub
			thread.requestSessionOut();
			
		}

	}
}






