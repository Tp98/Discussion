import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.*;

public class Client
{
	private EnvoiTCP env;
	private RecoiUDP rec;
	
	private String pseudo;
	
	public Client()
	{
		pseudo = "";
	}
	
	public void etablirConnexion()
	{
		env = new EnvoiTCP(pseudo);
		rec = new RecoiUDP();
	}
	
	public void deconnexion()
	{
		env.deconnecter();
	}
	
	public static void main(String[] args)
	{
		EnvoiTCP tcp1 = new EnvoiTCP("Paulin"); tcp1.start();
		EnvoiTCP tcp2 = new EnvoiTCP("Tom"); tcp2.start();
		EnvoiTCP tcp3 = new EnvoiTCP("Alexandre"); tcp3.start();
		
		RecoiUDP udp = new RecoiUDP(); udp.start();
		
		tcp1.envoyerCercle(true, 10, 10, 5, 159135);
		tcp2.envoyerCarre(false, 50, 50, 10, 159135);
		tcp3.envoyerTriangle(true, 100, 100, 15, 159135);
	}
}

class EnvoiTCP extends Thread
{
    private String  pseudo;
    private Socket  socket;

    private boolean connecte;
    private boolean actionEffectuee;

    private String[] informations; // La première case indique le type d'information texte/dessin
                                   // La deuxième case contient le texte à envoyer ou les informations de la forme à afficher concaténées en chaîne

    public EnvoiTCP(String pseudo)
    {
        this.pseudo          = pseudo;
        this.connecte        = true;
        this.actionEffectuee = false;
        this.informations    = new String[2];
    }

    public void run()
    {
        try {
            socket = new Socket("192.168.43.47",2010); // InetAddress.getLocalHost()
            PrintWriter fluxDonnees = new PrintWriter(socket.getOutputStream());

            while (connecte)
            {
				if (actionEffectuee)
                {
					fluxDonnees.println(informations[0] + "|" + informations[1]);
					fluxDonnees.flush();
					
					actionEffectuee = false;
				}
            }
			
			fluxDonnees.println("deconnexion"); fluxDonnees.flush();

            socket.close();
        } catch (UnknownHostException e) { e.printStackTrace(); }
          catch (IOException          e) { e.printStackTrace(); }
    }

    public void envoyerTexte(String texte)
    {
        informations[0] = "texte";
        informations[1] = texte;

        actionEffectuee = true;
    }

    public void envoyerCercle(boolean plein, int x, int y, int rayon, int couleur)
    {
        informations[0] = "cercle";
        informations[1] = plein + ";" + x + ";" + y + ";" + rayon + ";" + couleur;
		
        actionEffectuee = true;
    }

    public void envoyerCarre(boolean plein, int x, int y, int cote, int couleur)
    {
        informations[0] = "carre";
        informations[1] = plein + ";" + x + ";" + y + ";" + cote + ";" + couleur;
		
        actionEffectuee = true;
    }

    public void envoyerTriangle(boolean plein, int x, int y, int rayon, int couleur)
    {
        informations[0] = "triangle";
        informations[1] = plein + ";" + x + ";" + y + ";" + rayon + ";" + couleur;
		
        actionEffectuee = true;
    }

    public void deconnecter() { connecte = false; }
}

class RecoiUDP extends Thread
{
	boolean connecte;
	
	public RecoiUDP()
	{
		connecte = true;
	}
	
	public void run()
	{	
		try {
			InetAddress mcast = InetAddress.getByName("225.1.1.64");
			MulticastSocket ms = new MulticastSocket(2010);
			
			ms.joinGroup(mcast);
			
			while (connecte)
			{
				DatagramPacket msg = new DatagramPacket(new byte[1024], 1024);
				
				ms.receive(msg);
				String messageComplet = new String(msg.getData()).trim();
				
				String type    = messageComplet.split("\\|")[0];
				String donnees = messageComplet.split("\\|")[1];
				
				
				/*ms.receive(msg);
				String type = new String(msg.getData()).substring(0, msg.getLength());
				
				ms.receive(msg);
				String donnees = new String(msg.getData()).substring(0, msg.getLength());*/
				
				System.out.println(type + " : " + donnees);
			}
		} catch (IOException ioe) { ioe.printStackTrace(); }
	}
	
	public void deconnecter()
	{
		connecte = false;
	}
}