import java.util.ArrayList;


public class Grid {
	

	public int w, h;
	private int[] content;
	ArrayList<Integer> rowContent[], colContent[];

	public Grid(int w, int h, ArrayList<Integer> rowContent[], ArrayList<Integer> colContent[])
	{
		this.w = w;
		this.h = h;
		this.colContent = colContent;
		this.rowContent = rowContent;
		content = new int[w*h];
		for(int i=0; i<w*h; i++)
			content[i] = -1;
	}
	
	public int getContent(int i, int j)
	{
		return content[i+j*w];
	}
	
	public void setContent(int i, int j, int val)
	{
		content[i+j*w] = val;
	}
	
	public String toString()
	{
		String str = "";
		for(int y=0; y<h; y++)
		{
			for(int x=0; x<w; x++)
			{	
				if(getContent(x, y) == 1)
					str += "X";
				else if(getContent(x, y) == 0)
					str += "_";
				else if(getContent(x, y) == -1)
					str += "?";
			}
			str += "\n";
		}
		return str;
	}
	
	public int getW()
	{
		return w;
	}
	
	public int getH()
	{
		return h;
	}
	
	public ArrayList<Integer> getRowContent(int row)
	{
		return rowContent[row];
	}
	
	public ArrayList<Integer> getColContent(int col)
	{
		return colContent[col];
	}
}
