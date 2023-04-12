package com.sofka.contacts.service;

import com.sofka.contacts.domain.ContactDomain;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz que define los métodos que se pueden utilizar para interactuar con los contactos almacenados
 * @version 1.0.000 2023-01-20
 * @author Wendy Arcila
 */
public interface IContactService {

    /**
     * Devuelve una lista de todos los contactos almacenados en la base de datos.
     * @return Lista de objetos ContactDomain.
     */
    public List<ContactDomain> getList();

    /**
     * Busca un contacto por ID.
     * @param id El ID del contacto a buscar.
     * @return Un objeto ContactDomain si se encuentra, de lo contrario se devuelve un valor nulo.
     */
    Optional<ContactDomain> findContact(Integer id);

    /**
     * Guarda un contacto en la base de datos.
     * @param contact Objeto ContactDomain a guardar.
     * @return El objeto ContactDomain guardado.
     */
    ContactDomain postContact(ContactDomain contact);

    /**
     * Actualiza un contacto existente en la base de datos.
     * @param id      El ID del contacto a actualizar.
     * @param contact Objeto ContactDomain con la información actualizada.
     * @return El objeto ContactDomain actualizado.
     */
    ContactDomain putContacts(Integer id, ContactDomain contact);

    /**
     * Elimina un contacto existente
     * @param contact el objeto ContactDomain que representa el contacto a eliminar.
     */
    void deleteC(ContactDomain contact);


}
