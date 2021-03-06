package com.tiancaijiazu.app.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "THE_TOPIC_RECORD_BEAN".
*/
public class TheTopicRecordBeanDao extends AbstractDao<TheTopicRecordBean, Long> {

    public static final String TABLENAME = "THE_TOPIC_RECORD_BEAN";

    /**
     * Properties of entity TheTopicRecordBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property ContentId = new Property(1, String.class, "contentId", false, "CONTENT_ID");
        public final static Property ScoreSetting = new Property(2, String.class, "scoreSetting", false, "SCORE_SETTING");
        public final static Property Option = new Property(3, String.class, "option", false, "OPTION");
        public final static Property BabyId = new Property(4, String.class, "babyId", false, "BABY_ID");
        public final static Property SubjectId = new Property(5, String.class, "subjectId", false, "SUBJECT_ID");
        public final static Property Lenght = new Property(6, int.class, "lenght", false, "LENGHT");
    }


    public TheTopicRecordBeanDao(DaoConfig config) {
        super(config);
    }
    
    public TheTopicRecordBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"THE_TOPIC_RECORD_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"CONTENT_ID\" TEXT," + // 1: contentId
                "\"SCORE_SETTING\" TEXT," + // 2: scoreSetting
                "\"OPTION\" TEXT," + // 3: option
                "\"BABY_ID\" TEXT," + // 4: babyId
                "\"SUBJECT_ID\" TEXT," + // 5: subjectId
                "\"LENGHT\" INTEGER NOT NULL );"); // 6: lenght
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"THE_TOPIC_RECORD_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, TheTopicRecordBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String contentId = entity.getContentId();
        if (contentId != null) {
            stmt.bindString(2, contentId);
        }
 
        String scoreSetting = entity.getScoreSetting();
        if (scoreSetting != null) {
            stmt.bindString(3, scoreSetting);
        }
 
        String option = entity.getOption();
        if (option != null) {
            stmt.bindString(4, option);
        }
 
        String babyId = entity.getBabyId();
        if (babyId != null) {
            stmt.bindString(5, babyId);
        }
 
        String subjectId = entity.getSubjectId();
        if (subjectId != null) {
            stmt.bindString(6, subjectId);
        }
        stmt.bindLong(7, entity.getLenght());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, TheTopicRecordBean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String contentId = entity.getContentId();
        if (contentId != null) {
            stmt.bindString(2, contentId);
        }
 
        String scoreSetting = entity.getScoreSetting();
        if (scoreSetting != null) {
            stmt.bindString(3, scoreSetting);
        }
 
        String option = entity.getOption();
        if (option != null) {
            stmt.bindString(4, option);
        }
 
        String babyId = entity.getBabyId();
        if (babyId != null) {
            stmt.bindString(5, babyId);
        }
 
        String subjectId = entity.getSubjectId();
        if (subjectId != null) {
            stmt.bindString(6, subjectId);
        }
        stmt.bindLong(7, entity.getLenght());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public TheTopicRecordBean readEntity(Cursor cursor, int offset) {
        TheTopicRecordBean entity = new TheTopicRecordBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // contentId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // scoreSetting
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // option
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // babyId
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // subjectId
            cursor.getInt(offset + 6) // lenght
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, TheTopicRecordBean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setContentId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setScoreSetting(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setOption(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setBabyId(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setSubjectId(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setLenght(cursor.getInt(offset + 6));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(TheTopicRecordBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(TheTopicRecordBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(TheTopicRecordBean entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
