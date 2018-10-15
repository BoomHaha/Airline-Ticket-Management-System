package com.flightticket;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTextField;
import javax.swing.JButton;

public class Login {

	public JFrame frame;
	private JTextField User;
	private JTextField Passwd;
	private String user;
	private String password;

	public static String SQLAddress="192.168.1.110";
	public static String SQLUser="root";
	public static String SQLPassword="52a001";

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login() {
		initialize();
		this.frame.setVisible(true);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("登录界面");
		frame.setBounds(100, 100, 450, 283);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label_2 = new JLabel("用户名：");
		label_2.setFont(new Font("FZFangSong-Z02S", Font.PLAIN, 14));
		label_2.setBounds(51, 54, 104, 28);
		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("密码：");
		label_3.setFont(new Font("FZFangSong-Z02S", Font.PLAIN, 14));
		label_3.setBounds(51, 103, 104, 24);
		frame.getContentPane().add(label_3);
		
		User = new JTextField();
		User.setBounds(188, 59, 194, 20);
		frame.getContentPane().add(User);
		User.setColumns(10);
		
		Passwd = new JTextField();
		Passwd.setBounds(188, 106, 194, 20);
		frame.getContentPane().add(Passwd);
		Passwd.setColumns(10);
		
		JButton button = new JButton("确认");
		button.setFont(new Font("FZFangSong-Z02S", Font.PLAIN, 12));
		button.setBounds(51, 163, 89, 23);
		frame.getContentPane().add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				user=User.getText();
				password=Passwd.getText();
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:mysql://"+Login.SQLAddress+":3306/airline?autoReconnect=true&useSSL=false",Login.SQLUser,Login.SQLPassword);
					String sql_query="select * from users where userName=? and password=?";
					PreparedStatement ps=null;
					ps=conn.prepareStatement(sql_query);
					ps.setString(1,user);
					ps.setString(2,password);
					ResultSet rs=ps.executeQuery();
					if (rs.next()) {
                        if (rs.getString(7).equals("administor")) {
                        	JOptionPane.showMessageDialog(null,"用户为管理员。","登录成功！",JOptionPane.INFORMATION_MESSAGE);
                        	admin_class.main(null);
                        } else {
                        	JOptionPane.showMessageDialog(null,"用户为顾客。","登录成功！",JOptionPane.INFORMATION_MESSAGE);
                        	GUI_Class.LoginUser=user;
                        	GUI_Class myGUI=new GUI_Class();
                        }
                        frame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null,"用户名或密码不正确。","错误！",JOptionPane.ERROR_MESSAGE);
                    }
				}
				catch(Exception e) {
					JOptionPane.showMessageDialog(null,"数据库连接失败。","错误！",JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
			}
		});
		
		JButton button_1 = new JButton("取消");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		button_1.setFont(new Font("FZFangSong-Z02S", Font.PLAIN, 12));
		button_1.setBounds(293, 163, 89, 23);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("注册");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register myReg=new Register();
			}
		});
		button_2.setFont(new Font("FZFangSong-Z02S", Font.PLAIN, 12));
		button_2.setBounds(172, 163, 89, 23);
		frame.getContentPane().add(button_2);
	}
}
