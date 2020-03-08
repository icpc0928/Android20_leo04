package tw.org.iii.leo.leo04;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TextView tv , tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        tv2 = findViewById(R.id.tv2);
    }


    public void test1(View view) {
        Thread mt1 = new MyThread("mt1");
        Thread mt2 = new MyThread("mt2");
        mt1.start();
        mt2.start();
        Log.v("leo","test1()");

    }

    //執行緒
    private class MyThread extends Thread{
        String name;
        MyThread(String name){this.name = name;}
        @Override
        public void run() {
            for(int i=0; i<10 ;i++){
                Log.v("leo",name + " i = " +i);
               // tv.setText(name + " i = " +i);

                Message message = new Message();
                Bundle data = new Bundle();
                data.putCharSequence("data",name + " i = " +i);
                message.setData(data);
                uiHandler.sendMessage(message);


                try {
                    Thread.sleep(500);
                }catch (Exception e){

                }
            }
        }
    }

    private UIHandler uiHandler = new UIHandler();

    //內部類別方便存取跟使用
    private class UIHandler extends Handler{
        //handleMessage
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            CharSequence data = msg.getData().getCharSequence("data","no data");
            tv.setText(data);
        }
    }


    //timer
    private Timer timer = new Timer();
    private class MyTask extends TimerTask{
        int i ;
        @Override
        public void run() {
            Log.v("leo","i = " + i++);

        }
    }
    public void test2(View view) {
        timer.schedule(new MyTask(),1*1000,1*1000);

    }
    public void test3(View view) {

    }
}
