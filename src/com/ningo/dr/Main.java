package com.ningo.dr;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import com.ningo.dr.gui.WritingPanel;

public class Main {
	
	
	
	
	public static void main(String[] args)
	{
		
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		WritingPanel panel = new WritingPanel();
		
	frame.getContentPane().add(BorderLayout.CENTER,panel);
		
		
		
		frame.setSize(800,800);
		frame.setVisible(true);
	}
                                                 
	

}
