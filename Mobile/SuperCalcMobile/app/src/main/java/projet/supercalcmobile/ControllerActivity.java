package projet.supercalcmobile;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

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


    private static final int CREATE_REQUEST_CODE = 40;
    private static final int OPEN_REQUEST_CODE = 41;
    private static final int SAVE_REQUEST_CODE = 42;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO: If logged, home else login
        setLoginActivity();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }

    @Override
    public void onClick(View v) {
        //TODO : need to save context
        switch (v.getId()) {
            //Home
            case R.id.home_home_button:
                //Nothing
                break;
            case R.id.home_logout_button:
                //TODO
                setLoginActivity();
                break;
            case R.id.home_settings_button:
                //TODO
                setSettingsActivity();
                break;

            //Login
            case R.id.login_createaccount_button:
                //TODO
                setAccountCreationActivity();
                break;
            case R.id.login_login_button:
                //TODO
                setHomeActivity();
                break;

            //Settings
            case R.id.settings_batterythreshold_seekbar:
                //TODO
                break;
            case R.id.settings_home_button:
                //TODO
                setHomeActivity();
                break;
            case R.id.settings_settings_button:
                //Nothing
                break;
            case R.id.settings_logout_button:
                //TODO
                setLoginActivity();
                break;

            //Account creation
            case R.id.accountcreation_createaccount_button:
                //TODO
                break;
            case R.id.accountcreation_backtologin_button:
                //TODO
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
        loginCreateAccountButton.setOnClickListener(this);
        loginLoginButton.setOnClickListener(this);
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
        settingsHomeButton = (Button) findViewById(R.id.settings_home_button);
        settingsSettingsButton = (Button) findViewById(R.id.settings_settings_button);
        settingsLogoutButton = (Button) findViewById(R.id.settings_logout_button);

        final TextView seekBarValue = (TextView)findViewById(R.id.settings_batterythresholdvalue_text);
        //TODO:set text to user threshold value
        settingsBatteryThresholdSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //Set limit to progress
                if(progress < 5) {
                    seekBar.setProgress(5);
                    progress = 5;
                }
                seekBarValue.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        settingsBatteryThresholdSeekbar.setOnClickListener(this);
        settingsHomeButton.setOnClickListener(this);
        settingsSettingsButton.setOnClickListener(this);
        settingsLogoutButton.setOnClickListener(this);
    }

    void setAccountCreationActivity()
    {
        setContentView(R.layout.activity_accountcreation);
        accountCreationCreateAccountButton = (Button) findViewById(R.id.accountcreation_createaccount_button);
        accountCreationBackToLoginButton = (Button) findViewById(R.id.accountcreation_backtologin_button);
        accountCreationCreateAccountButton.setOnClickListener(this);
        accountCreationBackToLoginButton.setOnClickListener(this);
    }

/*
    public void openFile(View view)
    {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/plain");
        startActivityForResult(intent, OPEN_REQUEST_CODE);
    }

    public void onActivityResult(int requestCode, int resultCode,
                                 Intent resultData) {

        Uri currentUri = null;

        if (resultCode == Activity.RESULT_OK)
        {

            if (requestCode == CREATE_REQUEST_CODE)
            {
                if (resultData != null) {
                    textView.setText("");
                }
            } else if (requestCode == SAVE_REQUEST_CODE) {

                if (resultData != null) {
                    currentUri = resultData.getData();
                    writeFileContent(currentUri);
                }
            } else if (requestCode == OPEN_REQUEST_CODE)
            {

                if (resultData != null) {
                    currentUri = resultData.getData();

                    try {
                        String content =
                                readFileContent(currentUri);
                        textView.setText(content);
                    } catch (IOException e) {
                        // Handle error here
                    }
                }
            }
        }
    }*/
}
