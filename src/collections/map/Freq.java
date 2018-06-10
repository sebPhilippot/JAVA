package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static com.example.Freq.IterationAlgorithm.*;

public class Freq
{
	public static final IterationAlgorithm algo = STREAM;

	public static void main(String[] args)
	{
		// Création d'une HashMap pour stocker les mots et compter leur fréquence
		Map<String, Integer> wordFrequencyMap = new HashMap<>();
		
		// Comptage des mots
		for (String s : args)
		{
			Integer frequency = wordFrequencyMap.get(s);
			if (frequency == null)
			{
				frequency = 1;
			}
			else
			{
				frequency++;
			}
			wordFrequencyMap.put(s, frequency);
		}
		
		// Classement par nombre d'occurences et affichage
		// On utilise une méthode différente pour parcourir la liste en fonction de l'algorithme choisi
		List<Entry<String, Integer>> entryList;
		switch (algo)
		{
			// Méthode 1 : stream 
			// (depuis Java 1.8)
			case STREAM:
				wordFrequencyMap.entrySet().stream().
					sorted(Map.Entry.comparingByValue()).
					forEach(e -> System.out.println(e));
			break;

			// Méthode 2 : boucle 'for' implicite
			// (depuis Java 1.5)
			case ENHANCED_FOR:
				entryList = new ArrayList<>(wordFrequencyMap.entrySet());
				entryList.sort(Map.Entry.comparingByValue());

				for(Entry e : entryList)
				{
					System.out.println(e);
				}
			break;
				
			// Méthode 3 : itérateur
			// (depuis Java 1.2)
			case ITERATOR:
				entryList = new ArrayList<>(wordFrequencyMap.entrySet());
				entryList.sort(Map.Entry.comparingByValue());

				Iterator<Entry<String, Integer>> entryIterator = entryList.iterator();
				while (entryIterator.hasNext())
				{
					Entry e = entryIterator.next();
					System.out.println(e);
				}
			break;
		}
	}

	enum IterationAlgorithm
	{
		STREAM, ENHANCED_FOR, ITERATOR;
	}
}
