package com.flightticket;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class Register {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register window = new Register();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Register() {
		initialize();
		this.frame.setVisible(true);
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("用户注册界面");
		frame.setBounds(100, 100, 385, 360);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("用户名：");
		label.setFont(new Font("FZFangSong-Z02S", Font.PLAIN, 14));
		label.setBounds(28, 26, 97, 28);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("密码：");
		label_1.setFont(new Font("FZFangSong-Z02S", Font.PLAIN, 14));
		label_1.setBounds(28, 62, 97, 28);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("真实姓名：");
		label_2.setFont(new Font("FZFangSong-Z02S", Font.PLAIN, 14));
		label_2.setBounds(28, 101, 97, 28);
		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("身份证号：");
		label_3.setFont(new Font("FZFangSong-Z02S", Font.PLAIN, 14));
		label_3.setBounds(28, 140, 97, 28);
		frame.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("电话号码：");
		label_4.setFont(new Font("FZFangSong-Z02S", Font.PLAIN, 14));
		label_4.setBounds(28, 179, 97, 28);
		frame.getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("性别：");
		label_5.setFont(new Font("FZFangSong-Z02S", Font.PLAIN, 14));
		label_5.setBounds(28, 218, 97, 28);
		frame.getContentPane().add(label_5);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("FZFangSong-Z02S", Font.PLAIN, 13));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"男", "女"}));
		comboBox.setBounds(160, 223, 135, 20);
		frame.getContentPane().add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(160, 31, 135, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(160, 67, 135, 20);
		frame.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(160, 106, 135, 20);
		frame.getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(160, 145, 135, 20);
		frame.getContentPane().add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(160, 184, 135, 20);
		frame.getContentPane().add(textField_4);
		
		JButton button = new JButton("确认");
		button.setFont(new Font("FZFangSong-Z02S", Font.PLAIN, 14));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
		            Connection con=DriverManager.getConnection("jdbc:mysql://"+Login.SQLAddress+":3306/airline",Login.SQLUser,Login.SQLPassword);
		            con.createStatement();
                    String Username = textField.getText();
					String Password = textField_1.getText();
					String Realname = textField_2.getText();
					String ID = textField_3.getText();
					String Phone = textField_4.getText();
					String Sex = (String)comboBox.getSelectedItem();
                    if (Password.equals("")||(Username.equals(""))) {
                        JOptionPane.showMessageDialog(null, "用户名和密码不能为空。", "错误", JOptionPane.ERROR_MESSAGE);
                        textField.setText("");
                        textField_1.setText("");
                        textField_2.setText("");
                        textField_3.setText("");
                        textField_4.setText("");
                        return;
                    }
                    String sql = "insert into users (userName,password,realName,ID,phone,sex,Type,money) values(?,?,?,?,?,?,'customer','0')";
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setString(1, Username);
                    ps.setString(2, Password);
                    ps.setString(3, Realname);
                    ps.setString(4, ID);
                    ps.setString(5, Phone);
                    ps.setString(6, Sex);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "注册成功", "成功", JOptionPane.INFORMATION_MESSAGE);
				}
				catch(Exception e1) {
					e1.printStackTrace();
				}
				finally {
					frame.dispose();
				}
			}
		});
		button.setBounds(70, 271, 89, 23);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("取消");
		button_1.setFont(new Font("FZFangSong-Z02S", Font.PLAIN, 14));
		button_1.setBounds(216, 272, 89, 23);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		frame.getContentPane().add(button_1);
	}
}
