package histoire;

import villagegaulois.*; 

import personnages.*;

public class ScenarioCasDegrade {
	
	public static void main(String[] args) {
		Etal etal = new Etal();
		Gaulois g = new Gaulois("James", 15);
		etal.occuperEtal(g, "radie", 0);
		try {
			System.out.println(etal.acheterProduit(0, g));
		}catch (IllegalArgumentException e) {
			System.out.println("Argument illégal saisi");
		}
		catch (IllegalStateException e) {
			System.out.println("étal non occupé!");
		}
		System.out.println("Fin du test");
		}


}
