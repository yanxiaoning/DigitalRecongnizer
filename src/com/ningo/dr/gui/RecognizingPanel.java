package com.ningo.dr.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.ningo.dr.BPNetwork;

public class RecognizingPanel extends JPanel{
	private BPNetwork bpn;
	private final int basicLenth = 20;
	private final int root_X = 25;
	private final int root_Y = 50;
	
	private int drawX = -1;
	private int drawY = -1;
	
	private final int pixelsInRow = 25;
	private final int pixelsInColumn = 25;
	
	private RecognizingPanel mPanel;
	private MouseMotionAdapter motionAdapter;
	private float[] positionFilledArrayWholeMap;
	private ArrayList<int[]> pixelFilledList; 
	RecognizingPanel rp;
	
	/*private class Pos
	{
		public int x;
		public int y;
		
		public Pos(int x,int y)
		{
			this.x = x;
			this.y = y;
		}
	}
	*/
	public RecognizingPanel(BPNetwork bpn)
	{
		this.bpn = bpn;
		rp = this;
		pixelFilledList = new ArrayList<int[]>();
		positionFilledArrayWholeMap = new float[pixelsInRow*pixelsInColumn];
		
		
		for(int i = 0;i < pixelsInRow*pixelsInColumn;i ++)
		{
			
			
			positionFilledArrayWholeMap[i] = 0.0f;
			
		}
		
		
		mPanel = this;
		motionAdapter = new MouseMotionAdapter(){
			 public void mouseDragged(MouseEvent e)
			 {
				 int x = e.getX();
				 int y = e.getY();
				 
				 
				 for(int i = 0;i < pixelsInRow;i++)
					 for(int j = 0;j < pixelsInColumn;j++)
					 {
						 if(x > i*basicLenth + root_X&&y > j*basicLenth + root_Y&&x < (i+1)*basicLenth + root_X&&y < (j+1)*basicLenth + root_Y)
						 {
							 drawX = i*basicLenth + root_X;
							 drawY = j*basicLenth + root_Y;
							 
							 int[] element = {i,j};
							 
							 boolean canAdd = true;
							for(int k = 0;k < pixelFilledList.size();k++)
							{
								if(!isArrayEqual(pixelFilledList.get(k),element))
								{
							
								}
								else
								{
									canAdd = false;
									break;
								}
								
								
							}
							if(canAdd)
							{
							pixelFilledList.add(element);
							
							}
							 
							 repaint();
						 }
							 
					 }
			 }
		 };
		
		this.addMouseListener(new MouseAdapter(){
			 public void mousePressed(MouseEvent e)
			 {
				// System.out.println(e.getX()+"    "+e.getY());
				 mPanel.addMouseMotionListener(motionAdapter);
			 }
			 
			 
			 public void mouseReleased(MouseEvent e)
			 {
				 mPanel.removeMouseMotionListener(motionAdapter);
			 }
			 
			 
			 public void mouseClicked(MouseEvent e)
			 {
				 
				 int x = e.getX();
				 int y = e.getY();
				 
				 if(x < 550&&x > 450&&y < 725&&y > 675)
				 {
					 drawX = -1;
					 drawY = -1;
					 
					 
					 pixelFilledList = new ArrayList<int[]>();
					 System.out.println("flush");
					 repaint();
				 }
				 else if(x > 650&&x < 750&&y > 400&&y < 500)//skip to recognize
				 {
					 rp.bpn.singleRecognize(positionFilledArrayWholeMap);
				 }
				 
				 
				
				 
			 }
			
		});
	}
	
	
	@Override
	public void paint(Graphics g)
	{
		
		super.paint(g);
		g.drawRect(25, 50, 500, 500);
		
		
		
		
		
		g.drawRect(450, 675, 100, 50);
		g.setFont(new Font("ningo",Font.BOLD,25));
		g.drawString("CLEAR",458, 710);
		g.drawString("REC",680, 470);
		g.drawRect(650, 400, 100, 100);
		
		for(int i = 0;i < pixelFilledList.size();i++)
		g.fillRect(pixelFilledList.get(i)[0]*basicLenth + root_X, pixelFilledList.get(i)[1]*basicLenth + root_Y, basicLenth, basicLenth);
	}
	
	private boolean isArrayEqual(int[] a,int[] b)
	{
		if(a[0] == b[0]&&a[1] == b[1])
			
			return true;
		else
			return false;
	}
	

}
