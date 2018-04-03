import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.ArrayList;

public class ZoneDessin extends JPanel implements ActionListener {

	private ImageIcon carre;
	private ImageIcon triangle;
	private ImageIcon cercle;

	private ImageIcon carreVert;
	private ImageIcon triangleVert;
	private ImageIcon cercleVert;

	private JPanel dessin;
	private JPanel outils;

	private JButton btnCarre;
	private JButton btnTriangle;
	private JButton btnCercle;

	//private JButton btnPalette;

	public ZoneDessin() {
		setLayout(new BorderLayout());

		carre = new ImageIcon("images/carre.png");
		triangle = new ImageIcon("images/triangle.png");
		cercle = new ImageIcon("images/cercle.png");

		carreVert = new ImageIcon("images/carre_vert.png");
		triangleVert = new ImageIcon("images/triangle_vert.png");
		cercleVert = new ImageIcon("images/cercle_vert.png");

		dessin = new JPanel(new BorderLayout());
		outils = new JPanel(new FlowLayout(FlowLayout.CENTER));

		dessin.setBorder(BorderFactory.createLineBorder(Color.black));


		btnCarre = new JButton(carre);
		btnTriangle = new JButton(triangle);
		btnCercle = new JButton(cercle);

		//btnPalette = new JButton(new ImageIcon("images/palette.png"));

		outils.add(btnCarre);
		outils.add(btnTriangle);
		outils.add(btnCercle);

		outils.add(new JLabel(" "));

		//outils.add(btnPalette);

		add(dessin);
		add(outils, BorderLayout.SOUTH);

		btnCarre.addActionListener(this);
		btnTriangle.addActionListener(this);
		btnCercle.addActionListener(this);

		setVisible(true);
	}

	public void deselectBtnForme() {
		btnCarre.setIcon(carre);
		btnTriangle.setIcon(triangle);
		btnCercle.setIcon(cercle);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCarre) {
			deselectBtnForme();	
			btnCarre.setIcon(carreVert);
		} 

		if (e.getSource() == btnTriangle) {
			deselectBtnForme();	
			btnTriangle.setIcon(triangleVert);
		}
			
		if (e.getSource() == btnCercle) {
			deselectBtnForme();	
			btnCercle.setIcon(cercleVert);
		} 
	}

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.add(new ZoneDessin());
		f.setSize(600, 600);
		f.setVisible(true);
	}
}

class Dessin extends JPanel implements MouseListener{

	public Dessin() {

	}

	public void paintComponent(Graphics g) {

	}

	public void mouseClicked(MouseEvent e) {}

	public void mousePressed(MouseEvent e) {}

	public void mouseReleased(MouseEvent e) {}

	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}
}