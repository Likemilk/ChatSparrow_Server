package client.chat.chatting;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import client.chat.context.ChatContext;
import client.chat.thread.ChatThread;

public class ChatChattingUI extends JFrame implements ActionListener,KeyListener{

	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 933837881579312087L;

	public JTextArea messageDialog = null;
	public JTextField messageField = null;
	public List userList = null;
	
	
	public ChatThread thread=null;
	public String chatName=null;
	
	//생성자
	public ChatChattingUI(ChatThread thread,String pirate) {
		super(pirate);
		
		this.setTitle(pirate);
		this.setPreferredSize(new Dimension(ChatContext.UI_CHAT_WIDTH, ChatContext.UI_CHAT_HEIGHT));
		this.addWindowListener(new WinEvent());
		
		this.chatName = pirate;
		this.thread = thread;
	
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		//왼쪽 패널
		JPanel leftPanel = new JPanel();
		leftPanel.setBounds(0, 0, 526, 560);
		
		JLabel dialogText = new JLabel("Dialog");
		dialogText.setFont(new Font("바탕", Font.BOLD, 26));
		
		
		messageDialog = new JTextArea(26,39);
		messageDialog.setFont(new Font("바탕", Font.BOLD, 14));
		messageDialog.setLineWrap(true);
		messageDialog.setEditable(false);
		messageDialog.setText("====================================\n"
				+"기능 0, 메세지를 입력하고 엔터를 누르면 메세지가 전송됩니다.\n"
				+"기능 1, 귓속말을 하고싶으면 \n /w [다른 사용자 id] [메세지] 를 입력해주세요\n"
				+"기능 2, 전체에게 메세지를 보내고 싶으면 \n /! [메세지] 를 입력해주세요\n"
				+"기능 3, 대화 할 때 마다 5원 ,\n\t 방 생성할 때  100원,\n\t 방 들어갈 때 50원 씩 오릅니다.\n"
				+"기능 4, 대화방 나갈 때  50원,\n\t 로그아웃 할 때 300원씩 차감 됩니다.\n"
				+"기능 5, 해적의 돈이 -2000원이 되면 살해당합니다. 주의 해주세요. \n"
				+"====================================\n");
		JScrollPane scroll = new JScrollPane(messageDialog);
		
		
		messageField = new JTextField(31);
		messageField.setFont(new Font("바탕", Font.BOLD, 22));
		messageField.addKeyListener(this);
		
		leftPanel.add(dialogText);
		leftPanel.add(scroll);
		leftPanel.add(messageField);
		
		
		//오른쪽 패널
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BorderLayout());
		rightPanel.setBounds(525, 0, 269, 560);
		
		JLabel userListText = new JLabel("Pirates!!");
		userListText.setFont(new Font("바탕", Font.BOLD, 30));
		
		userList = new List();
		userList.setFont(new Font("바탕", Font.BOLD, 26));
		userList.addMouseListener(new ItemMouseEvent());
		
		
		rightPanel.add(userListText,"North");
		rightPanel.add(userList,"Center");
		
		
		panel.add(rightPanel);
		panel.add(leftPanel);
		
		getContentPane().add(panel);
		this.pack();
		this.setVisible(true);
		
		
	}
	
	
	
	class WinEvent extends WindowAdapter{
		@Override
		public void windowClosing(WindowEvent arg0) {
			// TODO Auto-generated method stub
			thread.requestExitChat(chatName);
		}
	}
	class ItemMouseEvent extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			super.mouseClicked(arg0);
			System.out.println("ChatChattingUI 회원리스트를 클릭하였도다 !!!!![[   "+arg0.getClickCount());
		}
		
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode()==KeyEvent.VK_ENTER){
			thread.requestMessage(messageField.getText().toString(),chatName);
			messageField.setText("");
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
