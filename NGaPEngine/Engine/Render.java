package NGaPEngine.Engine;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

import NGaPEngine.GameObject.*;

public final class Render extends JPanel{
    final Color BACKGROUND_COLOR = new Color(0,0,0);
    final Color BLANK_COLOR = new Color(0);
    private ArrayList<Sprite> sprites = new ArrayList<>();
    private ArrayList<TextLabel> labels = new ArrayList<>();
    public Render(String title,Dimension dim) {
        setSize(dim);
        repaint();
        try {Thread.sleep(100);} catch(InterruptedException e) {System.out.println(e);} //allow the background to be drawn
        setVisible(true);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.clearRect(0, 0, getWidth(), getHeight());
        g2d.setColor(BACKGROUND_COLOR);
        g2d.fillRect(0,0,getWidth(),getHeight());
        g2d.setColor(BLANK_COLOR);
        //window.clearRect(0, 0, 1280,720);

        if (AbstractEngine.running) {
            for (int z = 0;z<sprites.size();z++) {
                sprites.get(z).draw(g2d);
                //g2d.setColor(BLANK_COLOR);
            }
            for (int z = 0;z<labels.size();z++) {
                labels.get(z).draw(g2d);
                //g2d.setColor(BLANK_COLOR);
            }
        }
        g2d.dispose();
    }
    /**
     * Renders a single frame
     */
    public synchronized void draw() {
        repaint();
    }
    /**
     * Adds a sprite to the rendering list
     * @param s The sprite component to be added
     */
    public synchronized void addSprite(Sprite s) {
        int index = Math.min(s.getZIndex(),sprites.size());
        sprites.add(index,s);
    }
    /**
     * Adds a text label to the rendering list
     * @param l The text label component to be added
     */
    public synchronized void addLabel(TextLabel l) {
        int index = Math.min(l.getZIndex(),labels.size());
        labels.add(index,l);
    }
}
