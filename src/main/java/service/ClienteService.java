
package service;

import entity.Cliente;
import java.sql.SQLException;
import repository.ClienteRepository;


public class ClienteService {
    
    private final ClienteRepository clienteRepository = new ClienteRepository();
    
    public Cliente validaLogin (Cliente cliente){
        return clienteRepository.validarLogin(cliente);
    }
    
    public Cliente salvarCliente (Cliente cliente){
        return clienteRepository.salvarCliente(cliente);
    }
}
