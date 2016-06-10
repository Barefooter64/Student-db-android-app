package com.appsrus.mikesaj.studentapp.DAL;

/**
 * Created by msajuyigbe4350 on 6/10/2016.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "school.db";
    public static final String STUDENT_TABLE = "students";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FIRST_NAME = "first_name";
    public static final String COLUMN_lAST_NAME = "last_name";
    public static final String COLUMN_MARK = "mark";

    private HashMap hp;

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "CREATE TABLE " + STUDENT_TABLE +
                        "("+  COLUMN_ID+" integer primary key, "
                            + COLUMN_FIRST_NAME +" text,"+ COLUMN_lAST_NAME +" text,"+COLUMN_MARK+" text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS "+ STUDENT_TABLE );
        onCreate(db);
    }

    public boolean insertStudent (String first_name, String last_name, String mark)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FIRST_NAME, first_name);
        contentValues.put(COLUMN_lAST_NAME, last_name);
        contentValues.put(COLUMN_MARK, mark);
        db.insert(STUDENT_TABLE, null, contentValues);
        return true;
    }

    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + STUDENT_TABLE + " where "+ COLUMN_ID +"="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, STUDENT_TABLE);
        return numRows;
    }

    public boolean updateStudent (Integer id, String first_name, String last_name, String mark)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FIRST_NAME, first_name);
        contentValues.put(COLUMN_lAST_NAME, last_name);
        contentValues.put(COLUMN_MARK, mark);
        db.update(STUDENT_TABLE, contentValues, COLUMN_ID+" = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteStudent (Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("contacts",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getAllCotacts()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+STUDENT_TABLE, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(COLUMN_ID)));
            res.moveToNext();
        }
        return array_list;
    }
}