package co.usa.auditoriog35.auditoriog35.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.usa.auditoriog35.auditoriog35.Model.Administradores;
import co.usa.auditoriog35.auditoriog35.Repository.AdministradoresRepositorio;

@Service
public class AdministradoresServicio {
    @Autowired
    AdministradoresRepositorio administradoresRepositorio;

    public List<Administradores>mostrarTodo(){
        return administradoresRepositorio.mostarTodo();
    }

    public Optional<Administradores>mostarUno(int id){
        return administradoresRepositorio.mostrarUno(id);
    }

    public Administradores guardar(Administradores administradores){
        if (administradores.getIdUser()==null) {
            return administradoresRepositorio.guardar(administradores);
        } else {
            Optional<Administradores> respuesta=administradoresRepositorio.mostrarUno(administradores.getIdUser());
            if (respuesta.isEmpty()) {
                return administradoresRepositorio.guardar(administradores);
            } else {
                return administradores;
            }
        }
    }

    public Administradores actualizar(Administradores administradores){
        if (administradores.getIdUser()!=null) {
            Optional<Administradores> respuesta=administradoresRepositorio.mostrarUno(administradores.getIdUser());
            if (!respuesta.isEmpty()) {
                if(administradores.getEmail()!=null){
                    respuesta.get().setEmail(administradores.getEmail());
                }
                if(administradores.getPassword()!=null){
                    respuesta.get().setPassword(administradores.getPassword());
                }
                if(administradores.getName()!=null){
                    respuesta.get().setName(administradores.getName());
                }
                administradoresRepositorio.guardar(respuesta.get());
                return respuesta.get();
            } else {
                return administradores;
            }
            
        } else {
            return administradores;
        }
    }

    public boolean borrar(int id){
        Optional<Administradores> consulta=administradoresRepositorio.mostrarUno(id);
        if (!consulta.isEmpty()) {
            administradoresRepositorio.borrar(consulta.get());
            return true;
        }
        return false;
    }
}
