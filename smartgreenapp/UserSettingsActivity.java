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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserSettingsActivity extends AppCompatActivity {

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

            //get edittext fields
            EditText temperature = (EditText)findViewById(R.id.edtTemp);
            EditText humidity = (EditText)findViewById(R.id.edthumid);
            EditText soilA = (EditText)findViewById(R.id.edtsoil1);
            EditText soilB = (EditText)findViewById(R.id.edtsoil2);
            EditText soilC = (EditText)findViewById(R.id.edtsoil3);
            EditText soilD = (EditText)findViewById(R.id.edtsoil4);

            //get user input from edittext fields
            float tempPass = Float.valueOf(temperature.getText().toString());
            float humidPass = Float.valueOf(humidity.getText().toString());
            float sAPass = Float.valueOf(soilA.getText().toString());
            float sBPass = Float.valueOf(soilB.getText().toString());
            float sCPass = Float.valueOf(soilC.getText().toString());
            float sDPass = Float.valueOf(soilD.getText().toString());
//isn't working. start here
            //update TargetValues with tempPass
            String updateTemp = "UPDATE TargetValues SET Temp = ? ";
            PreparedStatement stmttemp = conn.prepareStatement(updateTemp);
            stmttemp.setFloat(0, tempPass);
            stmttemp.executeUpdate();
            /*
            //update TargetValues with humidPass
            String updateHumid = "UPDATE TargetValues SET Humidity = ? ";
            PreparedStatement stmthumid = conn.prepareStatement(updateHumid);
            stmthumid.setFloat(1, humidPass);
            stmthumid.executeUpdate();

            //update TargetValues with sAPass
            String updateA = "UPDATE TargetValues SET GroupAavg = ? ";
            PreparedStatement stmtA = conn.prepareStatement(updateA);
            stmtA.setFloat(1, sAPass);
            stmtA.executeUpdate();

            //update TargetValues with sBPass
            String updateB = "UPDATE TargetValues SET GroupBavg = ? ";
            PreparedStatement stmtB = conn.prepareStatement(updateB);
            stmtB.setFloat(1, sBPass);
            stmtB.executeUpdate();

            //update TargetValues with sCPass
            String updateC = "UPDATE TargetValues SET GroupCavg = ? ";
            PreparedStatement stmtC = conn.prepareStatement(updateC);
            stmtC.setFloat(1, sCPass);
            stmtC.executeUpdate();

            //update TargetValues with sDPass
            String updateD = "UPDATE TargetValues SET GroupDavg = ? ";
            PreparedStatement stmtD = conn.prepareStatement(updateD);
            stmtD.setFloat(1, sDPass);
            stmtD.executeUpdate();
            */
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
        setContentView(R.layout.activity_user_settings);


        //go back to homescreen
        Button back = (Button)findViewById(R.id.btnback);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });

        Button accept = (Button)findViewById(R.id.btnaccept);
        accept.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //issues UPDATE query on TargetValues.
                Connection conn = CONN();
            }
        });
    }
}
