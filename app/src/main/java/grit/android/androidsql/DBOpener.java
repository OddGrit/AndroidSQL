package grit.android.androidsql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBOpener extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Philoes.db";
    public static final String TABLE_NAME = "Philio";
    public static final String KEY_ID = "ID";
    public static final String KEY_NAME = "Name";
    public static final String KEY_BORN = "YearBorn";
    public static final String KEY_DIED = "YearDied";

    public DBOpener(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES = "CREATE TABLE " + TABLE_NAME + "(" +
            KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME +
            " TEXT, " + KEY_BORN + " INTEGER, " + KEY_DIED + " INTEGER)";
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void addEntries() {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, "Socrates");
        values.put(KEY_BORN, -470);
        values.put(KEY_DIED, -399);

        db.insert(TABLE_NAME, null, values);

        values = new ContentValues();
        values.put(KEY_NAME, "Leibnitz");
        values.put(KEY_BORN, 1646);
        values.put(KEY_DIED, 1716);

        db.insert(TABLE_NAME, null, values);


        db.close();
    }

    public List<Philosopher> getAll() {
        List<Philosopher> list = new ArrayList<Philosopher>();

        String query = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Philosopher p = new Philosopher();
                p.setId(cursor.getInt(0));
                p.setName(cursor.getString(1));
                p.setBorn(cursor.getInt(2));
                p.setDied(cursor.getInt(3));

                list.add(p);

            } while (cursor.moveToNext());
        }

        return list;
    }

    public Philosopher get(int index) {
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE ID=" + ++index;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Philosopher p = null;
        if (cursor.moveToFirst()) {
            p = new Philosopher();
            p.setId(cursor.getInt(0));
            p.setName(cursor.getString(1));
            p.setBorn(cursor.getInt(2));
            p.setDied(cursor.getInt(3));
        }

        return p;
    }
}
