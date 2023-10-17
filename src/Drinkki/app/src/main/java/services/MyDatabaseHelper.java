package services;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.estiam.drinkki.MainActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import Models.DrunkDay;
import Models.UserDrinkki;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private final static String TAG = "Sqlite";
    private final static int DATABASE_VERSION = 1;
    private final static String DATABASE_NAME = "DrinkkiDb";
    private final static String TABLE_USERDRINKKI = "UserDrinkki";
    private final static String COLUMN_USERDRINKKI_ID = "ID";
    private final static String COLUMN_USERDRINKKI_NAME="NAME";
    private final static String COLUMN_USERDRINKKI_GENDER = "GENDER";
    private final static String COLUMN_USERDRINKKI_WEIGHT = "WEIGHT";
    private final static String COLUMN_USERDRINKKI_WATERNEEDED = "WATER_NEEDED";
    private final static String TABLE_DRUNKDAY = "DrunkDay";
    private final static String COLUMN_DRUNKDAY_ID="ID";
    private final static String COLUMN_DRUNKDAY_DATE = "DRUNKDATE";
    private final static String COLUMN_DRUNKDAY_DRUNKWATER="DRUNKWATER";

    public MyDatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i(TAG,"DrinkkiDbOnCreate...");

        String script = "CREATE TABLE ".concat(TABLE_USERDRINKKI + "("
        + COLUMN_USERDRINKKI_ID+" INTEGER PRIMARY KEY,"+ COLUMN_USERDRINKKI_NAME +" TEXT,"
        + COLUMN_USERDRINKKI_GENDER +" TEXT,"+ COLUMN_USERDRINKKI_WATERNEEDED+" REAL);")
        +"CREATE TABLE ".concat(TABLE_DRUNKDAY+ "(" + COLUMN_DRUNKDAY_ID+ " INTEGER PRIMARY KEY,"
        + COLUMN_DRUNKDAY_DATE + " TEXT," + COLUMN_DRUNKDAY_DRUNKWATER +" REAL);");

        sqLiteDatabase.execSQL(script);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.i(TAG,"DrinkkiDbOnUpload...");

        //TODO ??
    }

    public DrunkDay GetDrunkDay(Date date) throws ParseException {
        String dateString = MainActivity.simpleDateFormat.format(date);

        Log.i(TAG,"DrinkkiDb.GetDrunkDay..."+ dateString);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_DRUNKDAY,
                null,
                COLUMN_DRUNKDAY_DATE+ "=" + dateString,
                null,
                null,
                null,
                null
        );
        DrunkDay drunkDay = null;
        while(cursor.moveToNext()){
            int itemId = cursor.getInt(cursor.getColumnIndexOrThrow("ID"));
            String itemDate = cursor.getString(cursor.getColumnIndexOrThrow("DRUNKDATE"));
            double itemDrunkWater = cursor.getDouble(cursor.getColumnIndexOrThrow("DRUNKWATER"));
       Date dateParse = MainActivity.simpleDateFormat.parse(itemDate);
        drunkDay = new DrunkDay(itemId,dateParse,itemDrunkWater);
        }
        cursor.close();
        return drunkDay;
    }

    public UserDrinkki GetUser(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_USERDRINKKI,
                null,
                null,
                null,
                null,
                null,
                null
        );
        UserDrinkki userDrinkki = new UserDrinkki(
                cursor.getString(cursor.getColumnIndexOrThrow("NAME")),
                cursor.getDouble(cursor.getColumnIndexOrThrow("WATER_NEEDED")),
                cursor.getString(cursor.getColumnIndexOrThrow("GENDER")));

        cursor.close();
        Log.i(TAG,"DrinkkiDb.GetUser..."+ userDrinkki.getName());
        return userDrinkki;
    }

    public void AddWater(double value){
        SQLiteDatabase db = this.getReadableDatabase();
        
    }
}
