package com.hit.algo;
import java.util.HashMap;

public class Random<K,V> extends AbstractAlgoCache <K,V> {

	
	private HashMap<K,V> Ranmap ;
	private final int capacity;

	public Random(int capacity) {
		super(capacity);
		this.capacity=capacity;
		Ranmap= new HashMap<K,V>(capacity);
	}
	@Override
	public  V getElement(K Key)
	{
		if (Ranmap.containsKey(Key)) 
		{
			return Ranmap.get(Key);
			
		}
	System.out.println("EROR Somthing went wrong key doesnt found ");
		return null;
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public V putElement(K Key, V Value)
	{
		V tmpValue = null;
		if(Ranmap.containsKey(Key)) {
			if(Ranmap.get(Key)==Value)
				return null;
		}
		
		if (Ranmap.size()>=this.capacity)
		{
			int Index = (int) new java.util.Random().nextInt(Ranmap.size());
			tmpValue = Ranmap.get((Ranmap.keySet().toArray())[Index]);
			K tmpKey = (K) (Ranmap.keySet().toArray())[Index];
			removeElement(tmpKey);
		}
		Ranmap.put(Key, Value);
		return tmpValue;
		
	}
	@Override
	public void removeElement(K Key)

	{
		if(Ranmap.containsKey(Key))
			Ranmap.remove(Key);
		
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		for (K p : Ranmap.keySet()) {
			V printval = Ranmap.get(p);
			result.append("key "+ p + "\t"+"Value "+ printval+"\n");
		}
		
		return result.toString();
	}
	
}