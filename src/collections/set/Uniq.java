package com.example;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.Uniq.IterationAlgorithm.*;

public class Uniq
{
	public static final IterationAlgorithm algo = STREAM;

	public static void main(String[] args)
	{
		// Création de l'ensemble à partir du tableau d'arguments
		Set<String> argSet = new HashSet<>();
		for(String s : args) {
			argSet.add(s);
		}

		// On utilise une méthode différente pour parcourir la liste en fonction de l'algorithme choisi
		switch (algo)
		{
			// Méthode 1 : stream 
			// (depuis Java 1.8)
			case STREAM:
				argSet.stream().
					map(s -> s + " ").
					forEach(e -> System.out.print(e));
			break;

			// Méthode 2 : boucle 'for' implicite
			// (depuis Java 1.5)
			case ENHANCED_FOR:
				for(String s : argSet)
				{
					s = s + " ";
					System.out.print(s);
				}
			break;
				
			// Méthode 3 : itérateur
			// (depuis Java 1.2)
			case ITERATOR:
				Iterator<String> argIterator = argSet.iterator();
				while (argIterator.hasNext())
				{
					String s = argIterator.next();
					s = s + " ";
					System.out.print(s);
				}
			break;
		}

		// On termine par un retour à la ligne
		System.out.println();
	}

	enum IterationAlgorithm
	{
		STREAM, ENHANCED_FOR, ITERATOR;
	}
}
