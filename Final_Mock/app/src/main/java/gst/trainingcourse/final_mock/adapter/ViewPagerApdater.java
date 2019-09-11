package gst.trainingcourse.final_mock.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerApdater extends FragmentPagerAdapter {
    private int mNumberPage;

    public ViewPagerApdater(FragmentManager fm, int mNumberPage) {
        super(fm);
        this.mNumberPage = mNumberPage;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                // to do add fragment
                return null;
            case 1:
                // to do add fragment

                return null;

            case 2:
                // to do add fragment

                return null;

            case 3:
                // to do add fragment

                return null;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumberPage;
    }
}
