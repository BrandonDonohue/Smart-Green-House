package com.example.bmeister.smartgreenapp;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class EnvironmentActivity extends AppCompatActivity {

    String url = "jdbc:mysql://a2ss35.a2hosting.com:3306/teamsgre_TeamsGreen";
    String classs = "com.mysql.jdbc.Driver";
    String DB = "teamsgre_TeamsGreen";
    String user = "teamsgre";
    String password = "gRl58uJs48";

    public Connection CONN(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Connection conn = null;
        String ConnURL = null;
        try{
            Class.forName(classs);
            conn = DriverManager.getConnection(url, user, password);

            //pull temperature
            String queryTemp = "SELECT Temp FROM ActualValues";
            java.sql.Statement stmt = conn.createStatement();
            ResultSet rsTemp = stmt.executeQuery(queryTemp);

            //set temp edittext field
            while(rsTemp.next()){
                String temperature = rsTemp.getString("Temp");
                EditText editText = (EditText)findViewById(R.id.edtTemp);
                editText.setText(temperature + " F", TextView.BufferType.EDITABLE);
            }
            // pull from ActualValues; update into TargetValues
            //pull humidity
            String queryHumid = "SELECT Humidity FROM ActualValues";
            java.sql.Statement stmtH = conn.createStatement();
            ResultSet rsHumid = stmtH.executeQuery(queryHumid);

            //set humidity edittext field
            while(rsHumid.next()){
                String humid = rsHumid.getString("Humidity");
                EditText editText = (EditText)findViewById(R.id.edthumid);
                editText.setText(humid + " g/m3", TextView.BufferType.EDITABLE);
            }

            //pull soil moisture average for pot A
            String queryGA = "SELECT GroupAavg FROM ActualValues";
            java.sql.Statement stmtGA = conn.createStatement();
            ResultSet rsGA = stmtGA.executeQuery(queryGA);

            //set groupA edittext field
            while(rsGA.next()){
                String sGA = rsGA.getString("GroupAavg");
                EditText editText = (EditText)findViewById(R.id.edtsoil1);
                editText.setText(sGA + " m3", TextView.BufferType.EDITABLE);
            }

            //pull soil moisture average for pot B
            String queryGB = "SELECT GroupBavg FROM ActualValues";
            java.sql.Statement stmtGB = conn.createStatement();
            ResultSet rsGB = stmtGB.executeQuery(queryGB);

            //set sGB edittext field
            while(rsGB.next()){
                String sGB = rsGB.getString("GroupBavg");
                EditText editText = (EditText)findViewById(R.id.edtsoil2);
                editText.setText(sGB + " m3", TextView.BufferType.EDITABLE);
            }

            //pull soil moisture average from pot C
            String queryGC = "SELECT GroupCavg FROM ActualValues";
            java.sql.Statement stmtGC = conn.createStatement();
            ResultSet rsGC = stmtGC.executeQuery(queryGC);

            //set sGC edittext field
            while(rsGC.next()){
                String sGC = rsGC.getString("GroupCavg");
                EditText editText = (EditText)findViewById(R.id.edtsoil3);
                editText.setText(sGC + " m3", TextView.BufferType.EDITABLE);
            }

            //pull soil moisture average from pot D
            String queryGD = "SELECT GroupDavg FROM TargetValues";
            java.sql.Statement stmtGD = conn.createStatement();
            ResultSet rsGD = stmtGD.executeQuery(queryGD);

            //set sGD edittext field
            while(rsGD.next()){
                String sGD = rsGD.getString("GroupDavg");
                EditText editText = (EditText)findViewById(R.id.edtsoil4);
                editText.setText(sGD + " m3", TextView.BufferType.EDITABLE);
            }




        }


        catch(SQLException se){
            Log.e("ERROR", se.getMessage());
        }

        catch(ClassNotFoundException e){
            Log.e("ERROR", e.getMessage());
        }

        catch(Exception e){
            Log.e("ERROR", e.getMessage());
        }

        return conn;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_environment);

        //pull temerature from database
        Connection conn = CONN();

        //go back to homescreen
        Button back = (Button)findViewById(R.id.btnback);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });

        //log out
        Button logout = (Button)findViewById(R.id.btnlogout);
        logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Runnable clickButton = new Runnable(){
                    @Override
                    public void run(){
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                };
                logout.postDelayed(clickButton, 2000);

            }
        });
    }
}
