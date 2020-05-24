package grit.android.androidsql;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ItemAdaptor extends BaseAdapter {

    private LayoutInflater inflator;
    private List<Philosopher> list;

    private DBOpener db;

    public ItemAdaptor(Context context) {

        db = new DBOpener(context);
        //db.addEntries();
        list = db.getAll();

        inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) { // filling up the list

        View v = inflator.inflate(R.layout.list_layout, null);

        TextView itemTextView = (TextView) v.findViewById(R.id.textView);

        itemTextView.setText(list.get(position).getName());

        return v;
    }
}