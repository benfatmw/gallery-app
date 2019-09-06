package com.lin.mobile.gallery.cache.db

/**
 * Defines constants for the db
 */
object CacheConstants {
    const val TABLE_NAME_ALBUM = "album"
    
    const val QUERY_ALBUMS = "SELECT * FROM $TABLE_NAME_ALBUM"
    const val QUERY_ALBUM = "SELECT * FROM $TABLE_NAME_ALBUM WHERE id =:id"
    const val POPULATE_DATA = """INSERT INTO $TABLE_NAME_ALBUM
        VALUES
        (1, 2,'Name 1'),
        (2, 3,'Name 2'),
        (3, 4,'Name 3'),
        (4, 5,'Name 4'),
        (5, 6,'Name 5'),
        (6, 7,'Name 6'),
        (7, 8,'Name 9')
         """
}