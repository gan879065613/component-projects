package game.code.demo;

import javax.swing.*;

/**
 * @author ganjunhao
 * @date 2023/4/17 21:21
 */
public class DemoFrame extends JFrame {

    public static void main(String[] args) {
        new DemoFrame();
    }

    public DemoFrame() {
        this.setTitle("Demo");// 窗口标题
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 窗体退出方式
        this.setSize(1000, 1000);//窗体大小
        this.setVisible(true);//设置显示窗体，默认false
        // 实例化我们的DemoPanel
        DemoPanel demoPanel = new DemoPanel();
        this.add(demoPanel);
    }
}
