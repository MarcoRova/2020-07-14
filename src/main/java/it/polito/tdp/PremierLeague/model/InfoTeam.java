package it.polito.tdp.PremierLeague.model;

public class InfoTeam implements Comparable<InfoTeam>{
	
	private Team team;
	private int punti;
	
	public InfoTeam(Team team, int punti) {
		super();
		this.team = team;
		this.punti = punti;
	}

	public Team getTeam() {
		return team;
	}
	
	public int getPunti() {
		return punti;
	}

	public void setPunti(int punti) {
		this.punti = punti;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	@Override
	public int compareTo(InfoTeam other) {
		return this.punti-other.getPunti();
	}

	@Override
	public String toString() {
		return ""+team + " (" + punti+")";
	}	
}
