import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class IhmGenerale extends JFrame implements ActionListener, KeyListener{
    private JButton valider;
    private JTextField saisiePseudo;
    private IhmDiscussion discussion;
    private ZoneDessin  dessin;
    private JPanel acceuilNorth;
    private JPanel acceuilCenter;
    private JPanel acceuilSouth;
    private JPanel acceuilEast;
    private JPanel acceuil;
    private JPanel discussionDessin;
    private String pseudo;
    private JLabel bienvenue;
    private JLabel instruction;
    private JPanel pDiscussion;

    public IhmGenerale(){
        acceuilNorth = new JPanel(new BorderLayout());
        acceuilCenter = new JPanel();
        acceuilSouth = new JPanel(new BorderLayout());
        acceuilEast = new JPanel();

        acceuil = new JPanel(new BorderLayout());

        valider = new JButton("Valider");
        saisiePseudo = new JTextField(20);

        bienvenue = new JLabel("Bienvenue dans cette application de discussion !!");
        instruction = new JLabel(" pour continuer veuillez entrer votre pseudo");

        acceuilNorth.add(bienvenue,BorderLayout.NORTH);
        acceuilNorth.add(instruction,BorderLayout.CENTER);
        acceuilCenter.add(saisiePseudo);
        acceuilEast.add(valider);
        acceuilSouth.add(acceuilEast,BorderLayout.EAST);

        acceuil.add(acceuilNorth,BorderLayout.NORTH);
        acceuil.add(acceuilCenter,BorderLayout.CENTER);
        acceuil.add(acceuilSouth,BorderLayout.SOUTH);


        valider.addActionListener(this);
        saisiePseudo.addKeyListener(this);

        add(acceuil);

        setVisible(true);

        pack();
    }

    public void afficherDiscussionDessin(String pseudo){

        setSize(1000,500);
        discussionDessin = new JPanel(new GridLayout(1,2));

        dessin = new ZoneDessin(); 
        discussion = new IhmDiscussion(pseudo);

        discussionDessin.add(discussion);
        discussionDessin.add(dessin);

        add(discussionDessin);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == valider){
            pseudo = saisiePseudo.getText();
            this.remove(acceuil);
            afficherDiscussionDessin(pseudo);
            repaint();
        }
    }

    public static void main(String[] args) {
        new IhmGenerale();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            valider.doClick();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
