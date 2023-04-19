package com.seminolestate.mypokedex

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
val DATABASENAME = "MY DATABASE"
val TABLENAME = "Search_Items"
val COL_SEARCHTEXT = "searchText"
val COL_ID = "id"
class DataBaseHandler(var context: Context) : SQLiteOpenHelper(context, DATABASENAME, null,
    1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLENAME + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_SEARCHTEXT + " VARCHAR(256) )"
        db?.execSQL(createTable)
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //onCreate(db);
    }
    fun insertData(searchItem: SearchItem) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_SEARCHTEXT, searchItem.searchText)
        val result = database.insert(TABLENAME, null, contentValues)
        if (result == (0).toLong()) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }
    }
    @SuppressLint("Range")
    fun readData(): MutableList<SearchItem> {
        val list: MutableList<SearchItem> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from $TABLENAME"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                val searchItem = SearchItem()
                searchItem.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                searchItem.searchText = result.getString(result.getColumnIndex(COL_SEARCHTEXT))
                list.add(searchItem)
            }
            while (result.moveToNext())
        }
        return list
    }
}