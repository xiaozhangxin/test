/*
 *  Copyright (C) 2017 Bilibili
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.akan.wms.impl;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;

import com.akan.wms.R;
import com.bilibili.boxing.loader.IBoxingCrop;
import com.bilibili.boxing.model.config.BoxingCropOption;
import com.yalantis.ucrop.UCrop;

/**
 * use Ucrop(https://github.com/Yalantis/uCrop) as the implement for {@link IBoxingCrop}
 *
 * @author ChenSL
 */
public class BoxingUcrop implements IBoxingCrop {

    @Override
    public void onStartCrop(Context context, Fragment fragment, @NonNull BoxingCropOption cropConfig,
                            @NonNull String path, int requestCode) {
        Uri uri = new Uri.Builder()
                .scheme("file")
                .appendPath(path)
                .build();
        UCrop.Options crop = new UCrop.Options();
        // do not copy exif information to crop pictures
        // because png do not have exif and png is not Distinguishable
        crop.setCompressionFormat(Bitmap.CompressFormat.PNG);
        crop. setHideBottomControls(false);//影藏图片下面的操作控制的界面
        crop.withMaxResultSize(cropConfig.getMaxWidth(), cropConfig.getMaxHeight());//最终的剪裁尺寸
        crop.withAspectRatio(cropConfig.getAspectRatioX(), cropConfig.getAspectRatioY());//剪裁的比例
        crop.setStatusBarColor(ActivityCompat.getColor(context, R.color.colorPrimary));//设置状态栏颜色
        crop.setToolbarColor(context.getResources().getColor(R.color.colorPrimary));//是指toolbar颜色
        crop.setShowCropGrid(true);//是否显示网格线
        UCrop.of(uri, cropConfig.getDestination())
                .withOptions(crop)
                .start(context, fragment, requestCode);



        UCrop.of(uri, cropConfig.getDestination())
                .withOptions(crop)
                .start(context, fragment, requestCode);
    }

    @Override
    public Uri onCropFinish(int resultCode, Intent data) {
        if (data == null) {
            return null;
        }
        Throwable throwable = UCrop.getError(data);
        if (throwable != null) {
            return null;
        }
        return UCrop.getOutput(data);
    }
}
