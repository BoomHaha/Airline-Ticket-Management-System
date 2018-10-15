package com.flightticket;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.Font;

public class PersonalCenter_admin {
	
	JTable table;
	JScrollPane pane;
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PersonalCenter_admin window = new PersonalCenter_admin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PersonalCenter_admin() {
		initialize();
		this.frame.setVisible(true);
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setFont(new Font("FZFangSong-Z02S", Font.PLAIN, 13));
		frame.setResizable(false);
		frame.setTitle("个人中心");
		Color my_color=new Color(102,255,178);
		frame.getContentPane().setBackground(my_color);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("用户名：");
		label.setFont(new Font("FZFangSong-Z02S", Font.PLAIN, 14));
		label.setBounds(47, 11, 97, 28);
		frame.getContentPane().add(label);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(179, 16, 135, 20);
		frame.getContentPane().add(textField);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(179, 52, 135, 20);
		frame.getContentPane().add(textField_1);
		
		JLabel label_1 = new JLabel("密码：");
		label_1.setFont(new Font("FZFangSong-Z02S", Font.PLAIN, 14));
		label_1.setBounds(47, 47, 97, 28);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("真实姓名：");
		label_2.setFont(new Font("FZFangSong-Z02S", Font.PLAIN, 14));
		label_2.setBounds(47, 86, 97, 28);
		frame.getContentPane().add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(179, 91, 135, 20);
		frame.getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(179, 130, 135, 20);
		frame.getContentPane().add(textField_3);
		
		JLabel label_3 = new JLabel("身份证号：");
		label_3.setFont(new Font("FZFangSong-Z02S", Font.PLAIN, 14));
		label_3.setBounds(47, 125, 97, 28);
		frame.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("电话号码：");
		label_4.setFont(new Font("FZFangSong-Z02S", Font.PLAIN, 14));
		label_4.setBounds(47, 164, 97, 28);
		frame.getContentPane().add(label_4);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(179, 169, 135, 20);
		frame.getContentPane().add(textField_4);
		
		JLabel label_5 = new JLabel("性别：");
		label_5.setFont(new Font("FZFangSong-Z02S", Font.PLAIN, 14));
		label_5.setBounds(47, 203, 97, 28);
		frame.getContentPane().add(label_5);
		
		JButton button = new JButton("编辑");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (button.getText().equals("编辑")) {
					button.setText("确定");
					textField.setEditable(true);
					textField_1.setEditable(true);
					textField_2.setEditable(true);
					textField_3.setEditable(true);
					textField_4.setEditable(true);
					textField_5.setEditable(true);
				}
				else {
					try {
						Class.forName("com.mysql.jdbc.Driver");
			            Connection con=DriverManager.getConnection("jdbc:mysql://"+Login.SQLAddress+":3306/airline",Login.SQLUser,Login.SQLPassword);
			            con.createStatement();
			            String sql = "update users set userName=?,password=?,realName=?,ID=?,phone=?,sex=? where userName=?";
			            PreparedStatement ps = con.prepareStatement(sql);
			            ps.setString(1, textField.getText());
			            ps.setString(2, textField_1.getText());
			            ps.setString(3, textField_2.getText());
			            ps.setString(4, textField_3.getText());
			            ps.setString(5, textField_4.getText());
			            ps.setString(6, textField_5.getText());
			            ps.setString(7, textField.getText());
			            int rs = ps.executeUpdate();
			            if (rs>0) JOptionPane.showMessageDialog(null, "记录更新成功");
			            else JOptionPane.showMessageDialog(null, "记录更新失败");
					}catch(Exception e1) {
						e1.printStackTrace();
					}
					button.setText("编辑");
					textField.setEditable(false);
					textField_1.setEditable(false);
					textField_2.setEditable(false);
					textField_3.setEditable(false);
					textField_4.setEditable(false);
					textField_5.setEditable(false);
				}
			}
		});
		button.setFont(new Font("FZFangSong-Z02S", Font.PLAIN, 14));
		button.setBounds(78, 257, 89, 23);
		frame.getContentPane().add(button);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setBounds(179, 208, 135, 20);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		JButton button_2 = new JButton("返回");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		button_2.setFont(new Font("FZFangSong-Z02S", Font.PLAIN, 14));
		button_2.setBounds(201, 257, 89, 23);
		frame.getContentPane().add(button_2);
		frame.setBounds(100, 100, 380, 334);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://"+Login.SQLAddress+":3306/airline",Login.SQLUser,Login.SQLPassword);
            con.createStatement();
            String sql = "select userName,password,realName,ID,phone,sex,money from users where userName=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, admin_class.LoginUser);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
            	textField.setText(rs.getString(1));
            	textField_1.setText(rs.getString(2));
            	textField_2.setText(rs.getString(3));
            	textField_3.setText(rs.getString(4));
            	textField_4.setText(rs.getString(5));
            	textField_5.setText(rs.getString(6));
            }
		}
		catch(Exception e1) {
			e1.printStackTrace();
		}
	}
}
