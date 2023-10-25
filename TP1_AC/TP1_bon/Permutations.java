import java.util.ArrayList;
import java.util.List;

public class Permutations {
	public static void permutation(String target, String original,boolean afficher){
	    int i;
	    String target1, original1;
	    if (original.length() == 0 && afficher){
	        System.out.println(target);
	    }
		else {
			i = 0;
			while (i < original.length()){
			    target1 = target + original.substring(i,i+1);
			    original1 = original.substring(0,i) + original.substring(i+1,original.length());
			    permutation(target1,original1,afficher);
			    i = i + 1;
			}
		} 
	}
	public static void main(String[] args) {
		String x="ABCD";
		String alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int o=4;
		String y=alphabet.substring(0, o);
		permutation(x,y,false);
		
		int taille_init = 1;
		int taille_fin  = 10;
		int nbrMesures = 10;
		String t ;
		int taille_incr = (taille_fin-taille_init)/(nbrMesures-1);
	
		List<Integer> tab_tailles = new ArrayList<Integer>();
		List<Integer> tab_temps = new ArrayList<Integer>();
		for(int n = taille_init; n<taille_fin; n=n+taille_incr){			  
			tab_tailles.add(n); //on sauvegarde la taille
			t = "";

			//A FAIRE : initialisation de t
			for(int k=0;k<n+1;k++){ //initialisation aléatoire
				t=t+"A";
			}
			long date1 = System.currentTimeMillis(); //on lance le chrono
			permutation(x,t,false); //on trie le tableau 
			long date2 = System.currentTimeMillis(); //on arrête le chrono
			tab_temps.add((int)(date2 - date1)); //on sauvegarde le temps
			System.out.println("Temps de calcul pour n="+n+" : "+ tab_temps.get(tab_temps.size()-1)+" millisecondes.");
		}
		
		// Affichage des mesures effectuées
		Graph g = new Graph(tab_tailles,tab_temps);
		g.display();
	}

}
