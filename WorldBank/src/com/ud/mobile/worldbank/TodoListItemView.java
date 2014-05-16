package com.ud.mobile.worldbank;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

public class TodoListItemView extends TextView {
	
	private Paint marginPaint;
	private Paint linePaint;
	private int paperColor;
	private float margin;

	public TodoListItemView(Context context) {
		super(context);
		init();
	}
	
	public TodoListItemView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public TodoListItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	private void init() {
		//get a ref to the resource table
		Resources todoResources = getResources();
		
		//create paint brushes to use in onDraw()
		marginPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		marginPaint.setColor(todoResources.getColor(R.color.notepad_margin));
		
		linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		linePaint.setColor(todoResources.getColor(R.color.notepad_lines));
		
		paperColor = todoResources.getColor(R.color.notepad_paper);
		margin = todoResources.getDimension(R.dimen.notepad_margin);
	}
	
	public void onDraw(Canvas canvas){
		canvas.drawColor(paperColor);
		
		//draw rules lines
		canvas.drawLine(0, 0, getMeasuredHeight(), 0, linePaint);
		canvas.drawLine(0, getMeasuredHeight(), getMeasuredWidth(), getMeasuredHeight(), linePaint);
		
		//draw margin
		canvas.drawLine(margin, 0, margin, getMeasuredHeight(), marginPaint);
		
		//move the text across from the margin
		canvas.save();
		canvas.translate(margin, 0);
		
		//use the superclass to render the text		
		super.onDraw(canvas);
		canvas.restore();
	}


}
