package com.twlk.weixin;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.ShapeBadgeItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;
import com.twlk.weixin.adapter.IndexAdapter;
import com.twlk.weixin.fragment.FoundFragment;
import com.twlk.weixin.fragment.IndexFragment;
import com.twlk.weixin.fragment.MyFragment;
import com.twlk.weixin.fragment.TXlFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.viewPager)
    NoPreloadViewPager viewPager;
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;

    private IndexAdapter indexAdapter;


    private ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initBottomNar();

        addFragment();

        initListener();
    }

    private void initListener() {
        viewPager.setOnPageChangeListener(new NoPreloadViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomNavigationBar.selectTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                viewPager.setCurrentItem(position,false);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }

    private void addFragment() {
        IndexFragment indexFragment = new IndexFragment();
        TXlFragment tXlFragment = new TXlFragment();
        FoundFragment foundFragment = new FoundFragment();
        MyFragment myFragment = new MyFragment();
        fragments.add(indexFragment);
        fragments.add(tXlFragment);
        fragments.add(foundFragment);
        fragments.add(myFragment);

        indexAdapter = new IndexAdapter(getSupportFragmentManager(),fragments);

        viewPager.setAdapter(indexAdapter);
        viewPager.setCurrentItem(0,false);
    }

    private void initBottomNar() {
        bottomNavigationBar.show(false);
        BottomNavigationItem bottomNavigationItem = new BottomNavigationItem(R.drawable.icon_index,"微信");

        TextBadgeItem textBadgeItem = new TextBadgeItem();

        textBadgeItem.setBorderWidth(2)
                .setText("5");
        bottomNavigationItem.setBadgeItem(textBadgeItem);

        bottomNavigationBar.addItem(bottomNavigationItem);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.icon_txl,"通讯录"));
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.icon_found,"发现"));
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.icon_my,"我"));
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setFirstSelectedPosition(0);
        bottomNavigationBar.initialise();

    }
}
