import java.io.*;
import java.util.Scanner;
import java.io.RandomAccessFile;
import java.io.IOException;

public class Log {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Which table do you want to work on?\nEnter '1' for City Population\nEnter '2' for Country Population");
		int opt = in.nextInt();
		
		if(opt==1)
		{
			LogCity r=new LogCity();
			r.read();
			System.out.println("Which do you want to do in the City Table?\nEnter '1' for Checking if the database is updated with a new Log File. Please make sure that the Log File is placed in the same location as the City.DB File\nEnter '2' for Increasing the population of all the cities by 2% will considered as '2' by default");
			opt = in.nextInt();
			if(opt==1)
			{
				r.updateLog();
			}
			else
			{
				r.updatePop();
			}
			r.write();
		}
		
		else if (opt==2)
		{
			LogCountry r=new LogCountry();
			r.read();
			System.out.println("Which do you want to do in the Country Table?\nEnter '1' for Checking if the database is updated with a new Log File. Please make sure that the Log File is placed in the same location as the Country.DB File\nEnter '2' for Increasing the population of all the countries by 2% will considered as '2' by default");
			opt = in.nextInt();
			if(opt==1)
			{
				r.updateLog();
			}
			else
			{
				r.updatePop();
			}
			r.write();		
		}
		
		else
		{
			System.out.println(opt+" is not one of the options.");
		}
		/*
		r.printDB();
		*/
	}

}

