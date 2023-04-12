package com.sofka.contacts.service;

import com.sofka.contacts.DAO.ContactDao;
import com.sofka.contacts.domain.ContactDomain;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * La clase ContactService implementa la interfaz IContactService para definir
 * los métodos que se pueden utilizar para interactuar con los contactos almacenados
 * en la base de datos. Esta clase usa un objeto ContactDao para acceder a los datos.
 * @version 1.0.000 2023-01-20
 * @author Wendy Arcila
 *
 */
@Service
public class ContactService implements IContactService {

    @Autowired
    private ContactDao contactDao;

    /**
     * Devuelve una lista de todos los contactos almacenados en la base de datos.
     * @return Lista de objetos ContactDomain.
     */
    @Override
    @Transactional
    public List<ContactDomain> getList() {
        return (List<ContactDomain>) contactDao.findAll();
    }

    /**
     * Busca un contacto por ID.
     * @param id El ID del contacto a buscar.
     * @return Un objeto ContactDomain si se encuentra, de lo contrario se devuelve un valor nulo.
     */
    @Override
    @Transactional
    public Optional<ContactDomain> findContact(Integer id) {
        return contactDao.findById(id);
    }

    /**
     * Guarda un contacto en la base de datos.
     * @param contact Objeto ContactDomain a guardar.
     * @return El objeto ContactDomain guardado.
     */
    @Override
    @Transactional
    public ContactDomain postContact(ContactDomain contact) {
        return contactDao.save(contact);
    }

    /**
     * Actualiza un contacto existente en la base de datos.
     * @param id      El ID del contacto a actualizar.
     * @param contact Objeto ContactDomain con la información actualizada.
     * @return El objeto ContactDomain actualizado.
     */
    @Override
    @Transactional
    public ContactDomain putContacts(Integer id, ContactDomain contact) {
        contact.setId(id);
        return contactDao.save(contact);
    }

    /**
     * Actualiza el nombre de un contacto existente en la base de datos.
     * @param id      El ID del contacto a actualizar.
     * @param contact Objeto ContactDomain con el nuevo nombre.
     */
    @Transactional
    public  void updateName(Integer id, ContactDomain contact){
        contactDao.updateName(id, contact.getName());
    }

    /**
     * Actualiza el número de teléfono de un contacto existente en la base de datos.
     * @param id      El ID del contacto a actualizar.
     * @param contact Objeto ContactDomain con el nuevo número de teléfono.
     */
    @Transactional
    public  void updatePhone(Integer id, ContactDomain contact){
        contactDao.updatePhone(id, contact.getPhone());
    }

    /**
     * Actualiza el correo electrónico de un contacto existente en la base de datos.
     * @param id      El ID del contacto a actualizar.
     * @param contact Objeto ContactDomain con el nuevo correo electrónico.
     */
    @Transactional
    public  void updateEmail(Integer id, ContactDomain contact){
        contactDao.updateEmail(id, contact.getEmail());
    }

    /**
     * Actualiza la fecha de nacimiento de un contacto existente en la base de datos.
     * @param id      El ID del contacto a actualizar.
     * @param contact Objeto ContactDomain con la nueva fecha de nacimiento.
     */
    @Transactional
    public  void updateBirth(Integer id, ContactDomain contact){
        contactDao.updateBirth(id, contact.getBirth());
    }

    /**
     * Actualiza la fecha de eliminación de un contacto existente en la base
     * @param id  El ID del contacto a actualizar.
     * @param contact  Objeto ContactDomain con la nueva fecha de eliminación
     */
    @Transactional
    public  void updateDeleteAt(Integer id, ContactDomain contact){
        contactDao.updateDeleteAt(id, contact.getDeleteAt());
    }

    /**
     * Elimina un contacto existente
     * @param contact el objeto ContactDomain que representa el contacto a eliminar.
     */
    @Override
    @Transactional
    public void deleteC(ContactDomain contact) {
        contactDao.delete(contact);
    }

    /**
     * Busca un contacto por su ID, el cual lo recibe como parámetro.
     * @param id El ID del contacto a buscar.
     * @return Condición boolean true si lo encuentra, false en caso contrario.
     */
    public boolean existsByIdContact(Integer id){
        return contactDao.existsById(id);
    }
}
