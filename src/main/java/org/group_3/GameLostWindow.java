package org.group_3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameLostWindow extends JFrame {
    private String currentLanguage;


    public GameLostWindow(String language) throws HeadlessException {
        this.currentLanguage = language;

        setTitle("Cities");
        setSize(400, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;
        setLocation(x, y);

        JPanel panel = new JPanel(new FlowLayout());
        JLabel label = new JLabel(currentLanguage.equals("ukrainian") ? "ГРА ЗАКІНЧЕНА" : "Game Over");
        panel.add(label);
        JButton playAgainButton = new JButton(currentLanguage.equals("ukrainian") ? "Грати знову" : "Play Again");
        panel.add(playAgainButton);
        JButton closeGameButton = new JButton(currentLanguage.equals("ukrainian") ? "Закрити гру" : "Close Game");
        panel.add(closeGameButton);
        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    WelcomeWindow welcomeWindow = new WelcomeWindow();
                    welcomeWindow.setVisible(true);
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
