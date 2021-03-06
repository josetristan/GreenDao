package com.jtristan.greendao.dao;

import java.util.List;
import com.jtristan.greendao.dao.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END
/**
 * Entity mapped to table PEDIDO.
 */
public class Pedido {

    private Long id;
    private Long numeroPedido;
    /** Not-null value. */
    private String cliente;
    private long direccion;
    private long idCondicionPago;
    private Boolean finalizado;
    private java.util.Date fechaCreacion;

    /** Used to resolve relations */
    private DaoSession daoSession;

    /** Used for active entity operations. */
    private PedidoDao myDao;

    private List<Linea> Lineas;
    private List<CondicionPagoDeUnPedido> CondicionesPago;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public Pedido() {
    }

    public Pedido(Long id) {
        this.id = id;
    }

    public Pedido(Long id, Long numeroPedido, String cliente, long direccion, long idCondicionPago, Boolean finalizado, java.util.Date fechaCreacion) {
        this.id = id;
        this.numeroPedido = numeroPedido;
        this.cliente = cliente;
        this.direccion = direccion;
        this.idCondicionPago = idCondicionPago;
        this.finalizado = finalizado;
        this.fechaCreacion = fechaCreacion;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getPedidoDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(Long numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    /** Not-null value. */
    public String getCliente() {
        return cliente;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public long getDireccion() {
        return direccion;
    }

    public void setDireccion(long direccion) {
        this.direccion = direccion;
    }

    public long getIdCondicionPago() {
        return idCondicionPago;
    }

    public void setIdCondicionPago(long idCondicionPago) {
        this.idCondicionPago = idCondicionPago;
    }

    public Boolean getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(Boolean finalizado) {
        this.finalizado = finalizado;
    }

    public java.util.Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(java.util.Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public synchronized List<Linea> getLineas() {
        if (Lineas == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            LineaDao targetDao = daoSession.getLineaDao();
            Lineas = targetDao._queryPedido_Lineas(id);
        }
        return Lineas;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetLineas() {
        Lineas = null;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public synchronized List<CondicionPagoDeUnPedido> getCondicionesPago() {
        if (CondicionesPago == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            CondicionPagoDeUnPedidoDao targetDao = daoSession.getCondicionPagoDeUnPedidoDao();
            CondicionesPago = targetDao._queryPedido_CondicionesPago(id);
        }
        return CondicionesPago;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetCondicionesPago() {
        CondicionesPago = null;
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}
