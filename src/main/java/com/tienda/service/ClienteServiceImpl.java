package com.tienda.service;

import com.tienda.dao.ClienteDao;
import com.tienda.dao.CreditoDao;
import com.tienda.domain.Cliente;
import com.tienda.domain.Credito;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteServiceImpl implements ClienteService {
    //Se implementan los 4 metodos de un CRUD
    //Create Read Update Delete mediante ClienteDao
    
    //Se utiliza una anotación Autowired para que el objeto clienteDao
    //Si ya está en memoria se use ese... sino se crea (Patrón singleton)
    @Autowired
    private ClienteDao clienteDao;
    
    //Se utiliza una anotación Autowired para que el objeto creditoDao
    //Si ya está en memoria se use ese... sino se crea (Patrón singleton)
    @Autowired
    private CreditoDao creditoDao;
    
    //Retorna la lista de clientes
    @Override
    @Transactional(readOnly = true)
    public List<Cliente> getClientes() {
        return (List<Cliente>)clienteDao.findAll();
    }
    
    //Dado un cliente.id se busca en la tabla y 
    //se retorna todo el objeto Cliente
    @Override
    @Transactional(readOnly = true)    
    public Cliente getCliente(Cliente cliente) {
        return clienteDao.findById(cliente.getIdCliente()).orElse(null);
    }
    
    //Si el cliente.id tiene un valor se busca y se actualiza
    //Si el cliente.id NO tiene valor, se inserta el objeto en la tabla
    @Override
    @Transactional
    public void save(Cliente cliente){
        Credito credito = cliente.getCredito();
        
        credito=creditoDao.save(credito);
        
        cliente.setCredito(credito);
        
        clienteDao.save(cliente);
    }
    
    //Elimina el registro que tiene el id igual a cliente.id
    @Override
    @Transactional
    public void delete(Cliente cliente){
        clienteDao.delete(cliente);
    }

    @Override
    public List<Cliente> getClientesPorApellido(String apellidos) {
        return clienteDao.findByApellidos(apellidos);
    }
}
