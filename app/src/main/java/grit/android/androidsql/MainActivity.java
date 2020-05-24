package grit.android.androidsql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ItemAdaptor itemAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DBOpener db = new DBOpener(view.getContext());

                Philosopher p = db.get(position);

                Toast.makeText(view.getContext(),"Born: " + p.getBorn() + "; Died: " + p.getDied(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void onStart(){
        super.onStart();

        itemAdaptor = new ItemAdaptor(this);
        listView.setAdapter(itemAdaptor);
    }
}
