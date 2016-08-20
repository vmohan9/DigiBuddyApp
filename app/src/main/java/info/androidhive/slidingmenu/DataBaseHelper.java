package info.androidhive.slidingmenu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by divya on 4/9/2016.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="user.db";
    private static final String TABLE_NAME="user";
    private static final String COLUMN_ID="id";
    private static final String COLUMN_NAME="name";
    private static final String COLUMN_EMAIL="email";
    private static final String COLUMN_USERNAME="username";
    private static final String COLUMN_PASSWORD="password";
    private static final String COLUMN_AGE="age";
    private static final String COLUMN_SEX="sex";
    private static final String COLUMN_HEIGHT="height";
    private static final String COLUMN_WEIGHT="weight";
    public static int weight;
    public static int height;

    SQLiteDatabase db;
    private static final String TABLE_CREATE="create table user(id integer primary key not null,name text not null,email text not null,username text not null,password text not null,age text not null,sex text not null,height INTEGER not null,weight INTEGER not null)";
    public DataBaseHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db=db;

    }

    public void insertmethod(User U)
    {
        db=this.getWritableDatabase();
        ContentValues values=new ContentValues();

        String query="select * from user";
        Cursor cursor=db.rawQuery(query,null);
        int count=cursor.getCount();

        values.put(COLUMN_ID,count);
        values.put(COLUMN_NAME,U.getName());
        values.put(COLUMN_EMAIL,U.getEmailaddress());
        values.put(COLUMN_USERNAME,U.getUsername());
        values.put(COLUMN_PASSWORD,U.getPassword());
        values.put(COLUMN_AGE,U.getAge());
        values.put(COLUMN_SEX,U.getSex());
        values.put(COLUMN_HEIGHT,U.getHeight());
        values.put(COLUMN_WEIGHT,U.getWeight());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public String searchpass(String uname)
    {
        db=this.getReadableDatabase();
        String query="select username,password,height,weight from user";
        Cursor cursor=db.rawQuery(query,null);
        String a,b; int c,d;
        b="not found";
        if(cursor.moveToFirst())
        {
            do{
                a = cursor.getString(0);

                if(a.equals(uname))
                {
                    b=cursor.getString(1);
                    height=cursor.getInt(2);
                    weight=cursor.getInt(3);
                    break;
                }
            }while(cursor.moveToNext());

        }
        return b;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String query="DROP TABLE IF EXITS"+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);

    }
}
