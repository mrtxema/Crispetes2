package cat.mrtxema.crispetes.store;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import cat.mrtxema.crispetes.view.R;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "crispetes";
    private static final int DATABASE_VERSION = 3;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            for (Class<?> dtoClass : DtoRegistry.getDtoClasses()) {
                TableUtils.createTable(connectionSource, dtoClass);
            }
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getSimpleName(), "Can't create database", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            for (Class<?> dtoClass : DtoRegistry.getDtoClassesInReverseOrder()) {
                TableUtils.dropTable(connectionSource, dtoClass, true);
            }
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getSimpleName(), "Can't upgrade database", e);
            throw new RuntimeException(e);
        }
    }
}
