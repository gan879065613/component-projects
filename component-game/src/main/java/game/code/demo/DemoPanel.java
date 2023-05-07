package game.code.demo;

import javax.swing.*;
import java.awt.*;

/**
 * @author ganjunhao
 * @date 2023/4/17 21:18
 */
public class DemoPanel extends JPanel {
    @Override
    public void print(Graphics g) {
        super.print(g);
        Image image = new ImageIcon("E:\\develop\\component_projects\\component-projects\\component-game\\src\\main\\java\\game\\resources\\image\\mpIcon.png").getImage();
        g.drawImage(image, 50 ,50 ,100 ,100 , null);
        g.drawImage(image, 50 ,150 ,200 ,200 , null);
    }
}
