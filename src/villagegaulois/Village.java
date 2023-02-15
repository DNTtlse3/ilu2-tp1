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
		/**/
		private void installerEtal(Etal[] etals) {
			for(int k=0; k< etals.length;k++) {
				etals[k] = new Etal();
			}
			
		}
		/**/
		void utiliserEtal(int indiceEtal,Gaulois vendeur,String produit, int nbProduit) {
			if(indiceEtal < nbetals) {
				etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
			}	
		}
		/**/
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
			for(int k=0;k<nbetals;k++) {
				Etal etalConsulter = etals[k];
				if((etalConsulter.isEtalOccupe())&& etalConsulter.contientProduit(produit) ) {
					trouve++;
				}
			}
			return trouve;
		}
		/**/
		Etal[] trouverEtals(String produit) {
			
			int trouve = nbVendeurAvecProduitChercher(produit);	
			/*Tableau*/
			Etal[] etalsTrouve = new Etal[trouve];
			
			int indiceEtalsTrouve = 0;
			
			/**/
			for(int k=0;k<nbetals;k++) {
				
				Etal etalCourant = etals[k];
				
				if( (etalCourant.isEtalOccupe()) && etalCourant.contientProduit(produit)) {
					
					etalsTrouve[indiceEtalsTrouve] = etalCourant;
					
					indiceEtalsTrouve++;
				}
			}
			/**/
			return etalsTrouve;
		}
		/**/
		Etal trouverVendeur(Gaulois gaulois) {
			for(int k = 0; k<etals.length;k++) {
				if(gaulois == etals[k].getVendeur()) {
					return etals[k];
				}
			}
			return null;
		}
		/**/
		String afficherMarche() {
			StringBuilder chaine = new StringBuilder();
			int nbEtalsNonOccupe = 0;
			
			for(int k = 0; k< etals.length;k++) {
				Etal etal_courant = etals[k];
				if(etal_courant.isEtalOccupe()) {
					chaine.append(etal_courant.afficherEtal()+"\n");
				}
				else {
					nbEtalsNonOccupe++;
				}
			}
			chaine.append("Il reste "+nbEtalsNonOccupe+" étals non utilisés dans le marché.\n");
			return chaine.toString();
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

	public String afficherVillageois() throws VillageSansChefException{
		StringBuilder chaine = new StringBuilder();
		if(chef == null) {
			throw new VillageSansChefException("Le village ne peut exister sans Chef.");
		}
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
	
	/*Méthode*/
	
	public String installerVendeur(Gaulois gaulois, String produit, int nbProduit) {
		
		int placeLibre = marche.trouverEtalLibre();
		StringBuilder chaine = new StringBuilder();
		
		if((0<=placeLibre) && (placeLibre < marche.nbetals)) {
			marche.utiliserEtal(placeLibre, gaulois, produit, nbProduit);
			chaine.append(gaulois.getNom()+" cherche un endroit pour vendre "
					+nbProduit+" "+produit+"\n");
			chaine.append("Le vendeur "+gaulois.getNom()+" vend des "+produit
					+" à l'étal n°"+placeLibre+"\n");
		}
		else {
			chaine.append(gaulois.getNom()+" désolé, les étals sont saturés\n");
		}
		
		return chaine.toString();
		
	}
	public Etal rechercherEtal(Gaulois gaulois) {
		return marche.trouverVendeur(gaulois);
	}
	
	/**/
	public String partirVendeur(Gaulois gaulois) {
		
		StringBuilder chaine = new StringBuilder();
		
		Etal etal = marche.trouverVendeur(gaulois);
		
		if(etal.isEtalOccupe()) {
			
			return etal.libererEtal();
		}
		else {
			chaine.append("Le vendeur "+ gaulois.getNom()+" introuvable.");
		}
		return chaine.toString();
	}
	
	
	public String afficherMarche() {
		return marche.afficherMarche();
	}
	
	public String rechercherVendeursProduit(String produit) {
		
		 StringBuilder chaine = new StringBuilder();
		 
		 Etal[] etalsAvecProduit = marche.trouverEtals(produit);
		 
		 chaine.append("Les vendeurs qui proposent des fleurs sont :\n");
		 
		 for(int k = 0; k < etalsAvecProduit.length;k++) {
			 
			 chaine.append("-"+etalsAvecProduit[k].getVendeur().getNom()+"\n");
		 }
		 /**/
		 return chaine.toString();
	 }
	 	 
}