package view.panelsView;

import javax.swing.*;
import java.awt.*;

import static controller.Constants.*;
import static controller.Variables.activeAbility;
import static controller.Variables.allXp;

public class SkillTreePanel extends JPanel {
    private static SkillTreePanel INSTANCE;
    public static SkillTreePanel getINSTANCE() {
        if(INSTANCE == null){
            INSTANCE = new SkillTreePanel();
        }
        return INSTANCE;
    }

    private static boolean aresUnlock, acesoUnlock, proteusUnlock;
    private static boolean aresActive, acesoActive, proteusActive;
    JButton aresButton, acesoButton, proteusButton;
    JLabel attackLabel, defendLabel, shapeLabel;
    JLabel xpLabel;
    JButton exit;
    JLabel helloSkillTreeLabel;
    private SkillTreePanel(){;
        ImageIcon lockIcon = new ImageIcon("lock.png");
        this.setSize(SKILLTREE_FRAME_WIDTH, SKILLTREE_FRAME_HEIGHT);
        this.setLocation(0, 0);
        this.setLayout(null);


        helloSkillTreeLabel = new JLabel("SKILL TREE");
        helloSkillTreeLabel.setSize(200, 50);
        helloSkillTreeLabel.setFont(new Font(null, Font.PLAIN,35));
        helloSkillTreeLabel.setForeground(Color.BLACK);
        helloSkillTreeLabel.setLocation(250, 0);
        this.add(helloSkillTreeLabel);

        xpLabel = new JLabel(String.valueOf(allXp));
        ImageIcon xpIcon = new ImageIcon("xpimage.png");
        xpLabel.setIcon(xpIcon);
        xpLabel.setSize(90, 20);
        xpLabel.setFont(new Font(null, Font.PLAIN,20));
        xpLabel.setForeground(Color.BLACK);
        xpLabel.setLocation(580, 10);
        this.add(xpLabel);

        //ATTACK
        attackLabel = new JLabel("ATTACK");
        attackLabel.setSize(200, 50);
        attackLabel.setFont(new Font(null, Font.PLAIN,33));
        attackLabel.setForeground(Color.BLACK);
        attackLabel.setLocation(70, 100);
        this.add(attackLabel);


        aresButton = new JButton("Writ of ares");
        aresButton.setIcon(lockIcon);
        aresButton.setSize(150, 50);
        aresButton.setFont(new Font(null, Font.PLAIN,20));
        aresButton.setBackground(SKILLTREE_UNLOCKED_BUTTONS_BACKGROUND_COLOR);
        aresButton.setForeground(Color.BLACK);
        aresButton.setLocation(60, 170);
        aresButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        aresButton.setFocusable(false);
        aresButton.addActionListener(e -> {
            if(!aresUnlock) {
                int result = JOptionPane.showConfirmDialog(null,
                        "Sure you want to unlock writ of ares ?", "title",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    if (allXp >= 750) {
                        aresUnlock = true;
                        aresButton.setIcon(null);
                        aresButton.setBackground(SKILLTREE_DIACTIVE_BUTTONS_BACKGROUND_COLOR);
                        allXp -= 750;
                        xpLabel.setText(String.valueOf(allXp));
                    }else{
                        JOptionPane.showMessageDialog(null,
                                "You need 750 xp to buy writ of ares !", "title",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }else{
                if(!aresActive){
                    if(!acesoActive && !proteusActive) {
                        aresActive = true;
                        activeAbility = "ares";
                        aresButton.setBackground(SKILLTREE_ACTIVE_BUTTONS_BACKGROUND_COLOR);
                    }else{
                        JOptionPane.showMessageDialog(null,
                                "Sorry you've already had active skill");
                    }
                }else{
                    aresActive = false;
                    activeAbility = "";
                    aresButton.setBackground(SKILLTREE_DIACTIVE_BUTTONS_BACKGROUND_COLOR);
                }
            }
        });
        this.add(aresButton);

        //DEFEND
        defendLabel = new JLabel("DEFEND");
        defendLabel.setSize(200, 50);
        defendLabel.setFont(new Font(null, Font.PLAIN,33));
        defendLabel.setForeground(Color.BLACK);
        defendLabel.setLocation(300, 100);
        this.add(defendLabel);

        acesoButton = new JButton("Writ of aceso");
        acesoButton.setIcon(lockIcon);
        acesoButton.setSize(150, 50);
        acesoButton.setFont(new Font(null, Font.PLAIN,20));
        acesoButton.setBackground(SKILLTREE_UNLOCKED_BUTTONS_BACKGROUND_COLOR);
        acesoButton.setForeground(Color.BLACK);
        acesoButton.setLocation(290, 170);
        acesoButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        acesoButton.setFocusable(false);
        acesoButton.addActionListener(e -> {
            if(!acesoUnlock) {
                int result = JOptionPane.showConfirmDialog(null,
                        "Sure you want to unlock writ of aceso ?", "title",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    if (allXp >= 500) {
                        acesoUnlock = true;
                        acesoButton.setIcon(null);
                        acesoButton.setBackground(SKILLTREE_DIACTIVE_BUTTONS_BACKGROUND_COLOR);
                        allXp -= 500;
                        xpLabel.setText(String.valueOf(allXp));
                    }else{
                        JOptionPane.showMessageDialog(null,
                                "You need 500 xp to buy writ of aceso !",
                                "title",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }else{
                if(!acesoActive){
                    if(!aresActive && !proteusActive) {
                        acesoActive = true;
                        activeAbility = "aceso";
                        acesoButton.setBackground(SKILLTREE_ACTIVE_BUTTONS_BACKGROUND_COLOR);
                    }else{
                        JOptionPane.showMessageDialog(null,
                                "Sorry you've already had active skill");
                    }
                }else{
                    acesoActive = false;
                    activeAbility = "";
                    acesoButton.setBackground(SKILLTREE_DIACTIVE_BUTTONS_BACKGROUND_COLOR);
                }
            }
        });
        this.add(acesoButton);

        //SHAPE
        shapeLabel = new JLabel("SHAPE");
        shapeLabel.setSize(200, 50);
        shapeLabel.setFont(new Font(null, Font.PLAIN,33));
        shapeLabel.setForeground(Color.BLACK);
        shapeLabel.setLocation(530, 100);
        this.add(shapeLabel);

        proteusButton = new JButton("Writ of proteus");
        proteusButton.setIcon(lockIcon);
        proteusButton.setSize(160, 50);
        proteusButton.setFont(new Font(null, Font.PLAIN,20));
        proteusButton.setBackground(SKILLTREE_UNLOCKED_BUTTONS_BACKGROUND_COLOR);
        proteusButton.setForeground(Color.BLACK);
        proteusButton.setLocation(505, 170);
        proteusButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        proteusButton.setFocusable(false);
        proteusButton.addActionListener(e -> {
            if(!proteusUnlock) {
                int result = JOptionPane.showConfirmDialog(null,
                        "Sure you want to unlock writ of proteus ?", "title",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    if (allXp >= 1000) {
                        proteusUnlock = true;
                        proteusButton.setIcon(null);
                        proteusButton.setBackground(SKILLTREE_DIACTIVE_BUTTONS_BACKGROUND_COLOR);
                        allXp -= 1000;
                        xpLabel.setText(String.valueOf(allXp));
                    }else{
                        JOptionPane.showMessageDialog(null,
                                "You need 1000 xp to buy writ of proteus !",
                                "title",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }else{
                if(!proteusActive){
                    if(!aresActive && !acesoActive) {
                        proteusActive = true;
                        activeAbility = "proteus";
                        proteusButton.setBackground(SKILLTREE_ACTIVE_BUTTONS_BACKGROUND_COLOR);
                    }else{
                        JOptionPane.showMessageDialog(null,
                                "Sorry you've already had active skill");
                    }
                }else{
                    proteusActive = false;
                    activeAbility = "";
                    proteusButton.setBackground(SKILLTREE_DIACTIVE_BUTTONS_BACKGROUND_COLOR);
                }
            }
        });
        this.add(proteusButton);

        //EXIT
        exit = new JButton("MAIN MENU");
        exit.setSize(150, 40);
        exit.setFont(new Font(null, Font.PLAIN,20));
        exit.setBackground(SETTING_SLIDERS_BACKGROUND_COLOR);
        exit.setForeground(Color.BLACK);
        exit.setLocation(0, 0);
        exit.setBorderPainted(false);
        exit.addActionListener(e -> {
            this.setVisible(false);
            MenuPanel.getINSTANCE();
        });
        this.add(exit);

        this.repaint();
        this.revalidate();
        MainView.mainPanel.add(this);
        MainView.mainPanel.repaint();
        MainView.mainPanel.revalidate();


    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        ImageIcon imageIcon = new ImageIcon("skilltreebackground.jpg");
        Image img = imageIcon.getImage();
        Graphics2D g2D = (Graphics2D)g;
        g2D.drawImage(img, 0 , 0, this);
        g2D.setColor(Color.BLACK);

        g2D.drawLine(135,66, 585, 66);
        g2D.drawLine(365,66, 365, 107);
        g2D.drawLine(135,66, 135, 107);
        g2D.drawLine(365,66, 365, 107);
        g2D.drawLine(585,66, 585, 107);
        //ATTACK
        g2D.drawRect(45,107,180,40);
        g2D.drawLine(135,148, 135, 170);
        //DEFEND
        g2D.drawRect(275,107,180,40);
        g2D.drawLine(365,148, 365, 170);
        //SHAPE
        g2D.drawRect(505,107,160,40);
        g2D.drawLine(585,148, 585, 170);
    }


}
