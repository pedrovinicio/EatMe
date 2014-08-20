package example.eatme.ui;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import example.eatme.R;
import example.eatme.db.DatabaseHelper;
import example.eatme.models.Food;
import example.eatme.tasks.LoadFoodsAsyncTask;
import example.eatme.tasks.LoadFoodsAsyncTask.FoodListener;

public class ListFoodActivity extends Activity implements FoodListener, OnClickListener{

	private final int DOWNLOAD_FOOD = 1;
	
	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
				case DOWNLOAD_FOOD :
					String filter = (String) msg.obj;
					
					LoadFoodsAsyncTask loadFoodTask = new LoadFoodsAsyncTask(ListFoodActivity.this,dataBase);
					loadFoodTask.setListener(ListFoodActivity.this);
					loadFoodTask.execute(filter);
					break;
				default :
					break;
			}
		};
	};
	
	private ListView listViewFood;

	private FoodArrayAdapter foodAdapter;

	private EditText editTextSearch;

	private boolean hasMovedContainer;
	
	private DatabaseHelper dataBase;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE); 
		this.setContentView(R.layout.list_food_activity_layout);

		this.dataBase = new DatabaseHelper(this);

		this.listViewFood = (ListView) this.findViewById(R.id.ListViewFood);

		this.listViewFood.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				Food food = (Food) parent.getItemAtPosition(position);
				Toast.makeText(ListFoodActivity.this,food.getTitle(), Toast.LENGTH_SHORT).show();
			}
		});
		this.editTextSearch = (EditText) findViewById(R.id.editTextSearch);
		this.editTextSearch.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable s) {
			}

			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			public void onTextChanged(CharSequence s, int start, int before, int count) {
				handler.removeMessages(DOWNLOAD_FOOD);
				
				moveSearchContainer();
				
				Message message = new Message();
				message.what = DOWNLOAD_FOOD;
				message.obj = s.toString();
				
				handler.sendMessageDelayed(message, 2000);
			}
		});
		
		this.findViewById(R.id.buttonSave).setOnClickListener(this);
		this.findViewById(R.id.buttonSearch).setOnClickListener(this);
	}

	protected void moveSearchContainer() {
		if(!this.hasMovedContainer){
			View view = findViewById(R.id.relativeLayoutSearchContainer);
			
			view.animate().setDuration(700);
			view.animate().x(view.getX()).y(20);
			
			this.hasMovedContainer = true;
		}
	}

	@Override
	public void onFoodLoaded(List<Food> foods) {
		if(foods != null){
			this.foodAdapter = new FoodArrayAdapter(this, R.layout.list_food_item_layout, foods);
			this.listViewFood.setAdapter(foodAdapter);
			this.listViewFood.setVisibility(View.VISIBLE);
			
			this.findViewById(R.id.buttonSave).setVisibility(View.VISIBLE);
		} else{
			Toast.makeText(this, "Ia quebrar", Toast.LENGTH_SHORT).show();
		}
	}
	
	@Override
	public void onClick(View view) {
		
		if(view.getId() == R.id.buttonSave){
			List<Food> foodsToSave = this.foodAdapter.getSelectedItems();
			
			if(!foodsToSave.isEmpty()){
				for (Food food : foodsToSave) {
					this.dataBase.insertOrUpdateFood(food);
				}
				
				this.foodAdapter.clearSelectedItems();
				this.foodAdapter.notifyDataSetChanged();
				
				Toast.makeText(this, "New items saved to database.", Toast.LENGTH_SHORT).show();
			} else{
				Toast.makeText(this, "Select at least one item to save.", Toast.LENGTH_SHORT).show();
			}
		} else if(view.getId() == R.id.buttonSearch){
			this.handler.removeMessages(DOWNLOAD_FOOD);
			
			Message message = new Message();
			message.what = DOWNLOAD_FOOD;
			message.obj = this.editTextSearch.getText().toString();
			
			this.handler.sendMessageDelayed(message, 2000);
		}
		
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		this.dataBase.closeDB();
	}
}
