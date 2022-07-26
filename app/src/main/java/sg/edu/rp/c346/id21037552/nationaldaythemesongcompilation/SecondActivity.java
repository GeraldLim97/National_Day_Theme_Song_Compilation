package sg.edu.rp.c346.id21037552.nationaldaythemesongcompilation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    Button btn5Stars;
    ListView lv;
    RadioGroup radGrp;
    ArrayList<Song> al;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        setTitle(getTitle().toString() + " ~ " + "Insert Song");

        btn5Stars = findViewById(R.id.btn5Stars);
        lv = findViewById(R.id.lv);
        radGrp = findViewById(R.id.rg);

        DBHelper dbh = new DBHelper(this);
        al = dbh.getAllSongs();
        dbh.close();


        adapter  = new CustomAdapter(this,R.layout.row,al);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(SecondActivity.this, ThirdActivity.class);
                i.putExtra("song",al.get(position));
                startActivity(i);
            }
        });

        btn5Stars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(SecondActivity.this);
                al.clear();
                al.addAll(dbh.getAllSongsbyStars(5));
                adapter.notifyDataSetChanged();
            }
        });
    }
}
