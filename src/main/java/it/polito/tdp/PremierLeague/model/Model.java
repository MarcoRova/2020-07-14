package it.polito.tdp.PremierLeague.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.PremierLeague.db.PremierLeagueDAO;

public class Model {
	
	private PremierLeagueDAO dao;
	private Graph<Team, DefaultWeightedEdge> grafo;

	public Model() {
		super();
		this.dao = new PremierLeagueDAO();
	}
	
	public void creaGrafo() {
		
		this.grafo = new SimpleDirectedWeightedGraph<Team, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(this.grafo, listAllTeams());
		
		
		List<InfoTeam> classifica = calcolaClassifica();
		
		for(InfoTeam i : classifica) {
			
			for(InfoTeam i2 : classifica) {
				
				if(this.grafo.containsVertex(i.getTeam()) && this.grafo.containsVertex(i2.getTeam()) && !i.getTeam().equals(i2.getTeam())){
					
					int diff = i.getPunti() - i2.getPunti();
					
					if(diff > 0) {
						Graphs.addEdgeWithVertices(this.grafo, i.getTeam(), i2.getTeam(), diff);
					}
					else if(diff < 0) {
						Graphs.addEdgeWithVertices(this.grafo, i2.getTeam(), i.getTeam(), -diff);
					}
					
				}
			}
			
		}
		
	}
	
	
	public List<InfoTeam> calcolaClassifica(){

		List<Team> team = listAllTeams();
		List<InfoTeam> classifica = new LinkedList<>();
		
		for(Team t : team) {
			int punti = getPunti(t);
			classifica.add(new InfoTeam(t, punti));
		}
		
		Collections.sort(classifica);
		
		return classifica;
		
	}
	
	public List<Match> getPartite(Team team) {
		List<Match> match = listAllMatches();
		
		List<Match> partite = new LinkedList<>();
		
		for(Match m : match) {
			if(m.teamHomeID == team.getTeamID() || m.teamAwayID == team.getTeamID()) {
				partite.add(m);
			}
		}
		return partite;
	}
	
	public int getPunti(Team team) {
		
		int punti = 0;
		
		List<Match> partite = getPartite(team);
		
		for(Match m : partite) {
			if(m.teamHomeID == team.teamID && m.resultOfTeamHome == 1) {
				punti += 3;
			}
			else if(m.teamAwayID == team.teamID && m.resultOfTeamHome == -1) {
				punti += 3;
			}
			else if(m.teamHomeID == team.teamID && m.resultOfTeamHome == 0 || m.teamAwayID == team.teamID && m.resultOfTeamHome == 0) {
				punti +=1;
			}
		}
		return punti;
	}		

	public String infoGrafo() {
		return "Grafo creato con successo!\nNumero di vertici: " + this.grafo.vertexSet().size() + " Numero archi: " + this.grafo.edgeSet().size();
	}
	
	public List<Team> listAllTeams(){
		return this.dao.listAllTeams();
	}
	
	public List<Match> listAllMatches(){
		return this.dao.listAllMatches();
	}
	
	public Graph<Team, DefaultWeightedEdge> getGrafo(){
		return this.grafo;
	}
}
 