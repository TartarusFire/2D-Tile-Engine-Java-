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

import java.awt.Color;
import java.awt.Image;
import java.util.Arrays;

//@SuppressWarnings("unused")
public class Blocks {
	
	private static int size = 8;
	
	public static int getSize(){
		return size;
	}
	
	public static void setSize(int size){
		if(size>256)size=256;
		if(size<1)size = 1;
		Blocks.size = size;
	}
	
	public static Block getBlock(short block){
		return defaultBlocks[block&0xFFFF];
	}
	
	public static Block[] getDefaultBlocks(){
		return defaultBlocks;
	}
	
	private static Block[] defaultBlocks = {
		new Block("Air",null),//air
		new Block("Water",null,(byte)0,(byte)1,(byte)0,(byte)0,(byte)0),//water
		new Block("Lava",null,(byte)0,(byte)1,(byte)0,(byte)0,(byte)1),//lava
		
		new Block("Stone",null,(byte)1,(byte)0,(byte)0,(byte)0,(byte)0),//static stone
		new Block("Wood",null,(byte)1,(byte)0,(byte)0,(byte)0,(byte)0),//static wood
		new Block("Metal",null,(byte)1,(byte)0,(byte)0,(byte)0,(byte)0),//static metal
		new Block("Dirt",null,(byte)1,(byte)0,(byte)0,(byte)0,(byte)0),//static dirt
		new Block("Spikes",null,(byte)1,(byte)0,(byte)0,(byte)0,(byte)1),//static spikes
		
		new Block("Break_Stone",null,(byte)1,(byte)0,(byte)1,(byte)0,(byte)0),//breakable stone
		new Block("Break_Wood",null,(byte)1,(byte)0,(byte)1,(byte)0,(byte)0),//breakable wood
		new Block("Break_Metal",null,(byte)1,(byte)0,(byte)1,(byte)0,(byte)0),//breakable metal
		new Block("Break_Dirt",null,(byte)1,(byte)0,(byte)1,(byte)0,(byte)0),//breakable dirt
		new Block("Break_Spikes",null,(byte)1,(byte)0,(byte)1,(byte)0,(byte)1),//breakable spikes
		
		new Block("Move_Stone",null,(byte)1,(byte)0,(byte)0,(byte)1,(byte)0),//movable stone
		new Block("Move_Wood",null,(byte)1,(byte)0,(byte)0,(byte)1,(byte)0),//movable wood
		new Block("Move_Metal",null,(byte)1,(byte)0,(byte)0,(byte)1,(byte)0),//movable metal
		new Block("Move_Dirt",null,(byte)1,(byte)0,(byte)0,(byte)1,(byte)0)//movable dirt
		//movable spikes do not exist
		
	};
	
	public static Color[] getErrorColors(){
		return errorColors;
	}
	
	private static Color[] errorColors = {
		Color.CYAN,
		Color.BLUE,
		Color.RED,
		
		Color.GRAY,
		new Color(0xFF755232),//brown _1
		Color.LIGHT_GRAY,
		new Color(0xFF6A2F1D),//brown _2
		Color.YELLOW,
		
		Color.GRAY,
		new Color(0xFF755232),//brown _1
		Color.LIGHT_GRAY,
		new Color(0xFF6A2F1D),//brown _2
	};
	
	public static void createCustomBlock(String name, Image texture, byte[] data){
		
		defaultBlocks =Arrays.copyOf(defaultBlocks, defaultBlocks.length+1);
		defaultBlocks[defaultBlocks.length-1] = new Block(texture,data);
		defaultBlocks[defaultBlocks.length-1].setName(name);
		
		errorColors =Arrays.copyOf(errorColors, errorColors.length+1);
		errorColors[errorColors.length-1] = new Color(0,128,0);
		
	}
	
	public static class Block{
		private String id = "-X-";
		private byte[] info = {0,0,0,0,0};
		private Image texture = null;
		public Block(Image gfx){
			texture = gfx;
		}
		public Block(String name,Image gfx){
			texture = gfx;
			id = name;
		}
		public Block(Image gfx, byte[] data){
			texture = gfx;
			info = data;
		}
		public Block(Image gfx, byte solid, byte hinder, byte breakable, byte movable, byte harmful){
			texture = gfx;
			info[0] = solid;
			info[1] = hinder;
			info[2] = breakable;
			info[3] = movable;
			info[4] = harmful;
		}
		public Block(String name,Image gfx, byte solid, byte hinder, byte breakable, byte movable, byte harmful){
			id = name;
			texture = gfx;
			info[0] = solid;
			info[1] = hinder;
			info[2] = breakable;
			info[3] = movable;
			info[4] = harmful;
		}
		public void setName(String blockName){
			id = blockName;
		}
		public String getName(){
			return id;
		}
		public void setImage(Image blockGraphics){
			texture = blockGraphics;
		}
		public Image getImage(){
			return texture;
		}
		public void setSolid(byte bool){
			info[0]=bool;
		}
		public boolean isSolid(){
			return info[0]==1;
		}
		public void setHinder(byte bool){
			info[1]=bool;
		}
		public boolean doesHinder(){
			return info[1]==1;
		}
		public void setBreakable(byte bool){
			info[2]=bool;
		}
		public boolean isBreakable(){
			return info[2]==1;
		}
		public void setMovable(byte bool){
			info[3]=bool;
		}
		public boolean isMovable(){
			return info[3]==1;
		}
		public void setHarmful(byte bool){
			info[4]=bool;
		}
		public boolean isHarmful(){
			return info[4]==1;
		}
	}

}
