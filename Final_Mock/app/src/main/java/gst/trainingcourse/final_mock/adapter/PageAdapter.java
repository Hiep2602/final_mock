package gst.trainingcourse.final_mock.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import gst.trainingcourse.final_mock.fragment.AppFragment;
import gst.trainingcourse.final_mock.fragment.VideoFragment;


public class PageAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    public PageAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new AppFragment();
            case 1:
            case 2:
                return new AppFragment();
            case 3:
                return new VideoFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
