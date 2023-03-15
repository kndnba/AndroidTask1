package com.bignerdranch.android.androidtask1.task2.model.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import org.junit.Before
import org.junit.Test

class DbHelperTest {
    private lateinit var db: SQLiteDatabase
    private lateinit var dbHelper: DbHelper

    @Before
    fun createDb() {
        val context = getApplicationContext<Context>()
        dbHelper = DbHelper(context)
    }

    @Test
    @Throws(Exception::class)
    fun testDbInsertion() {
        db = dbHelper.writableDatabase
        val cursor = db.rawQuery("SELECT * FROM ${MyDBNames.TABLE_NAME}", null)


        cursor.moveToFirst()
        assert(cursor.getString(cursor.getColumnIndex(MyDBNames.SONG)) == "bfg_division")
        assert(cursor.getString(cursor.getColumnIndex(MyDBNames.ARTIST)) == "mick_gordon")
        assert(cursor.getString(cursor.getColumnIndex(MyDBNames.GENRE)) == "score")
        assert(cursor.getString(cursor.getColumnIndex(MyDBNames.PATH)) == "R.raw.bfg_division.mp3")

        cursor.close()
        db.close()
    }
}