package BaseDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import Utiles.BaseDatosReflect;

/**
 * Created by Triboga on 5/3/18.
 */

public class BaseDatosAndroidReflectorama extends BaseDatosReflect {

    private SQLiteDatabase baseDatos;
    private BaseDatosHelper helper;
    private Context c;

    public BaseDatosAndroidReflectorama(Context context) {
        super();
        c = context;
        helper = new BaseDatosHelper(context);
        baseDatos = helper.getWritableDatabase();

    }


    @Override
    public void saveCurrentGame(int score) {
        Cursor b = baseDatos.rawQuery("select * from '" + getScoreTablename() + "';", null);
        // Cursor b = baseDatos.query(getDatabaseName(), new String[]{getScoreTablename()}, null, null, null, null, null);
        boolean cambio = false;

        if (b.getCount() <= 0) {

            ContentValues cv = new ContentValues();
            cv.put(getScoreFieldName(), "0");
            baseDatos.insert(getScoreTablename(), null, cv);
        }
        b = baseDatos.rawQuery("select * from '" + getScoreTablename() + "';", null);
        b.moveToFirst();

        if (b.getInt(0) <= score) {

            cambio = true;
        }

        if (cambio) {
            baseDatos.execSQL("delete from " + getScoreTablename());

            baseDatos.rawQuery("delete from '" + getScoreTablename() + "';", null);

            ContentValues cv = new ContentValues();
            cv.put(getScoreFieldName(), score);
            baseDatos.insert(getScoreTablename(), null, cv);
        }
    }

    @Override
    public void endCurrentGame(int score) {
        saveCurrentGame(score);
    }


    public int getScore() {
        Cursor b = baseDatos.rawQuery("select * from '" + getScoreTablename() + "'", null);
        int puntos = 0;
        if (b.moveToFirst())
            puntos = b.getInt(0);
        return puntos;
    }
}
