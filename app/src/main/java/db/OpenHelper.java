package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OpenHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "pawoondb.dbc";
	private static final int DATABASE_VERSION = 1;
	private static final String TES_TABLE_NAME = "tes";

	private Context context;
	private SQLiteDatabase db;
	private OpenHelper openHelper;

	public OpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.db=this.getWritableDatabase();
	}

	public SQLiteDatabase getDB()
	{
		return db;

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE "
				+ TES_TABLE_NAME
				+ "(userId TEXT, id TEXT,title TEXT,completed TEXT);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TES_TABLE_NAME + ";");
		onCreate(db);
	}
	
	public void closeConnection() {
		try {
			db.close();
			this.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}