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

import java.util.Arrays;

public class Map {
	
	private static int mapW=32,mapH=16;
	private static short[] map = new short[mapW*mapH];
		/*{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,
		3,4,5,3,4,5,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,0,3,3,3,3,3,3,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,0,0,3,3,3,3,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3,3,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};*/
	
	public static void wipeMap(){
		Arrays.fill(map, /*(byte)*/ (short) 0);
	}
	
	public static int getMapWidth(){
		return mapW;
	}
	
	public static int getMapHeight(){
		return mapH;
	}
	
	public static void setMapSize(int w, int h){
		mapW=w;mapH=h;
		wipeMap();
		map = Arrays.copyOf(map, mapW*mapH);
	}
	
	public static void setBlock(int x, int y, byte block){
		map[y*mapW+x]=block;
	}
	
	public static short[] getMap(){
		return map;
	}
	
	public static boolean ifHitboxCollidesWithSolidBlock_s(Hitbox hitbox){
		int startX = hitbox.getX()/Blocks.getSize();
		int startY = hitbox.getY()/Blocks.getSize();
		int endX = (hitbox.getX()+hitbox.getWidth()+((hitbox.getX()+hitbox.getWidth())%Blocks.getSize()))
				/Blocks.getSize();
		int endY = (hitbox.getY()+hitbox.getHeight()+((hitbox.getY()+hitbox.getHeight())%Blocks.getSize()))
				/Blocks.getSize();
		if((hitbox.getY()+hitbox.getHeight())%Blocks.getSize()!=0)endY++;
		if((hitbox.getX()+hitbox.getWidth())%Blocks.getSize()!=0)endX++;
		for(int i = startX;i < endX;i++){
			if(i>=mapW||i<=-1)return true;
			for(int n = startY; n < endY;n++){
				if(n>=mapH||n<=-1)return true;
				if(Blocks.getDefaultBlocks()[map[n*mapW+i]].isSolid())return true;
			}
		}
		return false;
	}
	
	public static boolean ifHitboxCollidesWithSolidBlock_s(int posX, int posY, int width, int height){
		int startX = posX/Blocks.getSize();
		int startY = posY/Blocks.getSize();
		int endX = (posX+width)/Blocks.getSize();
		int endY = (posY+height)/Blocks.getSize();
		if((posY+height)%Blocks.getSize()!=0)endY++;
		if((posX+width)%Blocks.getSize()!=0)endX++;
		for(int i = startX;i < endX;i++){
			if(i>=mapW||i<=-1)return true;
			for(int n = startY; n < endY;n++){
				if(n>=mapH||n<=-1)return true;
				if(Blocks.getDefaultBlocks()[map[n*mapW+i]].isSolid())return true;
			}
		}
		return false;
	}

}
