import java.util.ArrayList;


public class Column extends Line
{
	private int colNumber;
	
	public Column(Grid grid, int colNumber)
	{
		super(grid);
		this.colNumber = colNumber;
	}
	
	public int getSize()
	{
		return grid.getH();
	}
	
	public void setContent(int pos, int content)
	{
		grid.setContent(colNumber, pos, content);
	}
	
	public int getContent(int pos)
	{
		return grid.getContent(colNumber, pos);
	}
	
	public ArrayList<Integer> getLineInfos()
	{
		return grid.getColContent(colNumber);
	}
}
