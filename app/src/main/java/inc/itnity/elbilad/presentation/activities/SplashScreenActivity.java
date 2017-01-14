package inc.itnity.elbilad.presentation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import inc.itnity.elbilad.R;

public class SplashScreenActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash_screen);

    Intent intent = new Intent(this, MainActivity.class);
    startActivity(intent);
  }

}
