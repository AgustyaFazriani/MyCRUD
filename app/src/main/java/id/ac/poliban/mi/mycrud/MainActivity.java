package id.ac.poliban.mi.mycrud;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Dibawah ini merupakan perintah untuk mendefinikan View
    private EditText etNama;
    private EditText etPosisi;
    private EditText etGaji;
    private Button btTambahPegawai;
    private Button btTampilPegawai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inisialisasi dari View
        etNama = findViewById(R.id.et_nama);
        etPosisi = findViewById(R.id.et_posisi);
        etGaji = findViewById(R.id.et_gaji);
        btTambahPegawai = findViewById(R.id.bt_insert_pegawai);
        btTampilPegawai = findViewById(R.id.bt_get_all_pegawai);
        //Setting listeners to button
        btTambahPegawai.setOnClickListener(this);
        btTampilPegawai.setOnClickListener(this);
    }
    //Dibawah ini merupakan perintah untuk Menambahkan Pegawai (CREATE)
    private void addEmployee() {
        final String name = etNama.getText().toString().trim();
        final String posisi = etPosisi.getText().toString().trim();
        final String gaji = etGaji.getText().toString().trim();
        class AddEmployee extends AsyncTask<Void, Void, String> {
            private ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this,
                        "Menambahkan...", "Tunggu...", false, false);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
            }
            @Override
            protected String doInBackground(Void... v) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Configuration.KEY_EMP_NAMA, name);
                params.put(Configuration.KEY_EMP_POSISI, posisi);
                params.put(Configuration.KEY_EMP_GAJI, gaji);
                RequestHandler rh = new RequestHandler();
                return rh.sendPostRequest(Configuration.URL_ADD, params);
            }
        }
        AddEmployee ae = new AddEmployee();
        ae.execute();
    }
    @Override
    public void onClick(View v) {
        if (v == btTambahPegawai) {
            addEmployee();
        }
        if (v == btTampilPegawai) {
            startActivity(new Intent(this, TampilSemuaPgw.class));
        }
    }
}


