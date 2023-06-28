package com.example.laguardia_finals;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final int CAMERA_PERMISSION_CODE = 100;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView imgPreview;
    private EditText Fname;
    private EditText Lname;
    private EditText email;
    private CheckBox male;
    private CheckBox female;
    private EditText phone;
    private EditText height;
    private EditText weight;
    private CheckBox single;
    private CheckBox married;
    private CheckBox separated;
    private CheckBox widowed;
    private CheckBox others;
    private EditText pi;
    private EditText tin;
    private EditText ph;
    private EditText gsis;
    private EditText EmergencyName;
    private EditText EmergencyContact;
    private Spinner relation;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Personal Background");

        Fname = findViewById(R.id.Fname);
        Lname = findViewById(R.id.Lname);
        email = findViewById(R.id.email);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        phone = findViewById(R.id.phone);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        single = findViewById(R.id.single);
        married = findViewById(R.id.married);
        separated = findViewById(R.id.separated);
        widowed = findViewById(R.id.widowed);
        others = findViewById(R.id.others);
        pi = findViewById(R.id.pi);
        tin = findViewById(R.id.tin);
        ph = findViewById(R.id.ph);
        gsis = findViewById(R.id.gsis);
        EmergencyName = findViewById(R.id.EmergencyName);
        EmergencyName = findViewById(R.id.EmergencyContact);
        submit = findViewById(R.id.submit);

        Spinner relation = findViewById(R.id.relation);
        ArrayAdapter<CharSequence> ArrayAdpterr = ArrayAdapter.createFromResource(this, R.array.Relationship, android.R.layout.simple_spinner_item);
        ArrayAdpterr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        relation.setAdapter(ArrayAdpterr);
        relation.setOnItemSelectedListener(this);

        Button btnCapture = findViewById(R.id.btnCapture);
        imgPreview = findViewById(R.id.imgPreview);

        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestCameraPermission();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

try {

    String Firstname = Fname.getText().toString();
    String Lastname = Lname.getText().toString();
    String Email = email.getText().toString();
    String Phone = phone.getText().toString();
    String Height = height.getText().toString();
    String Weight = weight.getText().toString();
    String emergencyName = EmergencyName.getText().toString();
    String emergencyContact = EmergencyContact.getText().toString();

    boolean isMale = male.isChecked();
    boolean isFemale = female.isChecked();
    boolean isSingle = single.isChecked();
    boolean isMarried = married.isChecked();
    boolean isSeparated = separated.isChecked();
    boolean isWidowed = widowed.isChecked();
    boolean isOthers = others.isChecked();

    String selectedItem = relation.getSelectedItem().toString();

    if (Firstname.isEmpty() || Lastname.isEmpty() || Email.isEmpty() ||
            (!isMale && !isFemale) || Phone.isEmpty() || Height.isEmpty() || Weight.isEmpty() ||
            (!isSingle && !isMarried && !isSeparated && !isWidowed && !isOthers) ||
            emergencyName.isEmpty() || emergencyContact.isEmpty()) {

        Toast.makeText(MainActivity.this, "Please fill in all necessary information", Toast.LENGTH_SHORT).show();
    } else {
        Intent i = new Intent(MainActivity.this, Education.class);
        startActivity(i);
        Toast.makeText(MainActivity.this, "Information submitted successfully", Toast.LENGTH_SHORT).show();
    }
        } catch (Exception e){
        e.printStackTrace();
                }

            }
        });
    }

    private void requestCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.CAMERA},
                    CAMERA_PERMISSION_CODE
            );
        } else {
            openCamera();
        }
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            String[] permissions,
            int[] grantResults
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            } else {
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Open the camera
    private void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

    // Handle image capture result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imgPreview.setImageBitmap(imageBitmap);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
