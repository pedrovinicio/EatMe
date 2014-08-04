package example.eatme.util;

import java.util.HashMap;
import java.util.List;


public class Cache<T> {

	private HashMap<String, List<T>> itemsMap;
	
	public Cache() {
		this.itemsMap = new HashMap<String, List<T>>();
	}
	
	public void insert(String key, List<T> items){
		this.itemsMap.put(key, items);
	}
	
	public List<T> get(String key){
		return this.itemsMap.get(key);
	}
	
	public boolean contains(String key){
		return this.itemsMap.containsKey(key);
	}
	
	public void clear(){
		this.itemsMap.clear();
	}
}
