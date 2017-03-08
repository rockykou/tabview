package com.rockykou.views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rockykou.entity.TabEntity;
import com.rockykou.utils.CommonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：rockykou
 * 邮箱: kouzhen121@163.com
 * 版权: 供学习交流使用
 */
public class TabView extends LinearLayout {

    private static final int TAB_LEFT = 1;
    private static final int TAB_MIDDLE = 2;
    private static final int TAB_RIGHT = 3;
    private Context mContext;
    private int mTextColor;
    private int mTextSelectedColor;
    private int mTabSelected = 1;
    private float mTextSize;
    private int mStrokeWidth = 2;
    private int mDashWidth = 2;
    private int mDashGap = 4;
    private int mColor = Color.RED;
    private int mSelectedColor = Color.GREEN;
    private float mCornerRadius = 3;
    private int mStrokeColor = Color.GREEN;
    private int mStrokeSelectedColor = Color.RED;
    private OnTabItemClickListener mTabViewClickListener;
    private List<View> mTabViewList;
    private List<String> mTabTextList;//String
    private List<TabEntity> mTabEntityList;//Entity对象
    private int mTabNum;//设置tab总数

    public TabView(Context context) {
        this(context, null);
    }

    public TabView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        mContext = context;
        this.setOrientation(LinearLayout.HORIZONTAL);
        this.setGravity(Gravity.CENTER);
    }

    private TabView setLeftBackDrawable(View view, boolean checked) {
        //设置对应圆角
        float[] f = new float[]{mCornerRadius, mCornerRadius, 0, 0, 0, 0, mCornerRadius, mCornerRadius};
        setTabItemView(view, f, TAB_LEFT, checked);
        return this;
    }

    private TabView setMiddleBackDrawable(View view, boolean checked) {
        float[] f = new float[]{0, 0, 0, 0, 0, 0, 0, 0};
        setTabItemView(view, f, TAB_MIDDLE, checked);
        return this;
    }

    private TabView setRightBackDrawable(View view, boolean checked) {
        float[] f = new float[]{0, 0, mCornerRadius, mCornerRadius, mCornerRadius, mCornerRadius, 0, 0};
        setTabItemView(view, f, TAB_RIGHT, checked);
        return this;
    }

    private void setTabItemView(View view, float[] f, int tab, boolean selected) {
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(selected ? mSelectedColor : mColor);
//        gd.setCornerRadius(mCornerRadius);
        gd.setStroke(mStrokeWidth, selected ? mStrokeSelectedColor : mStrokeColor, mDashWidth, mDashGap);
        gd.setShape(GradientDrawable.RECTANGLE);
        gd.setCornerRadii(f);
        Drawable[] layer = new Drawable[1];
        layer[0] = gd;
        LayerDrawable ld = new LayerDrawable(layer);
        if (tab == TAB_LEFT) {
            ld.setLayerInset(0, 0, 0, 0, 0);
        } else if (tab == TAB_MIDDLE) {
            ld.setLayerInset(0, -mStrokeWidth, 0, 0, 0);
        } else {
            ld.setLayerInset(0, -mStrokeWidth, 0, 0, 0);
        }
        view.setBackgroundDrawable(ld);
    }

    public TabView setTextColor(int color, int selectedColor) {
        mTextColor = color;
        mTextSelectedColor = selectedColor;
        return this;
    }

    public TabView setTextSize(float size) {
        mTextSize = size;
        return this;
    }

    public TabView setTexts(List<String> textlist) {
        mTabTextList = textlist;
        return this;
    }

    public TabView setEntitys(List<TabEntity> textlist) {
        mTabEntityList = textlist;
        return this;
    }

    public TabView setColor(int color, int selectedColor) {
        this.mColor = color;
        this.mSelectedColor = selectedColor;
        return this;
    }

    public TabView setStrokeColor(int color, int selectedColor) {
        this.mStrokeColor = color;
        this.mStrokeSelectedColor = selectedColor;
        return this;
    }

    public TabView setCornerRadius(int radius) {
        this.mCornerRadius = radius;
        return this;
    }

    public TabView setStrokeWidth(int strokeWidth) {
        this.mStrokeWidth = strokeWidth;
        return this;
    }

    public TabView setDashWidth(int width) {
        this.mDashWidth = width;
        return this;
    }

    public TabView setDashGap(int gap) {
        this.mDashGap = gap;
        return this;
    }

    public TabView setSelected(int selectedTab) {
        mTabSelected = selectedTab;
        return this;
    }

    public void show() {
        checkTabNum();
        createView();
        setViewData();
    }

    private void checkTabNum() {
        if (mTabTextList == null || (mTabTextList != null && mTabTextList.size() == 0)) {
            if (mTabEntityList == null || (mTabTextList != null && mTabEntityList.size() == 0)) {
                throw new IllegalArgumentException("mTabTextList is "+mTabTextList+", or mTabEntityList is "+mTabEntityList);
            } else {
                mTabNum = mTabEntityList.size();
            }
        } else {
            mTabNum = mTabTextList.size();
        }
        if(mTabNum == 1 || mTabNum > 5){
            throw new IllegalArgumentException("mTabNum is "+mTabNum+", minSize 2 and maxSize 5");
        }
        if(mTabSelected <= 0 || mTabSelected > mTabNum){
            throw new IllegalArgumentException("mTabSelected is "+mTabSelected+", but mTabNum is "+mTabNum);
        }
    }

    private void createView() {
        mTabViewList = new ArrayList<View>();
        for (int i = 0; i < mTabNum; i++) {
            final TextView tv = new TextView(mContext);
            tv.setTag(i);
            tv.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (mTabViewClickListener != null) {
                        int position = Integer.valueOf(tv.getTag().toString());
                        mTabViewClickListener.onTabItemClick(v, position);
                        changeStatus(position);
                    }
                }
            });
            if (i == 0) {
                setLeftBackDrawable(tv, false);
            } else if (i == mTabNum - 1) {
                setRightBackDrawable(tv, false);
            } else {
                setMiddleBackDrawable(tv, false);
            }
            mTabViewList.add(tv);
        }
    }

    private void setViewData() {
        this.removeAllViews();
        for (int i = 0; i < mTabViewList.size(); i++) {
            TextView tv = ((TextView) mTabViewList.get(i));
            this.addView(mTabViewList.get(i));
            LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.width = getScreenWidth() / (4 + mTabNum / 2);
            params.height = getScreenHeight() / 22;
            if (mTabTextList != null && mTabTextList.size() > 1) {
                tv.setText(mTabTextList.get(i));
            }else if(mTabEntityList != null && mTabEntityList.size() > 1){
                tv.setText(mTabEntityList.get(i).tabName);
            }
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTextSize);
            tv.setTextColor(mTextColor);
            tv.setLayoutParams(params);
            tv.setGravity(Gravity.CENTER);
            if (mTabSelected - 1 == i) {
                changeStatus(i);
            }
        }
    }

    public void setOnTabItemClickListener(OnTabItemClickListener listener) {
        mTabViewClickListener = listener;
    }

    private void changeStatus(int position) {
        for (int i = 0; i < mTabNum; i++) {
            TextView tv = ((TextView) mTabViewList.get(i));
            if (position == i) {
                tv.setSelected(true);
                tv.setTextColor(mTextSelectedColor);
                if (i == 0) {
                    setLeftBackDrawable(tv, true);
                } else if (i == mTabNum - 1) {
                    setRightBackDrawable(tv, true);
                } else {
                    setMiddleBackDrawable(tv, true);
                }
            } else {
                tv.setSelected(false);
                tv.setTextColor(mTextColor);
                if (i == 0) {
                    setLeftBackDrawable(tv, false);
                } else if (i == mTabNum - 1) {
                    setRightBackDrawable(tv, false);
                } else {
                    setMiddleBackDrawable(tv, false);
                }
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    private int getScreenWidth() {
        return CommonUtil.getScreenWidth(mContext);
    }

    private int getScreenHeight() {
        return CommonUtil.getScreenHeight(mContext);
    }

    public interface OnTabItemClickListener {
        void onTabItemClick(View v, int position);
    }

}
