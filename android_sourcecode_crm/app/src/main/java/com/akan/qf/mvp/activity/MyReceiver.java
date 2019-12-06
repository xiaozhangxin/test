package com.akan.qf.mvp.activity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;

import com.akan.qf.MainActivity;
import com.akan.qf.R;
import com.akan.qf.bean.FirstEvent;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import cn.jpush.android.api.JPushInterface;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * 自定义接收器
 * 
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 */
public class MyReceiver extends BroadcastReceiver {
	private static final String TAG = "JIGUANG-Example";
	private static final int PUSH_NOTIFICATION_ID = (0x001);
	private static final String PUSH_CHANNEL_ID = "PUSH_NOTIFY_ID";
	private static final String PUSH_CHANNEL_NAME = "PUSH_NOTIFY_NAME";

	@RequiresApi(api = 26)
	@Override
	public void onReceive(Context context, Intent intent) {
		try {
			Bundle bundle = intent.getExtras();
			Logger.d(TAG, "[MyReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));

			if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
				String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
				Logger.d(TAG, "[MyReceiver] 接收Registration Id : " + regId);
				//send the Registration Id to your server...

			} else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
				EventBus.getDefault().post(new FirstEvent("pushRefresh"));
				Logger.d(TAG, "[MyReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
				String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
					processCustomMessage(context, bundle);
				NotificationManager notificationManager = (NotificationManager)context.getSystemService(NOTIFICATION_SERVICE);
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
					NotificationChannel channel = new NotificationChannel(PUSH_CHANNEL_ID, PUSH_CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
					if (notificationManager != null) {
						notificationManager.createNotificationChannel(channel);
					}
				}
				NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
				Intent notificationIntent = new Intent(context, MainActivity.class);
				notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
				PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);
				builder.setContentTitle("爱康企业管理系统")//设置通知栏标题
						.setContentIntent(pendingIntent) //设置通知栏点击意图
						.setContentText(message)
						.setTicker(message) //通知首次出现在通知栏，带上升动画效果的
						.setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示，一般是系统获取到的时间
						.setSmallIcon(R.mipmap.ic_launcher)//设置通知小ICON
						.setChannelId(PUSH_CHANNEL_ID)
						.setDefaults(Notification.DEFAULT_ALL);

				Notification notification = builder.build();
				notification.flags |= Notification.FLAG_AUTO_CANCEL;
				if (notificationManager != null) {
					notificationManager.notify(PUSH_NOTIFICATION_ID, notification);
				}


			} else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
				Logger.d(TAG, "[MyReceiver] 接收到推送下来的通知");
				int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
				Logger.d(TAG, "[MyReceiver] 接收到推送下来的通知的ID: " + notifactionId);

			} else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
				Logger.d(TAG, "[MyReceiver] 用户点击打开了通知");

				//打开自定义的Activity
				Intent i = new Intent(context, MainActivity.class);
				i.putExtras(bundle);
				//i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
				context.startActivity(i);

			} else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
				Logger.d(TAG, "[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
				//在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..

			} else if(JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
				boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
				Logger.w(TAG, "[MyReceiver]" + intent.getAction() +" connected state change to "+connected);
			} else {
				Logger.d(TAG, "[MyReceiver] Unhandled intent - " + intent.getAction());
			}
		} catch (Exception e){

		}

	}
/*	public static void showNotifictionIcon(Context context) {
		NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
		Intent intent = new Intent(context, MainActivity.class);//将要跳转的界面
		//Intent intent = new Intent();//只显示通知，无页面跳转
		builder.setAutoCancel(true);//点击后消失
		builder.setSmallIcon(R.drawable.login_logo);//设置通知栏消息标题的头像
		builder.setDefaults(NotificationCompat.DEFAULT_SOUND);//设置通知铃声
		builder.setTicker("状态栏显示的文字");
		builder.setContentTitle("标题");
		builder.setContentText("通知内容");
		//利用PendingIntent来包装我们的intent对象,使其延迟跳转
		PendingIntent intentPend = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
		builder.setContentIntent(intentPend);
		NotificationManager manager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
		manager.notify(0, builder.build());
	}*/




	// 打印所有的 intent extra 数据
	private static String printBundle(Bundle bundle) {
		StringBuilder sb = new StringBuilder();
		for (String key : bundle.keySet()) {
			if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
				sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
			}else if(key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)){
				sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
			} else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
				if (TextUtils.isEmpty(bundle.getString(JPushInterface.EXTRA_EXTRA))) {
					Logger.i(TAG, "This message has no Extra data");
					continue;
				}

				try {
					JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
					Iterator<String> it =  json.keys();

					while (it.hasNext()) {
						String myKey = it.next();
						sb.append("\nkey:" + key + ", value: [" +
								myKey + " - " +json.optString(myKey) + "]");
					}
				} catch (JSONException e) {
					Logger.e(TAG, "Get message extra JSON error!");
				}

			} else {
				sb.append("\nkey:" + key + ", value:" + bundle.get(key));
			}
		}
		return sb.toString();
	}
	
	//send msg to MainActivity
	private void processCustomMessage(Context context, Bundle bundle) {
		if (MainActivity.isForeground) {
			String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
			String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
			Intent msgIntent = new Intent(MainActivity.MESSAGE_RECEIVED_ACTION);
			msgIntent.putExtra(MainActivity.KEY_MESSAGE, message);
			if (!ExampleUtil.isEmpty(extras)) {
				try {
					JSONObject extraJson = new JSONObject(extras);
					if (extraJson.length() > 0) {
						msgIntent.putExtra(MainActivity.KEY_EXTRAS, extras);
					}
				} catch (JSONException e) {

				}

			}
			LocalBroadcastManager.getInstance(context).sendBroadcast(msgIntent);
		}
	}
}
