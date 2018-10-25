package lesson7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame {

    private static final int WINDOW_WIDHT = 507;
    private static final int WINDOW_HEIGNT = 555;
    private static final int WINDOW_POS_X = 650;
    private static final int WINDOW_POS_Y = 250;

    private Map map;
    private SettingsWindow settingsWindow;

        GameWindow () {
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setSize(WINDOW_WIDHT, WINDOW_HEIGNT);
            setLocation(WINDOW_POS_X, WINDOW_POS_Y);
            setResizable(false);
            setTitle("Игра - крестики нолики");
            JButton btnStart = new JButton("Star new game");
            // создание нового окна
            btnStart.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    settingsWindow.setVisible(true);
                }
            });
            JButton btnStop = new JButton("Exit");
            btnStop.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
            JPanel panBottom = new JPanel();
            panBottom.setLayout(new GridLayout(1, 2));
            panBottom.add(btnStart);
            panBottom.add(btnStop);
            add(panBottom, BorderLayout.SOUTH);

            map = new Map();
            settingsWindow = new SettingsWindow(this);
            add(map, BorderLayout.CENTER);
            setVisible(true);
        }
            void StartNewGame (int mode, int fieldSizeX, int fieldSizeY, int winLeng){
                map.StartNewGame(mode, fieldSizeX, fieldSizeY, winLeng);

            }

}
