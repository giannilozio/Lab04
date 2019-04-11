package it.polito.tdp.lab04.model;

import java.util.*;

import it.polito.tdp.lab04.DAO.*;

public class Model {									// NON FACCIO LE OPERAZIONI SUL DB DIRRETTAMENTE QUI, LO FACCIO SUL DAO
														// PER OGNI TABELLA HO IL MIO DAO -> DAOSTUDENTE E DAOCORSO

		public Collection<String> getNomeCorsi() {
			
			
				CorsoDAO dao = new CorsoDAO();
				List<String> nomi=new LinkedList<String>();
				for(Corso c : dao.getTuttiICorsi()) {
					nomi.add(c.getNome());
				}
				return nomi;
			
			
		}

		public Corso getCorso(String nomec) {
			
			CorsoDAO dao = new CorsoDAO();
			Corso corso=null;
			for(Corso c:dao.getTuttiICorsi()) {
				if(c.getNome().equals(nomec))
					corso=c;
			}
			return corso;
		}

		public String getStudenti(Corso corso) {
			
			String temp="";
			CorsoDAO dao = new CorsoDAO();
			//List<String> studenti=new LinkedList<String>();
			
			for( Studente s : dao.getStudentiIscrittiAlCorso(corso)) {
				temp+=s.getMatricola()+" "+s.getNome()+" "+s.getCognome()+" "+s.getCDS()+"\n";
			}
			
			return temp;
		}

		public boolean getEIscritto(int matr, Corso corso) {
			String codtemp= corso.getCodins();
			StudenteDAO dao=new StudenteDAO();					// DEVO RESTITUIRE UNA LIST DI CORSI 
			List<Corso> corsi=dao.getCorsi(matr);
			boolean trovato=false;
			for(Corso c:corsi)
				if(c.getCodins().equals(codtemp))
					trovato=true;
			return trovato;
		}

		public Studente checkStudenti(int matricola) {
			CorsoDAO dao = new CorsoDAO();
			return dao.checkS(matricola);
			
		}

		public String getCorsi(int matricola) {
			CorsoDAO dao = new CorsoDAO();
			String temp="";
			
			for( Corso c : dao.getCorsi(matricola)) {
				temp+=c.getCodins()+" "+c.getCrediti()+" "+c.getNome()+" "+c.getCrediti()+"\n";
			}
			return temp;
		}

		public void iscriviStudente(int matricola, Corso corso) {
			CorsoDAO dao = new CorsoDAO();
			Studente s =dao.checkS(matricola);
			if(dao.iscriviStudenteACorso(s, corso))
				System.out.println("Inserito");
			else {
				System.out.println("Non fa");
			}
			
		}
		
		
		

	}



