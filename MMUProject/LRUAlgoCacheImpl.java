package com.hit.algo;
import java.util.LinkedHashMap;


public class LRUAlgoCacheImpl<K,V>  extends AbstractAlgoCache<K, V>{


		private final int maxcapacity;
		private LinkedHashMap<K,V> LRUmap;
		
		
	
		
	public LRUAlgoCacheImpl(int capacity) {
		super(capacity);
		this.maxcapacity = capacity-1;
		LRUmap = new LinkedHashMap<K,V>(capacity);
		
		// TODO Auto-generated constructor stub
	}
	@Override
	public  V getElement(K Key)
	{
		V tmpValue = null;
		if (LRUmap.containsKey(Key)) 
		{
			tmpValue = LRUmap.get(Key);
			LRUmap.remove(Key);
			LRUmap.put(Key,tmpValue);
		}
		return tmpValue;
	}
	
	@Override
	public V putElement(K Key, V Value)
	{
		V tmpValue =null;
		if (LRUmap.size()>this.maxcapacity)
		{
			K eldest;
			eldest =  this.LRUmap.entrySet().iterator().next().getKey();
			
			if(LRUmap.containsKey(Key))
			{
				tmpValue =LRUmap.get(Key);
				LRUmap.remove(Key);
				 eldest = this.LRUmap.entrySet().iterator().next().getKey();
			}
			else
			{
				tmpValue =LRUmap.get(eldest);
				LRUmap.remove(eldest);
				eldest = this.LRUmap.entrySet().iterator().next().getKey();
				
			}
		}
		LRUmap.put(Key, Value);
		return tmpValue;
	}
	@Override
	public void removeElement(K Key)
	{

		if(LRUmap.containsKey(Key))
			LRUmap.remove(Key);
		else 
			System.out.println("the key "  +Key+ " doesn't exists" );
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		for (K p : LRUmap.keySet()) {
			V printval = LRUmap.get(p);
			result.append("key "+ p + "\t"+"Value "+ printval+"\n");
		}
		
		return result.toString();
	}

}
