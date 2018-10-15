package com.flightticket;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class aircraft_book_que {

	private JFrame frame;
	private JTextField flightID;
	private JTextField start;
	private JTextField startTime;
	private JTextField cost;
	private JTextField airplaneID;
	private JTextField end;
	private JTextField seatID;
	private JTextField level;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					aircraft_book_que window = new aircraft_book_que(args);
					window.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public aircraft_book_que(String[] args) {
		initialize(args);
	}

	private void initialize(String[] args) {
		frame = new JFrame();
		frame.setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
		frame.setResizable(false);
		frame.setTitle("支付确认界面");
		frame.setBounds(100, 100, 458, 305);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("始发地：");
		label.setFont(new Font("FZFangSong-Z02S", Font.PLAIN, 14));
		label.setBounds(44, 88, 65, 14);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("目的地：");
		label_1.setFont(new Font("FZFangSong-Z02S", Font.PLAIN, 14));
		label_1.setBounds(219, 88, 65, 14);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("航班号：");
		label_2.setFont(new Font("FZFangSong-Z02S", Font.PLAIN, 14));
		label_2.setBounds(44, 54, 65, 14);
		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("飞机号：");
		label_3.setFont(new Font("FZFangSong-Z02S", Font.PLAIN, 14));
		label_3.setBounds(219, 54, 65, 14);
		frame.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("起飞日期：");
		label_4.setFont(new Font("FZFangSong-Z02S", Font.PLAIN, 14));
		label_4.setBounds(33, 125, 76, 14);
		frame.getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("座位号：");
		label_5.setFont(new Font("FZFangSong-Z02S", Font.PLAIN, 14));
		label_5.setBounds(219, 125, 65, 14);
		frame.getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel("价格：");
		label_6.setFont(new Font("FZFangSong-Z02S", Font.PLAIN, 14));
		label_6.setBounds(44, 159, 65, 14);
		frame.getContentPane().add(label_6);
		
		JLabel label_7 = new JLabel("舱位等级：");
		label_7.setFont(new Font("FZFangSong-Z02S", Font.PLAIN, 14));
		label_7.setBounds(219, 159, 82, 14);
		frame.getContentPane().add(label_7);
		
		flightID = new JTextField();
		flightID.setEditable(false);
		flightID.setFont(new Font("Tahoma", Font.PLAIN, 11));
		flightID.setBounds(105, 52, 104, 20);
		flightID.setText(args[0]);
		frame.getContentPane().add(flightID);
		flightID.setColumns(10);
		
		start = new JTextField();
		start.setEditable(false);
		start.setColumns(10);
		start.setBounds(105, 86, 104, 20);
		start.setText(args[2]);
		frame.getContentPane().add(start);
		
		startTime = new JTextField();
		startTime.setEditable(false);
		startTime.setColumns(10);
		startTime.setBounds(105, 123, 104, 20);
		startTime.setText(args[4]);
		frame.getContentPane().add(startTime);
		
		cost = new JTextField();
		cost.setEditable(false);
		cost.setColumns(10);
		cost.setBounds(105, 157, 104, 20);
		cost.setText(args[6]);
		frame.getContentPane().add(cost);
		
		airplaneID = new JTextField();
		airplaneID.setEditable(false);
		airplaneID.setColumns(10);
		airplaneID.setBounds(292, 52, 104, 20);
		airplaneID.setText(args[1]);
		frame.getContentPane().add(airplaneID);
		
		end = new JTextField();
		end.setEditable(false);
		end.setColumns(10);
		end.setBounds(292, 86, 104, 20);
		end.setText(args[3]);
		frame.getContentPane().add(end);
		
		seatID = new JTextField();
		seatID.setEditable(false);
		seatID.setColumns(10);
		seatID.setBounds(292, 123, 104, 20);
		seatID.setText(args[5]);
		frame.getContentPane().add(seatID);
		
		level = new JTextField();
		level.setEditable(false);
		level.setColumns(10);
		level.setBounds(292, 157, 104, 20);
		level.setText(args[7]);
		frame.getContentPane().add(level);
		
		JLabel label_8 = new JLabel("请确认您的订单！");
		label_8.setFont(new Font("FZFangSong-Z02S", Font.PLAIN, 14));
		label_8.setBounds(170, 11, 134, 14);
		frame.getContentPane().add(label_8);
		
		JButton button = new JButton("确认付款");
		button.setFont(new Font("FZFangSong-Z02S", Font.PLAIN, 13));
		button.setBounds(105, 210, 89, 23);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:mysql://"+Login.SQLAddress+":3306/airline?autoReconnect=true&useSSL=false",Login.SQLUser,Login.SQLPassword);
					String sql_query="select money from users where userName=?";
					PreparedStatement ps=conn.prepareStatement(sql_query);
					ps.setString(1,GUI_Class.LoginUser);
					ResultSet rs=ps.executeQuery();
					if (rs.next()) {
                        	double currMoney = Double.parseDouble(rs.getString("money"));
                        	double thenMoney = currMoney - Double.parseDouble(cost.getText());
                        	if (thenMoney<0) {
                        		JOptionPane.showMessageDialog(null,"您的账户余额不足，请先充值。","错误！",JOptionPane.ERROR_MESSAGE);
                        		return;
                        	}
                        	else {
                        		try {
	            					String sql_insert="insert into tickets values(?,?,?,?,?)";
	            					PreparedStatement ps2=conn.prepareStatement(sql_insert);
	            					ps2.setString(1,Integer.toString((int)(Math.random()*1000)));
	            					ps2.setString(2, GUI_Class.LoginUser);
	            					ps2.setString(3, flightID.getText());
	            					ps2.setString(4, airplaneID.getText());
	            					ps2.setString(5, seatID.getText());
	            					int result = ps2.executeUpdate();
	            					if (result>0) {
	            						try {
	                    					String sql_update="update users set money=? where userName=?";
	                    					PreparedStatement ps3=conn.prepareStatement(sql_update);
	                    					ps3.setString(1,Double.toString(thenMoney));
	                    					ps3.setString(2, GUI_Class.LoginUser);
	                    					int result2 = ps3.executeUpdate();
	                    					if (result2>0) JOptionPane.showMessageDialog(null,"您的订单已记录到数据库中。","成功！",JOptionPane.INFORMATION_MESSAGE);
	            						}catch(Exception e2) {
	            							e2.printStackTrace();}
	            					}
	            					else JOptionPane.showMessageDialog(null,"插入失败。","错误！",JOptionPane.ERROR_MESSAGE);
	            					frame.dispose();
                        		} catch (Exception e3) {
                        			e3.printStackTrace();
                        		}
                        	}
                        }
                }
				catch(Exception e1) {
					JOptionPane.showMessageDialog(null,"数据库连接失败。","错误！",JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("取消");
		button_1.setFont(new Font("FZFangSong-Z02S", Font.PLAIN, 13));
		button_1.setBounds(271, 210, 89, 23);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		frame.getContentPane().add(button_1);
	}

}
