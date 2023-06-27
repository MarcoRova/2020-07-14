package it.polito.tdp.PremierLeague.model;

import java.util.LinkedList;
import java.util.List;

public class TestModel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Model testModel = new Model();
		
		List<InfoTeam> classifica = testModel.calcolaClassifica();
		
		for(InfoTeam i : classifica)
			System.out.println(i);
	}
}
