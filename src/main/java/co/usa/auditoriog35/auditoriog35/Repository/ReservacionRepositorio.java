package co.usa.auditoriog35.auditoriog35.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.usa.auditoriog35.auditoriog35.Model.Cliente;
import co.usa.auditoriog35.auditoriog35.Model.ContadorCliente;
import co.usa.auditoriog35.auditoriog35.Model.Reservacion;
import co.usa.auditoriog35.auditoriog35.Repository.Crud.ReservacionCrudRepositorio;



@Repository
public class ReservacionRepositorio {
    @Autowired
    ReservacionCrudRepositorio reservacionCrudRepositorio;

    public List<Reservacion> mostarTodo(){
        return(List<Reservacion>) reservacionCrudRepositorio.findAll();
    }

    public Optional<Reservacion> mostrarUno(int id){
        return reservacionCrudRepositorio.findById(id);
    }

    public Reservacion guardar(Reservacion reservacion){
        return reservacionCrudRepositorio.save(reservacion);
    }

    public void borrar(Reservacion reservacion){
        reservacionCrudRepositorio.delete(reservacion);
    }

    public List<Reservacion> reservacionStatusRepositorio (String status){
        return reservacionCrudRepositorio.findAllByStatus(status);
    }

    public List<Reservacion> reservacionTiempoRepositorio (Date dateOne, Date dateTwo){
        return reservacionCrudRepositorio.findAllByStartDateAfterAndStartDateBefore(dateOne, dateTwo);
    }

    public List<ContadorCliente> getTopClientes(){
        List<ContadorCliente> res= new ArrayList<>();

        List<Object[]> reporte= reservacionCrudRepositorio.countTotalReservationsByClient();
        for (int i = 0; i < reporte.size(); i++) {
            res.add(new ContadorCliente((Long) reporte.get(i)[1], (Cliente) reporte.get(i)[0]));            
        }
        return res;
    }
    
}
