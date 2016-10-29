/*

An engine that returns collsion values while not constraining movment to unit sizes.
    Copyright (C) 2016  TartarusFire

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
    
    https://github.com/TartarusFire/

*/
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
