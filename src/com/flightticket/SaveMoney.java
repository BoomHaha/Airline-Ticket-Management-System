package com.flightticket;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class SaveMoney {

	private JFrame frame;
	private JTextField textField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SaveMoney window = new SaveMoney();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SaveMoney() {
		initialize();
		this.frame.setVisible(true);
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setFont(new Font("FZFangSong-Z02S", Font.PLAIN, 14));
		frame.setTitle("存款");
		frame.setBounds(100, 100, 421, 239);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("请输入金额：");
		label.setFont(new Font("FZFangSong-Z02S", Font.PLAIN, 14));
		label.setBounds(67, 53, 97, 28);
		frame.getContentPane().add(label);
		
		textField = new JTextField();
		textField.setFont(new Font("FZFangSong-Z02S", Font.PLAIN, 14));
		textField.setColumns(10);
		textField.setBounds(199, 58, 135, 20);
		frame.getContentPane().add(textField);
		
		JButton button = new JButton("确定");
		button.setFont(new Font("FZFangSong-Z02S", Font.PLAIN, 14));
		button.setBounds(51, 118, 89, 23);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (textField.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "请输入一个合法数字。");
						return;
					}
					Class.forName("com.mysql.jdbc.Driver");
		            Connection con=DriverManager.getConnection("jdbc:mysql://"+Login.SQLAddress+":3306/airline",Login.SQLUser,Login.SQLPassword);
		            con.createStatement();
		            String sql = "update users set money=? where userName=?";
		            PreparedStatement ps = con.prepareStatement(sql);
		            Double money=Double.parseDouble(textField.getText())+Double.parseDouble(PersonalCenter.textField_6.getText());
		            ps.setString(1, money.toString());
		            ps.setString(2, GUI_Class.LoginUser);
		            int rs = ps.executeUpdate();
		            if (rs>0) {
		            	JOptionPane.showMessageDialog(null, "记录更新成功");
		            	PersonalCenter.textField_6.setText(money.toString());
		            	frame.dispose();
		            }
		            else JOptionPane.showMessageDialog(null, "记录更新失败");
				}catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("取消");
		button_1.setFont(new Font("FZFangSong-Z02S", Font.PLAIN, 14));
		button_1.setBounds(268, 118, 89, 23);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		frame.getContentPane().add(button_1);
	}

}
