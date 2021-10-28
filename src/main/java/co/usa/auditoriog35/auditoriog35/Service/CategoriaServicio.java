package co.usa.auditoriog35.auditoriog35.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.usa.auditoriog35.auditoriog35.Model.Categoria;
import co.usa.auditoriog35.auditoriog35.Repository.CategoriaRepositorio;

@Service
public class CategoriaServicio {
    @Autowired
    CategoriaRepositorio categoriaRepositorio;

    public List<Categoria>mostrarTodo(){
        return categoriaRepositorio.mostarTodo();
    }

    public Optional<Categoria>mostarUno(int id){
        return categoriaRepositorio.mostrarUno(id);
    }


    public Categoria guardar(Categoria guardarCategoria){
        if (guardarCategoria.getId()==null) {
            return categoriaRepositorio.guardar(guardarCategoria);
        } else {
            Optional<Categoria> consulta=categoriaRepositorio.mostrarUno(guardarCategoria.getId());
            if (consulta.isEmpty()) {
                return categoriaRepositorio.guardar(guardarCategoria);
            } else {
                return guardarCategoria;
            }
        }
    }

    public Categoria actualizar(Categoria categoria){
        if (categoria.getId()!=null) {
            Optional<Categoria> respuesta=categoriaRepositorio.mostrarUno(categoria.getId());
            if (!respuesta.isEmpty()) {
                if(categoria.getName()!=null){
                    respuesta.get().setName(categoria.getName());
                }
                if (categoria.getDescription()!=null) {
                    respuesta.get().setDescription(categoria.getDescription());
                }
                categoriaRepositorio.guardar(respuesta.get());
                return respuesta.get();
            } else {
                return categoria;
            }
            
        } else {
            return categoria;
        }
    }

    public boolean borrar(int id){
        Optional<Categoria> consulta=categoriaRepositorio.mostrarUno(id);
        if (!consulta.isEmpty()) {
            categoriaRepositorio.borrar(consulta.get());
            return true;
        }
        return false;
    }
}
