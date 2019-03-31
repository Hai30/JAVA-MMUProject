package com.hit.memoryunits;
import java.lang.Long;
import java.util.HashMap;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class HardDisk {
	
	public static final String DEFAULT_FILE_NAME = "hd.txt";
	private static int _SIZE = 1000;
	private static HardDisk instance = new HardDisk();
	HashMap<Long, Page<byte[]>> Data;


	
	private HardDisk()
	{
		Data = new HashMap<>(_SIZE);
		for (Long i = 0L; i < _SIZE; i++)
		{
			Data.put(i, new Page<byte[]>(i, i.toString().getBytes()));
		}
		try {
			writeDataToHd();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static HardDisk getInstance() 
	{
		if (instance == null)
			instance = new HardDisk();
		return instance;
	}
	
	 public Page<byte[]> pageFault(Long pageId)throws FileNotFoundException, IOException
	 {
		 readDataFromHd();
			Page<byte[]> pageToReturn = Data.get(pageId);
			
			return pageToReturn;
	}
	 
	public Page<byte[]> pageReplacement(Page<byte[]> moveToHdPage, Long moveToRamId) throws FileNotFoundException, IOException
	{
		readDataFromHd();
		Data.put(moveToHdPage.getPageId(), moveToHdPage);
		Page<byte[]> pageToReturn = Data.get(moveToRamId);
		writeDataToHd();
		return pageToReturn;
	}
	
	
	private void writeDataToHd() throws FileNotFoundException, IOException
	{
		FileOutputStream hdFile = null;
		ObjectOutputStream writeData = null;
		try
		{
			hdFile = new FileOutputStream(DEFAULT_FILE_NAME);
			writeData = new ObjectOutputStream(hdFile);
			writeData.writeObject(Data);
			writeData.flush();
		}
		catch (FileNotFoundException exception)
		{
			
		}
		finally
		{
			writeData.close();
			hdFile.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	private void readDataFromHd() throws FileNotFoundException, IOException
	{
		FileInputStream fileInput = new FileInputStream(DEFAULT_FILE_NAME);
		ObjectInputStream hdInputFile = new ObjectInputStream(fileInput);
		try 
		{
			Data = (HashMap<Long, Page<byte[]>>) hdInputFile.readObject();
		} 
		catch (ClassNotFoundException exception) 
		{
		}
		finally 
		{
			fileInput.close();
			hdInputFile.close();
		}
	}
	
}
