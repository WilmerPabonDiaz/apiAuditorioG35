package co.usa.auditoriog35.auditoriog35.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.usa.auditoriog35.auditoriog35.Model.Calificacion;
import co.usa.auditoriog35.auditoriog35.Repository.CalificacionRepositorio;

@Service
public class CalificacionServicio {
    @Autowired
    CalificacionRepositorio calificacionRepositorio;

    public List<Calificacion>mostrarTodo(){
        return calificacionRepositorio.mostarTodo();
    }

    public Optional<Calificacion>mostarUno(int id){
        return calificacionRepositorio.mostrarUno(id);
    }

    public Calificacion guardar(Calificacion calificacion){
        if (calificacion.getIdScore()==null) {
            return calificacionRepositorio.guardar(calificacion);
        } else {
            Optional<Calificacion> respuesta=calificacionRepositorio.mostrarUno(calificacion.getIdScore());
            if (respuesta.isEmpty()) {
                return calificacionRepositorio.guardar(calificacion);
            } else {
                return calificacion;
            }
        }
    }

    public Calificacion actualizar(Calificacion calificacion){
        if (calificacion.getIdScore()!=null) {
            Optional<Calificacion> respuesta=calificacionRepositorio.mostrarUno(calificacion.getIdScore());
            if (!respuesta.isEmpty()) {
                if(calificacion.getQualification()!=null){
                    respuesta.get().setQualification(calificacion.getQualification());
                }
                if(calificacion.getPrice()!=null){
                    respuesta.get().setPrice(calificacion.getPrice());
                }
                calificacionRepositorio.guardar(respuesta.get());
                return respuesta.get();
            } else {
                return calificacion;
            }
            
        } else {
            return calificacion;
        }
    }

    public boolean borrar(int id){
        Optional<Calificacion> consulta=calificacionRepositorio.mostrarUno(id);
        if (!consulta.isEmpty()) {
            calificacionRepositorio.borrar(consulta.get());
            return true;
        }
        return false;
    }

}
