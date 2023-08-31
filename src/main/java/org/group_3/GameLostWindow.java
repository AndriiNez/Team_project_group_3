package org.group_3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameLostWindow extends JFrame {
    public GameLostWindow() throws HeadlessException {

        setTitle("Cities");
        setSize(400, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;
        setLocation(x, y);

        JPanel panel = new JPanel(new FlowLayout());
        JLabel label = new JLabel("ГРА ЗАКІНЧЕНА ");
        panel.add(label);
        JButton playAgainButton = new JButton("Грати знову");
        panel.add(playAgainButton);
        JButton closeGameButton = new JButton("Закрити гру");
        panel.add(closeGameButton);
        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameWindow gameWindow = new GameWindow();
                gameWindow.setVisible(true);

                dispose();
            }
        });
        closeGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(panel);

    }
}
