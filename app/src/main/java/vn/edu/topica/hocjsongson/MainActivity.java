package vn.edu.topica.hocjsongson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import vn.edu.topica.model.GoogleData;
import vn.edu.topica.model.Result;

public class MainActivity extends AppCompatActivity {
    ListView lvKetQua;
    ArrayList<Result> dsKetQua;
    ArrayAdapter<Result> adapterKetQua;

    EditText txtKeyWord;
    Button btnTim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnTim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyTimKiem();
            }
        });
    }

    private void xuLyTimKiem() {
        GoogleDataTask googleDataTask = new GoogleDataTask();
        googleDataTask.execute(txtKeyWord.getText().toString());

    }
    class GoogleDataTask extends AsyncTask <String, Void, ArrayList<Result>>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            adapterKetQua.clear();
        }

        @Override
        protected void onPostExecute(ArrayList<Result> results) {
            super.onPostExecute(results);
            adapterKetQua.clear();
            adapterKetQua.addAll(results);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            Toast.makeText(MainActivity.this,"Processing",Toast. LENGTH_LONG);
        }

        @Override
        protected ArrayList<Result> doInBackground(String... params) {
            ArrayList<Result>ds = new ArrayList<>();
            try {
                 String keyword = params[0];
                 String api= "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";
                 String linkAPI_Search = api + URLEncoder.encode(keyword, "UTF-8")+"&start="+1+"&rsz=8";
                 URL url = new URL(linkAPI_Search);
                 InputStreamReader reader=new InputStreamReader(url.openStream(),"UTF-8");
                 GoogleData googleData = new Gson().fromJson(reader,GoogleData.class);
                 return googleData.getRespondData().getResults();

            }
            catch (Exception ex)
            {
               Log.e("LOI",ex.toString());
            }
            return ds;
        }
    }

    private void addControls() {
        txtKeyWord = findViewById(R.id.txtKeyWord);
        btnTim = findViewById(R.id.btnTim);
        lvKetQua = findViewById(R.id.lvKetQua);
        dsKetQua = new ArrayList<>();
        adapterKetQua = new ArrayAdapter<Result>(
            MainActivity.this,
            android.R.layout.simple_list_item_1,
            dsKetQua
        );
        lvKetQua.setAdapter(adapterKetQua);
    }
}