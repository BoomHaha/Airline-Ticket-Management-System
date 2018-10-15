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

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;

public class history_transcations {

	private JFrame frame;
	private JTable table;
	private JScrollPane jsp;
	private Vector<String> rowData,cloumnName;
	private DefaultTableModel myTable;
	private JButton button_2;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					history_transcations window = new history_transcations();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public history_transcations() {
		initialize();
		this.frame.setVisible(true);
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setFont(new Font("FZFangSong-Z02S", Font.PLAIN, 13));
		Color my_color=new Color(102,255,178);
		frame.setBounds(100, 100, 771, 524);
		frame.setTitle("我的历史订单");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setBackground(my_color);
		frame.getContentPane().setLayout(null);
		
		JButton button = new JButton("退票");
		button.setFont(new Font("FZFangSong-Z02S", Font.PLAIN, 14));
		button.setBounds(640, 15, 89, 23);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int sel = table.getSelectedRow();
				if (sel==-1) {
					JOptionPane.showMessageDialog(null,"请选择一个订单。","错误！",JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					String ticketID = (String) table.getValueAt(sel, 0);
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:mysql://"+Login.SQLAddress+":3306/airline?autoReconnect=true&useSSL=false",Login.SQLUser,Login.SQLPassword);
					String sql_delete="delete from tickets where ticketID=?";
					PreparedStatement ps=conn.prepareStatement(sql_delete);
					ps.setString(1,ticketID);
					int rs=ps.executeUpdate();
					if (rs>0) UpdateTable();
					else JOptionPane.showMessageDialog(null,"数据库连接失败。","错误！",JOptionPane.ERROR_MESSAGE);
                }
				catch(Exception e1) {
					JOptionPane.showMessageDialog(null,"数据库连接失败。","错误！",JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(button);
		
		table = new JTable();
		ClearTable();
		UpdateTable();
		
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.setFont(new Font("FZFangSong-Z02S", Font.PLAIN, 13));
		table.setPreferredScrollableViewportSize(new Dimension(735, 426));
		table.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		table.setBounds(10, 478, 735, -430);
		jsp=new JScrollPane(table);
		jsp.setBounds(10, 48, 735, 426);
		frame.getContentPane().add(jsp);
		
		button_2 = new JButton("改签");
		button_2.setFont(new Font("FZFangSong-Z02S", Font.PLAIN, 14));
		button_2.setBounds(514, 16, 89, 23);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int sel = table.getSelectedRow();
				if (sel==-1) {
					JOptionPane.showMessageDialog(null,"请选择一个订单。","错误！",JOptionPane.ERROR_MESSAGE);
					return;
				}
				String ticketID = (String) table.getValueAt(sel, 0);
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:mysql://"+Login.SQLAddress+":3306/airline?autoReconnect=true&useSSL=false",Login.SQLUser,Login.SQLPassword);
					String sql_delete="delete from tickets where ticketID=?";
					PreparedStatement ps=conn.prepareStatement(sql_delete);
					ps.setString(1,ticketID);
					int rs = ps.executeUpdate();
					if (rs>0) changeTicket.main(null);
					else JOptionPane.showMessageDialog(null,"数据库连接失败。","错误！",JOptionPane.ERROR_MESSAGE);
					frame.dispose();
				}catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		frame.getContentPane().add(button_2);		
	}
	private void UpdateTable()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://"+Login.SQLAddress+":3306/airline?autoReconnect=true&useSSL=false",Login.SQLUser,Login.SQLPassword);
			String sql_query="select * from tickets where userName=?";
			PreparedStatement ps=conn.prepareStatement(sql_query);
			ps.setString(1,GUI_Class.LoginUser);
			ResultSet rs=ps.executeQuery();
			ClearTable();
			while (rs.next()) {
                	rowData=new Vector<String>();
                	rowData.add(rs.getString(1));
                	rowData.add(rs.getString(2));
                	rowData.add(rs.getString(3));
                	rowData.add(rs.getString(4));
                	rowData.add(rs.getString(5));
                	myTable.addRow(rowData);
            }
			table.setModel(myTable);
        }
		catch(Exception e1) {
			JOptionPane.showMessageDialog(null,"数据库连接失败。","错误！",JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
	}
	private void ClearTable() {
		rowData=new Vector<String>();
		cloumnName=new Vector<String>();
		cloumnName.add("订单号");
		cloumnName.add("用户名");
		cloumnName.add("航班ID");
		cloumnName.add("飞机ID");
		cloumnName.add("座位号");
		myTable=new DefaultTableModel(rowData,cloumnName);
	}
}
