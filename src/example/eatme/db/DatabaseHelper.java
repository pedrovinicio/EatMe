package example.eatme.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import example.eatme.models.Food;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;

	private static final String DATABASE_NAME = "EatMe";

	private static final String TABLE_FOOD = "FOOD";

	private static final String CREATE_TABLE_FOOD = "CREATE TABLE "+ TABLE_FOOD + "(ID INTEGER PRIMARY KEY,"
			+ "TITLE TEXT, CATEGORY TEXT, BRAND TEXT, CALORIES FLOAT, CHOLESTEROL FLOAT, CARBOHYDRATES FLOAT,"
			+ "SUGAR FLOAT, PROTEIN FLOAT, FAT FLOAT)";

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_FOOD);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD);
		onCreate(db);
	}

	public long insertFood(Food food) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put("TITLE", food.getTitle());
		values.put("CATEGORY", food.getCategory());
		values.put("BRAND", food.getBrand());
		values.put("CALORIES", food.getCalories());
		values.put("CHOLESTEROL", food.getCholesterol());
		values.put("CARBOHYDRATES", food.getCarbohydrates());
		values.put("SUGAR", food.getSugar());
		values.put("PROTEIN", food.getProtein());
		values.put("FAT", food.getFat());

		return db.insert(TABLE_FOOD, null, values);
	}

	public List<Food> getFoods(String filter) {
		SQLiteDatabase db = this.getReadableDatabase();

		String selectQuery = "SELECT  * FROM " + TABLE_FOOD + " WHERE TITLE LIKE \'%" + filter + "%\'"
				+ " OR CATEGORY LIKE \'%" + filter + "%\' OR BRAND LIKE \'%" + filter + "%\' ORDER BY TITLE";

		Cursor c = db.rawQuery(selectQuery, null);

		List<Food> foods = new ArrayList<Food>();
		
		if (c.moveToFirst()) {
			do {
				Food food = new Food();
				food.setId(c.getInt((c.getColumnIndex("ID"))));
				food.setTitle(c.getString((c.getColumnIndex("TITLE"))));
				food.setCategory(c.getString((c.getColumnIndex("CATEGORY"))));
				food.setBrand(c.getString((c.getColumnIndex("BRAND"))));
				food.setCalories(c.getFloat((c.getColumnIndex("CALORIES"))));
				food.setCholesterol(c.getFloat((c.getColumnIndex("CHOLESTEROL"))));
				food.setCarbohydrates(c.getFloat((c.getColumnIndex("CARBOHYDRATES"))));
				food.setSugar(c.getFloat((c.getColumnIndex("SUGAR"))));
				food.setProtein(c.getFloat((c.getColumnIndex("PROTEIN"))));
				food.setFat(c.getFloat((c.getColumnIndex("FAT"))));

				foods.add(food);
			} while (c.moveToNext());
		}
		return foods;
	}

	public List<Food> getAllFoods() {
		List<Food> foods = new ArrayList<Food>();

		String selectQuery = "SELECT  * FROM " + TABLE_FOOD;

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);

		if (c.moveToFirst()) {
			do {
				Food food = new Food();
				food.setId(c.getInt((c.getColumnIndex("ID"))));
				food.setTitle(c.getString((c.getColumnIndex("TITLE"))));
				food.setCategory(c.getString((c.getColumnIndex("CATEGORY"))));
				food.setBrand(c.getString((c.getColumnIndex("BRAND"))));
				food.setCalories(c.getFloat((c.getColumnIndex("CALORIES"))));
				food.setCholesterol(c.getFloat((c.getColumnIndex("CHOLESTEROL"))));
				food.setCarbohydrates(c.getFloat((c.getColumnIndex("CARBOHYDRATES"))));
				food.setSugar(c.getFloat((c.getColumnIndex("SUGAR"))));
				food.setProtein(c.getFloat((c.getColumnIndex("PROTEIN"))));
				food.setFat(c.getFloat((c.getColumnIndex("FAT"))));

				foods.add(food);
			} while (c.moveToNext());
		}

		return foods;
	}

	public void deleteFood(int id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_FOOD, "ID = ?",
				new String[] { String.valueOf(id) });
	}
	
	public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen()){
        	db.close();
        }
    }
}
