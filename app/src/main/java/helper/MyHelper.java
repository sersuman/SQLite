package helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sqlite.Word;

import java.util.ArrayList;
import java.util.List;

public class MyHelper extends SQLiteOpenHelper {
    private static final String databaseName = "User";
    private static final int dbVersion = 1;


//    table fields
    private static final String tblUser = "tblUsername";
    private static final String UserId = "UserId";
    private static final String Username = "Username";
    private static final String Password = "Password";

    public MyHelper(Context context){
        super(context, databaseName, null, dbVersion);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + tblUser+"("+UserId+" INTEGER PRIMARY KEY AUTOINCREMENT ,"+
                Username + " TEXT,"+ Password +" TEXT"+")";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long InsertData(String username, String password,SQLiteDatabase db){
        long id;
        ContentValues contentValues = new ContentValues();
        contentValues.put(Username, username);
        contentValues.put(Password, password);

        id = db.insert(tblUser,null, contentValues);
        return id;
    }
    public List<Word> GetAllWords(SQLiteDatabase db){
        List<Word> userList = new ArrayList<>();
        String[] columns = (UserId, Username, Password);
        Cursor cursor = db.query(tblUser, columns, null,null,null,null,null);

        if (cursor.getCount()>0){
            while (cursor.moveToNext()){
                userList.add(new Word(cursor.getInt(0), cursor.getString(1),cursor.getString(2)));
            }
        }
        return userList;
    }

}
