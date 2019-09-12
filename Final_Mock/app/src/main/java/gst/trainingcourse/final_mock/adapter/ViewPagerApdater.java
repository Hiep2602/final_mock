package gst.trainingcourse.final_mock.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import gst.trainingcourse.final_mock.R;
import gst.trainingcourse.final_mock.fragment.MusicFragment;
import gst.trainingcourse.final_mock.fragment.PhotoFragment;

public class ViewPagerApdater extends FragmentPagerAdapter {
    private Context mContext;

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.category_app, R.string.category_music, R.string.category_photo, R.string.category_video};

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

                return MusicFragment.newMusicInstance();

            case 2:
                return  PhotoFragment.newPhotoInstance();

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

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }
}
