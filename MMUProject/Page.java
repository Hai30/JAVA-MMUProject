package com.hit.memoryunits;
import java.io.Serializable;
import java.lang.Long;

public class Page<T> implements Serializable {

	private static final long serialVersionUID =  -616017516154926601L ;
	private Long pageId;
	private T content;


	public Page (Long id , T content)
	{
		this.pageId = id;
		this.content = content;
	}
	
	public T getContent()
	{
		return content;
	}
	
	public Long getPageId()
	{
		return pageId;
	}
	
	public void setContent ( T content)
	{
		this.content = content;
	}
	
	public void setPageId(Long pageId)
	{
		this.pageId = pageId;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals (Object obj)
	{
		if (this == obj || pageId.equals(((Page<T>)obj).getPageId()))		{
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode()
	{
		return pageId.hashCode();
	}
	
	@Override
	public String toString()
	{
		return pageId.toString();
	}
}
