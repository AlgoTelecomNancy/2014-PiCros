import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;


public class Picross {
	private ArrayList<Integer>[] colInfos;
	
	private ArrayList<Integer>[] rowInfos;
	
	private Grid grid;
	private int w, h;
	
	@SuppressWarnings("unchecked")
	public Picross(String file){
		Scanner in;
		try {
			in = new Scanner(new BufferedReader(new FileReader(file)));
			w = in.nextInt();
			h = in. nextInt();
			colInfos = new ArrayList[w];
			rowInfos = new ArrayList[h];
			for(int i = 0; i < w; i ++){
				colInfos[i] = new ArrayList<Integer>();
				int n = in.nextInt();
				for(int j = 0; j < n; j ++){
					colInfos[i].add(in.nextInt());
				}	
			}
			for(int i = 0; i < h; i ++){
				rowInfos[i] = new ArrayList<Integer>();
				int n = in.nextInt();
				for(int j = 0; j < n; j ++){
					rowInfos[i].add(in.nextInt());
				}
			}
			grid = new Grid(w, h, rowInfos, colInfos);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void solve()
	{
		for(int i=0; i<w; i++)
		{
			Column c = new Column(grid, i);
			c.solveLineIncertitude();
		}
		for(int i=0; i<h; i++)
		{
			Row r = new Row(grid, i);
			r.solveLineIncertitude();
		}
		boolean flag = false;
		while(!flag)
		{
			flag = false;
			for(int i=0; i<grid.w; i++)
			{
				Column col = new Column(grid, i);
				if(col.solveLine())
					flag = true;
			}
			for(int i=0; i<grid.h; i++)
			{
				Row row = new Row(grid, i);
				if(row.solveLine())
					flag = true;
			}
		}
		System.out.println(grid);
	}
	
	
	public static void main(String[] args) {
		Picross p = new Picross("chat.txt");
		p.solve();
	}
}