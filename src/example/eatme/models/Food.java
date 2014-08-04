package example.eatme.models;

public class Food {

	private int id;
	
	private String title;
	private String category;
	private String Brand;
	
	private float calories;
	private float cholesterol;
	private float carbohydrates;
	private float sugar;
	private float protein;
	private float fat;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getBrand() {
		return Brand;
	}
	public void setBrand(String brand) {
		Brand = brand;
	}
	public float getCalories() {
		return calories;
	}
	public void setCalories(float calories) {
		this.calories = calories;
	}
	public float getCholesterol() {
		return cholesterol;
	}
	public void setCholesterol(float cholesterol) {
		this.cholesterol = cholesterol;
	}
	public float getCarbohydrates() {
		return carbohydrates;
	}
	public void setCarbohydrates(float carbohydrates) {
		this.carbohydrates = carbohydrates;
	}
	public float getSugar() {
		return sugar;
	}
	public void setSugar(float sugar) {
		this.sugar = sugar;
	}
	public float getProtein() {
		return protein;
	}
	public void setProtein(float protein) {
		this.protein = protein;
	}
	public float getFat() {
		return fat;
	}
	public void setFat(float fat) {
		this.fat = fat;
	}
}
