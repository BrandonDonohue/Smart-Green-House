package com.example.bmeister.smartgreenapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    // global variables to pass to other Activities
    String URL = "jdbc:mysql://a2ss35.a2hosting.com:3306/teamsgre_TeamsGreen";
    String UserName = "teamgre";
    String Password = "gRl58uJs48";

    public class ConnectionClass{
        String url = "jdbc:mysql://a2ss35.a2hosting.com:3306/teamsgre_TeamsGreen";
        String classs = "com.mysql.jdbc.Driver";
        String DB = "teamsgre_TeamsGreen";
        String user = "teamsgre";
        String password = "gRl58uJs48";

        // returns the Connection object for connecting with the server
        @SuppressLint("NewApi")
        public Connection CONN(){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            Connection conn = null;
            String ConnURL = null;

            try{
                Class.forName(classs);
                conn = DriverManager.getConnection(url, user, password);
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
    }

    private class DoLogin extends AsyncTask<String,String,String>{
        String z = "";
        Boolean isSuccess = false;
        String userid = edtuserid.getText().toString();
        String password = edtpass.getText().toString();

        @Override
        protected void onPreExecute(){
            pbbar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String r){
            pbbar.setVisibility(View.GONE);
            Toast.makeText(MainActivity.this, r, Toast.LENGTH_SHORT).show();

            if(isSuccess){
                //go to home screen
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        }

        protected String doInBackground(String... params){
            if(userid.trim().equals("") || password.trim().equals(""))
                z = "Please enter User ID and Password";
            else{
                try{
                    Connection con = connectionClass.CONN();

                    if(con == null){
                        z = "Error: Could not connect with SQL server";
                    }
                    else{
                        String query = "select * from test";
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(query);

                        if(rs.next()){
                            z = "Login successful";
                            isSuccess = true;
                        }
                        else{
                            z = "Invalid Credentials";
                            isSuccess = false;
                        }
                    }
                }

                catch(Exception ex){
                    isSuccess = false;
                    z = "Exceptions";
                }
            }

            return z;
        }
    }


    // global variables
    ConnectionClass connectionClass;
    EditText edtuserid, edtpass;
    Button btnlogin;
    ProgressBar pbbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectionClass = new ConnectionClass(); //the class file
        edtuserid = (EditText) findViewById(R.id.edtuserid);
        edtpass = (EditText) findViewById(R.id.edtpass);
        btnlogin = (Button) findViewById(R.id.btnlogin);
        pbbar = (ProgressBar) findViewById(R.id.pbbar);
        pbbar.setVisibility(View.GONE);

        btnlogin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                DoLogin doLogin = new DoLogin(); //this is the Asynctask
                doLogin.execute("");
            }
        });
    }
}
