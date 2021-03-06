package de.appplant.cordova.plugin.background;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.meizu.upspushsdklib.UpsCommandMessage;
import com.meizu.upspushsdklib.UpsPushMessage;
import com.meizu.upspushsdklib.UpsPushMessageReceiver;
import com.meizu.upspushsdklib.CommandType;
import android.content.SharedPreferences;
import static android.content.Context.MODE_PRIVATE;

/**
 * Author：loi on 2019-01-29 11:57
 * Emil：894900183@qq.com
 * - -
 */
public class MeiZuUpsReceiver extends UpsPushMessageReceiver {
    @Override
    public void onThroughMessage(Context context, UpsPushMessage upsPushMessage) {
        Log.e("MeiZuUpsReceiver", "魅族【onThroughMessage】" + upsPushMessage);
    }
    @Override
    public void onNotificationClicked(Context context, UpsPushMessage
            upsPushMessage) {
        Log.e("MeiZuUpsReceiver", "【onNotificationClicked】" + upsPushMessage);
    }
    @Override
    public void onNotificationArrived(Context context, UpsPushMessage
            upsPushMessage) {
        Log.e("MeiZuUpsReceiver", "【onNotificationArrived】" + upsPushMessage);
    }
    @Override
    public void onNotificationDeleted(Context context, UpsPushMessage
            upsPushMessage) {
        Log.e("MeiZuUpsReceiver", "【onNotificationDeleted】" + upsPushMessage);
    }

    @Override
    public void onUpsCommandResult(final Context context, final UpsCommandMessage upsCommandMessage) {
        Log.e("MeiZuUpsReceiver", "【onUpsCommandResult】" + upsCommandMessage);
            new Handler(context.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {              
                if(upsCommandMessage!=null && upsCommandMessage.getCode() == 200 && upsCommandMessage.getCommandType()== CommandType.REGISTER){
                    String token = upsCommandMessage.getCommandResult().split("_")[1];
//                    Toast.makeText(context, "标识符："+upsCommandMessage.toString(), Toast.LENGTH_LONG).show();
                    //Toast.makeText(context, "标识符："+token, Toast.LENGTH_LONG).show();
                    SharedPreferences sharedPreferences =context.getSharedPreferences("TokenFile", MODE_PRIVATE);
                    if(sharedPreferences!=null){
                        sharedPreferences.edit().putString("Token",token).commit();
                    }   
                }
                //Toast.makeText(context, "标识符："+upsCommandMessage.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
