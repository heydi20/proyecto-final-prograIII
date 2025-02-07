package com.itsqmet.app_hotel.Repositorio;

import com.itsqmet.app_hotel.Entidad.Reseñas;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReseñaRepositorio extends JpaRepository<Reseñas, Long> {
    List<Reseñas> findByFechacomentario(Date fechacomentario);


}
