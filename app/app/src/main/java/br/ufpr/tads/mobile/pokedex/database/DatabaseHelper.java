package br.ufpr.tads.mobile.pokedex.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import br.ufpr.tads.mobile.pokedex.constant.AppConstants;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_TABLE =
            String.format(
                    "create table %s (" +
                            "%s varchar(128) unique not null, " +
                            "%s varchar(128) not null, " +
                            "%s varchar(128) unique not null)",
                    AppConstants.Database.TABLE_NAME,
                    AppConstants.Database.ID,
                    AppConstants.Database.NOME,
                    AppConstants.Database.LOGIN
            );


    public DatabaseHelper(@Nullable Context context) {
        super(context, AppConstants.Database.DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int versaoAntiga, int versaoAtual) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + AppConstants.Database.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
