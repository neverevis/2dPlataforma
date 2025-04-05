package game;

import javax.swing.*;

public class GameFrame extends JFrame {


    public GameFrame() {
        setTitle("Lan Game"); //título da janela
        setResizable(false); //não permite redimensionamento
        add(new GamePanel()); //adiciona painel do jogo ao frame
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack(); //tamanho automático
        setLocationRelativeTo(null);
        setVisible(true); //inicia a EDT
    }
}
