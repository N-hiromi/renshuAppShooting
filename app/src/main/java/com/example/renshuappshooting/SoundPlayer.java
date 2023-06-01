package com.example.renshuappshooting;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;

public class SoundPlayer {
    // 効果音など短い音を流すならsoundPoolクラスを使う
    private static SoundPool soundPool;
    private static int hitSound;
    private static int overSound;
    private AudioAttributes audioAttributes;
    public SoundPlayer(Context context) {
        // Lolipop以降ではnew SoundPoolは非推奨。
        audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();
        soundPool = new SoundPool.Builder()
                .setAudioAttributes(audioAttributes)
                .setMaxStreams(2)
                .build();
        // 音声ファイルを読み込む。R.raw.idで音声ファイルのID。1は再生品質。デフォルト値
        hitSound = soundPool.load(context, R.raw.hit, 1);
        overSound = soundPool.load(context, R.raw.over, 1);
    }
    // orange, pinkに当たった時に音を出すメソッド
    public void playHitSound() {
        soundPool.play(hitSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }
    // blackに当たった時に音を出すメソッド
    public void playOverSound() {
        soundPool.play(overSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }
}
