package BaseDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Triboga on 5/3/18.
 */

public class BaseDatosHelper extends SQLiteOpenHelper {


    /**
     * Constructor del Helper
     * @param context contexto donde operara
     */
    public BaseDatosHelper(Context context) {
        super(context,
                BaseDatosAndroidReflectorama.getDatabaseName(),
                null,
                BaseDatosAndroidReflectorama.getDatabaseVersion());


    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(BaseDatosAndroidReflectorama.getDatabaseCreationQuery());

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(BaseDatosAndroidReflectorama.getDatabaseUpdateQuery());

    }
}
