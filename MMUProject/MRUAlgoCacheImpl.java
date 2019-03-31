
package com.hit.algo;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MRUAlgoCacheImpl<K,V> extends AbstractAlgoCache<K,V> {
	
	private V value;
	private final int capacity;
	private LinkedHashMap<K,V> mru = new LinkedHashMap<K,V>();
   

	public MRUAlgoCacheImpl(int capacity) {
		super(capacity);
		this.capacity = capacity;
	}
	public V getElement(K Key)
	{
		if (mru.containsKey(Key)) 
		{
			value = mru.get(Key);
			mru.remove(Key);
			mru.put(Key,value);
			System.out.println("the value that we get is : "+value);
			return value;
			
		}
	System.out.println("EROR Somthing went wrong key doesnt found ");
		return null;
	}
	
	public V putElement(K Key, V Value)
	{

		if (mru.size()>this.capacity)
		{
			
			if(mru.containsKey(Key))
			{
				mru.remove(Key);
				mru.put(Key, Value);
				System.out.println("The MRU Contain the KEY"+mru.keySet().toArray()[this.capacity-1]);
			}
			else
			{
				mru.remove(mru.keySet().toArray()[this.capacity]);
				mru.put(Key, Value);
				
				
			}
		}
		return mru.put(Key, Value);
	}
	
	public void removeElement(K key)
	{
		if(mru.containsKey(key))
		{
			mru.remove(key);
			
		}
		else
		System.out.println("The MRU did NOT Contain the KEY:"+ key);
		
	}

	public void PrintMe()
	{
		for (K p : mru.keySet()) {
		V printval = mru.get(p);
		System.out.println("key"+ p + "\t"+"Value"+ printval);
		}
	}
}
