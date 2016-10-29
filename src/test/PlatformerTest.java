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
package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import engine.Blocks;
import engine.Hitbox;
import engine.Map;

public class PlatformerTest {
	
	public static JFrame j = new JFrame("Platforming Engine Test");
	public static JPanel p = new JPanel();
	public static Hitbox character = new Hitbox(0,0,Blocks.getSize(),Blocks.getSize()*2);
	
	public static void main(String[] args){
		j.setResizable(false);
		j.add(p);
		p.setPreferredSize(new java.awt.Dimension(512,256));
		j.pack();
		j.setDefaultCloseOperation(3);
		j.setLocationRelativeTo(null);
		j.setVisible(true);
		
		j.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_D){
					for(int i = 0; i < 5;i++)
					if(!Map.ifHitboxCollidesWithSolidBlock_s(character.getX()+1,character.getY(),
							character.getWidth(),character.getHeight()))
						character.setX(character.getX()+1);
				}
				if(e.getKeyCode()==KeyEvent.VK_A){
					for(int i = 0; i < 5;i++)
					if(!Map.ifHitboxCollidesWithSolidBlock_s(character.getX()-1,character.getY(),
							character.getWidth(),character.getHeight()))
						character.setX(character.getX()-1);
				}
				if(e.getKeyCode()==KeyEvent.VK_W){
					for(int i = 0; i < 40;i++)
					if(!Map.ifHitboxCollidesWithSolidBlock_s(character.getX(),character.getY()-1,
							character.getWidth(),character.getHeight()))
						character.setY(character.getY()-1);
				}
			}
			public void keyReleased(KeyEvent arg0) {}
			public void keyTyped(KeyEvent arg0) {}
		});
		
		createGameThread();
		
	}
	
	public static Thread t;
	public static boolean running = false;
	public static Image i;
	public static void createGameThread(){
		
		i = p.createVolatileImage(512, 256);
		running = true;
		
		t = new Thread(new Runnable(){
			public void run(){
				while(running){
					try{
						
						tick();
						render();
						
						Thread.sleep(50);
						
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
			
			public void tick(){
				
				for(int i = 0; i < 5;i++)
				if(!Map.ifHitboxCollidesWithSolidBlock_s(character.getX(),character.getY()+1,
						character.getWidth(),character.getHeight()))
				character.setY(character.getY()+1);
			}
			
			public void render(){
				Graphics g = i.getGraphics();
				g.setColor(Color.WHITE);
				g.fillRect(0, 0, 512, 256);
				
				for(int x = 0; x < Map.getMapWidth();x++){
					for(int y = 0; y < Map.getMapHeight();y++){
						if(Blocks.getDefaultBlocks()[Map.getMap()[x+y*Map.getMapWidth()]].getImage()!=null)
						g.drawImage(Blocks.getDefaultBlocks()[Map.getMap()[x+y*Map.getMapWidth()]].getImage(),
								x*Blocks.getSize(),y*Blocks.getSize(),null);
						else{
							g.setColor(Blocks.getErrorColors()[
							Map.getMap()[x+y*Map.getMapWidth()]
							]);
							g.fillRect(x*Blocks.getSize(),y*Blocks.getSize(),Blocks.getSize(),Blocks.getSize());
						}
					}
				}
				
				g.setColor(Color.BLACK);
				g.fillRect(character.getX(), character.getY(), character.getWidth(), character.getHeight());
				
				g = p.getGraphics();
				g.drawImage(i, 0, 0, null);
				g.dispose();
				
			}
			
		});
		t.start();
		
	}
	
}
