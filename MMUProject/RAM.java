package com.hit.memoryunits;
import java.lang.Long;
import java.util.HashMap;
import java.util.Map;

public class RAM {
	
	private int capacity;
	private Map<Long, Page<byte[]>> ramPages;
	
	public RAM (int initialCapacity)
	{
		this.capacity = initialCapacity;
		this.ramPages = new HashMap<Long,Page<byte[]>>(initialCapacity);
		
	}
	
	public void addPage(Page<byte[]> addPage)
	{
		ramPages.put(addPage.getPageId(),addPage);
	}
	
	public void addPages (Page<byte[]>[] addPages)
	{
		for (Page<byte[]> page : addPages) 
		{
			addPage(page);
		}
	}
	public int getInitialCapacity ()
	{
		return this.capacity;
	}
	
	public Page<byte[]> getPage(Long PageId)
	{
		return ramPages.get(PageId);
	}
	
	public Map<Long,Page<byte[]>> getPages()
	{
		return ramPages;
	}
	
	public Page<byte[]>[] getPages (Long [] pageIds)
	{
		@SuppressWarnings("unchecked")
		Page<byte[]>[] retPages = new Page[pageIds.length];
		int index = 0;
		for (Long pageId : pageIds) 
		{
			if (ramPages.containsKey(pageId))
			{
				retPages[index] = ramPages.get(pageId);
				index++;
			}
		}
		
		return retPages;
	}
	
	public void removePage (Page<byte[]> removePage)
	{
		ramPages.remove(removePage.getPageId());
	}
	

	public void removePages(Page<byte[]>[] remvoePages)
	{
		for (Page<byte[]> page : remvoePages) 
		{
			removePage(page);
		}
	}
	
	public void setinitialCapacity(int initialCapacity)
	{
		this.capacity = initialCapacity;
	}
	public void setPages (Map<Long,Page<byte[]>> pages)
	{
		this.ramPages = pages;
	}
	
	public boolean isFull()
	{
		if(ramPages.size() >= this.capacity)
			return true;
		return false;
	}
}
