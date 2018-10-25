package lesson7;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsWindow extends JFrame {

    private static final int WINDOW_WIDHT = 350 ;
    private static final int WINDOW_HEIGNT = 230;
    private static final int MIN_WIN_LENGTH = 3;
    private static final int MIN_FIELD_SIZE = 3;
    private static final int MAX_FIELD_SIZE = 10;
    private static final String FIELD_SIZE_PREFIX = "Field size is: ";
    private static final String WIN_LENGTH_PREFIX = "Win length is: ";


    private JRadioButton humVSAI;
    private JRadioButton humVShub;
    private JSlider sliderWinlen;
    private JSlider sliderFieldSize;


    private GameWindow gameWindow;

    public SettingsWindow (GameWindow gameWindow){
        this.gameWindow = gameWindow;
        setSize(WINDOW_WIDHT, WINDOW_HEIGNT);
        Rectangle gameWindowBounds = gameWindow.getBounds();
        int posX = (int) gameWindowBounds.getCenterX() - WINDOW_WIDHT/2;
        int posY = (int) gameWindowBounds.getCenterY() - WINDOW_HEIGNT/2;
        setLocation(posX, posY);
        setResizable(false);
        setTitle("Creating new Game");
        setVisible(true);
        setLayout(new GridLayout(10, 1));
        addGameModeControls();
        addFieldControl();
        JButton btnStart = new JButton("Apply setting");
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applySetting();

            }
        });
        add(btnStart);

    }

    private void addFieldControl() {
        JLabel jblFieldSize = new JLabel(FIELD_SIZE_PREFIX + MIN_FIELD_SIZE);
        JLabel jblWinLength = new JLabel(WIN_LENGTH_PREFIX + MIN_WIN_LENGTH);

        sliderFieldSize = new JSlider(MIN_FIELD_SIZE, MAX_FIELD_SIZE,MIN_FIELD_SIZE);
        sliderWinlen = new JSlider (MIN_WIN_LENGTH, MIN_FIELD_SIZE, MIN_WIN_LENGTH);
        sliderFieldSize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int currentValue = sliderFieldSize.getValue();
                jblFieldSize.setText(FIELD_SIZE_PREFIX + currentValue);
                sliderWinlen.setMaximum(currentValue);
            }
        });
        sliderWinlen.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                jblWinLength.setText(WIN_LENGTH_PREFIX + sliderWinlen.getValue());
            }
        });
        add (new JLabel("Выбирете размер поля"));
        add (jblFieldSize);
        add(sliderFieldSize);
        add (new JLabel("Сhoose win length"));
        add (jblWinLength);
        add (sliderWinlen);
    }

    private void addGameModeControls() {
        add (new JLabel("Выбирете игровые настройки"));
        humVSAI = new JRadioButton("Human vs. AI");
        humVShub = new JRadioButton("Human vs. Human");
        ButtonGroup gameMode = new ButtonGroup();
        gameMode.add(humVSAI);
        gameMode.add(humVShub);
        humVSAI.setSelected(true);
        add(humVSAI);
        add(humVShub);
    }

    void applySetting (){
        int mode;
        if (humVSAI.isSelected()){
            mode = Map.MODE_AI_HUM;
        } else if (humVShub.isSelected()){
            mode = Map.MODE_HUM_HUM;
        } else
            throw new RuntimeException("Ошибка");
        int fieldSize = sliderFieldSize.getValue();
        int winlen = sliderWinlen.getValue();
       gameWindow.StartNewGame(mode, fieldSize, fieldSize, winlen);
        setVisible(false);
    }

}
