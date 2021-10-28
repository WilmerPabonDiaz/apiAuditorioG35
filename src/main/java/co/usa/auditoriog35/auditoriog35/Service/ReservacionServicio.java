package co.usa.auditoriog35.auditoriog35.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.usa.auditoriog35.auditoriog35.Model.Reservacion;
import co.usa.auditoriog35.auditoriog35.Repository.ReservacionRepositorio;

/**
 * Marca de spring boot que indica que la clase es de tipo servicio
 */
@Service
/**
 * Clase publica que contiene metodos crud con validaciones de los campos
 */
public class ReservacionServicio {
    /**
     * Autowired idica que se va a sobre escribir los metodos de una clase de nivel superior
     */
    @Autowired
    ReservacionRepositorio reservacionRepositorio;

    /**
     * Metodo que permite mostrar todos los datos ingresados en la tabla Reservation
     * @return un objeto de tipo lista
     */
    public List<Reservacion>mostrarTodo(){
        return reservacionRepositorio.mostarTodo();
    }

    /**
     * Metodo que permite mostrar un solo dato de los ingresados en la tabla Reservation
     * @param id que se envia para realizar la consulta
     * @return un objeto de tipo Optional el cual contiene los datos de la consulta realizada
     */
    public Optional<Reservacion>mostarUno(int id){
        return reservacionRepositorio.mostrarUno(id);
    }

    /**
     * Metodo que guarda un objeto de tipo Reservacion, adicionalmente evalua si el parametro idReservation esta vacio,
     * si asi lo es se ejecuta y adiciona un nuevo registro sino, solo retorna el objeto enviado 
     * @param reservacion es de tipo Reservacion en el cual se almacenara la informaciòn ingresada
     * @return retorna objeto de tipo Reservacion
     */
    public Reservacion guardar(Reservacion reservacion){
        if (reservacion.getIdReservation()==null) {
            return reservacionRepositorio.guardar(reservacion);
        } else {
            Optional<Reservacion> consulta=reservacionRepositorio.mostrarUno(reservacion.getIdReservation());
            if (consulta.isEmpty()) {
                return reservacionRepositorio.guardar(reservacion);
            } else {
                return reservacion;
            }
        }
    }

    /**
     * Metodo que actualiza un registro de la tabla reservacion, evalua si el parametro idReservation esta vacio,
     * si asi lo es, es se ejecuta y actualiza el registro indicado, sino, solo retorna el objeto enviado 
     * @param reservacion es de tipo Reservacion en el cual se almacenara la informaciòn que se desea actualizar
     * @return retorna objeto de tipo Reservacion
     */
    public Reservacion actualizar(Reservacion reservacion){
        if (reservacion.getIdReservation()!=null) {
            Optional<Reservacion> respuesta=reservacionRepositorio.mostrarUno(reservacion.getIdReservation());
            if (!respuesta.isEmpty()) {
                if(reservacion.getStartDate()!=null){
                    respuesta.get().setStartDate(reservacion.getStartDate());
                }
                if(reservacion.getDevolutionDate()!=null){
                    respuesta.get().setDevolutionDate(reservacion.getDevolutionDate());
                }
                if(reservacion.getStatus()!=null){
                    respuesta.get().setStatus(reservacion.getStatus());
                }

                reservacionRepositorio.guardar(respuesta.get());
                return respuesta.get();
            } else {
                return reservacion;
            }
            
        } else {
            return reservacion;
        }
    }

    /**
     * Metodo que permite borrar una reservacion de acuerdo al id ingresado.
     * @param id que es enviado por el usuario
     * @return true si se ejecuta o false sino lo hace.
     */
    public boolean borrar(int id){
        Optional<Reservacion> consulta=reservacionRepositorio.mostrarUno(id);
        if (!consulta.isEmpty()) {
            reservacionRepositorio.borrar(consulta.get());
            return true;
        }
        return false;
    }
}
