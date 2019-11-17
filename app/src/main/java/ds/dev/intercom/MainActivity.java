package ds.dev.intercom;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText et_call;
    Button btn_call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_call = findViewById(R.id.call_et);
        btn_call = findViewById(R.id.call_btn);

       // blockPeriod();

        Dexter.withActivity(MainActivity.this).withPermission(Manifest.permission.CALL_PHONE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        btn_call.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String call = "+914522443" + et_call.getText().toString();
                                Intent callIntent = new Intent(Intent.ACTION_CALL);
                                callIntent.setData(Uri.parse("tel:" + call));//change the number
                                startActivity(callIntent);
                            }
                        });
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        Toast.makeText(MainActivity.this, "You need this Permission to call", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                }).check();

    }

    /*private void blockPeriod() {
        long currentTime = System.currentTimeMillis();

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR,2020);
        cal.set(Calendar.MONTH,2);
        cal.set(Calendar.DAY_OF_MONTH,1);
        cal.set(Calendar.HOUR,9);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);

        long blockTime = cal.getTimeInMillis();

        if (currentTime >= blockTime){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Alert")
                    .setMessage("Contact the Administrator for more info")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            finish();
                        }
                    }).setCancelable(false).create().show();
        }
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.about, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Developed By")
                        .setMessage("S.Dhanuvanth\ndhanuvanth@gmail.com\n+91 7010 384 896")
                        .create().show();
                break;
        }
        return true;
    }
}
