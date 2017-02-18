package client.chat.login;

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
import client.chat.alert.ChatAlertUI;
import client.chat.context.ChatContext;
import client.chat.join.ChatJoinUI;
import client.chat.thread.ChatThread;

public class ChatLoginUI  extends JFrame implements ActionListener, KeyListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7851970002159099236L;

	//private ChatJoinUI joinUi = null;
	
	private JButton loginButton = null;
	private JButton joinButton = null;
	public JTextField idText = null;
	public JPasswordField passwordText = null;
	public ChatThread thread=null;

	String nickname;
	
	
	public ChatLoginUI(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(ChatContext.TITLE);
		this.thread=new ChatThread(this);
		thread.start();
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.decode(ChatContext.UI_BACKGROUND));
		panel.setPreferredSize(new Dimension(ChatContext.UI_LOGIN_WIDTH,ChatContext.UI_LOGIN_HEIGHT));
		panel.setLayout(null);

		JLabel lblChatsparrow = new JLabel("ChatSparrow!!");
		lblChatsparrow.setFont(new Font("바탕", Font.BOLD, 44));
		lblChatsparrow.setBounds(32, 24, 339, 65);

		JLabel logoContainer = new JLabel("");
		ImageIcon logo = new ImageIcon(ChatContext.RES_IMG_LOGO);
		logoContainer.setIcon(logo);
		
		
		
		JPanel center = new JPanel();
		center.setBackground(Color.decode(ChatContext.UI_BACKGROUND));
		center.setBounds(32, 91, 339, 281);
		center.add(logoContainer);
		
		panel.add(center);
		
		
		center.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel centerBottom = new JPanel();
		centerBottom.setBounds(32, 382, 339, 224);
		centerBottom.setBackground(Color.decode(ChatContext.UI_BACKGROUND));
		centerBottom.setLayout(new FlowLayout());
		
		
		loginButton = new JButton("              Pirate!!              ");
		loginButton.setFont(new Font("바탕", Font.BOLD, 23));
		loginButton.addActionListener(this);
		loginButton.setForeground(Color.WHITE);
		loginButton.setBackground(Color.decode(ChatContext.UI_BUTTON));
		
		joinButton = new JButton("         Cheers!! With us!!         ");
		joinButton.setFont(new Font("바탕", Font.BOLD, 21));
		joinButton.addActionListener(this);
		joinButton.setForeground(Color.WHITE);
		joinButton.setBackground(Color.decode(ChatContext.UI_BUTTON));
		
		idText = new JTextField(17);
		idText.setForeground(Color.DARK_GRAY);
		idText.setToolTipText("nickname");
		idText.setHorizontalAlignment(SwingConstants.CENTER);
		idText.setFont(new Font("바탕", Font.BOLD, 24));
		Border border1 = BorderFactory.createEmptyBorder();
		idText.setBorder(border1);
		idText.addKeyListener(this);
		
		passwordText = new JPasswordField(17);
		passwordText.setForeground(Color.DARK_GRAY);
		passwordText.setToolTipText("password");
		passwordText.setHorizontalAlignment(SwingConstants.CENTER);
		passwordText.setFont(new Font("바탕", Font.BOLD, 24));
		Border border2 = BorderFactory.createEmptyBorder();
		passwordText.setBorder(border2);
		passwordText.addKeyListener(this);
		JLabel help = new JLabel("Government : dydwls121200@gmail.com");
		help.setFont(new Font("바탕", Font.BOLD, 20));
		help.setBounds(32, 550, 339, 65);
		
		
		
		
		centerBottom.add(idText);
		centerBottom.add(passwordText);
		centerBottom.add(loginButton);
		centerBottom.add(joinButton);
		
		
		
		
		panel.add(help);
		panel.add(lblChatsparrow);
		panel.add(centerBottom);
		panel.setVisible(true);
		
		
		
		this.setContentPane(panel);
		this.pack();//프레임의 사이즈를 삽입될 페널에딱 맞춘다.
		this.setVisible(true);

	}
	public static void main(String args[]){
		new ChatLoginUI();
	}
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		JButton button = (JButton) arg0.getSource();
		if(button==loginButton){
			if(idText.getText().toString().length()>30||idText.getText().toString().length()<8){
				ChatAlertUI alert =new ChatAlertUI(this,ChatContext.ALERT_ERR_TITLE,"아이디 , 비밀번호 8글자~30글자 내외로 입력하세요");
				alert.setVisible(true);
			}else{
				if(passwordText.getPassword().length>30||passwordText.getPassword().length<8){
					ChatAlertUI alert =new ChatAlertUI(this,ChatContext.ALERT_ERR_TITLE,"8글자~30글자 내외로 입력하세요");
					alert.setVisible(true);
				}else{
					//로그인 동작 실행
					JSONObject packet = new JSONObject();
					packet.put("FUNC",ChatContext.FUNC_LOGIN);
					JSONObject data = new JSONObject();
					data.put("ID", idText.getText().toString());
					ChatThread.id = idText.getText().toString();
					data.put("PASSWORD", String.valueOf(passwordText.getPassword()));
					packet.put("DATA", data);
					thread.send(packet);
				}
			}
		}else if(button==joinButton){
			//회원 가입창 발생.
			new ChatJoinUI(thread);
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
			if(idText.getText().toString().length()>30||idText.getText().toString().length()<8){
				ChatAlertUI alert =new ChatAlertUI(this,ChatContext.ALERT_ERR_TITLE,"아이디 , 비밀번호 8글자~30글자 내외로 입력하세요");
				alert.setVisible(true);
			}else{
				if(passwordText.getPassword().length>30||passwordText.getPassword().length<8){
					ChatAlertUI alert =new ChatAlertUI(this,ChatContext.ALERT_ERR_TITLE,"8글자~30글자 내외로 입력하세요");
					alert.setVisible(true);
				}else{
					//로그인 동작 실행
					JSONObject packet = new JSONObject();
					packet.put("FUNC",ChatContext.FUNC_LOGIN);
					JSONObject data = new JSONObject();
					data.put("ID", idText.getText().toString());
					data.put("PASSWORD", String.valueOf(passwordText.getPassword()));
					packet.put("DATA", data);
					thread.send(packet);
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}
	
	
}
