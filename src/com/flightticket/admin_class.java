package com.flightticket;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import com.sun.scenario.effect.DropShadow;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class admin_class {

	public static JFrame frame;
	private final JButton btnNewButton = new JButton("确定");
	public static String LoginUser="admin";
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					admin_class window = new admin_class();
					admin_class.frame.setVisible(true);
					admin_class.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public admin_class() {
		initialize();
		admin_class.frame.setVisible(true);
		admin_class.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void initialize() {
		frame = new JFrame("航空售票管理系统");
		frame.getContentPane().setBackground(Color.GREEN);
		frame.setBounds(100, 100, 699, 456);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel lblNewJgoodiesTitle = new JLabel("航空售票管理系统");
		lblNewJgoodiesTitle.setBounds(10, 0, 663, 60);
		lblNewJgoodiesTitle.setFont(new Font("FZFangSong-Z02S", Font.PLAIN, 20));
		lblNewJgoodiesTitle.setHorizontalAlignment(SwingConstants.CENTER);
		Border border=BorderFactory.createLineBorder(Color.BLACK,5);
		lblNewJgoodiesTitle.setBorder(border);
		Color label_color=new Color(255,102,255);
		frame.getContentPane().setLayout(null);
		lblNewJgoodiesTitle.setBackground(Color.yellow);
		lblNewJgoodiesTitle.setForeground(label_color);
		frame.getContentPane().add(lblNewJgoodiesTitle);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("前往个人中心");
		rdbtnNewRadioButton.setSelected(true);
		rdbtnNewRadioButton.setFont(new Font("FZFangSong-Z02S", Font.PLAIN, 14));
		rdbtnNewRadioButton.setBounds(273, 104, 129, 54);
		Border border_1=BorderFactory.createLineBorder(Color.BLACK,5);
		rdbtnNewRadioButton.setBorder(border_1);
		
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		rdbtnNewRadioButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
			}
		});
				
		rdbtnNewRadioButton.setBackground(Color.GREEN);
		rdbtnNewRadioButton.setForeground(label_color);
		frame.getContentPane().add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnBookTicket = new JRadioButton("添加航班信息");
		rdbtnBookTicket.setFont(new Font("FZFangSong-Z02S", Font.PLAIN, 14));
		rdbtnBookTicket.setBounds(273, 228, 129, 43);
		rdbtnBookTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
						
			}
		});
		rdbtnBookTicket.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				
			}
		});
		rdbtnBookTicket.setBackground(Color.GREEN);
		rdbtnBookTicket.setForeground(label_color);
		frame.getContentPane().add(rdbtnBookTicket);
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnBookTicket);
		group.add(rdbtnNewRadioButton);
		
		JRadioButton rdnair_queries=new JRadioButton("查看航班信息");
		rdnair_queries.setFont(new Font("FZFangSong-Z02S", Font.PLAIN, 14));
		rdnair_queries.setBounds(273, 165, 129, 60);
		rdnair_queries.setBackground(Color.GREEN);
		rdnair_queries.setForeground(label_color);
		frame.getContentPane().add(rdnair_queries);
		group.add(rdnair_queries);
		btnNewButton.setFont(new Font("FZFangSong-Z02S", Font.PLAIN, 14));
		btnNewButton.setBounds(300, 313, 75, 43);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rdbtnBookTicket.isSelected()){
					addFlight.main(null);
				}
				else if(rdbtnNewRadioButton.isSelected()){
					PersonalCenter_admin.main(null);
				}
				else if(rdnair_queries.isSelected()){
					aircraft_queries.main(null);
				}
				
			}
		});
		
		frame.getContentPane().add(btnNewButton);
		
		JLabel label = new JLabel("您好，"+LoginUser+"！");
		label.setFont(new Font("FZFangSong-Z02S", Font.PLAIN, 14));
		label.setBounds(557, 71, 116, 14);
		frame.getContentPane().add(label);
		
	}
}
