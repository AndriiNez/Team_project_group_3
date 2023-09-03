package org.group_3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnglishGameWindow extends JFrame {

    public EnglishGameWindow() throws HeadlessException {
        EnglishLogic englishLogic = new EnglishLogic();
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

        JLabel label = new JLabel("Enter the city name");
        panel.add(label);


        JButton makeAMoveButton = new JButton("Make a Move");
        panel.add(makeAMoveButton);
        JButton giveUpButton = new JButton("Give Up");
        panel.add(giveUpButton);
        JLabel computerResponseLabel = new JLabel();
        makeAMoveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String userInput = textField.getText().trim();


                if (englishLogic.isValidCity(userInput)) {
                    JOptionPane.showMessageDialog(EnglishGameWindow.this,
                            "Incorrect city! Please try again.");
                    textField.setText("");
                    return;
                }

                if (englishLogic.isCityUsed(userInput)) {
                    JOptionPane.showMessageDialog(EnglishGameWindow.this,
                            "This city has already been used! Please choose another city.");
                    textField.setText("");
                    return;
                }

                String computerResponse = englishLogic.generateComputerResponse(userInput);

                if (englishLogic.usedCities.size() >= 2 && englishLogic.checkingFirstLastSymbol(userInput)) {
                    String lastAddedCity = englishLogic.usedCities.get(englishLogic.usedCities.size() - 1);
                    char lastLetter = Character.toUpperCase(lastAddedCity.charAt(lastAddedCity.length() - 1));
                    JOptionPane.showMessageDialog(EnglishGameWindow.this,
                            "This city has already been used! Please choose another city." + lastLetter);
                    textField.setText("");
                    return;
                }

                if (computerResponse.equals("i give up")) {
                    GameWonWindow gameWonWindow = new GameWonWindow("english");
                    gameWonWindow.setVisible(true);

                    englishLogic.clearCollections();
                    dispose();

                } else {
                    textField.setText("");
                    computerResponseLabel.setText("Computer response: " + computerResponse);
                    englishLogic.usedCities.add(userInput.toLowerCase());
                    englishLogic.usedCities.add(computerResponse.toLowerCase());


                }


            }
        });
        giveUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameLostWindow gameLostWindow = new GameLostWindow("english");
                gameLostWindow.setVisible(true);
                englishLogic.clearCollections();
                dispose();
            }
        });


        panel.add(computerResponseLabel);

        add(panel);


    }


}

