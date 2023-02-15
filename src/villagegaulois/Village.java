package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	
	private Marche marche;
	
		
	
	/*Déclaration de le classe inter Marche*/
	private static class Marche{
		private Etal[] etals;
		private int nbetals;
		
		private Marche(int nbetals) {
			this.nbetals = nbetals;
			etals = new Etal[nbetals];
			installerEtal(etals);
			
		}
		
		private void installerEtal(Etal[] etals) {
			for(int k=0; k< etals.length;k++) {
				etals[k] = new Etal();
			}
			
		}
		
		void utiliserEtal(int indiceEtal,Gaulois vendeur,String produit, int nbProduit) {
			if(indiceEtal < nbetals) {
				etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
			}
			
			
		}
		
		int trouverEtalLibre() {
			for(int k=0;k<etals.length;k++) {
				/*Si l'étale est libre*/
				if(!(etals[k].isEtalOccupe())) {
					return k;
				}
			}
			/**/
			return -1;
		}
		
		
		/**/
		private int nbVendeurAvecProduitChercher(String produit) {
			int trouve=0;
			for(int k=0;k<etals.length;k++) {
				if(etals[k].contientProduit(produit)) {
					trouve++;
				}
			}
			return trouve;
		}
		
		
		Etal[] trouverVendeur(String produit) {
			
			int trouve = nbVendeurAvecProduitChercher(produit);	
			/*Tableau*/
			Etal[] etalsTrouve = new Etal[trouve];
			int indiceEtalsTrouve = 0;
			
			/**/
			for(int k=0;k<etalsTrouve.length;k++) {
				if(etals[k].contientProduit(produit)) {
					etalsTrouve[indiceEtalsTrouve] = etals[k];
				}
			}
			/**/
			return etalsTrouve;
		
			
		}
	}
	
	public Village(String nom, int nbVillageoisMaximum,int nbetals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		this.marche = new Marche(nbetals);
		
		
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
}