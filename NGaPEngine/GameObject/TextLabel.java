package NGaPEngine.GameObject;

import java.awt.*;

public class TextLabel extends Component{
    private String text = "";
    private Color color;
    private Font font;
    protected int size = 20;
    public boolean visible = true;

    public TextLabel(WorldObject parent, String text) {
        this.parent = parent;
        this.text = text;
        this.font = new Font(Font.SANS_SERIF, Font.PLAIN, size);
    }
    public TextLabel(WorldObject parent, String text, int size) {
        this.parent = parent;
        this.text = text;
        this.size = size;
        this.font = new Font(Font.SANS_SERIF, Font.PLAIN, size);
    }
    public TextLabel(WorldObject parent, String text, Font font) {
        this.parent = parent;
        this.text = text;
        this.font = font;
    }
    
    public void changed() {
        this.parent.changed();
    }


    public String getText() {
        return this.text;
    }


    public void setText(String text) {
        this.text = text;
    }

    public void draw(Graphics2D g2d) {
        if (!visible) {return;}
        g2d.setColor(color);
        //System.out.println(shape);
        g2d.setFont(font);
        g2d.drawString(text, parent.getPosition().x+getPosition().x, parent.getPosition().y+getPosition().y);
    }
}
