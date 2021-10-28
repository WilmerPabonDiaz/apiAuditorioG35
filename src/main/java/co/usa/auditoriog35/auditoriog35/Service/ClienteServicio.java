package co.usa.auditoriog35.auditoriog35.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.usa.auditoriog35.auditoriog35.Model.Cliente;
import co.usa.auditoriog35.auditoriog35.Repository.ClienteRepositorio;

@Service
public class ClienteServicio {
    @Autowired
    ClienteRepositorio clienteRepositorio;

    public List<Cliente>mostrarTodo(){
        return clienteRepositorio.mostarTodo();
    }

    public Optional<Cliente>mostarUno(int id){
        return clienteRepositorio.mostrarUno(id);
    }

    public Cliente guardar(Cliente cliente){
        if (cliente.getIdClient()==null) {
            return clienteRepositorio.guardar(cliente);
        } else {
            Optional<Cliente> respuesta=clienteRepositorio.mostrarUno(cliente.getIdClient());
            if (respuesta.isEmpty()) {
                return clienteRepositorio.guardar(cliente);
            } else {
                return cliente;
            }
        }
    }

    public Cliente actualizar(Cliente cliente){
        if (cliente.getIdClient()!=null) {
            Optional<Cliente> respuesta=clienteRepositorio.mostrarUno(cliente.getIdClient());
            if (!respuesta.isEmpty()) {
                if(cliente.getEmail()!=null){
                    respuesta.get().setEmail(cliente.getEmail());
                }
                if(cliente.getPassword()!=null){
                    respuesta.get().setPassword(cliente.getPassword());
                }
                if(cliente.getName()!=null){
                    respuesta.get().setName(cliente.getName());
                }
                if(cliente.getAge()!=null){
                    respuesta.get().setAge(cliente.getAge());
                }
                clienteRepositorio.guardar(respuesta.get());
                return respuesta.get();
            } else {
                return cliente;
            }
            
        } else {
            return cliente;
        }
    }

    public boolean borrar(int id){
        Optional<Cliente> consulta=clienteRepositorio.mostrarUno(id);
        if (!consulta.isEmpty()) {
            clienteRepositorio.borrar(consulta.get());
            return true;
        }
        return false;
    }
}
