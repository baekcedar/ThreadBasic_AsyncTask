package com.baekcedar.android.threadbasic_asynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ProgressBar progress;
    TextView percent;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progress = ( ProgressBar) findViewById(R.id.progressBar);
        percent = (TextView) findViewById(R.id.textView);
        btn = (Button) findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DownloadTask().execute(100); //max 값 넘기기
            }
        });
    }
                                        // 1      2     3
    class DownloadTask extends AsyncTask<Integer,Integer,String>{
                                // 1. doInBackground 의 parameter type
                                // 2. onProgressUpdate 의 parameter type
                                // 3. onPostExecute 의 parameter type

        @Override   // new를 했을 때 제일 먼저 시작됨
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override   // execute 실행시 호출됨 // 파라미터가 배열로 생성됨. (인자값 수 만큼)
        protected String doInBackground(Integer... params) {
            int max = params[0];

            try{
                for(int i = 0 ; i <= max ; i ++){
                    publishProgress(i);
                    Thread.sleep(100);
                }

            }catch (Exception e){}


            return "Finish";

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progress.setProgress(values[0]);
            percent.setText(values[0].toString()+"%");

//            super.onProgressUpdate(values);
        }
    }

}
