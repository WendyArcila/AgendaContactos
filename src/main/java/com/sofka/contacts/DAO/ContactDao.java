package com.sofka.contacts.DAO;

import com.sofka.contacts.domain.ContactDomain;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

/**
 * Esta interfaz define métodos para acceder a la tabla ContactDomain en la base de datos.
 * @version 1.0.000 2023-01-20
 * @author Wendy Arcila
 *
 */
public interface ContactDao extends CrudRepository <ContactDomain, Integer> {

    /**
     * Actualiza el nombre de un contacto en la tabla ContactDomain.
     * @param id el ID del contacto que se va a actualizar.
     * @param name el nuevo nombre para el contacto.
     */
    @Modifying
    @Query("update ContactDomain contact set contact.name = :name where contact.id = :id")
    public void updateName(
            @Param(value = "id") Integer id,
            @Param(value = "name") String name);

    /**
     * Actualiza el número de teléfono de un contacto en la tabla ContactDomain.
     * @param id el ID del contacto que se va a actualizar.
     * @param phone el nuevo número de teléfono para el contacto.
     */
    @Modifying
    @Query("update ContactDomain contact set contact.phone = :phone where contact.id = :id")
    public void updatePhone(
            @Param(value = "id") Integer id,
            @Param(value = "phone") String phone);

    /**
     * Actualiza el correo electrónico de un contacto en la tabla ContactDomain.
     * @param id el ID del contacto que se va a actualizar.
     * @param email el nuevo correo electrónico para el contacto.
     */
    @Modifying
    @Query("update ContactDomain contact set contact.email = :email where contact.id = :id")
    public void updateEmail(
            @Param(value = "id") Integer id,
            @Param(value = "email") String email);

    /**
     * Actualiza la fecha de nacimiento de un contacto en la tabla ContactDomain.
     * @param id el ID del contacto que se va a actualizar.
     * @param birth la nueva fecha de nacimiento para el contacto.
     */
    @Modifying
    @Query("update ContactDomain contact set contact.birth = :birth where contact.id = :id")
    public void updateBirth(
            @Param(value = "id") Integer id,
            @Param(value = "birth") LocalDate birth);

    /**
     * Actualiza la fecha de eliminación de un contacto en la tabla ContactDomain.
     * @param id el ID del contacto que se va a actualizar.
     * @param deleteAt la nueva fecha de eliminación para el contacto.
     */
    @Modifying
    @Query("update ContactDomain contact set contact.deleteAt = :deleteAt where contact.id = :id")
    public void updateDeleteAt(
            @Param(value = "id") Integer id,
            @Param(value = "deleteAt") LocalDate deleteAt);
}
