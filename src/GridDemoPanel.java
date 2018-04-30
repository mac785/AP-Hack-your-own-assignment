import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class GridDemoPanel extends JPanel implements MouseListener
{
	private Cell[][] theGrid;
	public final static int NUM_ROWS = 8;
	public final static int NUM_COLS = 8;
	private boolean click = false;
	private int[] mouseLoc;
	private int[] newMouseLoc;
	private String locSymbol = "";
		
	public GridDemoPanel()
	{
		super();
		theGrid = new Cell[NUM_ROWS][NUM_COLS];
		int color = (int)(Math.random()*(5-1)+1);
		int counter = 0;
		mouseLoc = new int[2];
		newMouseLoc = new int[2];
		for (int r =0; r<NUM_ROWS; r++)
			for (int c=0; c<NUM_COLS; c++){
				theGrid[r][c] = new Cell(r,c);
				if(r%2==0){
					if (counter ==0){
						theGrid[r][c].setColorID(color);
						color--;
						counter++;
					}
					else{
						theGrid[r][c].setColorID(color);
						color++;
						counter--;
					}
				}
				else{
					if (counter ==0){
						theGrid[r][c].setColorID(color-1);
						color--;
						counter++;
					}
					else{
						theGrid[r][c].setColorID(color+1);
						color++;
						counter--;
					}
				}
				if (r == 1){
					if (c%2!=0){
						theGrid[r][c].setDisplayMarker(true);
						theGrid[r][c].setMarker("w");
					}
				}
				if (r == 7){
					if (c%2!=0){
						theGrid[r][c].setDisplayMarker(true);
						theGrid[r][c].setMarker("b");
					}
				}
				else if (r==0){
					if(c%2==0){
						theGrid[r][c].setDisplayMarker(true);
						theGrid[r][c].setMarker("w");
					}
				}
				else if (r==6){
					if(c%2==0){
						theGrid[r][c].setDisplayMarker(true);
						theGrid[r][c].setMarker("b");
					}
				}
			}
		setBackground(Color.BLACK);
		addMouseListener(this);
		
	}
		
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		for (int r =0; r<NUM_ROWS; r++)
			for (int c=0; c<NUM_COLS; c++)
				theGrid[r][c].drawSelf(g);
	}
	
	
	
	//============================ Mouse Listener Overrides ==========================

	@Override
	// mouse was just released within about 1 pixel of where it was pressed.
	public void mouseClicked(MouseEvent e)
	{
		// TODO Auto-generated method stub
		// mouse location is at e.getX() , e.getY().
		// if you wish to convert to the rows and columns, you can integer-divide by the cell size.
		
				
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		// TODO Auto-generated method stub
		// mouse location is at e.getX() , e.getY().
		// if you wish to convert to the rows and columns, you can integer-divide by the cell size.
				
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		// TODO Auto-generated method stub
		// mouse location is at e.getX() , e.getY().
		// if you wish to convert to the rows and columns, you can integer-divide by the cell size.
		if (click == false){
			mouseLoc[0] = (int)(e.getX()/50);
			mouseLoc[1] = (int)(e.getY()/50);
			locSymbol = theGrid[mouseLoc[1]][mouseLoc[0]].getMarker();
			click = true;
			theGrid[mouseLoc[1]][mouseLoc[0]].colorSwap();
		}
		else if (click == true){
			newMouseLoc[0] = (int)(e.getX()/50);
			newMouseLoc[1] = (int)(e.getY()/50);
			String temp = theGrid[newMouseLoc[1]][newMouseLoc[0]].getMarker();
			//Move legality checker of non-kinged white pieces
			if (theGrid[mouseLoc[1]][mouseLoc[0]].getMarker().equals("w") && mouseLoc[1]<newMouseLoc[1]){
				if (temp == "" && Math.abs(mouseLoc[1]-newMouseLoc[1])==1 && Math.abs(mouseLoc[0]-newMouseLoc[0])==1){
					theGrid[newMouseLoc[1]][newMouseLoc[0]].setDisplayMarker(true);
					theGrid[newMouseLoc[1]][newMouseLoc[0]].setMarker(locSymbol);
					theGrid[mouseLoc[1]][mouseLoc[0]].setDisplayMarker(false);
					theGrid[mouseLoc[1]][mouseLoc[0]].setMarker("");
					if (theGrid[((newMouseLoc[1]-mouseLoc[1])/2)+mouseLoc[1]][((newMouseLoc[0]-mouseLoc[0])/2)+mouseLoc[0]].shouldDisplayMarker()==true){
						theGrid[((newMouseLoc[1]-mouseLoc[1])/2)+mouseLoc[1]][((newMouseLoc[0]-mouseLoc[0])/2)+mouseLoc[0]].setMarker("");
						theGrid[((newMouseLoc[1]-mouseLoc[1])/2)+mouseLoc[1]][((newMouseLoc[0]-mouseLoc[0])/2)+mouseLoc[0]].setDisplayMarker(false);
					}
				}
				else if (temp =="" && Math.abs(mouseLoc[1]-newMouseLoc[1])==2 && Math.abs(mouseLoc[0]-newMouseLoc[0])==2 && (theGrid[(mouseLoc[1]+newMouseLoc[1])/2][(mouseLoc[0]+newMouseLoc[0])/2].getMarker().equals("b")||theGrid[(mouseLoc[1]+newMouseLoc[1])/2][(mouseLoc[0]+newMouseLoc[0])/2].getMarker().equals("B"))){
					theGrid[(mouseLoc[1]+newMouseLoc[1])/2][(mouseLoc[0]+newMouseLoc[0])/2].setMarker("");
					theGrid[(mouseLoc[1]+newMouseLoc[1])/2][(mouseLoc[0]+newMouseLoc[0])/2].setDisplayMarker(false);
					theGrid[newMouseLoc[1]][newMouseLoc[0]].setDisplayMarker(true);
					theGrid[newMouseLoc[1]][newMouseLoc[0]].setMarker(locSymbol);
					theGrid[mouseLoc[1]][mouseLoc[0]].setDisplayMarker(false);
					theGrid[mouseLoc[1]][mouseLoc[0]].setMarker("");
				}
				if (newMouseLoc[1]==7&&temp==""){
					theGrid[newMouseLoc[1]][newMouseLoc[0]].setMarker("W");
				}
			}
			//Move Legality checker of Kinged White Pieces
			if (theGrid[mouseLoc[1]][mouseLoc[0]].getMarker().equals("W")){
				if (temp == "" && Math.abs(mouseLoc[1]-newMouseLoc[1])==1 && Math.abs(mouseLoc[0]-newMouseLoc[0])==1){
					theGrid[newMouseLoc[1]][newMouseLoc[0]].setDisplayMarker(true);
					theGrid[newMouseLoc[1]][newMouseLoc[0]].setMarker(locSymbol);
					theGrid[mouseLoc[1]][mouseLoc[0]].setDisplayMarker(false);
					theGrid[mouseLoc[1]][mouseLoc[0]].setMarker("");
					if (theGrid[((newMouseLoc[1]-mouseLoc[1])/2)+mouseLoc[1]][((newMouseLoc[0]-mouseLoc[0])/2)+mouseLoc[0]].shouldDisplayMarker()==true){
						theGrid[((newMouseLoc[1]-mouseLoc[1])/2)+mouseLoc[1]][((newMouseLoc[0]-mouseLoc[0])/2)+mouseLoc[0]].setMarker("");
						theGrid[((newMouseLoc[1]-mouseLoc[1])/2)+mouseLoc[1]][((newMouseLoc[0]-mouseLoc[0])/2)+mouseLoc[0]].setDisplayMarker(false);
					}
				}
				else if (temp =="" && Math.abs(mouseLoc[1]-newMouseLoc[1])==2 && Math.abs(mouseLoc[0]-newMouseLoc[0])==2 && (theGrid[(mouseLoc[1]+newMouseLoc[1])/2][(mouseLoc[0]+newMouseLoc[0])/2].getMarker().equals("b")||theGrid[(mouseLoc[1]+newMouseLoc[1])/2][(mouseLoc[0]+newMouseLoc[0])/2].getMarker().equals("B"))){
					theGrid[(mouseLoc[1]+newMouseLoc[1])/2][(mouseLoc[0]+newMouseLoc[0])/2].setMarker("");
					theGrid[(mouseLoc[1]+newMouseLoc[1])/2][(mouseLoc[0]+newMouseLoc[0])/2].setDisplayMarker(false);
					theGrid[newMouseLoc[1]][newMouseLoc[0]].setDisplayMarker(true);
					theGrid[newMouseLoc[1]][newMouseLoc[0]].setMarker(locSymbol);
					theGrid[mouseLoc[1]][mouseLoc[0]].setDisplayMarker(false);
					theGrid[mouseLoc[1]][mouseLoc[0]].setMarker("");
				}
			}
			//Move legality checker of non-kinged black pieces
			if (theGrid[mouseLoc[1]][mouseLoc[0]].getMarker().equals("b") && mouseLoc[1]>newMouseLoc[1]){
				if (temp == "" && Math.abs(mouseLoc[1]-newMouseLoc[1])==1 && Math.abs(mouseLoc[0]-newMouseLoc[0])==1){
					theGrid[newMouseLoc[1]][newMouseLoc[0]].setDisplayMarker(true);
					theGrid[newMouseLoc[1]][newMouseLoc[0]].setMarker(locSymbol);
					theGrid[mouseLoc[1]][mouseLoc[0]].setDisplayMarker(false);
					theGrid[mouseLoc[1]][mouseLoc[0]].setMarker("");
					if (theGrid[((newMouseLoc[1]-mouseLoc[1])/2)+mouseLoc[1]][((newMouseLoc[0]-mouseLoc[0])/2)+mouseLoc[0]].shouldDisplayMarker()==true){
						theGrid[((newMouseLoc[1]-mouseLoc[1])/2)+mouseLoc[1]][((newMouseLoc[0]-mouseLoc[0])/2)+mouseLoc[0]].setMarker("");
						theGrid[((newMouseLoc[1]-mouseLoc[1])/2)+mouseLoc[1]][((newMouseLoc[0]-mouseLoc[0])/2)+mouseLoc[0]].setDisplayMarker(false);
					}
				}
				else if (temp =="" && Math.abs(mouseLoc[1]-newMouseLoc[1])==2 && Math.abs(mouseLoc[0]-newMouseLoc[0])==2 && (theGrid[(mouseLoc[1]+newMouseLoc[1])/2][(mouseLoc[0]+newMouseLoc[0])/2].getMarker().equals("w")||theGrid[(mouseLoc[1]+newMouseLoc[1])/2][(mouseLoc[0]+newMouseLoc[0])/2].getMarker().equals("W"))){
					theGrid[(mouseLoc[1]+newMouseLoc[1])/2][(mouseLoc[0]+newMouseLoc[0])/2].setMarker("");
					theGrid[(mouseLoc[1]+newMouseLoc[1])/2][(mouseLoc[0]+newMouseLoc[0])/2].setDisplayMarker(false);
					theGrid[newMouseLoc[1]][newMouseLoc[0]].setDisplayMarker(true);
					theGrid[newMouseLoc[1]][newMouseLoc[0]].setMarker(locSymbol);
					theGrid[mouseLoc[1]][mouseLoc[0]].setDisplayMarker(false);
					theGrid[mouseLoc[1]][mouseLoc[0]].setMarker("");
				}
				if (newMouseLoc[1]==0&&temp==""){
					theGrid[newMouseLoc[1]][newMouseLoc[0]].setMarker("B");
				}
			}
			//Move Legality checker of Kinged black Pieces
			if (theGrid[mouseLoc[1]][mouseLoc[0]].getMarker().equals("B")){
				if (temp == "" && Math.abs(mouseLoc[1]-newMouseLoc[1])==1 && Math.abs(mouseLoc[0]-newMouseLoc[0])==1){
					theGrid[newMouseLoc[1]][newMouseLoc[0]].setDisplayMarker(true);
					theGrid[newMouseLoc[1]][newMouseLoc[0]].setMarker(locSymbol);
					theGrid[mouseLoc[1]][mouseLoc[0]].setDisplayMarker(false);
					theGrid[mouseLoc[1]][mouseLoc[0]].setMarker("");
					if (theGrid[((newMouseLoc[1]-mouseLoc[1])/2)+mouseLoc[1]][((newMouseLoc[0]-mouseLoc[0])/2)+mouseLoc[0]].shouldDisplayMarker()==true){
						theGrid[((newMouseLoc[1]-mouseLoc[1])/2)+mouseLoc[1]][((newMouseLoc[0]-mouseLoc[0])/2)+mouseLoc[0]].setMarker("");
						theGrid[((newMouseLoc[1]-mouseLoc[1])/2)+mouseLoc[1]][((newMouseLoc[0]-mouseLoc[0])/2)+mouseLoc[0]].setDisplayMarker(false);
					}
				}
				else if (temp =="" && Math.abs(mouseLoc[1]-newMouseLoc[1])==2 && Math.abs(mouseLoc[0]-newMouseLoc[0])==2 && (theGrid[(mouseLoc[1]+newMouseLoc[1])/2][(mouseLoc[0]+newMouseLoc[0])/2].getMarker().equals("w")||theGrid[(mouseLoc[1]+newMouseLoc[1])/2][(mouseLoc[0]+newMouseLoc[0])/2].getMarker().equals("W"))){
					theGrid[(mouseLoc[1]+newMouseLoc[1])/2][(mouseLoc[0]+newMouseLoc[0])/2].setMarker("");
					theGrid[(mouseLoc[1]+newMouseLoc[1])/2][(mouseLoc[0]+newMouseLoc[0])/2].setDisplayMarker(false);
					theGrid[newMouseLoc[1]][newMouseLoc[0]].setDisplayMarker(true);
					theGrid[newMouseLoc[1]][newMouseLoc[0]].setMarker(locSymbol);
					theGrid[mouseLoc[1]][mouseLoc[0]].setDisplayMarker(false);
					theGrid[mouseLoc[1]][mouseLoc[0]].setMarker("");
				}
			}
			click = false;
			theGrid[mouseLoc[1]][mouseLoc[0]].colorSwap();
		}
		repaint();
	}

	@Override
	// mouse just entered this window
	public void mouseEntered(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	// mouse just left this window
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	
	
}
