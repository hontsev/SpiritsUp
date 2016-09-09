package spiritsup;

import java.awt.AWTException;
import java.awt.EventQueue;
import java.awt.Robot;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import javax.swing.JSeparator;
import javax.swing.JScrollPane;

public class Mainwindow {
	public Event e;
	private JFrame frmSpiritsUp;

	private JTextPane inputText ;
	private JTextPane outputText ;
	private JTextPane infoText;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mainwindow window = new Mainwindow();
					window.frmSpiritsUp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void println(String str)
	{
		if(str.length()>0)outputText.setText(outputText.getText()+str+"\n");
	}
	public void outputclean()
	{
		outputText.setText("");
	}
	public void infoclean()
	{
		infoText.setText("");
	}
	public void inputclean()
	{
		inputText.setText("");
		inputText.setCaretPosition(0);
	}
	public void printinfo(String str)
	{
		if(str.length()>0)
			//infoText.setText(str);
			infoText.setText(infoText.getText()+str+"\n");
	}
	/**
	 * Create the application.
	 */
	public Mainwindow() {
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		e= new Event();
		
		frmSpiritsUp = new JFrame();
		frmSpiritsUp.setTitle("\u60C5\u611F\u5206\u6790\u8F85\u5BFC\u52A9\u624B");
		frmSpiritsUp.setBounds(100, 100, 594, 519);
		frmSpiritsUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSpiritsUp.getContentPane().setLayout(null);
		
		outputText = new JTextPane();
		outputText.setEditable(false);
		outputText.setBounds(10, 10, 398, 285);
		frmSpiritsUp.getContentPane().add(outputText);
		JScrollPane scrollPane = new JScrollPane(outputText);
		scrollPane.setBounds(10, 10, 398, 285);
		frmSpiritsUp.getContentPane().add(scrollPane);
		
		inputText = new JTextPane();
		inputText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER)
				{
					//用户按回车输入语句的处理
					infoclean();
					String str = inputText.getText();
					if(str!=null && str.length()>=1)
					{
						println("【你】"+str);
						println("【小爱】"+e.inputWords(e.getWordlist(str)));
					}
					printinfo(e.showSituation());
					inputclean();
					// 下边这个为了使用户回车完毕后光标回到输入框开始而不是换行。
					try {
						Robot a = new Robot();
						a.keyPress(KeyEvent.VK_BACK_SPACE);
						a.keyRelease(KeyEvent.VK_BACK_SPACE);
					} catch (AWTException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		inputText.setBounds(10, 305, 398, 103);
		frmSpiritsUp.getContentPane().add(inputText);
		
		JButton button = new JButton("\u8F93\u5165");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent te) {
				//用户按按钮输入语句的处理
				infoclean();
				String str = inputText.getText();
				if(str!=null && str.length()>=1)
				{
					println("【你】"+str);
					println("【小爱】"+e.inputWords(e.getWordlist(str)));
				}
				printinfo(e.showSituation());
				inputclean();
				
			}
		});
		button.setBounds(315, 418, 93, 23);
		frmSpiritsUp.getContentPane().add(button);
		
		infoText = new JTextPane();
		infoText.setEditable(false);
		infoText.setBounds(418, 10, 150, 158);
		frmSpiritsUp.getContentPane().add(infoText);
		
		JScrollPane scrollPane2 = new JScrollPane(infoText);
		scrollPane2.setBounds(418, 10, 150, 158);
		frmSpiritsUp.getContentPane().add(scrollPane2);
		
		JMenuBar menuBar = new JMenuBar();
		frmSpiritsUp.setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("\u63A7\u5236");
		menuBar.add(menu);
		
		JMenuItem menuItem_2 = new JMenuItem("\u67E5\u770B\u5206\u6790\u7ED3\u679C");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//println("目前分析出的你的情绪："+e.showEmotion());
			}
		});
		menu.add(menuItem_2);
		
		JMenuItem menuItem_3 = new JMenuItem("\u6E05\u7A7A\u7ED3\u679C");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent te) {
				e.clear();
			}
		});
		menu.add(menuItem_3);
		
		JSeparator separator = new JSeparator();
		menu.add(separator);
		
		JMenuItem menuItem_5 = new JMenuItem("\u5BFC\u5165\u8BB0\u5F55");
		menu.add(menuItem_5);
		
		JMenuItem menuItem_4 = new JMenuItem("\u5BFC\u51FA\u8BB0\u5F55");
		menu.add(menuItem_4);
		
		JSeparator separator_1 = new JSeparator();
		menu.add(separator_1);
		
		JMenuItem menuItem_1 = new JMenuItem("\u9000\u51FA");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmSpiritsUp.dispatchEvent(new WindowEvent(frmSpiritsUp,WindowEvent.WINDOW_CLOSING) );
			}
		});
		menu.add(menuItem_1);
		
		JMenu menu_1 = new JMenu("\u5E2E\u52A9");
		menuBar.add(menu_1);
		
		JMenuItem menuItem = new JMenuItem("\u5173\u4E8E");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				println("——大学生心理辅导系统：v0.01——");
			}
		});
		menu_1.add(menuItem);
		//inputText.requestFocus(true);
		println("【小爱】嘿，你好啊！心情怎么样呢？有什么问题都可以跟我倾诉哦。");
	}
}
