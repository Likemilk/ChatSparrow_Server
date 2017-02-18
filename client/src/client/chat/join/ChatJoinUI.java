package client.chat.join;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import net.sf.json.JSONObject;
import client.chat.context.ChatContext;
import client.chat.thread.ChatThread;

public class ChatJoinUI extends JFrame implements ActionListener,KeyListener{
	
	private static final long serialVersionUID = -4601115652583871038L;
	
	public JTextField pirateText=null;
	public JTextField idText=null;
	public JPasswordField passwordText=null;
	public JButton joinButton = null;
	public ChatThread thread=null;
	

	
	
	public ChatJoinUI(ChatThread thread){
		super(ChatContext.TITLE);
		
		this.thread = thread;
		this.setTitle(ChatContext.TITLE);
		this.setPreferredSize(new Dimension(ChatContext.UI_JOIN_WIDTH, ChatContext.UI_JOIN_HEIGHT));
		
		
		
		JPanel allPanel = new JPanel();
		allPanel.setLayout(null);
		allPanel.setBackground(Color.decode(ChatContext.UI_BACKGROUND));
		
		JLabel lblJoin = new JLabel("Cheers!!With us!!");
		lblJoin.setFont(new Font("바탕", Font.BOLD, 35));
		lblJoin.setBounds(32, 24, 339, 65);

		JLabel logoContainer = new JLabel("");
		ImageIcon logo = new ImageIcon(ChatContext.RES_IMG_JOIN);
		logoContainer.setIcon(logo);
		
		JPanel center = new JPanel();
		center.setBackground(Color.decode(ChatContext.UI_BACKGROUND));
		center.setBounds(32, 91, 339, 281);
		center.add(logoContainer);
		

		pirateText = new JTextField(13);
		pirateText.setForeground(Color.DARK_GRAY);
		pirateText.setToolTipText(" pirate ");
		pirateText.setHorizontalAlignment(SwingConstants.CENTER);
		pirateText.setFont(new Font("바탕", Font.BOLD, 21));
		Border border1 = BorderFactory.createEmptyBorder();
		pirateText.setBorder(border1);
		pirateText.addKeyListener(this);
		
		
		idText = new JTextField(13);
		idText.setForeground(Color.DARK_GRAY);
		idText.setToolTipText("nickname");
		idText.setHorizontalAlignment(SwingConstants.CENTER);
		idText.setFont(new Font("바탕", Font.BOLD, 21));
		Border border2 = BorderFactory.createEmptyBorder();
		idText.setBorder(border2);
		idText.addKeyListener(this);
		
		passwordText = new JPasswordField(13);
		passwordText.setForeground(Color.DARK_GRAY);
		passwordText.setToolTipText("password");
		passwordText.setHorizontalAlignment(SwingConstants.CENTER);
		passwordText.setFont(new Font("바탕", Font.BOLD, 21));
		Border border3 = BorderFactory.createEmptyBorder();
		passwordText.setBorder(border3);
		passwordText.addKeyListener(this);
		
		joinButton = new JButton("     Yeah, I will drunken !!     ");
		joinButton.setFont(new Font("바탕", Font.BOLD, 22));
		joinButton.addActionListener(this);
		joinButton.setForeground(Color.WHITE);
		joinButton.setBackground(Color.decode(ChatContext.UI_BUTTON));
		joinButton.setEnabled(false);
		
		JPanel centerBottom = new JPanel();
		centerBottom.setLayout(new FlowLayout());
		centerBottom.setBounds(32, 382, 339, 150);
		centerBottom.setBackground(Color.decode(ChatContext.UI_BACKGROUND));

		
		JLabel pirateLabel = new JLabel("  Pirate  ");
		pirateLabel.setFont(new Font("바탕", Font.BOLD, 22));
		
		JPanel inputPanel1 = new JPanel();
		inputPanel1.setLayout(new BorderLayout());
		inputPanel1.add(pirateLabel,"West");
		inputPanel1.add(pirateText,"Center");
		inputPanel1.setBackground(Color.decode(ChatContext.UI_BACKGROUND));
		
		
		JLabel idLabel = new JLabel("    ID    ");
		idLabel.setFont(new Font("바탕", Font.BOLD, 22));
		
		JPanel inputPanel2 = new JPanel();
		inputPanel2.setLayout(new BorderLayout());
		inputPanel2.add(idLabel,"West");
		inputPanel2.add(idText,"Center");
		inputPanel2.setBackground(Color.decode(ChatContext.UI_BACKGROUND));
		
		JLabel passLabel = new JLabel("  PASS  ");
		passLabel.setFont(new Font("바탕", Font.BOLD, 22));
		
		JPanel inputPanel3 = new JPanel();
		inputPanel3.setLayout(new BorderLayout());
		inputPanel3.add(passLabel,"West");
		inputPanel3.add(passwordText,"Center");
		inputPanel3.setBackground(Color.decode(ChatContext.UI_BACKGROUND));
		
		
		centerBottom.add(inputPanel1);
		centerBottom.add(inputPanel2);
		centerBottom.add(inputPanel3);
		centerBottom.add(joinButton);
		
		
		
		allPanel.add(lblJoin);
		allPanel.add(center);
		allPanel.add(centerBottom);
		//allPanel.add(help);
		
		this.setVisible(true);
		getContentPane().add(allPanel);
		
		JLabel help = new JLabel("Government : dydwls121200@gmail.com");
		help.setBounds(40, 575, 331, 20);
		allPanel.add(help);
		help.setFont(new Font("바탕", Font.BOLD, 17));
		this.pack();
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		JButton btn = (JButton) arg0.getSource();
		System.out.println("dasaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		
		if(btn == joinButton){
			JSONObject packet = new JSONObject();
			JSONObject data = new JSONObject();
			data.put("ID", idText.getText().toString());
			data.put("PASSWORD",String.valueOf(passwordText.getPassword()));
			data.put("PIRATE", pirateText.getText().toString());
			packet.put("FUNC", ChatContext.FUNC_JOIN);
			packet.put("DATA", data);
			System.out.println(packet);
			ChatContext.println("ChatJoin.class","[[[[[[[[["+String.valueOf(passwordText.getPassword())+"]]]]]]]]]]");
			thread.send(packet,this);
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(idText.getText().toString().length()>30||idText.getText().toString().length()<8){
			joinButton.setEnabled(false);
		}else{
			if(passwordText.getPassword().length>30||passwordText.getPassword().length<8){
				joinButton.setEnabled(false);
			}else{
				if(pirateText.getText().toString().length()>30||pirateText.getText().toString().length()<2){
					joinButton.setEnabled(false);
				}else{
					joinButton.setEnabled(true);
					if(arg0.getKeyCode()==KeyEvent.VK_ENTER){
						JSONObject packet = new JSONObject();
						JSONObject data = new JSONObject();
						data.put("ID", idText.getText().toString());
						data.put("PASSWORD",String.valueOf(passwordText.getPassword()));
						data.put("PIRATE", pirateText.getText().toString());
						packet.put("FUNC", ChatContext.FUNC_JOIN);
						packet.put("DATA", data);
						thread.send(packet,this);
					}
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {}
	

	@Override
	public void keyPressed(KeyEvent arg0) {}

}
