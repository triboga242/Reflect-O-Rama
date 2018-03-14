package Utiles;

/**
 * Created by Triboga on 5/3/18.
 */

public abstract class BaseDatosReflect {

    private static  int databaseVersion;
    private static  String databaseName;
    private static  String SCORE_TABLENAME;
    private static  String SCORE_FIELD ;

    private static  String databaseCreationQuery;
    private static  String databaseUpdateQuery;


    public abstract void saveCurrentGame(int score);

    public abstract void endCurrentGame(int score);

    /**
     * Constructor de la bbdd
     */
    public BaseDatosReflect(){
        databaseVersion=1;
        databaseName="ReflectDB";
        SCORE_TABLENAME="totalScore";
        SCORE_FIELD ="score";
        databaseCreationQuery="CREATE TABLE '"+SCORE_TABLENAME+"' (" +
                SCORE_FIELD + " INT" +
                ");";
        databaseUpdateQuery="";
    }

    public static int getDatabaseVersion(){
        return databaseVersion;
    }

    public static String getScoreFieldName(){
        return SCORE_FIELD;
    }

    public static String getDatabaseName(){
        return databaseName;
    }

    public static String getDatabaseCreationQuery(){
        return databaseCreationQuery;
    }

    public static String getDatabaseUpdateQuery(){
        return databaseUpdateQuery;
    }

    public static String getScoreTablename(){
        return SCORE_TABLENAME;
    }


}
