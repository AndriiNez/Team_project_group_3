package org.group_3;

import javax.swing.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WelcomeWindow welcomeWindow = new WelcomeWindow();
            welcomeWindow.setVisible(true);
        });
    }
}