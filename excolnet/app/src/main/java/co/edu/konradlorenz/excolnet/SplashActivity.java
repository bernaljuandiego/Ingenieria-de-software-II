package co.edu.konradlorenz.excolnet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    // SplashActivity screen pause time
                    while (waited < 1000) {
                        sleep(240);
                        waited += 100;
                    }
                    Intent intent = new Intent(SplashActivity.this,
                            LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    SplashActivity.this.finish();
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    SplashActivity.this.finish();
                }

            }
        };
        splashTread.start();

    }



}
