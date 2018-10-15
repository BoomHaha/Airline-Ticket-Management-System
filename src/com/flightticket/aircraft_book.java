package com.flightticket;
import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;

public class aircraft_book {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	public static String aircraft_date;
	public static JButton button_1;
	private JTable table;
	private JScrollPane jsp;
	private Vector<String> rowData,cloumnName;
	private DefaultTableModel myTable;
	private JButton button_2;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					aircraft_book window = new aircraft_book();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public aircraft_book() {
		initialize();
		this.frame.setVisible(true);
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private void initialize() {
		frame = new JFrame();
		Color my_color=new Color(102,255,178);
		frame.setBounds(100, 100, 771, 524);
		frame.setTitle("航班信息界面");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(my_color);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("始发地：");
		label.setFont(new Font("FZFangSong-Z02S", Font.PLAIN, 14));
		label.setBounds(10, 19, 65, 14);
		frame.getContentPane().add(label);
		
		textField = new JTextField();
		textField.setBounds(85, 17, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("目的地：");
		label_1.setFont(new Font("FZFangSong-Z02S", Font.PLAIN, 14));
		label_1.setBounds(181, 19, 65, 14);
		frame.getContentPane().add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(256, 17, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton button = new JButton("搜索");
		button.setFont(new Font("FZFangSong-Z02S", Font.PLAIN, 14));
		button.setBounds(584, 15, 65, 23);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (button_1.getText().equals("选择日期")) {
					JOptionPane.showMessageDialog(null,"请选择一个日期。","错误！",JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:mysql://"+Login.SQLAddress+":3306/airline?autoReconnect=true&useSSL=false",Login.SQLUser,Login.SQLPassword);
					String sql_query="select * from airplane where start=? and end=? and startTime=?";
					PreparedStatement ps=conn.prepareStatement(sql_query);
					ps.setString(1,textField.getText());
					ps.setString(2,textField_1.getText());
					ps.setString(3,button_1.getText());
					ResultSet rs=ps.executeQuery();
					
					UpdateTable();
					while (rs.next()) {
                        	rowData=new Vector<String>();
                        	rowData.add(rs.getString(1));
                        	rowData.add(rs.getString(2));
                        	rowData.add(rs.getString(3));
                        	rowData.add(rs.getString(4));
                        	rowData.add(rs.getString(5));
                        	rowData.add(rs.getString(6));
                        	rowData.add(rs.getString(7));
                        	myTable.addRow(rowData);
                    }
					table.setModel(myTable);
                }
				catch(Exception e1) {
					JOptionPane.showMessageDialog(null,"数据库连接失败。","错误！",JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(button);
		
		JLabel label_2 = new JLabel("出发日期：");
		label_2.setFont(new Font("FZFangSong-Z02S", Font.PLAIN, 14));
		label_2.setBounds(352, 19, 77, 14);
		frame.getContentPane().add(label_2);
		
		button_1 = new JButton("选择日期");
		button_1.setFont(new Font("FZFangSong-Z02S", Font.PLAIN, 14));
		button_1.setBounds(439, 15, 135, 23);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DateChooser2.main(null);
			}
		});
		frame.getContentPane().add(button_1);

		UpdateTable();
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.setModel(myTable);
		table.setFont(new Font("FZFangSong-Z02S", Font.PLAIN, 13));
		table.setPreferredScrollableViewportSize(new Dimension(735, 426));
		table.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		table.setBounds(10, 478, 735, -430);
		jsp=new JScrollPane(table);
		jsp.setBounds(10, 48, 735, 426);
		frame.getContentPane().add(jsp);
		
		button_2 = new JButton("订购");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int sel = table.getSelectedRow();
				int rnd=(int)(Math.random()*100);
				String[] a = { (String) table.getValueAt(sel, 0),(String) table.getValueAt(sel, 1),(String) table.getValueAt(sel, 2),(String) table.getValueAt(sel, 3),
						(String) table.getValueAt(sel, 4),Integer.toString(rnd),(String) table.getValueAt(sel, 5),(String) table.getValueAt(sel, 6)};
				aircraft_book_que.main(a);
			}
		});
		button_2.setFont(new Font("FZFangSong-Z02S", Font.PLAIN, 14));
		button_2.setBounds(666, 15, 65, 23);
		frame.getContentPane().add(button_2);
	}
	private void UpdateTable() {
		
		rowData=new Vector<String>();
		cloumnName=new Vector<String>();
		cloumnName.add("航班ID");
		cloumnName.add("飞机ID");
		cloumnName.add("始发地");
		cloumnName.add("目的地");
		cloumnName.add("起飞日期");
		cloumnName.add("价格");
		cloumnName.add("座位等级");
		myTable=new DefaultTableModel(rowData,cloumnName);
	}
}
