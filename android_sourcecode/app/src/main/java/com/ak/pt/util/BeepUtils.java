package com.ak.pt.util;

import android.content.Context;
import android.media.MediaPlayer;

import com.ak.pt.R;

/**
 * Created by admin on 2019/3/19.
 */

public class BeepUtils {

    private static MediaPlayer mediaPlayer;

    public static void jixu(Context context) {
        if (mediaPlayer != null) {
            mediaPlayer.reset();
        }
        mediaPlayer = MediaPlayer.create(context, R.raw.x_ceshijixu);
        mediaPlayer.start();
    }

    //开始
    public void begin(Context context) {
        if (mediaPlayer != null) {
            mediaPlayer.reset();
        }
        mediaPlayer = MediaPlayer.create(context, R.raw.kaishi);
        mediaPlayer.start();
    }


    public static void chooseTenOld(Context context) {
        if (mediaPlayer != null) {
            mediaPlayer.reset();
        }
        mediaPlayer = MediaPlayer.create(context, R.raw.xuanzeyidianlingzp);
        mediaPlayer.start();

    }

    public static void chooseTwelveOld(Context context) {
        if (mediaPlayer != null) {
            mediaPlayer.reset();
        }
        mediaPlayer = MediaPlayer.create(context, R.raw.xuanzeyidianerzp);
        mediaPlayer.start();
    }


    //电池电量不足
    public static void moPower(Context context) {
        if (mediaPlayer != null) {
            mediaPlayer.reset();
        }
        mediaPlayer = MediaPlayer.create(context, R.raw.dianliangbugou);
        mediaPlayer.start();
    }

    //已超压
    public static void ptOver(Context context) {
        if (mediaPlayer != null) {
            mediaPlayer.reset();
        }
        mediaPlayer = MediaPlayer.create(context, R.raw.yichaoya);
        mediaPlayer.start();

    }

    //请加压
    public static void addpt(Context context) {
        if (mediaPlayer != null) {
            mediaPlayer.reset();
        }
        mediaPlayer = MediaPlayer.create(context, R.raw.qingjiaya);
        mediaPlayer.start();
    }

    //蓝牙已连接
    public static void yilianjie(Context context) {
        if (mediaPlayer != null) {
            mediaPlayer.reset();
        }
        mediaPlayer = MediaPlayer.create(context, R.raw.x_lanyalianjiechenggong);
        mediaPlayer.start();
    }

    public static void startPt(Context context) {
        if (mediaPlayer != null) {
            mediaPlayer.reset();
        }
        mediaPlayer = MediaPlayer.create(context, R.raw.kaishiceshi);
        mediaPlayer.start();

    }

    public static void endPt(Context context) {
        if (mediaPlayer != null) {
            mediaPlayer.reset();
        }
        mediaPlayer = MediaPlayer.create(context, R.raw.x_lanyalianjiechenggong);
        mediaPlayer.start();
    }


    //第一阶段测试开始

    public static void oneBegin(Context context) {
        if (mediaPlayer != null) {
            mediaPlayer.reset();
        }
        mediaPlayer = MediaPlayer.create(context, R.raw.y_yijieduanceshikaishi);
        mediaPlayer.start();
    }

    //第一阶段测试结束
    public static void oneOver(Context context) {
        if (mediaPlayer != null) {
            mediaPlayer.reset();
        }
        mediaPlayer = MediaPlayer.create(context, R.raw.y_erjieduanceshikaishi);
        mediaPlayer.start();
    }


    //第二阶段测试结束
    public static void twoOver(Context context) {
        if (mediaPlayer != null) {
            mediaPlayer.reset();
        }
        mediaPlayer = MediaPlayer.create(context, R.raw.y_sanjieduanceshikaishi);
        mediaPlayer.start();
    }


    //第三阶段测试结束
    public static void threeOver(Context context) {
        if (mediaPlayer != null) {
            mediaPlayer.reset();
        }
        mediaPlayer = MediaPlayer.create(context, R.raw.y_sijieduanceshikaishi);
        mediaPlayer.start();

    }


    //第四阶段测试结束
    public static void fourOver(Context context) {
        if (mediaPlayer != null) {
            mediaPlayer.reset();
        }
        mediaPlayer = MediaPlayer.create(context, R.raw.y_wujieduanceshikaishi);
        mediaPlayer.start();

    }   //请选择测压模式

    public static void selectKg(Context context) {
        if (mediaPlayer != null) {
            mediaPlayer.reset();
        }
        mediaPlayer = MediaPlayer.create(context, R.raw.x_qingxuanzeyalimoshi);
        mediaPlayer.start();

    }

    //测试合格
    public static void success(Context context) {
        if (mediaPlayer != null) {
            mediaPlayer.reset();
        }
        mediaPlayer = MediaPlayer.create(context, R.raw.ceshihege);
        mediaPlayer.start();

    }

