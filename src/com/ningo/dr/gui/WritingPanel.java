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

public class WritingPanel extends JPanel{
	private BPNetwork bpn;
	private final int basicLenth = 20;
	private final int root_X = 25;
	private final int root_Y = 50;
	
	private int drawX = -1;
	private int drawY = -1;
	
	private final int pixelsInRow = 25;
	private final int pixelsInColumn = 25;
	
	private WritingPanel mPanel;
	private MouseMotionAdapter motionAdapter;
	private float[] positionFilledArrayWholeMap;
	private ArrayList<int[]> pixelFilledList; 
	
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
	public WritingPanel()
	{
		bpn = new BPNetwork();
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
				 
				 
				 
				 else if(x > 25&& x < 75&& y > 600&&y < 650)//training "0"
				 {
					 
					 if(pixelFilledList.size() == 0)
						 return;
					 
					 for(int i = 0;i < pixelFilledList.size();i ++)
						 positionFilledArrayWholeMap[pixelFilledList.get(i)[0]*pixelsInRow + pixelFilledList.get(i)[1]] = 1.0f;
					 
					 
					float[] targetValue = new float[10];
					 targetValue[0] = 1.0f;
					 targetValue[1] = 0.0f;
					 targetValue[2] = 0.0f;
					 targetValue[3] = 0.0f;
					 targetValue[4] = 0.0f;
					 targetValue[5] = 0.0f;
					 targetValue[6] = 0.0f;
					 targetValue[7] = 0.0f;
					 targetValue[8] = 0.0f;
					 targetValue[9] = 0.0f;
					 
					 bpn.singleTraining(positionFilledArrayWholeMap, targetValue);
					 
					 pixelFilledList = new ArrayList<int[]>();
					 positionFilledArrayWholeMap = new float[pixelsInRow*pixelsInColumn];
					 repaint();
				 }
				 else if(x > 100&&x < 150&&y > 600&&y < 650)//training "1"
				 {
					 if(pixelFilledList.size() == 0)
						 return;
					 for(int i = 0;i < pixelFilledList.size();i ++)
						 positionFilledArrayWholeMap[pixelFilledList.get(i)[0]*pixelsInRow + pixelFilledList.get(i)[1]] = 1.0f;
					 
					 
					 float[] targetValue = new float[10];
					 targetValue[0] = 0.0f;
					 targetValue[1] = 1.0f;
					 targetValue[2] = 0.0f;
					 targetValue[3] = 0.0f;
					 targetValue[4] = 0.0f;
					 targetValue[5] = 0.0f;
					 targetValue[6] = 0.0f;
					 targetValue[7] = 0.0f;
					 targetValue[8] = 0.0f;
					 targetValue[9] = 0.0f;
					 
					 bpn.singleTraining(positionFilledArrayWholeMap, targetValue);
					 
					 pixelFilledList = new ArrayList<int[]>();
					 positionFilledArrayWholeMap = new float[pixelsInRow*pixelsInColumn];
					 repaint();
				 }
				 else if(x > 175&&x < 225&&y > 600&&y < 650)//training "2"
				 {
					 
					 if(pixelFilledList.size() == 0)
						 return;
					 for(int i = 0;i < pixelFilledList.size();i ++)
						 positionFilledArrayWholeMap[pixelFilledList.get(i)[0]*pixelsInRow + pixelFilledList.get(i)[1]] = 1.0f;
					 
					 
					 float[] targetValue = new float[10];
					 targetValue[0] = 0.0f;
					 targetValue[1] = 0.0f;
					 targetValue[2] = 1.0f;
					 targetValue[3] = 0.0f;
					 targetValue[4] = 0.0f;
					 targetValue[5] = 0.0f;
					 targetValue[6] = 0.0f;
					 targetValue[7] = 0.0f;
					 targetValue[8] = 0.0f;
					 targetValue[9] = 0.0f;
					 
					 bpn.singleTraining(positionFilledArrayWholeMap, targetValue);
					 
					 
					 pixelFilledList = new ArrayList<int[]>();
					 positionFilledArrayWholeMap = new float[pixelsInRow*pixelsInColumn];
					 repaint();
				 }
				 else if(x > 250&&x < 300&&y > 600&&y < 650)//training "3"
				 {
					 if(pixelFilledList.size() == 0)
						 return;
					 for(int i = 0;i < pixelFilledList.size();i ++)
						 positionFilledArrayWholeMap[pixelFilledList.get(i)[0]*pixelsInRow + pixelFilledList.get(i)[1]] = 1.0f;
					 
					 
					 float[] targetValue = new float[10];
					 targetValue[0] = 0.0f;
					 targetValue[1] = 0.0f;
					 targetValue[2] = 0.0f;
					 targetValue[3] = 1.0f;
					 targetValue[4] = 0.0f;
					 targetValue[5] = 0.0f;
					 targetValue[6] = 0.0f;
					 targetValue[7] = 0.0f;
					 targetValue[8] = 0.0f;
					 targetValue[9] = 0.0f;
					 
					 bpn.singleTraining(positionFilledArrayWholeMap, targetValue);
					 
					 
					 pixelFilledList = new ArrayList<int[]>();
					 positionFilledArrayWholeMap = new float[pixelsInRow*pixelsInColumn];
					 repaint();
				 }
				 else if(x > 325&&x < 375&&y > 600&&y < 650)//training "4"
				 {
					 if(pixelFilledList.size() == 0)
						 return;
					 for(int i = 0;i < pixelFilledList.size();i ++)
						 positionFilledArrayWholeMap[pixelFilledList.get(i)[0]*pixelsInRow + pixelFilledList.get(i)[1]] = 1.0f;
					 
					 
					 float[] targetValue = new float[10];
					 targetValue[0] = 0.0f;
					 targetValue[1] = 0.0f;
					 targetValue[2] = 0.0f;
					 targetValue[3] = 0.0f;
					 targetValue[4] = 1.0f;
					 targetValue[5] = 0.0f;
					 targetValue[6] = 0.0f;
					 targetValue[7] = 0.0f;
					 targetValue[8] = 0.0f;
					 targetValue[9] = 0.0f;
					 
					 bpn.singleTraining(positionFilledArrayWholeMap, targetValue);
					 
					 pixelFilledList = new ArrayList<int[]>();
					 positionFilledArrayWholeMap = new float[pixelsInRow*pixelsInColumn];
					 repaint();
				 }
				 else if(x > 25&& x < 75&& y > 675&&y < 725)//training "5"
				 {
					 if(pixelFilledList.size() == 0)
						 return;
					 for(int i = 0;i < pixelFilledList.size();i ++)
						 positionFilledArrayWholeMap[pixelFilledList.get(i)[0]*pixelsInRow + pixelFilledList.get(i)[1]] = 1.0f;
					 
					 
					 float[] targetValue = new float[10];
					 targetValue[0] = 0.0f;
					 targetValue[1] = 0.0f;
					 targetValue[2] = 0.0f;
					 targetValue[3] = 0.0f;
					 targetValue[4] = 0.0f;
					 targetValue[5] = 1.0f;
					 targetValue[6] = 0.0f;
					 targetValue[7] = 0.0f;
					 targetValue[8] = 0.0f;
					 targetValue[9] = 0.0f;
					 
					 bpn.singleTraining(positionFilledArrayWholeMap, targetValue);
					 
					 pixelFilledList = new ArrayList<int[]>();
					 positionFilledArrayWholeMap = new float[pixelsInRow*pixelsInColumn];
					 repaint();
				 }
				 else if(x > 100&&x < 150&&y > 675&&y < 725)//training "6"
				 {
					 if(pixelFilledList.size() == 0)
						 return;
					 for(int i = 0;i < pixelFilledList.size();i ++)
						 positionFilledArrayWholeMap[pixelFilledList.get(i)[0]*pixelsInRow + pixelFilledList.get(i)[1]] = 1.0f;
					 
					 
					 float[] targetValue = new float[10];
					 targetValue[0] = 0.0f;
					 targetValue[1] = 0.0f;
					 targetValue[2] = 0.0f;
					 targetValue[3] = 0.0f;
					 targetValue[4] = 0.0f;
					 targetValue[5] = 0.0f;
					 targetValue[6] = 1.0f;
					 targetValue[7] = 0.0f;
					 targetValue[8] = 0.0f;
					 targetValue[9] = 0.0f;
					 
					 bpn.singleTraining(positionFilledArrayWholeMap, targetValue);
					 
					 
					 pixelFilledList = new ArrayList<int[]>();
					 positionFilledArrayWholeMap = new float[pixelsInRow*pixelsInColumn];
					 repaint();
				 }
				 else if(x > 175&&x < 225&&y > 675&&y < 725)//training "7"
				 {
					 if(pixelFilledList.size() == 0)
						 return;
					 for(int i = 0;i < pixelFilledList.size();i ++)
						 positionFilledArrayWholeMap[pixelFilledList.get(i)[0]*pixelsInRow + pixelFilledList.get(i)[1]] = 1.0f;
					 
					 
					 float[] targetValue = new float[10];
					 targetValue[0] = 0.0f;
					 targetValue[1] = 0.0f;
					 targetValue[2] = 0.0f;
					 targetValue[3] = 0.0f;
					 targetValue[4] = 0.0f;
					 targetValue[5] = 0.0f;
					 targetValue[6] = 0.0f;
					 targetValue[7] = 1.0f;
					 targetValue[8] = 0.0f;
					 targetValue[9] = 0.0f;
					 
					 bpn.singleTraining(positionFilledArrayWholeMap, targetValue);
					 
					 
					 pixelFilledList = new ArrayList<int[]>();
					 positionFilledArrayWholeMap = new float[pixelsInRow*pixelsInColumn];
					 repaint();
				 }
				 else if(x > 250&&x < 300&&y > 675&&y < 725)//training "8"
				 {
					 if(pixelFilledList.size() == 0)
						 return;
					 for(int i = 0;i < pixelFilledList.size();i ++)
						 positionFilledArrayWholeMap[pixelFilledList.get(i)[0]*pixelsInRow + pixelFilledList.get(i)[1]] = 1.0f;
					 
					 
					 float[] targetValue = new float[10];
					 targetValue[0] = 0.0f;
					 targetValue[1] = 0.0f;
					 targetValue[2] = 0.0f;
					 targetValue[3] = 0.0f;
					 targetValue[4] = 0.0f;
					 targetValue[5] = 0.0f;
					 targetValue[6] = 0.0f;
					 targetValue[7] = 0.0f;
					 targetValue[8] = 1.0f;
					 targetValue[9] = 0.0f;
					 
					 bpn.singleTraining(positionFilledArrayWholeMap, targetValue);
					 
					 pixelFilledList = new ArrayList<int[]>();
					 positionFilledArrayWholeMap = new float[pixelsInRow*pixelsInColumn];
					 repaint();
				 }
				 else if(x > 325&&x < 375&&y > 675&&y < 725)//training "9"
				 {
					 if(pixelFilledList.size() == 0)
						 return;
					 for(int i = 0;i < pixelFilledList.size();i ++)
						 positionFilledArrayWholeMap[pixelFilledList.get(i)[0]*pixelsInRow + pixelFilledList.get(i)[1]] = 1.0f;
					 
					 
					 float[] targetValue = new float[10];
					 targetValue[0] = 0.0f;
					 targetValue[1] = 0.0f;
					 targetValue[2] = 0.0f;
					 targetValue[3] = 0.0f;
					 targetValue[4] = 0.0f;
					 targetValue[5] = 0.0f;
					 targetValue[6] = 0.0f;
					 targetValue[7] = 0.0f;
					 targetValue[8] = 0.0f;
					 targetValue[9] = 1.0f;
					 
					 bpn.singleTraining(positionFilledArrayWholeMap, targetValue);
					 
					 
					 pixelFilledList = new ArrayList<int[]>();
					 positionFilledArrayWholeMap = new float[pixelsInRow*pixelsInColumn];
					 repaint();
				 }
				 
				 
				 else if(x > 650&&x < 750&&y > 400&&y < 500)//skip to recognize
				 {
					 JFrame frame = new JFrame();
						frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						
						RecognizingPanel panel = new RecognizingPanel(bpn);
						
					frame.getContentPane().add(BorderLayout.CENTER,panel);
						
						
						
						frame.setSize(800,800);
						frame.setVisible(true);
				 }
				 
			 }
			
		});
	}
	
	
	@Override
	public void paint(Graphics g)
	{
		
		super.paint(g);
		g.drawRect(25, 50, 500, 500);
		
		
		g.drawRect(25, 600, 50, 50);
		g.drawRect(100, 600, 50, 50);
		g.drawRect(175, 600, 50, 50);
		g.drawRect(250, 600, 50, 50);
		g.drawRect(325, 600, 50, 50);
		
		
		g.drawRect(25, 675, 50, 50);
		g.drawRect(100, 675, 50, 50);
		g.drawRect(175, 675, 50, 50);
		g.drawRect(250, 675, 50, 50);
		g.drawRect(325, 675, 50, 50);
		
		
		g.setFont(new Font("ningo",Font.BOLD,50));
		g.drawString("0",37, 645);
		g.drawString("1",112, 645);
		g.drawString("2",187, 645);
		g.drawString("3",262, 645);
		g.drawString("4",337, 645);
		
		
		g.drawString("5",37, 720);
		g.drawString("6",112, 720);
		g.drawString("7",187, 720);
		g.drawString("8",262, 720);
		g.drawString("9",337, 720);
		
		
		g.drawRect(450, 675, 100, 50);
		g.setFont(new Font("ningo",Font.BOLD,25));
		g.drawString("CLEAR",458, 710);
		
		
		g.drawString("END",680, 470);
		
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
