package com.example;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.Shuffle.IterationAlgorithm.*;

public class Shuffle
{
	public static final IterationAlgorithm algo = STREAM;

	public static void main(String[] args)
	{
		// Création d'une liste à partir du tableau d'arguments
		List<String> argList = Arrays.asList(args);
		// Mélange de la liste
		Collections.shuffle(argList);

		// On utilise une méthode différente pour parcourir la liste en fonction de l'algorithme choisi
		switch (algo)
		{
			// Méthode 1 : stream 
			// (depuis Java 1.8)
			case STREAM:
				argList.stream().
					forEach(e -> System.out.print(e + " "));
			break;

			// Méthode 2 : boucle 'for' implicite
			// (depuis Java 1.5)
			case ENHANCED_FOR:
				for(String s : argList)
				{
					System.out.print(s + " ");
				}
			break;
				
			// Méthode 3 : itérateur
			// (depuis Java 1.2)
			case ITERATOR:
				Iterator<String> argIterator = argList.iterator();
				while (argIterator.hasNext())
				{
					String s = argIterator.next();
					System.out.print(s + " ");
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
