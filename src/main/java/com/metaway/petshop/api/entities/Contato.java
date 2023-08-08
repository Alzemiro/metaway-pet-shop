package com.metaway.petshop.api.entities;

import com.metaway.petshop.api.enums.EnumTipoContato;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contato")
public class Contato {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne	
	@JoinColumn(name = "cliente", referencedColumnName = "id")
	private Cliente cliente;

	@Column(name = "tag")
	private String tag;	

    @Column(name = "tipo_contato")
    private EnumTipoContato tipo;

    @Column(name = "valor")
    private String valor;
}
