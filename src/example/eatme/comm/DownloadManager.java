package example.eatme.comm;
import java.util.List;

import example.eatme.models.Food;
import example.eatme.util.Cache;


public class DownloadManager {

	private Cache<Food> cache; 
	
	public DownloadManager() {
		this.cache = new Cache<Food>();
	}
	
	public List<Food> downloadFood(String filter){
		
		if(this.cache.contains(filter)){
			return this.cache.get(filter);
		} else{
			List<Food> downloadedFoods = LifeSumService.getFoods(filter); 
			this.cache.insert(filter, downloadedFoods);
			return downloadedFoods;
		}
	}
}
