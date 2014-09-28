package example.eatme.comm;
import java.util.List;

import android.content.Context;

import example.eatme.models.Food;
import example.eatme.util.Cache;


public class DownloadManager {

	private Cache<Food> cache; 
	
	private LifeSumService lifeSumService;
	
	public DownloadManager(Context context) {
		this.cache = new Cache<Food>();
		
		this.lifeSumService = new LifeSumService(context);
	}
	
	public List<Food> downloadFood(String filter){
		
		if(this.cache.contains(filter)){
			return this.cache.get(filter);
		} else{
			List<Food> downloadedFoods = this.lifeSumService.getFoods(filter); 
			
			if(downloadedFoods != null){
				this.cache.insert(filter, downloadedFoods);
			}
			
			return downloadedFoods;
		}
	}
}
