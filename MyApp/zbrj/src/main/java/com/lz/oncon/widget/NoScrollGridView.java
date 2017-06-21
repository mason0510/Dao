package com.lz.oncon.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridView;

public class NoScrollGridView extends GridView {

	public NoScrollGridView(Context context) {
		super(context);
	}
	
	public NoScrollGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public NoScrollGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		if(ev.getAction() == MotionEvent.ACTION_MOVE){
			return true;//禁止Gridview进行滑动
		}
		return super.dispatchTouchEvent(ev);
	}
}