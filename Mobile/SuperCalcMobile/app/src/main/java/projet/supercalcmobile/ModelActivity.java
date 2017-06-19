package projet.supercalcmobile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ModelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onNewIntent(Intent intent){
        //TODO
        //get pref to change or update

    }

    @Override
    public void onStop()
    {
        super.onStop();
    }
    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }
    //Gère la classe User (Parcelable), gère les intents du contrôleur (et de la vue ?)


}
