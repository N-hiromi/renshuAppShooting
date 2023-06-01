package com.example.renshuappshooting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.prefs.Preferences;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // もう一度プレイのid取得
        findViewById(R.id.tryAgain).setOnClickListener(this);
        TextView scoreLabel = findViewById(R.id.scoreLabel);
        TextView highScoreLabel = findViewById(R.id.highScoreLabel);

        // MainActivityからscoreを受け取る。スコアはintなのでgetIntExtra。キー, 値を取得できなかった場合の値
        int score = getIntent().getIntExtra("SCORE", 0);
        scoreLabel.setText(score + "");

        // sharedPreferencesは非推奨。後継dataStoreが推奨されている
        SharedPreferences sharedPreferences = getSharedPreferences("GAME_DATA", MODE_PRIVATE);
        // 書き出し
        int highScore = sharedPreferences.getInt("HIGH_SCORE", 0);

        if (score > highScore) {
            highScoreLabel.setText("High score : " + score);

            SharedPreferences.Editor editor = sharedPreferences.edit();
            // 書き込みキー名を揃えること
            editor.putInt("HIGH_SCORE", score);
            editor.apply();
            return;
        }
        if (score <= highScore) {
            highScoreLabel.setText("High Score: " + highScore);
            return;
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tryAgain){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
    }

    @Override
    public void onBackPressed() {
        // 戻るボタンを押された時の操作。今回は戻るボタン無効化したいので何も書かない
    }
}