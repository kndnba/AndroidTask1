package com.bignerdranch.android.androidtask1.task2.model

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import android.provider.BaseColumns
import com.bignerdranch.android.androidtask1.task2.model.db.DbHelper
import com.bignerdranch.android.androidtask1.task2.model.db.MyDBNames

class MusicProvider : ContentProvider() {
    lateinit var db : SQLiteDatabase

    override fun onCreate(): Boolean {
        val helper = DbHelper(context)
        db = helper.writableDatabase
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        return db.query(MyDBNames.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder)
    }

    override fun getType(uri: Uri): String {
        return "vnd.android.cursor.dir/vnd.com.bignerdranch.android.androidtask1.task2.${MyDBNames.TABLE_NAME}"
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri {
        db.insert(MyDBNames.TABLE_NAME, null, values)
        context?.contentResolver?.notifyChange(uri, null)
        return uri
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        val counter = db.delete(MyDBNames.TABLE_NAME, selection, selectionArgs)
        context?.contentResolver?.notifyChange(uri, null)
        return counter
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        val counter = db.update(MyDBNames.TABLE_NAME, values, selection, selectionArgs)
        context?.contentResolver?.notifyChange(uri, null)
        return counter
    }

    companion object{
         const val PROVIDER_NAME = "com.bignerdranch.android.androidtask1.task2/MusicProvider"
         val URL = "content://$PROVIDER_NAME/${MyDBNames.TABLE_NAME}"
         val CONTENT_URI: Uri = Uri.parse(URL)
         const val _ID = BaseColumns._ID
         const val SONG = MyDBNames.SONG
         const val ARTIST = MyDBNames.ARTIST
         const val GENRE = MyDBNames.GENRE
         const val PATH = MyDBNames.PATH
    }
}