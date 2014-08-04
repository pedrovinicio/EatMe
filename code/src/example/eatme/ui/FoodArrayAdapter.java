package example.eatme.ui;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import example.eatme.R;
import example.eatme.models.Food;

public class FoodArrayAdapter extends ArrayAdapter<Food> implements OnClickListener, OnCheckedChangeListener{

	private class ViewHolder {
		TextView title;
		TextView category;
		TextView calories;
		TextView cholesterol;
		TextView carbohydrates;
		TextView sugar;
		TextView protein;
		TextView fat;
		CheckBox selection;
	}

	private List<Food> selectedFoodList;

	private DecimalFormat decimalFormat = new DecimalFormat("0.00"); 

	public FoodArrayAdapter(Context context, int resource, List<Food> objects) {
		super(context, resource, objects);

		this.selectedFoodList = new ArrayList<Food>();
	}

	@SuppressLint("InflateParams") 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder = null;

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.list_food_item_layout,null);

			holder = new ViewHolder();
			holder.title = (TextView) convertView.findViewById(R.id.textViewTitle);
			holder.category = (TextView) convertView.findViewById(R.id.textViewCategory);
			holder.calories = (TextView) convertView.findViewById(R.id.textViewCalories);
			holder.cholesterol = (TextView) convertView.findViewById(R.id.textViewCholesterol);
			holder.carbohydrates = (TextView) convertView.findViewById(R.id.textViewCarbohydrates);
			holder.sugar = (TextView) convertView.findViewById(R.id.textViewSugar);
			holder.protein = (TextView) convertView.findViewById(R.id.textViewProtein);
			holder.fat = (TextView) convertView.findViewById(R.id.textViewFat);

			holder.selection = (CheckBox) convertView.findViewById(R.id.checkBoxSelection);

			convertView.setOnClickListener(this);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		Food food = this.getItem(position);

		String brand = ((food.getBrand() != null && !food.getBrand().isEmpty()) ? (" - " + food.getBrand()) : "");
		holder.title.setText(food.getTitle() + brand);

		holder.category.setText(food.getCategory());
		holder.calories.setText(String.valueOf(decimalFormat.format(food.getCalories())));
		holder.cholesterol.setText(String.valueOf(decimalFormat.format(food.getCholesterol())));
		holder.carbohydrates.setText(String.valueOf(decimalFormat.format(food.getCarbohydrates())));
		holder.sugar.setText(String.valueOf(decimalFormat.format(food.getSugar())));
		holder.protein.setText(String.valueOf(decimalFormat.format(food.getProtein())));
		holder.fat.setText(String.valueOf(decimalFormat.format(food.getFat())));

		holder.selection.setOnCheckedChangeListener(null);
		holder.selection.setChecked(this.selectedFoodList.contains(food));
		holder.selection.setOnCheckedChangeListener(this);
		holder.selection.setTag(position);

		return convertView;
	}

	public List<Food> getSelectedItems(){
		return this.selectedFoodList;
	}

	public void clearSelectedItems(){
		this.selectedFoodList.clear();
	}

	@Override
	public void onClick(View view) {
		this.changeRotateScaleItem(view);
	}

	@Override
	public void onCheckedChanged(CompoundButton checkBox, boolean isChecked) {
		int position = (Integer) checkBox.getTag();

		Food food = this.getItem(position);

		if(isChecked){
			if(!this.selectedFoodList.contains(food)){
				this.selectedFoodList.add(food);
			}
		} else{
			if(this.selectedFoodList.contains(food)){
				this.selectedFoodList.remove(food);
			}
		}

	}

	private void changeRotateScaleItem(final View view) {
		ObjectAnimator rotation = ObjectAnimator.ofFloat(view, "rotationY", 0f, 90f);
		rotation.setDuration(200);

		ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 0.5f);
		scaleDownX.setDuration(200);

		ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(view, "scaleY", 1.0f, 0.5f);
		scaleDownY.setDuration(200);

		ObjectAnimator fadeOut = ObjectAnimator.ofFloat(view, "alpha", 1f, 0.5f);
		fadeOut.setDuration(200);

		AnimatorSet animSet = new AnimatorSet();

		animSet.play(scaleDownX).with(scaleDownY).with(rotation).with(fadeOut);

		rotation.addListener(new AnimatorListener() {

			@Override
			public void onAnimationStart(Animator animation) {
			}

			@Override
			public void onAnimationRepeat(Animator animation) {
			}

			@Override
			public void onAnimationEnd(Animator animation) {

				View frontContainer = view.findViewById(R.id.linearLayoutFrontContainer);
				View backContainer = view.findViewById(R.id.linearLayoutBackContainer);

				if(frontContainer.getVisibility() == View.VISIBLE){
					frontContainer.setVisibility(View.GONE);
					backContainer.setVisibility(View.VISIBLE);
				} else{
					frontContainer.setVisibility(View.VISIBLE);
					backContainer.setVisibility(View.GONE);
				}

				ObjectAnimator rotation = ObjectAnimator.ofFloat(view, "rotationY", 270f, 360f);
				rotation.setDuration(200);

				ObjectAnimator scaleUpX = ObjectAnimator.ofFloat(view, "scaleX", 0.5f, 1.0f);
				scaleUpX.setDuration(200);

				ObjectAnimator scaleUpY = ObjectAnimator.ofFloat(view, "scaleY", 0.5f, 1.0f);
				scaleUpY.setDuration(200);

				ObjectAnimator fadeIn = ObjectAnimator.ofFloat(view, "alpha", 0.5f, 1.0f);
				fadeIn.setDuration(200);

				AnimatorSet animSet = new AnimatorSet();
				animSet.play(rotation);
				animSet.play(scaleUpX).with(scaleUpY).with(rotation).with(fadeIn);
				animSet.start();
			}

			@Override
			public void onAnimationCancel(Animator animation) {

			}
		});

		animSet.start();
	}


}
