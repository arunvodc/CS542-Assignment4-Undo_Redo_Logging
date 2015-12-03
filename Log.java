import java.io.*;
import java.util.Scanner;
import java.io.RandomAccessFile;
import java.io.IOException;

public class Log {

	public void begin()
	{
		Scanner in = new Scanner(System.in);
		System.out.println("Which table do you want to work on?\nEnter '1' for City Population\nEnter '2' for Country Population");
		int opt = in.nextInt();
		
		if(opt==1)
		{
			LogCity r=new LogCity();
			r.read();
			System.out.println("Which do you want to do in the City Table?\nEnter '1' for Checking if the database is updated with a new Log File. Please make sure that the Log File is placed in the same location as the City.DB File\nEnter '2' for Increasing the population of all the cities by 2%\nEnter '3' to display the contents of the database");
			opt = in.nextInt();
			if(opt==1)
			{
				r.updateLog();
				r.write();
			}
			else if(opt==2)
			{
				r.updatePop();
				r.write();
			}
			else if(opt==3)
			{
				r.printDB();
			}
			else
			{
				System.out.println(opt+" is not one of the options.");
			}
		}
		
		else if (opt==2)
		{
			LogCountry r=new LogCountry();
			r.read();
			System.out.println("Which do you want to do in the Country Table?\nEnter '1' for Checking if the database is updated with a new Log File. Please make sure that the Log File is placed in the same location as the Country.DB File\nEnter '2' for Increasing the population of all the cities by 2%\nEnter '3' to display the contents of the database");
			opt = in.nextInt();
			if(opt==1)
			{
				r.updateLog();
				r.write();
			}
			else if(opt==2)
			{
				r.updatePop();
				r.write();
			}
			else if(opt==3)
			{
				r.printDB();
			}
			else
			{
				System.out.println(opt+" is not one of the options.");
			}
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

class LogCity
{
	int i,j;
	public byte[] bytes   = new byte[1024];
	public String city[]    = new String[501];
	public String log[]    = new String[501];
	
	public void read()
	{		
		try
		{
		System.out.println("****Starting to Read****");
		RandomAccessFile rafc = new RandomAccessFile("City.db", "rw");
		rafc.readLine();
		for(i=1;i<=100;i++)
		{
			city[i] = rafc.readLine();
		}
		RandomAccessFile rafl = new RandomAccessFile("LogCity.txt", "rw");
		rafl.readLine();
		for(i=1;i<=100;i++)
		{
			log[i] = rafl.readLine();
		}
		System.out.println("****Read Completed****");
		System.out.println();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public void write()
	{		
		try
		{
			String str;
			System.out.println("****Starting to Write****");
			RandomAccessFile rafc = new RandomAccessFile("City.db", "rw");
			rafc.readLine();
			for(i=1;i<=50;i++)//&&city[i]!=null
			{
				if(city[i]==null)
					continue;
				str = city[i]+'\n';
				bytes = str.getBytes();
				rafc.write(bytes);
			}
			RandomAccessFile rafl = new RandomAccessFile("LogCity.txt", "rw");
			
			rafl.readLine();
			for(i=1;i<=50;i++)
			{
				if(log[i]==null)
					continue;
				str = log[i]+'\n';
				bytes = str.getBytes();
				rafl.write(bytes);
			}
			System.out.println("****Write Completed****");
			System.out.println();
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public void printDB()
	{
		try
		{
		String temp="";
		System.out.println("****Printing City DB****");
		for(i=1;i<=100&&city[i]!=null;i++)
		{
			if(!(city[i].equals(temp)))
				System.out.println(city[i]);
		}
		System.out.println("****Print Completed****");
		System.out.println();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void updatePop()
	{
		try
		{
			String[] parts;
			int log_counter=0;
			System.out.println("****Updating Population****");
			String temp="";
			RandomAccessFile rafc = new RandomAccessFile("City.db", "rw");
			RandomAccessFile rafl = new RandomAccessFile("LogCity.txt", "rw");
			for(i=1;i<=100;i++)
			{
				log[i] = "";
			}
			int key = Integer.parseInt(rafc.readLine());
			temp = Integer.toString(key+1)+'\n';
			bytes = temp.getBytes();
			rafl.seek(0);
			rafl.write(bytes);
			rafc.seek(0);
			rafc.write(bytes);
			for(i=1;i<=500&&city[i]!=null;i++)
			{
			log_counter++;
			parts=city[i].split(",");
			log[i]=parts[0]+",old_population,"+parts[4];
			city[i]="";
			for(int j=0;j<4;j++)
			{
				city[i]+=parts[j]+",";
			}
			Double d = Double.parseDouble(parts[4])*1.02;
			int pop = Integer.valueOf(d.intValue());
			city[i]+=Integer.toString(pop);
			// write update statement to log file
			log[i] += ",new_population,"+Integer.toString(pop);
			// writing to log file over
		}
		System.out.println("****Population Increased by 2%****");
		System.out.println();
	}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	}
	
	public void updateLog()
	{
		try
		{
			System.out.println("Checking for Database updation");
			RandomAccessFile rafc = new RandomAccessFile("City.db", "rw");
			RandomAccessFile rafl = new RandomAccessFile("LogCity.txt", "rw");
			int ckey = Integer.parseInt(rafc.readLine());
			int lkey = Integer.parseInt(rafl.readLine());
			if (ckey==lkey)
			{
				System.out.println("****Database remains unchanged****");
				return;
			}
			String partl[],partc[],temp;
			String NL = "null";
			for(i=1;i<=50&&log[i]!=null;i++)
			{
				partl=log[i].split(",");
				for(j=1;j<=50&&city[j]!=null;j++)
				{
					
					partc = city[j].split(",");
					if(partl[0].equals(partc[0]))
					{
						city[j]="";
						for(int k=0;k<4;k++)
						{
							city[j]+=partc[k]+",";
						}
						city[j]+=partl[4];
					}
				}
								
			}
			rafl.seek(0);
			int key = Integer.parseInt(rafl.readLine());
			rafc.seek(0);
			temp = Integer.toString(key);
			bytes = temp.getBytes();
			rafc.write(bytes);
			System.out.println("****Updated Database based on the Log File****");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
}
}

class LogCountry
{
	int i,j;
	public byte[] bytes   = new byte[1024];
	public String city[]    = new String[501];
	public String log[]    = new String[501];
	
	public void read()
	{		
		try
		{
		System.out.println("****Starting to Read****");
		RandomAccessFile rafc = new RandomAccessFile("Country.db", "rw");
		rafc.readLine();
		for(i=1;i<=100;i++)
		{
			city[i] = rafc.readLine();
		}
		RandomAccessFile rafl = new RandomAccessFile("LogCountry.txt", "rw");
		rafl.readLine();
		for(i=1;i<=100;i++)
		{
			log[i] = rafl.readLine();
		}
		System.out.println("****Read Completed****");
		System.out.println();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public void write()
	{		
		try
		{
			String str;
			System.out.println("****Starting to Write****");
			RandomAccessFile rafc = new RandomAccessFile("Country.db", "rw");
			rafc.readLine();
			for(i=1;i<=50;i++)//&&city[i]!=null
			{
				if(city[i]==null)
					continue;
				str = city[i]+'\n';
				bytes = str.getBytes();
				rafc.write(bytes);
			}
			RandomAccessFile rafl = new RandomAccessFile("LogCountry.txt", "rw");
			
			rafl.readLine();
			for(i=1;i<=50;i++)
			{
				if(log[i]==null)
					continue;
				str = log[i]+'\n';
				bytes = str.getBytes();
				rafl.write(bytes);
			}
			System.out.println("****Write Completed****");
			System.out.println();
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public void printDB()
	{
		try
		{
		String temp="";
		System.out.println("****Printing Country DB****");
		for(i=1;i<=100&&city[i]!=null;i++)
		{
			if(!(city[i].equals(temp)))
				System.out.println(city[i]);
		}
		System.out.println("****Print Completed****");
		System.out.println();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void updatePop()
	{
		try
		{
		String[] parts;
		int log_counter=0;
		System.out.println("****Updating Population****");
		String temp="";
		RandomAccessFile rafc = new RandomAccessFile("Country.db", "rw");
		RandomAccessFile rafl = new RandomAccessFile("LogCountry.txt", "rw");
		for(i=1;i<=100;i++)
		{
			log[i] = "";
		}
		int key = Integer.parseInt(rafc.readLine());
		temp = Integer.toString(key+1)+'\n';
		bytes = temp.getBytes();
		rafl.seek(0);
		rafl.write(bytes);
		rafc.seek(0);
		rafc.write(bytes);
		for(i=1;i<=500&&city[i]!=null;i++)
		{
			log_counter++;
			parts=city[i].split(",");
			log[i]=parts[0]+",old_population,"+parts[4];
			city[i]="";
			for(int j=0;j<4;j++)
			{
				city[i]+=parts[j]+",";
			}
			Double d = Double.parseDouble(parts[4])*1.02;
			int pop = Integer.valueOf(d.intValue());
			city[i]+=Integer.toString(pop);
			// write update statement to log file
			log[i] += ",new_population,"+Integer.toString(pop);
			// writing to log file over
		}
		System.out.println("****Population Increased by 2%****");
		System.out.println();
	}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	}
	
	public void updateLog()
	{
		try
		{
			System.out.println("Checking for Database updation");
			RandomAccessFile rafc = new RandomAccessFile("Country.db", "rw");
			RandomAccessFile rafl = new RandomAccessFile("LogCountry.txt", "rw");
			int ckey = Integer.parseInt(rafc.readLine());
			int lkey = Integer.parseInt(rafl.readLine());
			if (ckey==lkey)
			{
				System.out.println("****Database remains unchanged****");
				return;
			}
			String partl[],partc[],temp;
			String NL = "null";
			for(i=1;i<=50&&log[i]!=null;i++)
			{
				partl=log[i].split(",");
				for(j=1;j<=50&&city[j]!=null;j++)
				{
					partc = city[j].split(",");
					if(partl[0].equals(partc[0]))
					{
						city[j]="";
						for(int k=0;k<4;k++)
						{
							city[j]+=partc[k]+",";
						}
						city[j]+=partl[4];
					}
				}
								
			}
			rafl.seek(0);
			int key = Integer.parseInt(rafl.readLine());
			rafc.seek(0);
			temp = Integer.toString(key);
			bytes = temp.getBytes();
			rafc.write(bytes);
			System.out.println("****Updated Database based on the Log File****");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
}
}
