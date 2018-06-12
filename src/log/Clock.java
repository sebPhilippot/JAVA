package com.example;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


public class Clock
{
	private static final Logger logger = Logger.getLogger("com.example.Clock");

	public static void main(String[] args) throws IOException
	{
		for(int i = 0; i < 3600; i++)
		{
			logger.fine("Début de l'itération " + i);
			System.out.println(ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME));
			
			try
			{
				Thread.sleep(1000);
			}
			catch (InterruptedException e)
			{
				logger.severe("Le programme a été interrompu alors qu'il était suspendu. Fin de l'exécution");
				return;
			}
		}
	}
}
