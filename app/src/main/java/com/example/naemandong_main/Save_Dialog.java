package com.example.naemandong_main;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.naemandong_main.Data.savebookData;
import com.example.naemandong_main.Data.savebookResponse;
import com.example.naemandong_main.Network.RetrofitClient;
import com.example.naemandong_main.Network.ServiceApi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Save_Dialog extends Dialog {
    ImageButton save, voice;
    Button exit;
    EditText title;
    private String dtitle;
    private String cover;
    private int storynum;
    private ArrayList<Integer> myList = new ArrayList<>();
    private Activity activity;
    Intent intent;
    SpeechRecognizer mRecognizer;

    ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);

    public Save_Dialog(@NonNull Activity activity, String title, int num, ArrayList<Integer> myList, String cover) {
        super(activity);
        this.activity = activity;
        this.dtitle = title;
        this.storynum = num;
        this.myList = myList;
        this.cover = cover;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //다이얼로그 밖의 화면은 흐리게 만들어줌
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.8f;
        getWindow().setAttributes(layoutParams);

        setContentView(R.layout.save_dialog);

        //다이어로그 배경 투명하게
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        //셋팅
        save = (ImageButton) findViewById(R.id.save);
        exit = (Button) findViewById(R.id.exit);
        voice = (ImageButton) findViewById(R.id.voice);
        title = (EditText) findViewById(R.id.title);

        title.setText(dtitle);

        try {
            intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, activity.getPackageName());
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ko-KR");
        } catch (Exception e) {
            Toast.makeText(activity,e.toString(),Toast.LENGTH_LONG).show();
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String book_title = title.getText().toString();
                int book_select0 = myList.get(0);
                int book_select1 = myList.get(1);
                int book_select2 = myList.get(2);
                int book_select3 = myList.get(3);
                int book_select4 = myList.get(4);
                int book_select5 = myList.get(5);
                int book_select6 = myList.get(6);
                String book_cover = cover;

                startSave(new savebookData(storynum, book_title, book_select0, book_select1, book_select2, book_select3, book_select4, book_select5, book_select6, book_cover));
                Toast.makeText(activity, String.valueOf(myList), Toast.LENGTH_LONG).show();
                dismiss();
                activity.finish();
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                activity.finish();
            }
        });
        voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voice.setSelected(true);
                mRecognizer = SpeechRecognizer.createSpeechRecognizer(activity);
                mRecognizer.setRecognitionListener(listener);
                mRecognizer.startListening(intent);
            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
            return false;
        }
        return true;
    }

    private void startSave(savebookData data) {
        service.userSave(data).enqueue(new Callback<savebookResponse>() {
            @Override
            public void onResponse(Call<savebookResponse> call, Response<savebookResponse> response) {
                savebookResponse result = response.body();
                Toast.makeText(activity, "저장을 완료했습니다.", Toast.LENGTH_SHORT).show();

                /*if (result.getCode() == 200) {
                    finish();
                }*/
            }

            @Override
            public void onFailure(Call<savebookResponse> call, Throwable t) {
                Toast.makeText(activity, "저장에 실패했습니다.", Toast.LENGTH_SHORT).show();
//                Log.e("회원가입에 실패했습니다.", t.getMessage());
            }
        });
    }

    private RecognitionListener listener = new RecognitionListener() {
        @Override
        public void onReadyForSpeech(Bundle params) {
            Toast.makeText(activity,"음성입력 시작",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onBeginningOfSpeech() {

        }

        @Override
        public void onRmsChanged(float rmsdB) {

        }

        @Override
        public void onBufferReceived(byte[] buffer) {

        }

        @Override
        public void onEndOfSpeech() {
            Toast.makeText(activity,"음성입력 종료",Toast.LENGTH_SHORT).show();
            voice.setSelected(false);
        }

        @Override
        public void onError(int error) {
            String message;

            switch (error) {
                case SpeechRecognizer.ERROR_AUDIO:
                    message = "오디오 에러";
                    break;
                case SpeechRecognizer.ERROR_CLIENT:
                    message = "클라이언트 에러";
                    break;
                case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                    message = "퍼미션 없음";
                    break;
                case SpeechRecognizer.ERROR_NETWORK:
                    message = "네트워크 에러";
                    break;
                case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                    message = "네트웍 타임아웃";
                    break;
                case SpeechRecognizer.ERROR_NO_MATCH:
                    message = "찾을 수 없음";
                    break;
                case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                    message = "RECOGNIZER가 바쁨";
                    break;
                case SpeechRecognizer.ERROR_SERVER:
                    message = "서버가 이상함";
                    break;
                case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                    message = "말하는 시간초과";
                    break;
                default:
                    message = "알 수 없는 오류임";
                    break;
            }

            Toast.makeText(activity, "에러가 발생하였습니다. : " + message,Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onResults(Bundle results) {
            ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
            title.setText(matches.get(0));
            mRecognizer.destroy();
        }

        @Override
        public void onPartialResults(Bundle partialResults) {

        }

        @Override
        public void onEvent(int eventType, Bundle params) {

        }
    };
}
