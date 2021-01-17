package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import models.Brew;
import models.enums.GrindSize;

public class BroegDB extends SQLiteOpenHelper {
    static final int VERSION = 2;
    static final String DB_NAME = "broeg_db.db";
    static final String BREW_TABLE = "brews";

    static final String ID = "id";
    static final String NAME = "name";
    static final String GRIND_SIZE = "grind_size";
    static final String BREWING_TEMP = "brewing_temperature";
    static final String GROUND_COFFEE_AMT = "ground_coffee_amount";
    static final String BLOOM_WATER_AMT = "bloom_water_amount";
    static final String COFFEE_WATER_RATIO = "coffee_water_ratio";
    static final String BLOOM_TIME = "bloom_time";
    static final String TOTAL_BREW_TIME = "total_brew_time";

    static final String[] COLUMNS = {ID, NAME, GRIND_SIZE, BREWING_TEMP, GROUND_COFFEE_AMT, BLOOM_WATER_AMT, COFFEE_WATER_RATIO, BLOOM_TIME, TOTAL_BREW_TIME};

    public BroegDB(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + BREW_TABLE + " (" +
                    ID + " integer primary key autoincrement, " +
                    NAME + " text not null, " +
                    GRIND_SIZE + " text check( " + GRIND_SIZE + " in ('FINE','MEDIUM','COARSE')) not null, " +
                    BREWING_TEMP + " real not null, " +
                    GROUND_COFFEE_AMT + " real not null, " +
                    BLOOM_WATER_AMT + " real not null, " +
                    COFFEE_WATER_RATIO + " real not null, " +
                    BLOOM_TIME + " integer not null, " +
                    TOTAL_BREW_TIME + " integer not null" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<Brew> getBrews() {
        ArrayList<Brew> brews = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(BREW_TABLE, COLUMNS, null, null, null, null, null);

        while (cursor.moveToNext()) {
            Brew brew = new Brew();

            brew.setCommunityId(cursor.getInt(0));
            brew.setName(cursor.getString(1));
            brew.setGrindSize(GrindSize.valueOf(cursor.getString(2)));
            brew.setBrewingTemperature(cursor.getDouble(3));
            brew.setGroundCoffeeAmount(cursor.getDouble(4));
            brew.setBloomAmount(cursor.getDouble(5));
            brew.setCoffeeWaterRatio(cursor.getDouble(6));
            brew.setBloomTime(cursor.getInt(7));
            brew.setTotalBrewTime(cursor.getInt(8));

            brews.add(brew);
        }

        cursor.close();
        db.close();

        return brews;
    }

    public void saveBrews(List<Brew> brews) {
        SQLiteDatabase db = getWritableDatabase();

        for (Brew brew : brews) {
            insertBrew(db, brew);
        }

        db.close();
    }

    public long insertBrew(SQLiteDatabase db, Brew brew) {
        ContentValues row = getBrewContentValues(brew);

        Cursor cursor = db.query(BREW_TABLE, COLUMNS, "id = " + brew.getCommunityId(), null, null, null, null);
        if (cursor.getCount() > 0) {
            updateBrew(db, brew);
            return -1;
        }

        return db.insert(BREW_TABLE, null, row);
    }

    public long insertBrew(Brew brew) {
        SQLiteDatabase db = getWritableDatabase();
        long id = insertBrew(db, brew);
        db.close();
        return id;
    }

    public void updateBrew(SQLiteDatabase db, Brew brew) {
        Cursor cursor = db.query(BREW_TABLE, COLUMNS, "id = " + brew.getCommunityId(), null, null, null, null);

        if (cursor.getCount() == 0)
            return;

        db.update(BREW_TABLE, getBrewContentValues(brew), "id = " + brew.getCommunityId(), null);
    }

    public void updateBrew(Brew brew) {
        SQLiteDatabase db = getWritableDatabase();
        updateBrew(db, brew);
        db.close();
    }

    private ContentValues getBrewContentValues(Brew brew) {
        ContentValues row = new ContentValues();

        row.put(NAME, brew.getName());
        row.put(GRIND_SIZE, brew.getGrindSize().toString());
        row.put(BREWING_TEMP, brew.getBrewingTemperature());
        row.put(GROUND_COFFEE_AMT, brew.getGroundCoffeeAmount());
        row.put(BLOOM_WATER_AMT, brew.getBloomAmount());
        row.put(COFFEE_WATER_RATIO, brew.getCoffeeWaterRatio());
        row.put(BLOOM_TIME, brew.getBloomTime());
        row.put(TOTAL_BREW_TIME, brew.getTotalBrewTime());

        return row;
    }

    public void deleteBrew(Brew brew) {
        if (brew.getCommunityId() == 0)
            return;

        SQLiteDatabase db = getWritableDatabase();
        db.delete(BREW_TABLE, "id = " + brew.getCommunityId(), null);
    }

    public void deleteAll() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("drop table " + BREW_TABLE);
        this.onCreate(db);
    }
}
