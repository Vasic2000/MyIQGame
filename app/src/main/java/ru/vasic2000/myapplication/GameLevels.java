package ru.vasic2000.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameLevels extends AppCompatActivity {
    int level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_levels);

        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
        level = save.getInt("Level", 1);

        FullScreen();
        BtnBack();
        btnTv1();
        btnTv2();
        btnTv3();
        btnTv4();
        btnTv5();
        btnTv6();
        btnTv7();
        btnTv8();
        btnTv9();
        btnTv10();
        btnTv11();
        btnTv12();
        btnTv13();
        btnTv14();
        btnTv15();
        btnTv16();
    }

    private void btnTv1() {
        TextView tv1 = findViewById(R.id.tv1);
        if(level >= 1)
            tv1.setText("1");
        tv1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {
                    if (level >= 1) {
                        Intent intent = new Intent(GameLevels.this, Level1.class);
                        startActivity(intent);
                        finish();
                    } else {

                    }
                } catch (Exception e) {
                }
            }
        });
    }

    private void btnTv2() {
        TextView tv2 = findViewById(R.id.tv2);
        if(level >= 2)
            tv2.setText("2");
        tv2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {
                    if (level >= 2) {
                        Intent intent = new Intent(GameLevels.this, Level2.class);
                        startActivity(intent);
                        finish();
                    } else {

                    }
                } catch (Exception e) {
                }
            }
        });
    }

    private void btnTv3() {
        TextView tv3 = findViewById(R.id.tv3);
        if(level >= 3)
            tv3.setText("3");
        tv3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {
                    if (level >= 3) {
                        Intent intent = new Intent(GameLevels.this, Level3.class);
                        startActivity(intent);
                        finish();
                    } else {

                    }
                } catch (Exception e) {
                }
            }
        });
    }

    private void btnTv4() {
        TextView tv4 = findViewById(R.id.tv4);
        if(level >= 4)
            tv4.setText("4");
        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (level >= 4) {
                        Intent intent = new Intent(GameLevels.this, Level4.class);
                        startActivity(intent);
                        finish();
                    } else {

                    }
                } catch (Exception e) {
                }
            }
        });
    }

    private void btnTv5() {
        TextView tv5 = findViewById(R.id.tv5);
        if(level >= 5)
            tv5.setText("5");
        tv5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {
                    if (level >= 5) {
                        Intent intent = new Intent(GameLevels.this, Level5.class);
                        startActivity(intent);
                        finish();
                    } else {

                    }
                } catch (Exception e) {
                }
            }
        });
    }

    private void btnTv6() {
        TextView tv6 = findViewById(R.id.tv6);
        if(level >= 6)
            tv6.setText("6");
        tv6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {
                    if (level >= 6) {
                    Intent intent = new Intent(GameLevels.this, Level6.class);
                    startActivity(intent);
                    finish();
                    } else {

                    }
                } catch (Exception e) {
                }
            }
        });
    }

    private void btnTv7() {
        TextView tv7 = findViewById(R.id.tv7);
        if(level >= 7)
            tv7.setText("7");
        tv7.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {
                    if (level >= 7) {
                    Intent intent = new Intent(GameLevels.this, Level7.class);
                    startActivity(intent);
                    finish();
                    } else {

                    }
                } catch (Exception e) {
                }
            }
        });
    }

    private void btnTv8() {
        TextView tv8 = findViewById(R.id.tv8);
        if(level >= 8)
            tv8.setText("8");
        tv8.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {
                    if (level >= 8) {
                    Intent intent = new Intent(GameLevels.this, Level8.class);
                    startActivity(intent);
                    finish();
                    } else {

                    }
                } catch (Exception e) {
                }
            }
        });
    }

    private void btnTv9() {
        TextView tv9 = findViewById(R.id.tv9);
        if(level >= 9)
            tv9.setText("9");
        tv9.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {
                    if (level >= 9) {
                    Intent intent = new Intent(GameLevels.this, Level9.class);
                    startActivity(intent);
                    finish();
                    } else {

                    }
                } catch (Exception e) {
                }
            }
        });
    }

    private void btnTv10() {
        TextView tv10 = findViewById(R.id.tv10);
        if(level >= 10)
            tv10.setText("10");
        tv10.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {
                    if (level >= 10) {
                        Intent intent = new Intent(GameLevels.this, Level10.class);
                        startActivity(intent);
                        finish();
                    } else {

                    }
                } catch (Exception e) {
                }
            }
        });
    }

    private void btnTv11() {
        TextView tv11 = findViewById(R.id.tv11);
        if(level >= 11)
            tv11.setText("11");
        tv11.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {
                    if (level >= 11) {
                    Intent intent = new Intent(GameLevels.this, Level11.class);
                    startActivity(intent);
                    finish();
                } else {

                }
                } catch (Exception e) {
                }
            }
        });
    }

    private void btnTv12() {
        TextView tv12 = findViewById(R.id.tv12);
        if(level >= 12)
            tv12.setText("12");
        tv12.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {
                    if (level >= 12) {
                    Intent intent = new Intent(GameLevels.this, Level12.class);
                    startActivity(intent);
                    finish();
                    } else {

                    }
                } catch (Exception e) {
                }
            }
        });
    }

    private void btnTv13() {
        TextView tv13 = findViewById(R.id.tv13);
        if(level >= 13)
            tv13.setText("13");
        tv13.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {
                    if (level >= 13) {
                    Intent intent = new Intent(GameLevels.this, Level13.class);
                    startActivity(intent);
                    finish();
                    } else {

                    }
                } catch (Exception e) {
                }
            }
        });
    }

    private void btnTv14() {
        TextView tv14 = findViewById(R.id.tv14);
        if(level >= 14)
            tv14.setText("14");
        tv14.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {
                    if (level >= 14) {
                    Intent intent = new Intent(GameLevels.this, Level14.class);
                    startActivity(intent);
                    finish();
                    } else {

                    }
                } catch (Exception e) {
                }
            }
        });
    }

    private void btnTv15() {
        TextView tv15 = findViewById(R.id.tv15);
        if(level >= 15)
            tv15.setText("15");
        tv15.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {
                    if (level >= 15) {
                    Intent intent = new Intent(GameLevels.this, Level15.class);
                    startActivity(intent);
                    finish();
                    } else {

                    }
                } catch (Exception e) {
                }
            }
        });
    }

    private void btnTv16() {
        TextView tv16 = findViewById(R.id.tv16);
        if(level >= 16)
            tv16.setText("16");
            tv16.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {
                    if (level >= 16) {
                        Intent intent = new Intent(GameLevels.this, Level16.class);
                        startActivity(intent);
                        finish();
                    } else {

                    }
                } catch (Exception e) {
                }
            }
        });
    }

    private void FullScreen() {
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public void onBackPressed() {
        btnBackPress();
    }

    private void btnBackPress() {
        try {
            Intent intent = new Intent(GameLevels.this, MainActivity.class);
            startActivity(intent);
            finish();
        } catch (Exception e) {
        }
    }

    private void BtnBack() {
        Button btnBack = findViewById(R.id.button_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnBackPress();
            }
        });
    }
}
