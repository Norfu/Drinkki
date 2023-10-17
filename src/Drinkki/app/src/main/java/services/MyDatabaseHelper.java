package services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.estiam.drinkki.MainActivity;

import java.text.ParseException;
import java.util.Date;

import Models.DrunkTime;
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
    private final static String TABLE_DRUNKTIME = "DrunkTime";
    private final static String COLUMN_DRUNKTIME_ID="ID";
    private final static String COLUMN_DRUNKTIME_DATE = "DRUNKDATE";
    private final static String COLUMN_DRUNKTIME_DRUNKWATER="DRUNKWATER";

    public MyDatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i(TAG,"DrinkkiDbOnCreate...");

        String script = "CREATE TABLE ".concat(TABLE_USERDRINKKI + "("
        + COLUMN_USERDRINKKI_ID+" INTEGER PRIMARY KEY,"+ COLUMN_USERDRINKKI_NAME +" TEXT,"
        + COLUMN_USERDRINKKI_GENDER +" TEXT,"+ COLUMN_USERDRINKKI_WATERNEEDED+" REAL);")
        +"CREATE TABLE ".concat(TABLE_DRUNKTIME+ "(" + COLUMN_DRUNKTIME_ID+ " INTEGER PRIMARY KEY,"
        + COLUMN_DRUNKTIME_DATE + " TEXT," + COLUMN_DRUNKTIME_DRUNKWATER +" REAL);");

        sqLiteDatabase.execSQL(script);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.i(TAG,"DrinkkiDbOnUpload...");

        //TODO ??
    }


    //TODO METHODE A MODIFIER VOIR SUPPRIMER
    public DrunkTime getLastDrunkTime(Date date) throws ParseException {
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
        DrunkTime drunkDay = null;
        while(cursor.moveToNext()){
            int itemId = cursor.getInt(cursor.getColumnIndexOrThrow("ID"));
            String itemDate = cursor.getString(cursor.getColumnIndexOrThrow("DRUNKDATE"));
            double itemDrunkWater = cursor.getDouble(cursor.getColumnIndexOrThrow("DRUNKWATER"));
       Date dateParse = MainActivity.simpleDateFormat.parse(itemDate);
        drunkDay = new DrunkTime(itemId,dateParse,itemDrunkWater);
        }
        cursor.close();
        return drunkDay;
    }

    public UserDrinkki getUser(){
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

    public void addDrunkTime(DrunkTime drunkTime){
        SQLiteDatabase db = this.getWritableDatabase();
        String dateString = MainActivity.simpleDateFormat.format(drunkTime.getDrunkDate());
        ContentValues values = new ContentValues();
        values.put(COLUMN_DRUNKTIME_ID, drunkTime.getId());
        values.put(COLUMN_DRUNKTIME_DATE, dateString);
        values.put(COLUMN_DRUNKTIME_DRUNKWATER, drunkTime.getDrunkWater());

        long rowId = db.insert(TABLE_DRUNKTIME,null,values);
        db.close();
    }
    
}
