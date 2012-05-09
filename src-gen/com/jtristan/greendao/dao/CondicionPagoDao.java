package com.jtristan.greendao.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.DaoConfig;
import de.greenrobot.dao.Property;

import com.jtristan.greendao.dao.CondicionPago;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table CONDICION_PAGO.
*/
public class CondicionPagoDao extends AbstractDao<CondicionPago, Long> {

    public static final String TABLENAME = "CONDICION_PAGO";

    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Condicion = new Property(1, String.class, "condicion", false, "CONDICION");
        public final static Property Porcentaje = new Property(2, Double.class, "porcentaje", false, "PORCENTAJE");
        public final static Property Valor = new Property(3, Double.class, "valor", false, "VALOR");
    };


    public CondicionPagoDao(DaoConfig config) {
        super(config);
    }
    
    public CondicionPagoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String sql = "CREATE TABLE " + (ifNotExists? "IF NOT EXISTS ": "") + "'CONDICION_PAGO' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'CONDICION' TEXT," + // 1: condicion
                "'PORCENTAJE' REAL," + // 2: porcentaje
                "'VALOR' REAL);"; // 3: valor
        db.execSQL(sql);
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'CONDICION_PAGO'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, CondicionPago entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String condicion = entity.getCondicion();
        if (condicion != null) {
            stmt.bindString(2, condicion);
        }
 
        Double porcentaje = entity.getPorcentaje();
        if (porcentaje != null) {
            stmt.bindDouble(3, porcentaje);
        }
 
        Double valor = entity.getValor();
        if (valor != null) {
            stmt.bindDouble(4, valor);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public CondicionPago readEntity(Cursor cursor, int offset) {
        CondicionPago entity = new CondicionPago( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // condicion
            cursor.isNull(offset + 2) ? null : cursor.getDouble(offset + 2), // porcentaje
            cursor.isNull(offset + 3) ? null : cursor.getDouble(offset + 3) // valor
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, CondicionPago entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setCondicion(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setPorcentaje(cursor.isNull(offset + 2) ? null : cursor.getDouble(offset + 2));
        entity.setValor(cursor.isNull(offset + 3) ? null : cursor.getDouble(offset + 3));
     }
    
    @Override
    protected Long updateKeyAfterInsert(CondicionPago entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(CondicionPago entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}