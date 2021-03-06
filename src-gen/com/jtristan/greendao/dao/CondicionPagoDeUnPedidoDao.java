package com.jtristan.greendao.dao;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.DaoConfig;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.SqlUtils;
import de.greenrobot.dao.Query;
import de.greenrobot.dao.QueryBuilder;

import com.jtristan.greendao.dao.CondicionPagoDeUnPedido;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table CONDICION_PAGO_DE_UN_PEDIDO.
*/
public class CondicionPagoDeUnPedidoDao extends AbstractDao<CondicionPagoDeUnPedido, Void> {

    public static final String TABLENAME = "CONDICION_PAGO_DE_UN_PEDIDO";

    public static class Properties {
        public final static Property IdPedido = new Property(0, long.class, "idPedido", false, "ID_PEDIDO");
        public final static Property IdCondicion = new Property(1, long.class, "idCondicion", false, "ID_CONDICION");
    };

    private DaoSession daoSession;

    private Query<CondicionPagoDeUnPedido> pedido_CondicionesPagoQuery;

    public CondicionPagoDeUnPedidoDao(DaoConfig config) {
        super(config);
    }
    
    public CondicionPagoDeUnPedidoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String sql = "CREATE TABLE " + (ifNotExists? "IF NOT EXISTS ": "") + "'CONDICION_PAGO_DE_UN_PEDIDO' (" + //
                "'ID_PEDIDO' INTEGER NOT NULL ," + // 0: idPedido
                "'ID_CONDICION' INTEGER NOT NULL );"; // 1: idCondicion
        db.execSQL(sql);
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'CONDICION_PAGO_DE_UN_PEDIDO'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, CondicionPagoDeUnPedido entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getIdPedido());
        stmt.bindLong(2, entity.getIdCondicion());
    }

    @Override
    protected void attachEntity(CondicionPagoDeUnPedido entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    /** @inheritdoc */
    @Override
    public CondicionPagoDeUnPedido readEntity(Cursor cursor, int offset) {
        CondicionPagoDeUnPedido entity = new CondicionPagoDeUnPedido( //
            cursor.getLong(offset + 0), // idPedido
            cursor.getLong(offset + 1) // idCondicion
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, CondicionPagoDeUnPedido entity, int offset) {
        entity.setIdPedido(cursor.getLong(offset + 0));
        entity.setIdCondicion(cursor.getLong(offset + 1));
     }
    
    @Override
    protected Void updateKeyAfterInsert(CondicionPagoDeUnPedido entity, long rowId) {
        // TODO XXX Only Long PKs are supported currently
        return null;
    }
    
    /** @inheritdoc */
    @Override
    public Void getKey(CondicionPagoDeUnPedido entity) {
        return null;
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "CondicionesPago" to-many relationship of Pedido. */
    public synchronized List<CondicionPagoDeUnPedido> _queryPedido_CondicionesPago(long idPedido) {
        if (pedido_CondicionesPagoQuery == null) {
            QueryBuilder<CondicionPagoDeUnPedido> queryBuilder = queryBuilder();
            queryBuilder.where(Properties.IdPedido.eq(idPedido));
            pedido_CondicionesPagoQuery = queryBuilder.build();
        } else {
            pedido_CondicionesPagoQuery.setParameter(0, idPedido);
        }
        return pedido_CondicionesPagoQuery.list();
    }

    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getCondicionPagoDao().getAllColumns());
            builder.append(" FROM CONDICION_PAGO_DE_UN_PEDIDO T");
            builder.append(" LEFT JOIN CONDICION_PAGO T0 ON T.'ID_CONDICION'=T0.'_id'");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected CondicionPagoDeUnPedido loadCurrentDeep(Cursor cursor, boolean lock) {
        CondicionPagoDeUnPedido entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        CondicionPago condicionPago = loadCurrentOther(daoSession.getCondicionPagoDao(), cursor, offset);
         if(condicionPago != null) {
            entity.setCondicionPago(condicionPago);
        }

        return entity;    
    }

    public CondicionPagoDeUnPedido loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<CondicionPagoDeUnPedido> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<CondicionPagoDeUnPedido> list = new ArrayList<CondicionPagoDeUnPedido>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<CondicionPagoDeUnPedido> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<CondicionPagoDeUnPedido> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
