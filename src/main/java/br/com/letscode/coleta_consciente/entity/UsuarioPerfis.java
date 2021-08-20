package br.com.letscode.coleta_consciente.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name= "usuario_perfis")
public class UsuarioPerfis {

    @Id
    @Column(name = "usuario_id")
    private int userId;

    @Column(name = "perfis_id")
    private int perfilId;


}
