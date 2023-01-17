package com.bignerdranch.android.androidtask1.task2.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(context: Context?) : SQLiteOpenHelper(context, MyDBNames.DATABASE_NAME, null, MyDBNames.DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(MyDBNames.CREATE_TABLE)
        db?.execSQL("INSERT INTO ${MyDBNames.TABLE_NAME} (${MyDBNames.SONG},${MyDBNames.ARTIST},${MyDBNames.GENRE},${MyDBNames.PATH}) VALUES('bfg_division','mick_gordon','score','R.raw.bfg_division.mp3')")
        db?.execSQL("INSERT INTO ${MyDBNames.TABLE_NAME} (${MyDBNames.SONG},${MyDBNames.ARTIST},${MyDBNames.GENRE},${MyDBNames.PATH}) VALUES('born_this_way','lady_gaga','pop','R.raw.born_this_way.mp3')")
        db?.execSQL("INSERT INTO ${MyDBNames.TABLE_NAME} (${MyDBNames.SONG},${MyDBNames.ARTIST},${MyDBNames.GENRE},${MyDBNames.PATH}) VALUES('contact','daft_punk','electronic','R.raw.contact.mp3')")
        db?.execSQL("INSERT INTO ${MyDBNames.TABLE_NAME} (${MyDBNames.SONG},${MyDBNames.ARTIST},${MyDBNames.GENRE},${MyDBNames.PATH}) VALUES('new_mode','kid_cudi','rap','R.raw.new_mode.mp3')")
        db?.execSQL("INSERT INTO ${MyDBNames.TABLE_NAME} (${MyDBNames.SONG},${MyDBNames.ARTIST},${MyDBNames.GENRE},${MyDBNames.PATH}) VALUES('new_yorks_only_spider_man','john_paesano','score','R.raw.new_yorks_only_spider_man.mp3')")
        db?.execSQL("INSERT INTO ${MyDBNames.TABLE_NAME} (${MyDBNames.SONG},${MyDBNames.ARTIST},${MyDBNames.GENRE},${MyDBNames.PATH}) VALUES('philosophy_of_the_world','the_shaggs','grunge','R.raw.philosophy_of_the_world.mp3')")
        db?.execSQL("INSERT INTO ${MyDBNames.TABLE_NAME} (${MyDBNames.SONG},${MyDBNames.ARTIST},${MyDBNames.GENRE},${MyDBNames.PATH}) VALUES('scar_tissue','red_hot_chili_peppers','alternative','R.raw.scar_tissue.mp3')")
        db?.execSQL("INSERT INTO ${MyDBNames.TABLE_NAME} (${MyDBNames.SONG},${MyDBNames.ARTIST},${MyDBNames.GENRE},${MyDBNames.PATH}) VALUES('souk_eye','gorillaz','alternative','R.raw.souk_eye.mp3')")
        db?.execSQL("INSERT INTO ${MyDBNames.TABLE_NAME} (${MyDBNames.SONG},${MyDBNames.ARTIST},${MyDBNames.GENRE},${MyDBNames.PATH}) VALUES('the_car','arctic_monkeys','art_rock','R.raw.the_car.mp3')")
        db?.execSQL("INSERT INTO ${MyDBNames.TABLE_NAME} (${MyDBNames.SONG},${MyDBNames.ARTIST},${MyDBNames.GENRE},${MyDBNames.PATH}) VALUES('undertale','toby_fox','score','R.raw.undertale.mp3')")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(MyDBNames.SQL_DELETE_TABLE)
        onCreate(db)
    }
}