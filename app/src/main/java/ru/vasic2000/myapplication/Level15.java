package ru.vasic2000.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Level15 extends AppCompatActivity {

    Dialog dialog;
    Dialog dialogEnd;
    ImageView backGround;

    SoundPool sounds;
    int levelWin;
    int goodAnswer1, goodAnswer2, goodAnswer3, goodAnswer4, goodAnswer5, goodAnswer6, goodAnswer7, goodAnswer8;
    int badAnswer1, badAnswer2, badAnswer3, badAnswer4, badAnswer5, badAnswer6, badAnswer7, badAnswer8;

    public int numLeft;  //Номер левой картинки
    public int numRight; //Номер правой картинки
    public int numLeftOld = -1; //Предыдущий номер левой картинки
    ImageView imgLeft;
    ImageView imgRight;
    TextView textLevels;
    TextView left_text;
    TextView right_text;
    TextView task_text;

    public int count = 0;  //Счётчик правильных ответов

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        initComponents();
        imgRoundCorners();
        FullScreen();
        previewDialog();
        dialogEnd();
        textBtnBack();
        buttonContinue();
        dialog.show();
        backFromPreview();
        backFromLevel();
        makeRndImages();
    }

    private void initComponents() {
        textLevels = findViewById(R.id.text_levels);
        backGround = findViewById(R.id.main_background);
        imgLeft = findViewById(R.id.imgLeft);
        imgRight = findViewById(R.id.imgRight);
        left_text = findViewById(R.id.textLeft);
        right_text = findViewById(R.id.textRight);
        task_text = findViewById(R.id.textTask);

        sounds = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);

        levelWin = sounds.load(this, R.raw.level_win, 0);

        goodAnswer1 = sounds.load(this, R.raw.good1, 0);
        goodAnswer2 = sounds.load(this, R.raw.good2, 0);
        goodAnswer3 = sounds.load(this, R.raw.good3, 0);
        goodAnswer4 = sounds.load(this, R.raw.good4, 0);
        goodAnswer5 = sounds.load(this, R.raw.good5, 0);
        goodAnswer6 = sounds.load(this, R.raw.good6, 0);
        goodAnswer7 = sounds.load(this, R.raw.good7, 0);
        goodAnswer8 = sounds.load(this, R.raw.good8, 0);

        badAnswer1 = sounds.load(this, R.raw.bad1, 0);
        badAnswer2 = sounds.load(this, R.raw.bad2, 0);
        badAnswer3 = sounds.load(this, R.raw.bad3, 0);
        badAnswer4 = sounds.load(this, R.raw.bad4, 0);
        badAnswer5 = sounds.load(this, R.raw.bad5, 0);
        badAnswer6 = sounds.load(this, R.raw.bad6, 0);
        badAnswer7 = sounds.load(this, R.raw.bad7, 0);
        badAnswer8 = sounds.load(this, R.raw.bad8, 0);

        backGround.setImageResource(R.drawable.level_background);

        textLevels.setTextColor(getResources().getColor(R.color.colorBlack95));
        textLevels.setText(R.string.level_15);
        task_text.setText(R.string.level15short);

        left_text.setTextColor(getResources().getColor(R.color.colorBlack95));
        right_text.setTextColor(getResources().getColor(R.color.colorBlack95));
        task_text.setTextColor(getResources().getColor(R.color.colorBlack95));

        //Массив прогресса игры
        final int[] progress = {
                R.id.point1, R.id.point2, R.id.point3, R.id.point4, R.id.point5,
                R.id.point6, R.id.point7, R.id.point8, R.id.point9, R.id.point10,
                R.id.point11, R.id.point12, R.id.point13, R.id.point14, R.id.point15,
                R.id.point16, R.id.point17, R.id.point18, R.id.point19, R.id.point20
        };

        // Обработка нажатий
        // Левая кнопка
        imgLeft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //  Касание картинки
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    imgRight.setEnabled(false); // Блокирую правую картинку

                    //  Если левая больше
                    if(numLeft > numRight) {
                        imgLeft.setImageResource(R.drawable.img_true);
                    } else {
                        imgLeft.setImageResource(R.drawable.img_false);
                    }
                }
                else if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if((numLeft > numRight) && (count < 20)) {
                        if(count < 19) {
                            playGoodAnswer();
                        }
                        count++;
                        // Закрашиваю прогресс
                        for(int i=0; i<20; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        // Закрашиваю выполненный прогресс
                        for(int i=0; i<count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }

                    } else {
                        playBadAnswer();
                        if(count>0) {
                            if (count == 1) {
                                count = 0;
                            } else {
                                count -=2;
                            }
                        }

                        // Закрашиваю прогресс
                        for(int i=0; i<20; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        // Закрашиваю выполненный прогресс
                        for(int i=0; i<count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }
                    if(count == 20) {
                        // ВЫХОД ИЗ УРОВНЯ
                        SaveResult15();
                        playSoundWin();
                        dialogEnd.show();
                    } else {
                        makeRndImages();
                        // Разблокирую правую картинку
                        imgRight.setEnabled(true);
                    }
                }
                return true;
            }
        });

        // Правая кнопка
        imgRight.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //  Касание картинки
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    imgLeft.setEnabled(false); // Блокирую левую картинку

                    //  Если левая меньше
                    if(numLeft < numRight) {
                        imgRight.setImageResource(R.drawable.img_true);
                    } else {
                        imgRight.setImageResource(R.drawable.img_false);
                    }
                }
                else if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if((numLeft < numRight) && (count < 20)) {
                        if(count < 19) {
                            playGoodAnswer();
                        }
                        count++;
                        // Закрашиваю прогресс
                        for(int i=0; i<20; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        // Закрашиваю выполненный прогресс
                        for(int i=0; i<count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    } else {
                        playBadAnswer();
                        if(count>0) {
                            if (count == 1) {
                                count = 0;
                            } else {
                                count -=2;
                            }
                        }

                        // Закрашиваю прогресс
                        for(int i=0; i<20; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        // Закрашиваю выполненный прогресс
                        for(int i=0; i<count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }
                    if(count == 20) {
                        // ВЫХОД ИЗ УРОВНЯ
                        SaveResult15();
                        playSoundWin();
                        dialogEnd.show();
                    } else {
                        makeRndImages();
                        // Разблокирую левую картинку
                        imgLeft.setEnabled(true);
                    }
                }
                return true;
            }
        });


        // Обработка нажатий на слова
        // Левая кнопка
        left_text.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //  Касание картинки
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    imgRight.setEnabled(false); // Блокирую правую картинку

                    //  Если левая больше
                    if(numLeft > numRight) {
                        imgLeft.setImageResource(R.drawable.img_true);
                    } else {
                        imgLeft.setImageResource(R.drawable.img_false);
                    }
                }
                else if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if((numLeft > numRight) && (count < 20)) {
                        if(count < 19) {
                            playGoodAnswer();
                        }
                        count++;
                        // Закрашиваю прогресс
                        for(int i=0; i<20; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        // Закрашиваю выполненный прогресс
                        for(int i=0; i<count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }

                    } else {
                        playBadAnswer();
                        if(count>0) {
                            if (count == 1) {
                                count = 0;
                            } else {
                                count -=2;
                            }
                        }

                        // Закрашиваю прогресс
                        for(int i=0; i<20; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        // Закрашиваю выполненный прогресс
                        for(int i=0; i<count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }
                    if(count == 20) {
                        // ВЫХОД ИЗ УРОВНЯ
                        SaveResult15();
                        playSoundWin();
                        dialogEnd.show();
                    } else {
                        makeRndImages();
                        // Разблокирую правую картинку
                        imgRight.setEnabled(true);
                    }
                }
                return true;
            }
        });

        // Правая кнопка
        right_text.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //  Касание картинки
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    imgLeft.setEnabled(false); // Блокирую левую картинку

                    //  Если левая меньше
                    if(numLeft < numRight) {
                        imgRight.setImageResource(R.drawable.img_true);
                    } else {
                        imgRight.setImageResource(R.drawable.img_false);
                    }
                }
                else if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if((numLeft < numRight) && (count < 20)) {
                        if(count < 19) {
                            playGoodAnswer();
                        }
                        count++;
                        // Закрашиваю прогресс
                        for(int i=0; i<20; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        // Закрашиваю выполненный прогресс
                        for(int i=0; i<count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    } else {
                        playBadAnswer();
                        if(count>0) {
                            if (count == 1) {
                                count = 0;
                            } else {
                                count -=2;
                            }
                        }

                        // Закрашиваю прогресс
                        for(int i=0; i<20; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        // Закрашиваю выполненный прогресс
                        for(int i=0; i<count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }
                    if(count == 20) {
                        // ВЫХОД ИЗ УРОВНЯ
                        SaveResult15();
                        playSoundWin();
                        dialogEnd.show();
                    } else {
                        makeRndImages();
                        // Разблокирую левую картинку
                        imgLeft.setEnabled(true);
                    }
                }
                return true;
            }
        });

    }

    private void SaveResult15() {
        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
        final int level = save.getInt("Level", 1);
        if (level > 15) {

        } else {
            SharedPreferences.Editor editor = save.edit();
            editor.putInt("Level", 16);
            editor.apply();
        }
    }

    private void imgRoundCorners() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imgLeft.setClipToOutline(true);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imgRight.setClipToOutline(true);
        }
    }

    private void FullScreen() {
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void makeRndImages() {
        Array array = new Array();
        Random rnd = new Random();

        //Анимация
        final Animation an = AnimationUtils.loadAnimation(Level15.this, R.anim.alpha);

        while((numLeft = rnd.nextInt(14)) == numLeftOld) numLeft = rnd.nextInt(14);
        numLeftOld = numLeft;
        imgLeft.setImageResource(array.images15[numLeft]);
        imgLeft.setAnimation(an);
        left_text.setText(array.text15[numLeft]);

        numRight = rnd.nextInt(14);
        while(numRight == numLeft) {
            numRight = rnd.nextInt(14);
        }
        imgRight.setImageResource(array.images15[numRight]);
        imgRight.setAnimation(an);
        right_text.setText(array.text15[numRight]);
    }

    private void backFromLevel() {
        Button btn_Back = findViewById(R.id.btnBack);
        btn_Back.setBackgroundResource(R.drawable.btn_stroke_black95_pressed_white);
        btn_Back.setTextColor(getResources().getColor(R.color.colorBlack95));
        btn_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level15.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                } catch (Exception exc) {
//                Здесь кода нет
                }
            }
        });
    }

    private void backFromPreview() {
        Button btnContinue = dialog.findViewById(R.id.btnContinue);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void previewDialog() {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);  //Скрытие заголовка
        dialog.setContentView(R.layout.previewdialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //прозрачный фон диалогового окна
        dialog.setCancelable(false);  //окно нельзя закрыыть кнопкой назад

        //Замена картинки
        ImageView preview = dialog.findViewById(R.id.previewImg);
        preview.setImageResource(R.drawable.previewimage15);
        //Фон
        LinearLayout dialogFone = dialog.findViewById(R.id.dialogfon);
        dialogFone.setBackgroundResource(R.drawable.previewbacground4);

        //Замена текста
        TextView tvDescription = dialog.findViewById(R.id.textDescription);
        tvDescription.setText(R.string.level15);

        textBtnBack();
        buttonContinue();

        dialog.show();
    }

    private void textBtnBack() {
        TextView btnClose = dialog.findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level15.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                } catch (Exception exc) {
                    //  Здесь кода нет
                }
                dialog.dismiss();
            }
        });
    }

    private void buttonContinue() {
        Button btnContinue = dialog.findViewById(R.id.btnContinue);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void dialogEnd() {
        dialogEnd = new Dialog(this);
        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE);  //Скрытие заголовка
        dialogEnd.setContentView(R.layout.dialogendend);
        dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //прозрачный фон диалогового окна
        dialogEnd.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);
        dialogEnd.setCancelable(false);  //окно нельзя закрыыть кнопкой назад

        //Фон
        LinearLayout dialogFone = dialogEnd.findViewById(R.id.dialogfon);
        dialogFone.setBackgroundResource(R.drawable.previewbacground4);

        //Замена текста
        TextView tvDescription = dialogEnd.findViewById(R.id.textDescriptionEnd);
        tvDescription.setText(R.string.level15End);

        textBtnBack2();
        buttonContinue2();
    }

    private void buttonContinue2() {
        Button btnContinue = dialogEnd.findViewById(R.id.btnContinue);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level15.this, Level16.class);
                    startActivity(intent);
                    finish();
                } catch (Exception exc) {
                    //  Здесь кода нет
                }
                dialogEnd.dismiss();
            }
        });
    }

    private void textBtnBack2() {
        TextView btnClose = dialogEnd.findViewById(R.id.btnClose2);
        btnClose.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level15.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                } catch (Exception exc) {
                    //  Здесь кода нет
                }
                dialogEnd.dismiss();
            }
        });
    }

    private void playSoundWin() {
        sounds.play(levelWin,1f,1f,0,0,1f);
    }

    private void playGoodAnswer() {
        Random rnd = new Random();
        int soundNum = rnd.nextInt(8);
        switch (soundNum) {
            case 0:
                sounds.play(goodAnswer1,1f,1f,0,0,1f);
                break;
            case 1:
                sounds.play(goodAnswer2,1f,1f,0,0,1f);
                break;
            case 2:
                sounds.play(goodAnswer3,1f,1f,0,0,1f);
                break;
            case 3:
                sounds.play(goodAnswer4,1f,1f,0,0,1f);
                break;
            case 4:
                sounds.play(goodAnswer5,1f,1f,0,0,1f);
                break;
            case 5:
                sounds.play(goodAnswer6,1f,1f,0,0,1f);
                break;
            case 6:
                sounds.play(goodAnswer7,1f,1f,0,0,1f);
                break;
            default:
                sounds.play(goodAnswer8,1f,1f,0,0,1f);
        }
    }

    private void playBadAnswer() {
        Random rnd = new Random();
        int soundNum = rnd.nextInt(8);
        switch (soundNum) {
            case 0:
                sounds.play(badAnswer1,1f,1f,0,0,1f);
                break;
            case 1:
                sounds.play(badAnswer2,1f,1f,0,0,1f);
                break;
            case 2:
                sounds.play(badAnswer3,1f,1f,0,0,1f);
                break;
            case 3:
                sounds.play(badAnswer4,1f,1f,0,0,1f);
                break;
            case 4:
                sounds.play(badAnswer5,1f,1f,0,0,1f);
                break;
            case 5:
                sounds.play(badAnswer6,1f,1f,0,0,1f);
                break;
            case 6:
                sounds.play(badAnswer7,1f,1f,0,0,1f);
                break;
            default:
                sounds.play(badAnswer8,1f,1f,0,0,1f);
        }
    }

    @Override
    public void onBackPressed() {
        try {
            Intent intent = new Intent(Level15.this, GameLevels.class);
            startActivity(intent);
            finish();
        } catch (Exception exc) {
//                Здесь кода нет
        }
    }
}