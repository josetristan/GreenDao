package com.jtristan.greendao;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import com.jtristan.greendao.dao.CondicionPago;
import com.jtristan.greendao.dao.CondicionPagoDao;
import com.jtristan.greendao.dao.CondicionPagoDao.Properties;
import com.jtristan.greendao.dao.CondicionPagoDeUnPedido;
import com.jtristan.greendao.dao.CondicionPagoDeUnPedidoDao;
import com.jtristan.greendao.dao.DaoMaster;
import com.jtristan.greendao.dao.DaoMaster.DevOpenHelper;
import com.jtristan.greendao.dao.DaoSession;
import com.jtristan.greendao.dao.Linea;
import com.jtristan.greendao.dao.LineaDao;
import com.jtristan.greendao.dao.Pedido;
import com.jtristan.greendao.dao.PedidoDao;

import de.greenrobot.dao.CloseableListIterator;

public class GreenDaoActivity extends Activity {
    
	private SQLiteDatabase db;    
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private PedidoDao pedidoDao;
    private LineaDao lineaDao;
    private CondicionPagoDao condicionPagoDao;
    private CondicionPagoDeUnPedidoDao condicionPagoDeUnPedidoDao;  
    
    private final static String PRONTOPAGO = "PRONTOPAGO";
    private final static String PREFERENTE = "PREFERENTE";
    private final static String ACUERDO = "ACUERDO";
    
    private final static String TAG = "GreenDaoActivity"; 
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
               
       DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "greendao", null);       
       try{
    	   db = helper.getWritableDatabase();
       }catch (Exception e){
    	   System.out.println(e.getMessage());
       }
       daoMaster = new DaoMaster(db);
       
       daoSession = daoMaster.newSession();                     
       
       pedidoDao = daoSession.getPedidoDao();
       lineaDao = daoSession.getLineaDao();
       condicionPagoDao = daoSession.getCondicionPagoDao();
       condicionPagoDeUnPedidoDao = daoSession.getCondicionPagoDeUnPedidoDao();
       
       darDeAltaCondicionesPago();
       darDeAltaPedidos();
       
       
			   
       CloseableListIterator<Pedido> pedidos = pedidoDao.queryBuilder().listIterator();
       Pedido pedido = pedidos.next();
	   List<Linea> lineas = pedido.getLineas();
       for (Linea linea_pedido: lineas){
    	   Log.i(TAG, linea_pedido.getIdPedido() + " " + 
    			   				linea_pedido.get_id() + " " + 
    			   				linea_pedido.getMaterial());
       }
       List<CondicionPagoDeUnPedido> condicionesDePago = pedido.getCondicionesPago();       
       for(CondicionPagoDeUnPedido condicionPagoDeUnPedido: condicionesDePago){
    	   Log.i(TAG, condicionPagoDeUnPedido.getIdPedido() + " " + 
    			   		condicionPagoDeUnPedido.getIdCondicion());    	   
       } 
       
       condicionesDePago = condicionPagoDeUnPedidoDao._queryPedido_CondicionesPago(pedido.getId());
       for(CondicionPagoDeUnPedido condicionPagoDeUnPedido: condicionesDePago){
    	   Log.i(TAG, condicionPagoDeUnPedido.getIdPedido() + " " + 
    			   		condicionPagoDeUnPedido.getIdCondicion());    	   
       }
       
       try {
    	   pedidos.close();
       } catch (IOException e) {
    	   Log.e(TAG, e.getMessage());
       }
        
       probarCacheRelaciones();
        
    }

	private void probarCacheRelaciones() {
		
		Pedido pedido = getPedido(1);
		
		List lineas = pedido.getLineas();
		Linea linea = new Linea();
		linea.setIdPedido(pedido.getId());
		linea.setMaterial("TUERCAS");
		linea.setCantidad(3400);
		linea.setPrecio(new Double(0.48));
		linea.setFechaCreacion(new Date());
		
		lineaDao.insert(linea);
		List lineas2 = pedido.getLineas();
		Log.i(TAG, lineas.size() + " " + lineas2.size());
		
		lineas.add(linea);
		Log.i(TAG, String.valueOf(lineas.size()));				
		
	}

	
	private Pedido getPedido(int numero_pedido)  {
		CloseableListIterator<Pedido> consulta = null;
		Pedido pedido = null;
		try{
			consulta = pedidoDao.queryBuilder()
							.where(Properties.Id.eq(numero_pedido))
							.listIterator();
			pedido = consulta.next();
			
		}catch (Exception e){
			Log.e(TAG, e.getMessage());
		}finally{
			try {
				consulta.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return pedido;
		
	}

	private void darDeAltaPedidos() {
		
		
		
		Pedido pedido = new Pedido();
		pedido.setNumeroPedido(new Long(1));
		pedido.setCliente("CARRETILLAS S.A.");
		pedido.setDireccion(new Long(1));
		pedido.setFinalizado(false);
		pedido.setFechaCreacion(new Date());
		   	   		
		pedidoDao.insert(pedido);
		
		CondicionPagoDeUnPedido condicionPagoDeUnPedido = new CondicionPagoDeUnPedido();
		condicionPagoDeUnPedido.setIdPedido(pedido.getNumeroPedido());						
		condicionPagoDeUnPedido.setIdCondicion(getIdCondicionPago(PRONTOPAGO));
		long insertados = condicionPagoDeUnPedidoDao.insert(condicionPagoDeUnPedido);
		
		condicionPagoDeUnPedido = new CondicionPagoDeUnPedido();
		condicionPagoDeUnPedido.setIdPedido(pedido.getNumeroPedido());						
		condicionPagoDeUnPedido.setIdCondicion(getIdCondicionPago(PREFERENTE));
		insertados = condicionPagoDeUnPedidoDao.insert(condicionPagoDeUnPedido);
			
		Linea linea = new Linea();
		linea.setIdPedido(pedido.getId());
		linea.setMaterial("TORNILLOS");
		linea.setCantidad(1000);
		linea.setPrecio(new Double(0.23));
		linea.setFechaCreacion(new Date());
			
		lineaDao.insert(linea);
			
		linea = new Linea();
		linea.setIdPedido(pedido.getId());
		linea.setMaterial("MARTILLOS");
		linea.setCantidad(5);
		linea.setPrecio(new Double(6.48));
		linea.setFechaCreacion(new Date());
			
		lineaDao.insert(linea);				
		
	}

	private long getIdCondicionPago(String condicion) {
		
		Long id = null;
		CloseableListIterator<CondicionPago> consulta = null;
		try{
			consulta = condicionPagoDao.queryBuilder().where(Properties.Condicion.eq(condicion)).listIterator();
			CondicionPago condicionPago = consulta.next(); 
			id = condicionPago.getId();
			Log.i(TAG, condicionPago.getCondicion());
		}catch (Exception e){
			Log.e(TAG, e.getMessage());
		}finally{
			try {
				consulta.close();
			} catch (IOException e) {
				Log.e(TAG, e.getMessage());				
			}			
		}
		return id;
	}

	private void darDeAltaCondicionesPago() {
		CondicionPago condicionPago = new CondicionPago();
		condicionPago.setCondicion(PRONTOPAGO);
		condicionPago.setPorcentaje(new Double(10));		
		condicionPagoDao.insert(condicionPago);
				
		
		condicionPago = new CondicionPago();
		condicionPago.setCondicion(PREFERENTE);
		condicionPago.setValor(new Double(50));		
		condicionPagoDao.insert(condicionPago);
		
		condicionPago = new CondicionPago();
		condicionPago.setCondicion(ACUERDO);
		condicionPago.setPorcentaje(new Double(2.5));		
		condicionPagoDao.insert(condicionPago);
		
		
		
		
	}
}
