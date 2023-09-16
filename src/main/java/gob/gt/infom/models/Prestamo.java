package gob.gt.infom.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "prestamos")
public class Prestamo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String no_dictamen;
  private String no_pagare;
  private String fecha;
  private String fecha_vencimiento;
  private String monto;
  private Integer plazo_meses;
  private String fecha_acta;
  private Double deposito_intereses;
  private Double intereses;
  private String intereses_fecha_fin;
  private Integer tiempo_gracia;
  private String destino_prestamo;
  private Boolean cobro_intereses;
  private String acta;
  private String punto;
  private String fecha_memorial;
  private String autorizacion;
  private String certficacion;
  private String oficioaj;
  private String oficioaj2;
  private String estado;
  private Integer id_municipalidad;
  private Integer id_funcionario;
  private Integer id_regional;
  private Integer id_usuario;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_municipalidad", insertable = false, updatable = false)
  private Municipalidad municipalidad;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_funcionario", insertable = false, updatable = false)
  private Funcionario funcionario;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_regional", insertable = false, updatable = false)
  private Regional regional;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_usuario", insertable = false, updatable = false)
  private Usuario usuario;

}
