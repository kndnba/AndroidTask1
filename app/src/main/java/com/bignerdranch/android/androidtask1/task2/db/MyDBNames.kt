package com.bignerdranch.android.androidtask1.task2.db

import android.provider.BaseColumns

object MyDBNames {
    const val TABLE_NAME = "MY_TABLE"
    const val SONG = "SONG"
    const val ARTIST = "ARTIST"
    const val GENRE = "GENRE"
    const val PATH = "PATH"
    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "MyDatabase.db"

    const val CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (${BaseColumns._ID} INTEGER PRIMARY KEY, $SONG TEXT, $ARTIST TEXT, $GENRE TEXT, $PATH TEXT)"
    const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
}