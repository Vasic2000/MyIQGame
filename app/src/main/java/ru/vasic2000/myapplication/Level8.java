package ru.vasic2000.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

public class Level8 extends AppCompatActivity {

    Dialog dialog;
    Dialog dialogEnd;

    ImageView backGround;

    public int numLeft;  //Номер левой картинки
    public int numRight; //Номер правой картинки
    ImageView imgLeft;
    ImageView imgRight;
    TextView textLevels;
    TextView left_text;
    TextView right_text;

    public int count = 0;  //Счётчик правильных ответов

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);

        initComponents();
        imgRoundCorners();
        FullScreen();
        previewDialog();
        dialogEnd();
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

        backGround.setImageResource(R.drawable.level_background);

        textLevels.setTextColor(getResources().getColor(R.color.colorWhite));
        textLevels.setText(R.string.level_8);

        left_text.setTextColor(getResources().getColor(R.color.colorWhite));
        right_text.setTextColor(getResources().getColor(R.color.colorWhite));

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
        final Animation an = AnimationUtils.loadAnimation(Level8.this, R.anim.alpha);

        numLeft = rnd.nextInt(14);
        imgLeft.setImageResource(array.images8[numLeft]);
        imgLeft.setAnimation(an);
        left_text.setText(array.text8[numLeft]);

        numRight = rnd.nextInt(14);
        while(numRight == numLeft) {
            numRight = rnd.nextInt(14);
        }
        imgRight.setImageResource(array.images8[numRight]);
        imgRight.setAnimation(an);
        right_text.setText(array.text8[numRight]);
    }

    private void backFromLevel() {
        Button btn_Back = findViewById(R.id.btnBack);
        btn_Back.setBackgroundResource(R.drawable.style_btn_whitex_blue_press);
        btn_Back.setTextColor(getResources().getColor(R.color.colorWhite));
        btn_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level8.this, GameLevels.class);
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
        preview.setImageResource(R.drawable.previewimage8);
        //Фон
        LinearLayout dialogFone = dialog.findViewById(R.id.dialogfon);
        dialogFone.setBackgroundResource(R.drawable.previewspace);

        //Замена текста
        TextView tvDescription = dialog.findViewById(R.id.textDescription);
        tvDescription.setText(R.string.level8);

        textBtnBack();

        buttonContinue();

        dialog.show();
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

    private void textBtnBack() {
        TextView btnClose = dialog.findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level8.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                } catch (Exception exc) {
//                Здесь кода нет
                }
                dialog.dismiss();
            }
        });
    }

    private void dialogEnd() {
        dialogEnd = new Dialog(this);
        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE);  //Скрытие заголовка
        dialogEnd.setContentView(R.layout.dialogend);
        dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //прозрачный фон диалогового окна
        dialogEnd.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);
        dialogEnd.setCancelable(false);  //окно нельзя закрыыть кнопкой назад

        //Фон
        LinearLayout dialogFone = dialogEnd.findViewById(R.id.dialogfon);
        dialogFone.setBackgroundResource(R.drawable.previewspace);

        //Замена текста
        TextView tvDescription = dialogEnd.findViewById(R.id.textDescriptionEnd);
        tvDescription.setText(R.string.level8End);

        textBtnBack3();
        buttonContinue3();
    }

    private void buttonContinue3() {
        Button btnContinue = dialogEnd.findViewById(R.id.btnContinue);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level8.this, Level11.class);
                    startActivity(intent);
                    finish();
                } catch (Exception exc) {
                    //  Здесь кода нет
                }
                dialogEnd.dismiss();
            }
        });
    }

    private void textBtnBack3() {
        TextView btnClose = dialogEnd.findViewById(R.id.btnClose2);
        btnClose.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Level8.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                } catch (Exception exc) {
                    //  Здесь кода нет
                }
                dialogEnd.dismiss();
            }
        });
    }

    @Override
    public void onBackPressed() {
        try {
            Intent intent = new Intent(Level8.this, GameLevels.class);
            startActivity(intent);
            finish();
        } catch (Exception exc) {
//                Здесь кода нет
        }
    }
}