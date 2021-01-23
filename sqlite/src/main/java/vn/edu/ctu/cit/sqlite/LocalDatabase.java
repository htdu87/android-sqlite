package vn.edu.ctu.cit.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class LocalDatabase extends SQLiteOpenHelper {
    private final static String DB_NAME="ql_chi_tieu";
    private final static int DB_VERSION=1;

    public LocalDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="create table STUDENTS(" +
                "fullname text not null," +
                "primary key(fullname))";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public List<String> getStudents() {
        List<String> users=new ArrayList<>();
        SQLiteDatabase reader=getReadableDatabase();
        Cursor cursor=reader.rawQuery("select * from STUDENTS",null);
        if (cursor.moveToNext()) {
            do {
                users.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        reader.close();
        return users;
    }

    public boolean addStudent(String str) {
        SQLiteDatabase writer=getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("fullname",str);
        long id=writer.insert("STUDENTS",null,cv);
        writer.close();
        return id!=-1;
    }

    public void removeStudent(String str) {
        SQLiteDatabase writer=getWritableDatabase();
        writer.delete("STUDENTS","fullname=?",new String[]{str});
        writer.close();
    }

    public void removeAllUser() {
        SQLiteDatabase writer=getWritableDatabase();
        writer.delete("STUDENTS",null,null);
        writer.close();
    }
}
