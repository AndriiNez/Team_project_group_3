package org.group_3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameWindow extends JFrame {


    public GameWindow() throws HeadlessException {
        GameLogic gameLogic = new GameLogic();
        setTitle("Cities");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - getWidth()) / 2;
        int y = (screenSize.height - getHeight()) / 2;
        setLocation(x, y);

        JPanel panel = new JPanel(new FlowLayout());

        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(200, 30));
        panel.add(textField);

        JLabel label = new JLabel("Введіть назву міста");
        panel.add(label);


        JButton makeAMoveButton = new JButton("Зробити хід");
        panel.add(makeAMoveButton);
        JButton giveUpButton = new JButton("Здаюсь");
        panel.add(giveUpButton);
        JLabel computerResponseLabel = new JLabel();
        makeAMoveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String userInput = textField.getText().trim();


                if (gameLogic.isValidCity(userInput)) {
                    JOptionPane.showMessageDialog(GameWindow.this,
                            "Невірне місто! Спробуйте ще раз.");
                    textField.setText("");
                    return;
                }

                if (gameLogic.isCityUsed(userInput)) {
                    JOptionPane.showMessageDialog(GameWindow.this,
                            "Це місто вже було використане! Спробуйте інше місто.");
                    textField.setText("");
                    return;
                }

                    String computerResponse = gameLogic.generateComputerResponse(userInput);

                    if (gameLogic.usedCities.size() >= 2 && gameLogic.checkingFirstLastSymbol(userInput)) {
                        String lastAddedCity = gameLogic.usedCities.get(gameLogic.usedCities.size() - 1);
                        char lastLetter = Character.toUpperCase(lastAddedCity.charAt(lastAddedCity.length() - 1));
                        JOptionPane.showMessageDialog(GameWindow.this,
                                "Місто повинно починатись з " + lastLetter);
                        textField.setText("");
                        return;
                    }

                    if (computerResponse.equals("здаюсь")) {
                        GameWonWindow gameWonWindow = new GameWonWindow();
                        gameWonWindow.setVisible(true);

                        gameLogic.clearCollections();
                        dispose();

                    } else {
                        textField.setText("");
                        computerResponseLabel.setText("Відповідь комп'ютера: " + computerResponse);
                        gameLogic.usedCities.add(userInput.toLowerCase());
                        gameLogic.usedCities.add(computerResponse.toLowerCase());


                    }



            }
        });
        giveUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameLostWindow gameLostWindow = new GameLostWindow();
                gameLostWindow.setVisible(true);
                gameLogic.clearCollections();
                dispose();
            }
        });


        panel.add(computerResponseLabel);

        add(panel);


    }


}