    //测试不合格
    public static void fail(Context context) {
        if (mediaPlayer != null) {
            mediaPlayer.reset();
        }
        mediaPlayer = MediaPlayer.create(context, R.raw.ceshibuhegeqingjiancha);
        mediaPlayer.start();

    }

    public static void chooseOne(Context context) {
        if (mediaPlayer != null) {
            mediaPlayer.reset();
        }
        mediaPlayer = MediaPlayer.create(context, R.raw.x_x_xuanzegongjin1);
        mediaPlayer.start();

    }

    public static void chooseTwo(Context context) {
        if (mediaPlayer != null) {
            mediaPlayer.reset();
        }
        mediaPlayer = MediaPlayer.create(context, R.raw.x_x_xuanzegongjin2);
        mediaPlayer.start();

    }

    public static void chooseThree(Context context) {
        if (mediaPlayer != null) {
            mediaPlayer.reset();
        }
        mediaPlayer = MediaPlayer.create(context, R.raw.x_x_xuanzegongjin3);
        mediaPlayer.start();

    }

    public static void chooseFour(Context context) {
        if (mediaPlayer != null) {
            mediaPlayer.reset();
        }
        mediaPlayer = MediaPlayer.create(context, R.raw.x_x_xuanzegongjin4);
        mediaPlayer.start();

    }

    public static void chooseFive(Context context) {
        if (mediaPlayer != null) {
            mediaPlayer.reset();
        }
        mediaPlayer = MediaPlayer.create(context, R.raw.x_x_xuanzegongjin5);
        mediaPlayer.start();

    }

    public static void chooseSix(Context context) {
        if (mediaPlayer != null) {
            mediaPlayer.reset();
        }
        mediaPlayer = MediaPlayer.create(context, R.raw.x_x_xuanzegongjin6);
        mediaPlayer.start();

    }

    public static void chooseSeven(Context context) {
        if (mediaPlayer != null) {
            mediaPlayer.reset();
        }
        mediaPlayer = MediaPlayer.create(context, R.raw.x_x_xuanzegongjin7);
        mediaPlayer.start();

    }

    public static void chooseEight(Context context) {
        if (mediaPlayer != null) {
            mediaPlayer.reset();
        }
        mediaPlayer = MediaPlayer.create(context, R.raw.x_x_xuanzegongjin8);
        mediaPlayer.start();

    }

    public static void chooseNine(Context context) {
        if (mediaPlayer != null) {
            mediaPlayer.reset();
        }
        mediaPlayer = MediaPlayer.create(context, R.raw.x_x_xuanzegongjin9);
        mediaPlayer.start();

    }

    public static void chooseTen(Context context) {
        if (mediaPlayer != null) {
            mediaPlayer.reset();
        }
        mediaPlayer = MediaPlayer.create(context, R.raw.x_x_xuanzegongjin10);
        mediaPlayer.start();

    }

    public static void chooseEleven(Context context) {
        if (mediaPlayer != null) {
            mediaPlayer.reset();
        }
        mediaPlayer = MediaPlayer.create(context, R.raw.x_x_xuanzegongjin11);
        mediaPlayer.start();

    }

    public static void chooseTwelve(Context context) {
        if (mediaPlayer != null) {
            mediaPlayer.reset();
        }
        mediaPlayer = MediaPlayer.create(context, R.raw.x_x_xuanzegongjin12);
        mediaPlayer.start();

    }

    public static void chooseThirteen(Context context) {
        if (mediaPlayer != null) {
            mediaPlayer.reset();
        }
        mediaPlayer = MediaPlayer.create(context, R.raw.x_x_xuanzegongjin13);
        mediaPlayer.start();

    }

    public static void chooseFourteen(Context context) {
        if (mediaPlayer != null) {
            mediaPlayer.reset();
        }
        mediaPlayer = MediaPlayer.create(context, R.raw.x_x_xuanzegongjin14);
        mediaPlayer.start();

    }

    public static void chooseFifteen(Context context) {
        if (mediaPlayer != null) {
            mediaPlayer.reset();
        }
        mediaPlayer = MediaPlayer.create(context, R.raw.x_x_xuanzegongjin15);
        mediaPlayer.start();

    }

    public static void chooseSixteen(Context context) {
        if (mediaPlayer != null) {
            mediaPlayer.reset();
        }
        mediaPlayer = MediaPlayer.create(context, R.raw.x_x_xuanzegongjin16);
        mediaPlayer.start();

    }

    public static void chooseSixOld(Context context) {
        if (mediaPlayer != null) {
            mediaPlayer.reset();
        }
        mediaPlayer = MediaPlayer.create(context, R.raw.xunzelingdanliuzp);
        mediaPlayer.start();

    }

    public static void chooseEightOld(Context context) {
        if (mediaPlayer != null) {
            mediaPlayer.reset();
        }
        mediaPlayer = MediaPlayer.create(context, R.raw.xuanzelingdianbazp);
        mediaPlayer.start();

    }


}
