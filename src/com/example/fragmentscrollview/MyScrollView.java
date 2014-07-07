package com.example.fragmentscrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;

public class MyScrollView extends LinearLayout{
	private int scaledSlop;
	private int mWidth;
	private int mHeight;
	public MyScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		scaledSlop = ViewConfiguration.get(context).getScaledTouchSlop();
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		for(int i=0; i<getChildCount(); i++){
			getChildAt(i).layout(0, i * mHeight, mWidth, (i + 1) * mHeight);
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		mWidth = MeasureSpec.getSize(widthMeasureSpec);
		mHeight = MeasureSpec.getSize(heightMeasureSpec);
	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		System.out.println("onIntercepterTouchEnvent");
		return super.onInterceptTouchEvent(ev);
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		System.out.println("dispatchTouchenvent");
		return super.dispatchTouchEvent(ev);
	}
	
	
	
	private float mDownY;
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		System.out.println("µ÷ÓÃÁË");
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mDownY = event.getY();
			return true;
		case MotionEvent.ACTION_MOVE:
			System.out.println("scaledSlop = " + scaledSlop);
			if(Math.abs(event.getY() - mDownY) > 3 * scaledSlop){
				int distance = Math.round(event.getY() - mDownY);
				//scrollTo(0, - ());
				scrollBy(0,  -distance);
			}
			return true;
		case MotionEvent.ACTION_UP:
			break;
		default:
			break;
		}
		return false;
	}
}
