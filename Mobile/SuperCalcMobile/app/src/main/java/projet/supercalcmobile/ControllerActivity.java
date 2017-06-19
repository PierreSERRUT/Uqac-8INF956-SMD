package projet.supercalcmobile;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ControllerActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener{

    private Button homeHomeButton = null;
    private Button homeLogoutButton = null;
    private Button homeSettingsButton = null;

    private Button loginCreateAccountButton = null;
    private Button loginLoginButton = null;

    private SeekBar settingsBatteryThresholdSeekbar = null;
    private Button settingsHomeButton = null;
    private Button settingsSettingsButton = null;
    private Button settingsLogoutButton = null;

    private Button accountCreationCreateAccountButton = null;
    private Button accountCreationBackToLoginButton = null;

    public static final String PREFS_FILE_NAME = "Settings";
    public static final int BATTERY_THRESHOLD_DEFAULT = 20;
    public static final int BATTERY_THRESHOLD_MIN = 5;
    SharedPreferences settings = null;
    SharedPreferences.Editor editor = null;
    private CheckBox loginStayConnectedCheckBox = null;
    private TextView settingsBatteryThresholdValueText = null;
    private CheckBox settingsDataUsageCheckBox = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settings = getSharedPreferences(PREFS_FILE_NAME, 0);
        editor = settings.edit();
        //Create a file if doesn't exist
        editor.commit();
        //TODO : see server connection
        if(settings.getBoolean("StayConnected", false))
        {
            setHomeActivity();
        }
        else
        {
            setLoginActivity();
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }

    @Override
    public void onClick(View v) {
        //TODO : need to save context
        //TODO : put editor.commit(s) elsewhere
        switch (v.getId()) {
            //Home
            case R.id.home_home_button:
                //Nothing
                break;
            case R.id.home_logout_button:
                //TODO : remove login info
                setLoginActivity();
                break;
            case R.id.home_settings_button:
                setSettingsActivity();
                break;

            //Login
            case R.id.login_createaccount_button:
                setAccountCreationActivity();
                break;
            case R.id.login_login_button:
                //TODO : send Intent to ServerCom to check login info
                //TODO : save login info if StayConnected checked
                setHomeActivity();
                break;
            case R.id.login_stayconnected_checkbox:
                editor.putBoolean("StayConnected", loginStayConnectedCheckBox.isChecked());
                editor.commit();
                break;

            //Settings
            case R.id.settings_datausage_checkbox:
                editor.putBoolean("DataUsage", settingsDataUsageCheckBox.isChecked());
                editor.commit();
                break;
            case R.id.settings_home_button:
                setHomeActivity();
                break;
            case R.id.settings_settings_button:
                //Nothing
                break;
            case R.id.settings_logout_button:
                //TODO : remove login info
                setLoginActivity();
                break;

            //Account creation
            case R.id.accountcreation_createaccount_button:
                //TODO : send Intent to ServerCommunication to send a request to server
                //TODO : display a toast "Account created" on callback/Intent if relevant
                break;
            case R.id.accountcreation_backtologin_button:
                setLoginActivity();
                break;
        }
    }

    @Override
    public void onNewIntent(Intent intent){
        //TODO
    }

    void setLoginActivity()
    {
        setContentView(R.layout.activity_login);

        loginCreateAccountButton = (Button) findViewById(R.id.login_createaccount_button);
        loginLoginButton = (Button) findViewById(R.id.login_login_button);
        loginStayConnectedCheckBox = (CheckBox) findViewById(R.id.login_stayconnected_checkbox);

        loginCreateAccountButton.setOnClickListener(this);
        loginLoginButton.setOnClickListener(this);
        loginStayConnectedCheckBox.setOnClickListener(this);

        //Read data
        loginStayConnectedCheckBox.setChecked(settings.getBoolean("StayConnected",false));

    }

    void setHomeActivity()
    {
        setContentView(R.layout.activity_home);

        homeHomeButton = (Button) findViewById(R.id.home_home_button);
        homeLogoutButton = (Button) findViewById(R.id.home_logout_button);
        homeSettingsButton = (Button) findViewById(R.id.home_settings_button);

        homeHomeButton.setOnClickListener(this);
        homeLogoutButton.setOnClickListener(this);
        homeSettingsButton.setOnClickListener(this);
    }

    void setSettingsActivity()
    {
        setContentView(R.layout.activity_settings);

        settingsBatteryThresholdSeekbar = (SeekBar) findViewById(R.id.settings_batterythreshold_seekbar);
        settingsDataUsageCheckBox = (CheckBox) findViewById(R.id.settings_datausage_checkbox);
        settingsHomeButton = (Button) findViewById(R.id.settings_home_button);
        settingsSettingsButton = (Button) findViewById(R.id.settings_settings_button);
        settingsLogoutButton = (Button) findViewById(R.id.settings_logout_button);
        settingsBatteryThresholdValueText = (TextView)findViewById(R.id.settings_batterythresholdvalue_text);

        settingsBatteryThresholdSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //Set limit to progress
                if(progress < BATTERY_THRESHOLD_MIN) {
                    seekBar.setProgress(BATTERY_THRESHOLD_MIN);
                }
                settingsBatteryThresholdValueText.setText(String.valueOf(seekBar.getProgress()));
                editor.putInt("BatteryThreshold", settingsBatteryThresholdSeekbar.getProgress());
                editor.commit();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        settingsBatteryThresholdSeekbar.setOnClickListener(this);
        settingsDataUsageCheckBox.setOnClickListener(this);
        settingsHomeButton.setOnClickListener(this);
        settingsSettingsButton.setOnClickListener(this);
        settingsLogoutButton.setOnClickListener(this);

        //Read data
        settingsDataUsageCheckBox.setChecked(settings.getBoolean("DataUsage",false));
        settingsBatteryThresholdValueText.setText(String.valueOf(settings.getInt("BatteryThreshold", BATTERY_THRESHOLD_DEFAULT)));
        settingsBatteryThresholdSeekbar.setProgress(settings.getInt("BatteryThreshold", BATTERY_THRESHOLD_DEFAULT));
    }

    void setAccountCreationActivity()
    {
        setContentView(R.layout.activity_accountcreation);

        accountCreationCreateAccountButton = (Button) findViewById(R.id.accountcreation_createaccount_button);
        accountCreationBackToLoginButton = (Button) findViewById(R.id.accountcreation_backtologin_button);

        accountCreationCreateAccountButton.setOnClickListener(this);
        accountCreationBackToLoginButton.setOnClickListener(this);
    }

    void savePreferences()
    {
        //TODO
        editor.putBoolean("StayConnected", loginStayConnectedCheckBox.isChecked());
        editor.putInt("BatteryThreshold", settingsBatteryThresholdSeekbar.getProgress());
        editor.putBoolean("DataUsage", settingsDataUsageCheckBox.isChecked());
        editor.commit();
    }
}
