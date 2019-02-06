package com.alphaindelhi.registration;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText_Name, editText_Address, editText_Email, editText_Phone, editText_Password;
    Button signUp;
    String Name, Address, Email, Phone, Password;
    Databasehelper myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new Databasehelper(this);

        editText_Name = findViewById(R.id.editText_Name);
        editText_Address = findViewById(R.id.editText_Address);
        editText_Email = findViewById(R.id.editText_Email);
        editText_Phone = findViewById(R.id.editText_Phone);
        editText_Password = findViewById(R.id.editText_Password);
        signUp = findViewById(R.id.signUp);


        SIgnUp();

        }



    public void SIgnUp()
    {
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                register();
                //AddData();
                viewAll();


            }
        });
    }

    public void register() {
        initialize();
        if (!validate()) {
            Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
        } else {
            AddData();

            //RegisterSuccess();
        }
    }

    public void RegisterSuccess() {
        Toast.makeText(MainActivity.this, "Registration successful!", Toast.LENGTH_SHORT).show();

        Name = Address = Email = Phone = Password = "";
        editText_Name.setText("");
        editText_Address.setText("");
        editText_Email.setText("");
        editText_Phone.setText("");
        editText_Password.setText("");
    }

    private boolean validate() {

        boolean value = true;

        if (Name.isEmpty()) {
            editText_Name.setError("Name is Comppulsory");
            editText_Name.requestFocus();
            value = false;
          //signUp.setEnabled(false);
        }
        if (Address.isEmpty()) {
            editText_Address.setError("Address is Comppulsory");
            editText_Address.requestFocus();
            value = false;
           // signUp.setEnabled(false);
        }

        if (Email.isEmpty()) {
            editText_Email.setError("Email is Comppulsory");
            editText_Email.requestFocus();
            value = false;
          //  signUp.setEnabled(false);
        }


        if (Phone.isEmpty()) {
            editText_Phone.setError("Mobile number is Comppulsory");
            editText_Phone.requestFocus();
            value = false;
            //signUp.setEnabled(false);
        }
        if (Password.isEmpty()) {
            editText_Password.setError("Password is Comppulsory");
            editText_Password.requestFocus();
            value = false;
         //   signUp.setEnabled(false);
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            editText_Email.setError("Please enter a valid email address!!");
            editText_Email.requestFocus();
            value = false;
        }

        if (Password.length() < 8 && Password.length() >= 15) {
            editText_Password.setError("Password can't be less than 8 characters and less than 15 and must contain atleast one uppercase letter");
            editText_Password.requestFocus();
            value = false;
        }

        if (Address.length() < 10)

        {
            editText_Address.setError("Address can't be less than 10 characters.");
            editText_Address.requestFocus();
            value = false;
        }

        if (Phone.length() < 10) {
            editText_Phone.setError("Mobile No. can't be less than 10 numbers");
            editText_Phone.requestFocus();
            value = false;
        }

        return value;
    }

    public void initialize() {
        Name = editText_Name.getText().toString().trim();
        Address = editText_Address.getText().toString().trim();
        Email = editText_Email.getText().toString().trim();
        Phone = editText_Phone.getText().toString().trim();
        Password = editText_Password.getText().toString().trim();

    }


    public void AddData() {


                boolean isInserted = myDB.insertData(editText_Name.getText().toString(), editText_Address.getText().toString(), editText_Email.getText().toString(),editText_Phone.getText().toString());
                if (isInserted == true) {
                    RegisterSuccess();

                } else {
                    Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }

    }

    public void viewAll(){

                  Cursor cursor = myDB.getAllData();
                if(cursor.getCount()==0)
                {
                    showMessage("Error" , "nothing found in DATABASE");
                }



                    StringBuffer buffer = new StringBuffer();


                    while (cursor.moveToNext()) {
                        buffer.append("ID :" + cursor.getString(0) + "\n");
                        buffer.append("Name :" + cursor.getString(1) + "\n");
                        buffer.append("Address :" + cursor.getString(2) + "\n");
                        buffer.append("Email :" + cursor.getString(3) + "\n \n");
                        buffer.append("Phone :" + cursor.getString(4) + "\n \n");
                    }

                    showMessage("All data", buffer.toString());


        }

        private void showMessage(String title, String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.create();
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }
}
