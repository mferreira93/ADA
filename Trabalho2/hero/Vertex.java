package hero;

public class Vertex {
	
	private int x;
	private int y;
	private int m;
	private int n;
	private int xFont;
	private int yFont;
	
	public Vertex(int x, int y, int m, int n){
		this.x = x;
		this.y = y;
		this.m = m;
		this.n = n;
	}
	
	public Vertex(int x, int y, int m, int n, int xFont, int yFont){
		this.x = x;
		this.y = y;
		this.m = m;
		this.n = n;
		this.xFont = xFont;
		this.yFont = yFont;
	}
	
	public int xCoord(){
		return x;
	}
	
	public int yCoord(){
		return y;
	}
	
	public int mValue(){
		return m;
	}
	
	public int nValue(){
		return n;
	}
	
	public int xFont(){
		return xFont;
	}
	
	public int yFont(){
		return yFont;
	}
}