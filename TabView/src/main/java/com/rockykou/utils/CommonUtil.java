package com.rockykou.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.WindowManager;
/**
 * 作者：rockykou
 * 邮箱: kouzhen121@163.com
 * 版权: 供学习交流使用
 */
public class CommonUtil {

	private static final String TAG = "CommonUtil";

	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	@SuppressWarnings("deprecation")
	public static int getScreenWidth(Context context) {
		WindowManager windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE); // 屏幕宽（像素，如：480px）
		Log.d(TAG, "getScreenWidth: "+windowManager.getDefaultDisplay().getWidth());
		return windowManager.getDefaultDisplay().getWidth();
	}

	@SuppressWarnings("deprecation")
	public static int getScreenHeight(Context context) {
		WindowManager windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE); // 屏幕宽（像素，如：480px）
		Log.d(TAG, "getScreenHeight: "+windowManager.getDefaultDisplay().getHeight());
		return windowManager.getDefaultDisplay().getHeight();
	}

	@SuppressWarnings("deprecation")
	public static int getScreenWidth(Activity context) {
		int screenWidth = context.getWindowManager().getDefaultDisplay()
				.getWidth(); // 屏幕宽（像素，如：480px）
		return screenWidth;
	}

	@SuppressWarnings("deprecation")
	public static int getScreenHeight(Activity context) {
		int screenHeight = context.getWindowManager().getDefaultDisplay()
				.getHeight(); // 屏幕高（像素，如：800p）
		return screenHeight;
	}

}
