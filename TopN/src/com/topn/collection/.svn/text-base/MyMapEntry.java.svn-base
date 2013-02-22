package com.topn.collection;

import java.util.Map;

/**
 * 
 * 创建人 KingXt
 * 创建时间：2011-3-2 下午04:31:55
 * @param <K>
 * @param <V>
 * 
 * 键值对
 */
public class MyMapEntry<K, V> implements Map.Entry<K, V>{

	private K key;
	
	private V value;
	
	public MyMapEntry() {}
	
	public MyMapEntry(K key, V value) {
		super();
		this.key = key;
		this.value = value;
	}

	@Override
	public K getKey() {
		// TODO Auto-generated method stub
		return key;
	}

	@Override
	public V getValue() {
		// TODO Auto-generated method stub
		return value;
	}

	@Override
	public V setValue(V newValue) {
		 V oldValue = value;
         value = newValue;
         return oldValue;
	}
}
