package view.panelsView;

import controller.Update;
import model.charactersModel.EpsilonModel;
import view.charactersView.EpsilonView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;

import static controller.Controller.apolloImpact;
import static controller.Variables.*;

public class StorePanel extends JPanel {
    private static StorePanel INSTANCE;

    JButton exit;
    JLabel helloStoreLabel;
    JButton hephaestusButton, athenaButton, apolloButton ;
    public static StorePanel getINSTANCE() {
        if(INSTANCE == null){
            INSTANCE = new StorePanel();
        }
        return INSTANCE;
    }
    public StorePanel(){
        this.setSize(587,336);
        this.setLocation(0, 0);
        this.setLayout(null);
        this.setVisible(true);
        StoreFrame.getINSTANCE().getContentPane().add(this);
        StoreFrame.getINSTANCE().setVisible(true);

        helloStoreLabel = new JLabel("STORE");
        helloStoreLabel.setSize(200, 50);
        helloStoreLabel.setFont(new Font(null, Font.PLAIN,35));
        helloStoreLabel.setForeground(Color.WHITE);
        helloStoreLabel.setLocation(230, 0);
        this.add(helloStoreLabel);


        hephaestusButton = new JButton("O'Hephaestus");
        hephaestusButton.setSize(200, 40);
        hephaestusButton.setContentAreaFilled(false);
        hephaestusButton.setFocusable(false);
        hephaestusButton.setFont(new Font(null, Font.PLAIN,25));
        hephaestusButton.setForeground(Color.WHITE);
        hephaestusButton.setLocation(200, 130);
        hephaestusButton.addActionListener(e -> {
            if(xp >= 100) {
                xp -= 100;
                apolloImpact(new Point2D.Double(EpsilonView.getINSTANCE().getX(), EpsilonView.getINSTANCE().getY()));
                close();
            }else{
                JOptionPane.showMessageDialog(null,
                        "You need 50 xp to activate O'Hephaestus !",
                        "title",JOptionPane.INFORMATION_MESSAGE);
            }
        });
        this.add(hephaestusButton);

        athenaButton = new JButton("O'Athena");
        athenaButton.setSize(200, 40);
        athenaButton.setContentAreaFilled(false);
        athenaButton.setFocusable(false);
        athenaButton.setFont(new Font(null, Font.PLAIN,25));
        athenaButton.setForeground(Color.WHITE);
        athenaButton.setLocation(80, 190);
        athenaButton.addActionListener(e -> {
            if(xp >= 75) {
                xp -= 75;
                athenaActive = true;
                close();
            }else{
                JOptionPane.showMessageDialog(null,
                        "You need 50 xp to activate O'Athena !",
                        "title",JOptionPane.INFORMATION_MESSAGE);
            }
        });
        this.add(athenaButton);

        apolloButton = new JButton("O'Apollo");
        apolloButton.setSize(200, 40);
        apolloButton.setContentAreaFilled(false);
        apolloButton.setFocusable(false);
        apolloButton.setFont(new Font(null, Font.PLAIN,25));
        apolloButton.setForeground(Color.WHITE);
        apolloButton.setLocation(320, 190);
        apolloButton.addActionListener(e -> {
            if(xp >= 50) {
                xp -= 50;
                hp += 10;
                close();
            }else{
                JOptionPane.showMessageDialog(null,
                        "You need 50 xp to activate O'Apollo !",
                        "title",JOptionPane.INFORMATION_MESSAGE);
            }
        });
        this.add(apolloButton);

        exit = new JButton("EXIT");
        exit.setSize(200, 40);
        exit.setFont(new Font(null, Font.PLAIN,25));
        exit.setContentAreaFilled(false);
        exit.setFocusable(false);
        exit.setForeground(Color.WHITE);
        exit.setLocation(-40, 260);
        exit.setBorderPainted(false);
        exit.addActionListener(e -> {
            close();
        });
        this.add(exit);
    }

    public void close(){
        StoreFrame.getINSTANCE().setVisible(false);
        StoreFrame.setINSTANCE(null);
        INSTANCE = null;
        Update.getINSTANCE().getMainSong().start();;
        Update.getINSTANCE().getTimer().start();
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        ImageIcon imageIcon = new ImageIcon("storeBackground.jpg");
        Image img = imageIcon.getImage();
        Graphics2D g2D = (Graphics2D)g;
        g2D.drawImage(img, 0 , 0, this);
    }

}
