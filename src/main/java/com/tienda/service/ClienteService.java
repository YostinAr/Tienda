package com.tienda.service;

import com.tienda.domain.Cliente;
import java.util.List;

public interface ClienteService {
    //se definen los 4 metodos de un CRUD
    //Create Read Update Delete
    
    //Retorna la lista de clientes
    public List<Cliente> getClientes();
    
    //Dado un cliente.id se busca en la tabla y 
    //se retorna todo el objeto Cliente
    public Cliente getCliente(Cliente cliente);
    
    //Si el cliente.id tiene un valor se busca y se actualiza
    //Su el cliente.id NO tiene valor, se inserta el objeto en la tabla
    public void save(Cliente cliente);
    
    //Elimina el registro que tiene el id igual a cliente.id
    public void delete(Cliente cliente);
    
    
    //Retorna la lista de clientes con apellidos esecificos
    public List<Cliente> getClientesPorApellido(String apellidos);
    
}
