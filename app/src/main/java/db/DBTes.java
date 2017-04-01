package db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import models.TesDTO;

public class DBTes {
	private final String tag = "DBTes";
	private static final String DATABASE_NAME = "pawoondb.dbc";
	private static final int DATABASE_VERSION = 1;
	private static final String TABLE_NAME = "tes";


	private Context context;
	private SQLiteDatabase db;
	private OpenHelper openHelper;

	private SQLiteStatement insertStmt;
	private static final String INSERT = "insert into " + TABLE_NAME
			+ "(userId,id,title,completed) values (?,?,?,?);";



	public DBTes(Context context, SQLiteDatabase db) {
		this.context = context;
		//openHelper = op;
		this.db = db;
		this.insertStmt = this.db.compileStatement(INSERT);
	}
	public long insert(TesDTO _tes) {
		this.insertStmt.bindString(1, _tes.getUserId());
        this.insertStmt.bindString(2, _tes.getId());
        this.insertStmt.bindString(3, _tes.getTitle());
		this.insertStmt.bindString(4, _tes.getCompleted());
		return this.insertStmt.executeInsert();
	}
	
	public void deleteAll() {
		this.db.delete(TABLE_NAME, null, null);
	}

	public ArrayList<TesDTO> selectAll() {
		ArrayList<TesDTO> list = new ArrayList<TesDTO>();
		Cursor cursor = this.db.query(TABLE_NAME,
				new String[] { "userId,id,title,completed" }, null, null, null, null, "userId asc, id asc");
		if (cursor.moveToFirst()) {
			do {
				TesDTO jd = new TesDTO();
				jd.setUserId(cursor.getString(0));
				jd.setId(cursor.getString(1));
				jd.setTitle(cursor.getString(2));
				jd.setCompleted(cursor.getString(3));
				list.add(jd);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return list;
	}

	public void closeConnection() {
		try {
			db.close();
			openHelper.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
