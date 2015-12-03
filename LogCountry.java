
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
		for(i=1;i<=100&&log[i]!=null;i++)
		{
			if(!(log[i].equals(temp)))
				System.out.println(log[i]);
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
		rafl.seek(0);
		for(i=1;i<=100;i++)
		{
			log[i] = "";
		}
		int key = Integer.parseInt(rafc.readLine());
		temp = Integer.toString(key+1)+'\n';
		bytes = temp.getBytes();
		rafl.write(bytes);
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
