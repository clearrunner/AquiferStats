package com.aquifer.stats;

import org.jsoup.nodes.Document;

public class DataObject {
	private static DataObject instance;
	public String currentLevel;
	public String pastLevel;
	public String stage;
	public String date;
	public Document doc;
	public String strStageLink;
	public double[] levelArray;

	public static void initInstance()
	{
		if (instance == null)
		{
			// Create the instance
			instance = new DataObject();
		}
	}

	public static DataObject getInstance()
	{
		// Return the instance
		return instance;
	}

	private DataObject()
	{
		// Constructor hidden because this is a singleton
	}
}
