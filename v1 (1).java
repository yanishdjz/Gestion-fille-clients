package monpackage;
import java.util.Arrays;
public class v1 {

	

	
	    
	    // Fonction pour rechercher un caissier libre
	    public static int rechercheCaissierLibre(int[] caissier, int a) {
	        int index = Arrays.binarySearch(caissier, a);
	        if (index >= 0) {
	            // Si un caissier est libre à l'instant a
	            return index;
	        } else {
	            // Si aucun caissier n'est libre à l'instant a
	            int insertionPoint = -index - 1;
	            if (insertionPoint < caissier.length) {
	                return insertionPoint;
	            } else {
	                // Si aucun caissier ne sera libre avant la fin de la journée
	                return -1;
	            }
	        }
	    }
	    
	    // Procédure pour traiter l'attente des clients
	    public static void traiteAttente(int[] arrivee, int[] service, int[] sortie, int[] numeroCaissier, int v) {
	        int n = arrivee.length;
	        int[] caissier = new int[v];
	        Arrays.fill(caissier, 0); // Initialisation des caissiers à l'instant 0
	        
	        for (int i = 0; i < n; i++) {
	            int caissierLibre = rechercheCaissierLibre(caissier, arrivee[i]);
	            
	            if (caissierLibre == -1) {
	                // Aucun caissier ne sera libre avant la fin de la journée
	                sortie[i] = -1;
	                numeroCaissier[i] = -1;
	            } else {
	                sortie[i] = Math.max(arrivee[i], caissier[caissierLibre]) + service[i];
	                numeroCaissier[i] = caissierLibre;
	                caissier[caissierLibre] = sortie[i];
	            }
	        }
	    }
	    
	    // Programme principal
	    public static void main(String[] args) {
	        int[] arrivee = {3, 4, 7, 11, 13, 15};
	        int[] service = {2, 6, 3, 5, 1, 10};
	        int n = arrivee.length;
	        int[] sortie = new int[n];
	        int[] numeroCaissier = new int[n];
	        int v = 2;
	        
	        traiteAttente(arrivee, service, sortie, numeroCaissier, v);
	        
	        for (int i = 0; i < n; i++) {
	            if (sortie[i] == -1) {
	                System.out.println("Le client numéro " + (i + 1) + " doit revenir demain.");
	            } else {
	                int attente = sortie[i] - arrivee[i] - service[i];
	                System.out.println("Le client numéro " + (i + 1) + " arrive à l'instant " + arrivee[i] + ". Il attend " + attente + ".");
	                System.out.println("Il ressort à l'instant " + sortie[i] + ", traité par le caissier numéro " + (numeroCaissier[i] + 1) + ".");
	            }
	        }
	    }
	}
