package com.backend.ejercicioOdontologoSpring.repository;

import java.util.List;

public interface IDao <T>{
    T registrar(T t);
    List<T> listarTodos();
    T buscarPorId(Long id);
    T actualizar(T t, Long id);
    T eliminar(Long id);
}
