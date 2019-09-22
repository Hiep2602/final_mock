package gst.trainingcourse.final_mock.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Constants {
    private Constants() {
    }

    public static void shareData(List<Uri> uris, String type, Context context) {
        try {
            Intent share = new Intent();
            share.setAction(Intent.ACTION_SEND_MULTIPLE);
            share.setType(type);
            share.putParcelableArrayListExtra(Intent.EXTRA_STREAM, (ArrayList<? extends Parcelable>) uris);
            share.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
            context.startActivity(share);
        } catch (Exception e) {
            Log.e("ShareApp", e.getMessage());
        }
    }
}
