package co.usa.auditoriog35.auditoriog35.Repository.Crud;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import co.usa.auditoriog35.auditoriog35.Model.Reservacion;

public interface ReservacionCrudRepositorio extends CrudRepository<Reservacion,Integer>{

    public List<Reservacion> findAllByStatus (String status); 
    
    public List<Reservacion> findAllByStartDateAfterAndStartDateBefore(Date dateOne, Date dateTwo);
    
    // SELECT clientid, COUNT(*) AS total FROM reservacion group by clientid order by desc;
    @Query ("SELECT c.client, COUNT(c.client) from Reservacion AS c group by c.client order by COUNT(c.client)DESC")
    public List<Object[]> countTotalReservationsByClient();

       
   
}
