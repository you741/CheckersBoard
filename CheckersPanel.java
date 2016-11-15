//checkers panel
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

class CheckersPanel extends JPanel{
	private int x;
	private int y;
	private CheckersBoard cb;
	
	public CheckersPanel() {
		cb = new CheckersBoard();
		x = -1;
		y = -1;
		setPreferredSize(new Dimension(400,400));
		
	}
	public void setBoard(CheckersBoard cb){
		this.cb = cb;
	}
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public CheckersBoard getBoard(){
		return cb;
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//fills the board
		for(int i = 7;i >= 0;i--){
			for(int j = 0;j < 8;j++){
				if(i%2 == j%2)
					g.setColor(Color.GRAY);
				else{
					g.setColor(Color.WHITE);
				}
				g.fillRect(j*50,(7-i)*50,50,50);
			}
		}
		//fills selected square
		if(x != -1){
			g.setColor(new Color(230,220,100));
			g.fillRect(x*50,y*50,50,50);
		}
		//fills the pieces
		for(int i = 7;i >= 0;i--){
			for(int j = 0;j < 8;j++){
				int spot = cb.getSquare(j,i);
				if(spot == cb.RED){
					g.setColor(Color.RED);
				} else if(spot == cb.BLACK){
					g.setColor(Color.BLACK);
				} else{
					continue;
				}
				g.fillOval(j*50,(7-i)*50,50,50);
			}
		}
	}
}