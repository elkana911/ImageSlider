package com.eric.imageslider;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Handler mBannerHandler = new Handler();
    public static final int DELAY_BANNER = 4000;
    private Runnable mRunnableBanner;
    private int currentBannerIndex;
    List<Banner> fBannerList = new ArrayList<>();
    MyBannerAdapter bannerAdapter;
    private ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPager = (ViewPager) findViewById(R.id.pager);

        Banner banner1 = new Banner();
        banner1.setId(1);
        banner1.setUrl("https://firebasestorage.googleapis.com/v0/b/dinginsejuk-2fdef.appspot.com/o/ZYDSC_0226-600.JPG?alt=media");

        Banner banner2 = new Banner();
        banner2.setId(2);
        banner2.setUrl("https://firebasestorage.googleapis.com/v0/b/dinginsejuk-2fdef.appspot.com/o/ZYDSC_0314-600.JPG?alt=media");

        fBannerList.add(banner1);
        fBannerList.add(banner2);

        //fBannerList.add(FragmentBanner.newInstance("https://firebasestorage.googleapis.com/v0/b/dinginsejuk-2fdef.appspot.com/o/ZYDSC_0226-600.JPG?alt=media", "eric"));
        //  fBannerList.add(FragmentBanner.newInstance("https://firebasestorage.googleapis.com/v0/b/dinginsejuk-2fdef.appspot.com/o/ZYDSC_0314-600.JPG?alt=media", "elkana"));

        mPager.setAdapter(new MyBannerAdapter(MainActivity.this,fBannerList));

    }

    @Override
    protected void onStart() {
        super.onStart();

        mRunnableBanner = new Runnable()
        {
            @Override
            public void run()
            {
                if (currentBannerIndex >= fBannerList.size()) {
                    currentBannerIndex = 0;
                }
                if (mPager != null)
                    mPager.setCurrentItem(currentBannerIndex++, true);
                mBannerHandler.postDelayed(mRunnableBanner, DELAY_BANNER );
            }
        };

        mBannerHandler.postDelayed(mRunnableBanner, DELAY_BANNER );

    }

    @Override
    protected void onStop() {
        super.onStop();

        mBannerHandler.removeCallbacks(mRunnableBanner);
    }
}
