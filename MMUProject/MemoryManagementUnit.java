package com.hit.memoryunits;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.hit.algo.IAlgoCache;

public class MemoryManagementUnit {
	
	private IAlgoCache<Long,Long> m_algo ;
	private RAM  m_RAM ;

	public MemoryManagementUnit(int ramCapacity , IAlgoCache<Long,Long> algo)
	{
		this.m_algo = algo;
		this.m_RAM = new RAM (ramCapacity);
	}
	
	public IAlgoCache<Long,Long> getAlgo()
	{
		return this.m_algo;
	}
	
	public Page<byte[]>[] getPages(Long[] pageIds) throws FileNotFoundException, IOException
	{
		ArrayList<Page<byte[]>> pageList = new ArrayList<Page<byte[]>>();
		List<Long> returnedPages = m_algo.getElement(Arrays.asList(pageIds));
		
		for (Long pageId : pageIds)
		{
			if(m_algo.getElement(pageId)==null) // not in the RAM
			{
				if(!m_RAM.isFull()) // RAM is not full
				{
					m_algo.putElement(pageId,m_algo.getElement(pageId));
					m_RAM.addPage(HardDisk.getInstance().pageFault(pageId)); // put the page in the RAM
					pageList.add(m_RAM.getPage(pageId)); // Add to the new array
				}
				else
				{
					Page<byte[]> pageToRemoveID = m_RAM.getPage(m_algo.getElement(pageId));
				}
				
			}
		}
		return null;
	}
	
	public RAM getRam()
	{
		return this.m_RAM;
	}
	
	public void setAlgo (IAlgoCache<Long,Long> algo)
	{
		this.m_algo= algo;
	}
	
	public void setRam (RAM ram)
	{
		this.m_RAM=ram;
	}
	
	public void shutDown()
	{
		
	}
	
	public void update()
	{
		
	}
}
