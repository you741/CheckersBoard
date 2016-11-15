//tests checkboard
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class TestCheckers extends JFrame implements MouseListener{
	CheckersPanel cp;
	public static void delay(int d){
		try{
			Thread.sleep(d);
		} catch(InterruptedException e){
			System.out.println("RIP");
		}
	}
	
	public TestCheckers(){
		super("Checkers Game");
		setSize(400,400);
		cp = new CheckersPanel();
		add(cp);
		addMouseListener(this);
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);    
		setVisible(true); 
	}
	
	@Override
	public void mouseClicked(MouseEvent e){}
	
	@Override
	public void mousePressed(MouseEvent e){
		cp.setX((int)((e.getX()-5)/50));
		cp.setY((int)((e.getY()-27)/50));
		cp.repaint();
	}
	
	@Override
	public void mouseReleased(MouseEvent e){
		CheckersBoard cb = cp.getBoard();
		cb.move(cp.getX(),7-cp.getY(),(int)((e.getX()-5)/50),7-(int)((e.getY()-27)/50));
		cp.setBoard(cb);
		cp.setX(-1);
		cp.setY(-1);
		cp.repaint();
	}
	
	@Override
	public void mouseEntered(MouseEvent e){}
	
	@Override
	public void mouseExited(MouseEvent e){}
	
	public static void main(String[] args){
		TestCheckers frame = new TestCheckers();
		while(true){
			frame.repaint();
			delay(100);
		}
	}
}

		/*CheckersBoard cb = new CheckersBoard();
		cb.display();
		int[][] moves = 
		{{0,5,1,4},
		{1,4,2,3},
		{1,2,1,6},
		{2,1,4,3}};
		for(int i = 0;i < moves.length;i++){
			System.out.println(cb.move(moves[i][0],moves[i][1],moves[i][2],moves[i][3]));
			cb.display();
			System.out.printf("Red: %d, Black: %d\n",cb.count(2),cb.count(1));
		}*/