package com.sofka.contacts.controller;

import com.sofka.contacts.domain.ContactDomain;
import com.sofka.contacts.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Clase ContactController contiene los controladores que se encargarán de recibir, orquestar
 * el proceso necesario y responder las solicitudes de nuestras API.
 * @version 1.0.000 2023-01-20
 * @author Wendy Arcila
 *
 */
@Slf4j
@RestController
public class ContactController {

    /**
     * Servicio de contactos que se inyecta en la clase.
     */
    @Autowired
    private ContactService contactService;


    /**
     * Retorna una lista de todos los contactos.
     * @return una lista de objetos ContactDomain que representa todos los contactos disponibles.
     */
    @GetMapping(path = "/all")
    public List<ContactDomain> contactList(){
        return contactService.getList();
    }


    /**
     * Retorna un solo contacto identificado por su ID.
     * @param id el ID del contacto a buscar.
     * @return un objeto ResponseEntity que contiene el contacto encontrado.
     */
    @GetMapping(path = "/get/{id}")
    public ResponseEntity<ContactDomain> getOneContact(@PathVariable("id") Integer id){
        if (!contactService.existsByIdContact(id)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        ContactDomain contact = contactService.findContact(id).get();
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    /**
     * Inserta un nuevo contacto en la base de datos.
     * @param contact el objeto ContactDomain que representa el contacto a crear.
     * @return un objeto ResponseEntity que contiene el contacto a guardar
     */
    @PostMapping(path = "/save")
    public ResponseEntity<ContactDomain> insertContact(@RequestBody ContactDomain contact){
        try{
            log.info("Contacto nuevo: {}", contact);
            contactService.postContact(contact);
            return new ResponseEntity<>(contact, HttpStatus.CREATED);
        }catch (Exception exc){
        System.out.println(exc);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Actualiza un contacto existente identificado por su ID
     * @param contact el objeto ContactDomain que representa el contacto a actualizar.
     * @param id el ID del contacto a actualizar
     * @return un objeto ResponseEntity que contiene el contacto actualizado.
     */
    @PutMapping(path = "/update/{id}")
    public ResponseEntity<ContactDomain> updateContact(@RequestBody ContactDomain contact, @PathVariable("id") Integer id){
        contactService.putContacts(id, contact);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    /**
     * Actualiza el nombre de un contacto existente identificado por su ID
     * @param contact el objeto ContactDomain que representa el contacto a actualizar.
     * @param id el ID del contacto a actualizar
     * @return retorna un objeto ResponseEntity que contiene el contacto actualizado.
     */
    @PatchMapping(path = "/update/name/{id}")
    public ResponseEntity<ContactDomain> updateNameContact(ContactDomain contact, @PathVariable("id") Integer id){
        contactService.updateName(id, contact);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    /**
     * Actualiza el número de teléfono de un contacto existente identificado por su ID
     * @param contact el objeto ContactDomain que representa el contacto a actualizar.
     * @param id el ID del contacto a actualizar
     * @return retorna un objeto ResponseEntity que contiene el contacto actualizado.
     */
    @PatchMapping(path = "/update/phone/{id}")
    public ResponseEntity<ContactDomain> updatePhoneContact(ContactDomain contact, @PathVariable("id") Integer id) {
        contactService.updatePhone(id, contact);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    /**
     * Actualiza el correo electrónico de un contacto existente identificado por su ID
     * @param contact el objeto ContactDomain que representa el contacto a actualizar.
     * @param id el ID del contacto a actualizar
     * @return retorna un objeto ResponseEntity que contiene el contacto actualizado.
     */
    @PatchMapping(path = "/update/email/{id}")
    public ResponseEntity<ContactDomain> updateEmailContact(ContactDomain contact, @PathVariable("id") Integer id){
            contactService.updateEmail(id, contact);
            return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    /**
     * Actualiza la fecha de nacimiento de un contacto existente identificado por su ID
     * @param contact el objeto ContactDomain que representa el contacto a actualizar.
     * @param id el ID del contacto a actualizar
     * @return retorna un objeto ResponseEntity que contiene el contacto actualizado.
     */
    @PatchMapping(path = "/update/birth/{id}")
    public  ResponseEntity<ContactDomain> updateBirthContact(ContactDomain contact, @PathVariable("id") Integer id){
        contactService.updateBirth(id, contact);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    /**
     * Actualiza la fecha de eliminación de un contacto existente identificado por su ID
     * @param contact el objeto ContactDomain que representa el contacto a actualizar.
     * @param id el ID del contacto a actualizar
     * @return retorna un objeto ResponseEntity que contiene el contacto actualizado.
     */
    @PatchMapping(path = "/update/delete/{id}")
    public  ResponseEntity<ContactDomain> updateDeleteAt(ContactDomain contact, @PathVariable("id") Integer id){
        contactService.updateDeleteAt(id, contact);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    /**
     * Elimina un contacto existente
     * @param contact  el objeto ContactDomain que representa el contacto a eliminar.
     * @return etorna un objeto HttpEntity que contiene el contacto eliminado.
     */
    @DeleteMapping(path = "/delete/{id}")
    public HttpEntity<ContactDomain> deleteContact(ContactDomain contact){
        contactService.deleteC(contact);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

}
