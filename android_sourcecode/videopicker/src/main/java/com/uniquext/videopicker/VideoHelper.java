package com.uniquext.videopicker;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

/**
 * 　 　　   へ　　　 　／|
 * 　　    /＼7　　　 ∠＿/
 * 　     /　│　　 ／　／
 * 　    │　Z ＿,＜　／　　   /`ヽ
 * 　    │　　　 　　ヽ　    /　　〉
 * 　     Y　　　　　   `　  /　　/
 * 　    ｲ●　､　●　　⊂⊃〈　　/
 * 　    ()　 へ　　　　|　＼〈
 * 　　    >ｰ ､_　 ィ　 │ ／／      去吧！
 * 　     / へ　　 /　ﾉ＜| ＼＼        比卡丘~
 * 　     ヽ_ﾉ　　(_／　 │／／           消灭代码BUG
 * 　　    7　　　　　　　|／
 * 　　    ＞―r￣￣`ｰ―＿
 * ━━━━━━感觉萌萌哒━━━━━━
 *
 * @author UniqueXT
 * @version 1.0
 * @date 2019/5/29  10:51
 */
public class VideoHelper {
    public static final String ACTION_VIDEO_LIBRARY = "uniquext.intent.action.COMMON_VIDEO_LIB";

    public static final String PICK_TOTAL = "PICK_TOTAL";
    public static final String PICK_VIDEO = "PICK_VIDEO";
    public static final String MAX_VIDEO_SIZE = "MAX_VIDEO_SIZE";
    public static final String VIDEO_PATH = "VIDEO_PATH";


    public static String getThumbPathByCursor(Context context, String videoId) {
        String thumb = null;
        Cursor thumbCursor = context.getContentResolver().query(
                MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Video.Thumbnails.DATA},
                MediaStore.Video.Thumbnails.VIDEO_ID + "= ?",
                new String[]{videoId},
                null
        );
        if (thumbCursor != null && thumbCursor.moveToNext()) {
            thumb = thumbCursor.getString(thumbCursor.getColumnIndex(MediaStore.Video.Thumbnails.DATA));
            thumbCursor.close();
        }
        return thumb;
    }

    public static int getVideoId(Context context, String videoPath) {
        int videoId = -1;
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Video.Media._ID},
                MediaStore.Video.Media.DATA + "= ?",
                new String[]{videoPath},
                null
        );
        if (cursor != null && cursor.moveToNext()) {
            videoId = cursor.getInt(cursor.getColumnIndex(MediaStore.Video.Media._ID));
            cursor.close();
        }
        return videoId;
    }

}
