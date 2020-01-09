package id.ac.poliban.mi.mycrud;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class TampilPegawai extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextName;
    private EditText editTextDesg;
    private EditText editTextSalary;
    private Button buttonUpdate;
    private Button buttonDelete;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_pegawai);

        Intent intent = getIntent();
        id = intent.getStringExtra(Configuration.EMP_ID);
        EditText editTextId = findViewById(R.id.editTextId);
        editTextName = findViewById(R.id.et_nama);
        editTextDesg = findViewById(R.id.et_posisi);
        editTextSalary = findViewById(R.id.et_gaji);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonUpdate.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);
        editTextId.setText(id);
        getEmployee();
    }
    private void getEmployee(){
        class GetEmployee extends AsyncTask<Void,Void,String> {
            private ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilPegawai.this,
                        "Fetching...","Wait...",false,false);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showEmployee(s);
            }
            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                return rh.sendGetRequestParam(Configuration.URL_GET_EMP,id);
            }
        }
        GetEmployee ge = new GetEmployee();
        ge.execute();
    }
    private void showEmployee(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Configuration.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String name = c.getString(Configuration.TAG_NAMA);
            String desg = c.getString(Configuration.TAG_POSISI);
            String sal = c.getString(Configuration.TAG_GAJI);
            editTextName.setText(name);
            editTextDesg.setText(desg);
            editTextSalary.setText(sal);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void updateEmployee(){
        final String name = editTextName.getText().toString().trim();
        final String desg = editTextDesg.getText().toString().trim();
        final String salary = editTextSalary.getText().toString().trim();
        class UpdateEmployee extends AsyncTask<Void,Void,String>{
            private ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilPegawai.this,
                        "Updating...","Wait...",false,false);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(TampilPegawai.this,s,Toast.LENGTH_LONG).show();
            }
            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(Configuration.KEY_EMP_ID,id);
                hashMap.put(Configuration.KEY_EMP_NAMA,name);
                hashMap.put(Configuration.KEY_EMP_POSISI,desg);
                hashMap.put(Configuration.KEY_EMP_GAJI,salary);
                RequestHandler rh = new RequestHandler();
                return rh.sendPostRequest(Configuration.URL_UPDATE_EMP,hashMap);
            }
        }
        UpdateEmployee ue = new UpdateEmployee();
        ue.execute();
    }
    private void deleteEmployee(){
        class DeleteEmployee extends AsyncTask<Void,Void,String> {
            private ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilPegawai.this,
                        "Updating...", "Tunggu...", false, false);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(TampilPegawai.this, s, Toast.LENGTH_LONG).show();
            }
            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                return rh.sendGetRequestParam(Configuration.URL_DELETE_EMP, id);
            }
        }
        DeleteEmployee de = new DeleteEmployee();
        de.execute();
    }
    private void confirmDeleteEmployee(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Apakah Kamu Yakin Ingin Menghapus Pegawai ini?");
        alertDialogBuilder.setPositiveButton("Ya",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        deleteEmployee();
                        startActivity(new Intent(TampilPegawai.this,TampilSemuaPgw.class));
                    }
                });
        alertDialogBuilder.setNegativeButton("Tidak",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    @Override
    public void onClick(View v) {
        if(v == buttonUpdate){
            updateEmployee();
        }
        if(v == buttonDelete){
            confirmDeleteEmployee();
        }
    }
}


