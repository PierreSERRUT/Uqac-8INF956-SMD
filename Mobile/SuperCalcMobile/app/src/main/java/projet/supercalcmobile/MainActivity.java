package projet.supercalcmobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Intent[] activities = new Intent[]{(this, )};
        //startActivities();
        Intent controllerIntent = new Intent(this, ControllerActivity.class);
        startActivity(controllerIntent);

        //Start les autres activités/services
    }
}
