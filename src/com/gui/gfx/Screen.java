
package com.gui.gfx;

import com.gui.interactable.GUI;
import com.gui.input.KeyboardInputListener;
import com.gui.input.MouseInputListener;
import com.gui.input.MouseMotionInputListener;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class Screen extends Canvas {
    private int WIDTH = 512;
    private int HEIGHT = 512;
    private final String TITLE = "Test";
    private BufferedImage draw;
    private GUI gui;
    //offsets
    private int xoff=0;
    private int yoff=0;
    
    //inputs
    private MouseInputListener mouse;
    private MouseMotionInputListener mouseMovement;
    private KeyboardInputListener keyboard;
    
    
    
    public Screen(int width, int height){
        WIDTH=width;
        HEIGHT=height;
        
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setMaximumSize(new Dimension(WIDTH,HEIGHT));
        setMinimumSize(new Dimension(WIDTH,HEIGHT));
        
        JFrame frame = new JFrame(TITLE);
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        init();
    }
    
    public final void init() {
        requestFocus();

        draw=new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        
    }
    
    public void setInput(GUI g){
        gui = g;
        mouse = new MouseInputListener(gui);
        mouseMovement = new MouseMotionInputListener(gui);
        keyboard = new KeyboardInputListener();
        
        this.addMouseListener(mouse);
        this.addMouseMotionListener(mouseMovement);
        this.addKeyListener(keyboard);
        
    }
    
    public void setXOff(int i){
        xoff+=i;
    }
    
    public void setYOff(int i){
        yoff+=i;
    }
    
    public void render(){
        BufferStrategy bs = getBufferStrategy();
        
        if (bs==null){
            createBufferStrategy(5);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        
        /*g.setColor(Color.BLACK);
        g.fillRect(0,0,getWidth(),getHeight());*/

        
        g.drawImage(draw,0,0,null);
        
        g.dispose();
        bs.show();
        
    }

    public void drawImage(BufferedImage image, int x, int y) {
        if(image == null){
            return;
        }
        Graphics g = draw.getGraphics();
        g.drawImage(image, x, y, null);
    }

    
    /**
     * used to set offset
     */
    /*public void tick(){
        int movementSpeed = 8;
        if(keyboard.right){
            setXOff(movementSpeed);
        }
        if(keyboard.left){
            setXOff(-movementSpeed);
        }
        if(keyboard.up){
            setYOff(-movementSpeed);
        }
        if(keyboard.down){
            setYOff(movementSpeed);
        }
    }*/
}
