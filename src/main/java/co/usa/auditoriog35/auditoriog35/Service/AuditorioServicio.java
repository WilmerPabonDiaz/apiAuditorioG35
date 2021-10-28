package co.usa.auditoriog35.auditoriog35.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.usa.auditoriog35.auditoriog35.Model.Auditorio;
import co.usa.auditoriog35.auditoriog35.Repository.AuditorioRepositorio;

@Service
public class AuditorioServicio {
    @Autowired
    AuditorioRepositorio auditorioRepositorio;

    public List<Auditorio>mostrarTodo(){
        return auditorioRepositorio.mostarTodo();
    }

    public Optional<Auditorio>mostarUno(int id){
        return auditorioRepositorio.mostrarUno(id);
    }

    public Auditorio guardar(Auditorio auditorio){
        if (auditorio.getId()==null) {
            return auditorioRepositorio.guardar(auditorio);
        } else {
            Optional<Auditorio> respuesta=auditorioRepositorio.mostrarUno(auditorio.getId());
            if (respuesta.isEmpty()) {
                return auditorioRepositorio.guardar(auditorio);
            } else {
                return auditorio;
            }
        }
    }

    public Auditorio actualizar(Auditorio auditorio){
        if (auditorio.getId()!=null) {
            Optional<Auditorio> respuesta=auditorioRepositorio.mostrarUno(auditorio.getId());
            if (!respuesta.isEmpty()) {
                if(auditorio.getName()!=null){
                    respuesta.get().setName(auditorio.getName());
                }
                if (auditorio.getOwner()!=null) {
                    respuesta.get().setOwner(auditorio.getOwner());
                }
                if (auditorio.getCapacity()!=null) {
                    respuesta.get().setCapacity(auditorio.getCapacity());
                }
                if (auditorio.getDescription()!=null) {
                    respuesta.get().setDescription(auditorio.getDescription());
                }
                if (auditorio.getCategory()!=null) {
                    respuesta.get().setCategory(auditorio.getCategory());
                }
                auditorioRepositorio.guardar(respuesta.get());
                return respuesta.get();
            } else {
                return auditorio;
            }
            
        } else {
            return auditorio;
        }
    }

    public boolean borrar(int id){
        Optional<Auditorio> consulta=auditorioRepositorio.mostrarUno(id);
        if (!consulta.isEmpty()) {
            auditorioRepositorio.borrar(consulta.get());
            return true;
        }
        return false;
    }
}
