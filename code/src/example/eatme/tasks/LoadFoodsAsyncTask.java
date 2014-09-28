package example.eatme.tasks;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import example.eatme.R;
import example.eatme.comm.DownloadManager;
import example.eatme.db.DatabaseHelper;
import example.eatme.models.Food;
import example.eatme.util.ConnectionStatus;

public class LoadFoodsAsyncTask extends AsyncTask<String, Integer, List<Food>> {

	public interface FoodListener{
		void onFoodLoaded(List<Food> foods);
	}
	
	private Context context;
	
	private DatabaseHelper dataBase;
	
	private DownloadManager downloadManager;
	
	private FoodListener foodListener;
	
	private ProgressDialog progressDialog;

	public LoadFoodsAsyncTask(Context context, DatabaseHelper dataBase) {
		this.context = context;
		this.dataBase = dataBase;
		
		this.downloadManager = new DownloadManager(context);
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();

		this.progressDialog = ProgressDialog.show(this.context, this.context.getString(R.string.please_wait),
				this.context.getString(R.string.loading_message), false, false);  
	}

	@Override
	protected List<Food> doInBackground(String... params) {
		boolean isConnected = ConnectionStatus.isConnectingToInternet(this.context);

		if (isConnected) {
			return downloadManager.downloadFood(params[0]);
		} else {
			return this.dataBase.getFoods(params[0]);
		}
	}

	@Override
	protected void onPostExecute(List<Food> result) {
		super.onPostExecute(result);
		
		if(progressDialog != null){
			progressDialog.dismiss();
		}
		
		if(this.foodListener != null){
			this.foodListener.onFoodLoaded(result);
		}
	}
	
	public void setListener(FoodListener listener){
		this.foodListener = listener;
	}

}
