package com.std.ec.model.dto;



import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Builder(toBuilder = true)
public class ClienteDto implements Serializable {

    private Integer idCliente;
    private String nombre;
    private String apellido;
    private String email;
    private Date fechaRegistro;

}
