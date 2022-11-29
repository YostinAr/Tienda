package com.tienda.service;

import com.tienda.dao.ArticuloDao;
import com.tienda.domain.Articulo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArticuloServiceImpl implements ArticuloService {
    //Se implementan los 4 metodos de un CRUD
    //Create Read Update Delete mediante ArticuloDao
    
    //Se utiliza una anotación Autowired para que el objeto articuloDao
    //Si ya está en memoria se use ese... sino se crea (Patrón singleton)
    @Autowired
    private ArticuloDao articuloDao;
    
    //Retorna la lista de articulos
    @Override
    @Transactional(readOnly = true)
    public List<Articulo> getArticulos(boolean activos) {
        var lista = (List<Articulo>)articuloDao.findAll();
        
        if (activos) {  //sólo los articulos activos
            lista.removeIf(e -> !e.isActivo());
        }
        
        return lista;
    }
    
    //Dado un articulo.id se busca en la tabla y 
    //se retorna todo el objeto Articulo
    @Override
    @Transactional(readOnly = true)    
    public Articulo getArticulo(Articulo articulo) {
        return articuloDao.findById(articulo.getIdArticulo()).orElse(null);
    }
    
    //Si el articulo.id tiene un valor se busca y se actualiza
    //Si el articulo.id NO tiene valor, se inserta el objeto en la tabla
    @Override
    @Transactional
    public void save(Articulo articulo){
        articuloDao.save(articulo);
    }
    
    //Elimina el registro que tiene el id igual a articulo.id
    @Override
    @Transactional
    public void delete(Articulo articulo){
        articuloDao.delete(articulo);
    }
}
