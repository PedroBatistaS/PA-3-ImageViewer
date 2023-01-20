package ui;

import persistance.FileImageReader;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
import model.Image;


public class ImagePanel extends JPanel implements ImageDisplay {
    private Image image;
    private double slide;
    private int x1;
    private int x2;
    private int width;

    public ImagePanel(FileImageReader images) {
        this.image = images.read();
        image.next();
        this.slide = 0.0;
        this.x1 = 0;
        this.x2 = 0;
        this.width = image.getWidth();
        
        addMouseListener(getFirstCoordinateX());
        addMouseMotionListener(getSlidePercentage());
        
    }
    
    private MouseListener getFirstCoordinateX() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    x1 = e.getX();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (slide > 0.7) {
                    image = image.previous();
                    slide = 0;
                } else if (slide < -0.7) {
                    image = image.next();
                    slide = 0;
                } 
                repaint();
            }


            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        
        };
    }
    private MouseMotionListener getSlidePercentage() {
        return new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                x2 = e.getX();
                e.getY();
                slide = (x2 - x1) / (double) width;                
                if (slide > 0.7) {
                    image = image.previous();
                    slide = 0;
                } else if (slide < -0.7) {
                    image = image.next();
                    slide = 0;
                }
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {                
            }            
        };
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.clearRect(0, 0, image.getWidth(), image.getHeight());
        if (slide > 0.7) {
            g.drawImage(image.bitmap(), 0, 0, null);
            slide = 0;
        } else if (slide < -0.7) {
            g.drawImage(image.bitmap(), 0, 0, null);
            slide = 0;
        }
        if (slide >= 0) {
            g.drawImage(image.bitmap(), - (0 - (int) (image.getWidth() * slide)), 0, null);
            g.drawImage(image.previous().bitmap(), - (image.getWidth() - (int) (image.previous().getWidth() * slide)), 0, null);   
            slide = 0;
        } else if (slide < 0) {
            g.drawImage(image.bitmap(), - (0 - (int) (image.getWidth() * slide)), 0, null);
            g.drawImage(image.next().bitmap(),  (image.getWidth() + (int) (image.getWidth() * slide)), 0, null);
            slide = 0;
        }
    }
    
    @Override
    public Image image() {
        return image;
    }

    @Override
    public void show(Image image) {
        this.image = image;     
        repaint();    
    }
    
    
}

    