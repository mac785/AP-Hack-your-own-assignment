import java.awt.GridLayout;

import javax.swing.JFrame;

public class GridDemoFrame extends JFrame
{
	GridDemoPanel thePanel;
	
	public GridDemoFrame()
	{
		super("Grid Demo");
		
		setSize(425,470);
		
		this.getContentPane().setLayout(new GridLayout(1,1));
		thePanel = new GridDemoPanel();
		this.getContentPane().add(thePanel);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);		
	}
}
