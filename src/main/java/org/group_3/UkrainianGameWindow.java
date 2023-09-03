package org.group_3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UkrainianGameWindow extends JFrame {


    public UkrainianGameWindow() throws HeadlessException {
        UkrainianLogic ukrainianLogic = new UkrainianLogic();
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


                if (ukrainianLogic.isValidCity(userInput)) {
                    JOptionPane.showMessageDialog(UkrainianGameWindow.this,
                            "Невірне місто! Спробуйте ще раз.");
                    textField.setText("");
                    return;
                }

                if (ukrainianLogic.isCityUsed(userInput)) {
                    JOptionPane.showMessageDialog(UkrainianGameWindow.this,
                            "Це місто вже було використане! Спробуйте інше місто.");
                    textField.setText("");
                    return;
                }

                String computerResponse = ukrainianLogic.generateComputerResponse(userInput);

                if (ukrainianLogic.usedCities.size() >= 2 && ukrainianLogic.checkingFirstLastSymbol(userInput)) {
                    String lastAddedCity = ukrainianLogic.usedCities.get(ukrainianLogic.usedCities.size() - 1);
                    char lastLetter = Character.toUpperCase(lastAddedCity.charAt(lastAddedCity.length() - 1));
                    JOptionPane.showMessageDialog(UkrainianGameWindow.this,
                            "Місто повинно починатись з " + lastLetter);
                    textField.setText("");
                    return;
                }

                if (computerResponse.equals("здаюсь")) {
                    GameWonWindow gameWonWindow = new GameWonWindow("ukrainian");
                    gameWonWindow.setVisible(true);

                    ukrainianLogic.clearCollections();
                    dispose();

                } else {
                    textField.setText("");
                    computerResponseLabel.setText("Відповідь комп'ютера: " + computerResponse);
                    ukrainianLogic.usedCities.add(userInput.toLowerCase());
                    ukrainianLogic.usedCities.add(computerResponse.toLowerCase());


                }


            }
        });
        giveUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameLostWindow gameLostWindow = new GameLostWindow("ukrainian");
                gameLostWindow.setVisible(true);
                ukrainianLogic.clearCollections();
                dispose();
            }
        });


        panel.add(computerResponseLabel);

        add(panel);


    }


}