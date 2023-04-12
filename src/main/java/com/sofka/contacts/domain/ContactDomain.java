package com.sofka.contacts.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Clase ContactDomain contiene la entidad del contacto
 * @version 1.0.000 2023-01-20
 * @author Wendy Arcila
 *
 */
@Data
@Entity
@Table(name = "contact")
@Where(clause = "delete_at IS null")
public class ContactDomain implements Serializable {

    /**
     * Variable usada para manejar el tema del identificador de la tupla (consecutivo)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Identificador de la tupla
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//autoincrementa el id
    @Column
    private Integer id;


    private String name;


    private String phone;


    private String email;

    private LocalDate birth;

    @Column (name = "delete_at")
    private LocalDate deleteAt;
}
