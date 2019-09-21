package gst.trainingcourse.final_mock.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;



public abstract class BaseFragment extends Fragment {

    protected void shareData(List<Uri> uris, String type) {
        try {
            Intent share = new Intent();
            share.setAction(Intent.ACTION_SEND_MULTIPLE);
            share.setType(type);
            share.putParcelableArrayListExtra(Intent.EXTRA_STREAM, (ArrayList<? extends Parcelable>) uris);
            getContext().startActivity(share);
        } catch (Exception e) {
            Log.e("ShareApp", e.getMessage());
        }
    }


}
