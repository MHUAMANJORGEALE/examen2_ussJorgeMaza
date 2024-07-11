// Se crea la base de datos

package com.facturacion.almacenamiento2.entity;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "categorias")
@EntityListeners(AuditingEntityListener.class)
public class Category {
	@Id 
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	@Column(name="nombre", unique = true, nullable = false, length = 100)
	private String nombre;

	@Column(name="created_at", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;
	
	@Column(name="updated_at", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updatedAt;
	
	@Column(name="activo", nullable=false)
	private Boolean activo;
}
