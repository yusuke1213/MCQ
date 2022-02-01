package com.example.mcq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //どのメソッドからも使えるようん
    private TextView count;
    private TextView question;
    private Button ans1;
    private Button ans2;
    private Button ans3;
    private Button ans4;
    private Button next;

    private int i = 0;
    // preparation
    // quiz datta
    String quizData[][] = {
            // {"問題", "正解", "選択肢１", "選択肢２", "選択肢３"}
            {"問題A", "A1", "A2", "A3", "A4"},
            {"問題B", "B1", "B2", "B3", "B4"},
            {"問題C", "C1", "C2", "C3", "C4"},
            {"問題D", "D1", "D2", "D3", "D4"},
            {"問題E", "E1", "E2", "E3", "E4"}
    };

    @Override
    //起動と同時
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //idを取得
        getId();
        //print quiz
        showQuiz();

    }

    // 起動のあと
    //Idを取得して準備
    public void getId(){
        count = findViewById(R.id.count);
        question = findViewById(R.id.question);
        ans1 = findViewById(R.id.ans1);
        ans2 = findViewById(R.id.ans2);
        ans3 = findViewById(R.id.ans3);
        ans4 = findViewById(R.id.ans4);
        next = findViewById(R.id.next);
    }



    // 表示
    public void showQuiz(){
        count.setText("残り" + (5-i) + "問");
        List<Integer> num = Arrays.asList(1, 2, 3, 4);
        Collections.shuffle(num);// 重複なし、一回は出る

        question.setText(quizData[i][0]);
        ans1.setText(quizData[i][num.get(0)]);
        ans2.setText(quizData[i][num.get(1)]);
        ans3.setText(quizData[i][num.get(2)]);
        ans4.setText(quizData[i][num.get(3)]);
    }

    //ボタンが押された時の正誤判定。処理
    public void onAnswer(View view){
        //押されたボタン
        Button clickedButton = (Button)view;
        String strClickedAns = clickedButton.getText().toString();
        if(strClickedAns.equals(quizData[i][1])){
            clickedButton.setText("正解");

            // 選択ボタンを無効化して、nextButtonを押させる
            ans1.setEnabled(false);
            ans2.setEnabled(false);
            ans3.setEnabled(false);
            ans4.setEnabled(false);
            next.setEnabled(true);

            if(i == 4){
                //activity画面作成。画面遷移
                Intent intent = new Intent(this, Result.class);// ここのインスタンスと遷移先のクラス名前
                startActivity(intent);
                finish();
            }else{
                i++;
            }

        }else{
            clickedButton.setText("不正解");
            question.setText("Game Over...");
            // 選択ボタンを無効化して、nextButtonを押させる
            ans1.setEnabled(false);
            ans2.setEnabled(false);
            ans3.setEnabled(false);
            ans4.setEnabled(false);
            next.setEnabled(false);
        }
    }

    // NextButtonが押された時
    public void onNext(View view){
        showQuiz();
        ans1.setEnabled(true);
        ans2.setEnabled(true);
        ans3.setEnabled(true);
        ans4.setEnabled(true);
        next.setEnabled(true);
    }

}