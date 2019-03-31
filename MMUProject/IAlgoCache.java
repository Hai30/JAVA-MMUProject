package com.hit.algo;

public interface IAlgoCache<K,V>
{

	public V getElement(K Key);
	public V putElement(K Key, V Value);
	public void removeElement(K Key);

 
	
}
