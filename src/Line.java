import java.util.ArrayList;


public abstract class Line {
	public Grid grid;
	
	public Line(Grid grid)
	{
		this.grid = grid;
	}
	
	public abstract int getSize();
	
	public abstract int getContent(int pos);
	
	public abstract void setContent(int pos, int content);
	
	public abstract ArrayList<Integer> getLineInfos();
	
	public void solveLineIncertitude()
	{
		ArrayList<Integer> groups = getLineInfos();
		int incertitude = getSize() - groups.size() + 1;
		for(int i=0; i<groups.size(); i++)
		{
			incertitude -= groups.get(i);
		}
		int pos = 0;
		for(int i=0; i<groups.size(); i++)
		{
			if(groups.get(i) > incertitude)
			{
				int certPos = pos + incertitude;
				int certLength = groups.get(i)-incertitude;
				for(int k=0; k<certLength; k++)
				{
					setContent(certPos + k, 1);
				}
				pos += groups.get(i)+1;
			}
		}
	}
	
	public boolean solveLine()
	{
		boolean flag = false;
		int content[] = new int[getSize()];
		for(int i=0; i<getSize(); i++)
			content[i] = -1;
		int posMin = 0, posMax = 0, nextMinPos = 0, nextMaxPos = 0;
		while(posMax < getSize() && getContent(posMax) != 1)
			posMax++;
		for(int i=0; i<getLineInfos().size(); i++)
		{
			boolean nextMinPosFound = false;
			int minPosChecked = posMax;
			for(int j=posMin; j<posMax; j++)
			{
				if(testPossible(this, j, getLineInfos().get(i)))
				{
					if(minPosChecked > j)
						minPosChecked = j;
					printInArray(content, j, getLineInfos().get(i), minPosChecked);
					nextMaxPos = Math.max(nextMaxPos, j+getLineInfos().get(i));
					if(!nextMinPosFound)
					{
						nextMinPos = j+getLineInfos().get(i)+1;
						nextMinPosFound = true;
					}
				}
			}
			posMin = nextMinPos;
			posMax = nextMaxPos;
			while(posMax < getSize() && getContent(posMax) != 1)
				posMax++;
			posMax++;
		}
		for(int i=0; i<content.length; i++)
		{
			if(content[i] == 0)
			{
				setContent(i, 0);
				flag = true;
			}
			if(content[i] == 1)
			{
				setContent(i, 1);
				flag = true;
			}
		}
		
		return flag;
	}
	
	public boolean testPossible(Line line, int pos, int size)
	{
		if(pos < 0 || pos+size-1 >= line.getSize())
			return false;
		if(pos>0 && line.getContent(pos-1) == 1)
			return false;
		if(pos+size<line.getSize() && line.getContent(pos+size) == 1)
			return false;
		for(int i=pos; i<pos+size; i++)
		{
			if(line.getContent(i) == 0)
				return false;
		}
		return true;
	}
	
	public void printInArray(int[] content, int pos, int length, int minPosChecked)
	{
		for(int i=minPosChecked; i<=pos; i++)
		{
			if(content[i] == -1)
				content[i] = 0;
			else if(content[i] == 1)
				content[i] = 2;
		}
		for(int i=pos; i<pos+length; i++)
		{
			if(content[i] == -1)
				content[i] = 1;
			else if(content[i] == 0)
				content[i] = 2;
		}
		if(pos+length < content.length)
		{
			if(content[pos+length] == -1)
				content[pos+length] = 0;
			else if(content[pos+length] == 1)
				content[pos+length] = 2;
		}
		System.out.print("Content : ");
		for(int i=0; i<content.length; i++)
			System.out.print(content[i] + " ");
		System.out.println();
	}
	
	public String toString()
	{
		String result = "";
		for(int i=0; i<getSize(); i++)
			result += getContent(i) + " ";
		return result;
	}
}
