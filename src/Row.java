import java.util.ArrayList;


public class Row extends Line
{
	private int rowNumber;
	
	public Row(Grid grid, int rowNumber)
	{
		super(grid);
		this.rowNumber = rowNumber;
	}
	
	public int getSize()
	{
		return grid.getW();
	}
	
	public void setContent(int pos, int content)
	{
		grid.setContent(pos, rowNumber, content);
	}
	
	public int getContent(int pos)
	{
		return grid.getContent(pos, rowNumber);
	}
	
	public ArrayList<Integer> getLineInfos()
	{
		return grid.getRowContent(rowNumber);
	}
}
