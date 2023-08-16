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
@Table(name = "permisos")
public class Permiso {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String nombre;
  private String id_rol;
  private String id_menu;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_rol", insertable = false, updatable = false)
  private Rol rol;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "id_menu", insertable = false, updatable = false)
  private Menu menu;

}
