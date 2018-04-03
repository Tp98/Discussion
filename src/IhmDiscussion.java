import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class IhmDiscussion extends JPanel implements ActionListener, KeyListener{
    private JPanel pDiscussion;
    private JPanel pFilDiscussion;
    private JPanel pRedaction;

    private JTextArea filDiscussion;
    private JTextField message;
    private JButton envoyer;

    private String pseudo;

    public IhmDiscussion(String pseudo){
       // setSize(500,800);
        setLayout(new BorderLayout());

        this.pseudo = pseudo;

        pDiscussion = new JPanel(new BorderLayout());

        pFilDiscussion = new JPanel(new BorderLayout());

        pRedaction = new JPanel();

        filDiscussion = new JTextArea();
        message = new JTextField(20);
        envoyer = new JButton("Envoyer");


        filDiscussion.setSize(300,250);
        filDiscussion.setEditable(false);
        filDiscussion.setAutoscrolls(true);

        pFilDiscussion.add(filDiscussion,BorderLayout.CENTER);
        pFilDiscussion.setBorder(new EmptyBorder(10,25,10,25));


        filDiscussion.setBorder(BorderFactory.createLineBorder(Color.black));


        pRedaction.add(message);
        pRedaction.add(envoyer);
        pDiscussion.add(pFilDiscussion,BorderLayout.CENTER);
        pDiscussion.add(pRedaction,BorderLayout.SOUTH);

        envoyer.addActionListener(this);
        message.addKeyListener(this);

        add(pDiscussion);

       // setVisible(true);

    }

    public void afficherMessage(){
        if(filDiscussion.getText().equals(""))
            filDiscussion.setText(pseudo + " : " + message.getText());
        else
            filDiscussion.setText(filDiscussion.getText()+ "\n" + pseudo + " : " +  message.getText());
        message.setText("");
    }



    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == envoyer)
            afficherMessage();

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            envoyer.doClick();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

  /* public static void main(String[] args) {
        new IhmDiscussion("alex");
    }*/
}
