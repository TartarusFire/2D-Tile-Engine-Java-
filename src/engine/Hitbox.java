package engine;

public class Hitbox {
	
	private int hx=0,hy=0,hw=0,hh=0;
	
	public Hitbox(int width, int height){
		hw = width; hh = height;
	}
	
	public Hitbox(int posX,int posY,int width, int height){
		hx = posX; hy = posY; hw = width; hh = height;
	}
	
	public int getX(){
		return hx;
	}
	
	public int getY(){
		return hy;
	}
	
	public int getWidth(){
		return hw;
	}
	
	public int getHeight(){
		return hh;
	}
	
	public void setX(int posX){
		hx = posX;
	}
	
	public void setY(int posY){
		hy = posY;
	}
	
	public void setWidth(int width){
		hw = width;
	}
	
	public void setHeight(int height){
		hh = height;
	}
	
}
