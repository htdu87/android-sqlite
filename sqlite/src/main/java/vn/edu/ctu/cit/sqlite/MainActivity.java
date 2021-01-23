package vn.edu.ctu.cit.sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    private MyAdapter adapter;
    private TextInputEditText txtName;
    private LocalDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db=new LocalDatabase(this);
        adapter=new MyAdapter(db.getStudents());

        txtName=findViewById(R.id.txt_name);
        RecyclerView recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    public void onAdd(View view) {
        if (TextUtils.isEmpty(txtName.getText())) {
            new AlertDialog.Builder(this)
                    .setTitle("SQLite")
                    .setMessage("Vui lòng nhập thông tin học viên!")
                    .setPositiveButton("OK",null)
                    .create()
                    .show();
            return;
        }

        if(db.addStudent(txtName.getText().toString()))
            adapter.setData(db.getStudents());
    }
}