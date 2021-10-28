package co.usa.auditoriog35.auditoriog35.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.usa.auditoriog35.auditoriog35.Model.Mensaje;
import co.usa.auditoriog35.auditoriog35.Repository.MensajeRepositorio;

@Service
public class MensajeServicio {
    @Autowired
    MensajeRepositorio mensajeRepositorio;

    public List<Mensaje>mostrarTodo(){
        return mensajeRepositorio.mostarTodo();
    }

    public Optional<Mensaje>mostarUno(int id){
        return mensajeRepositorio.mostrarUno(id);
    }

    public Mensaje guardar(Mensaje mensaje){
        if (mensaje.getIdMessage()==null) {
            return mensajeRepositorio.guardar(mensaje);
        } else {
            Optional<Mensaje> consulta=mensajeRepositorio.mostrarUno(mensaje.getIdMessage());
            if (consulta.isEmpty()) {
                return mensajeRepositorio.guardar(mensaje);
            } else {
                return mensaje;
            }
        }
    }

    public Mensaje actualizar(Mensaje mensaje){
        if (mensaje.getIdMessage()!=null) {
            Optional<Mensaje> respuesta=mensajeRepositorio.mostrarUno(mensaje.getIdMessage());
            if (!respuesta.isEmpty()) {
                if(mensaje.getMessageText()!=null){
                    respuesta.get().setMessageText(mensaje.getMessageText());
                }
                mensajeRepositorio.guardar(respuesta.get());
                return respuesta.get();
            } else {
                return mensaje;
            }
            
        } else {
            return mensaje;
        }
    }

    public boolean borrar(int id){
        Optional<Mensaje> consulta=mensajeRepositorio.mostrarUno(id);
        if (!consulta.isEmpty()) {
            mensajeRepositorio.borrar(consulta.get());
            return true;
        }
        return false;
    }
}
