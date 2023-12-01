package com.std.ec.controller;

import com.std.ec.model.dto.ClienteDto;
import com.std.ec.model.entity.Cliente;
import com.std.ec.model.payload.MensajeResponse;
import com.std.ec.service.ICliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1")
public class ClienteController {

    @Autowired
    private ICliente clienteService;

    @PostMapping("cliente")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody ClienteDto clienteDto) {
        Cliente clienteSave = null;
        try {
            clienteSave = clienteService.save(clienteDto);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Guardado correctamente")
                    .object(ClienteDto.builder()
                            .idCliente(clienteSave.getIdCliente())
                            .nombre(clienteSave.getNombre())
                            .apellido(clienteSave.getApellido())
                            .email(clienteSave.getEmail())
                            .fechaRegistro(clienteSave.getFechaRegistro())
                            .build())
                    .build()
                    ,HttpStatus.CREATED);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(e.getMessage())
                            .object(null)
                            .build()
                    ,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("cliente")
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDto update(ClienteDto clienteDto) {
        Cliente clienteUpdate = clienteService.save(clienteDto);
        return ClienteDto.builder()
                .idCliente(clienteUpdate.getIdCliente())
                .nombre(clienteUpdate.getNombre())
                .apellido(clienteUpdate.getApellido())
                .email(clienteUpdate.getEmail())
                .fechaRegistro(clienteUpdate.getFechaRegistro())
                .build();
    }

    @DeleteMapping("cliente/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            Cliente clienteDelete = clienteService.findById(id);
            clienteService.delete(clienteDelete);
            return new ResponseEntity<>(clienteDelete, HttpStatus.NO_CONTENT);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(e.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("cliente/{id}")
    public ClienteDto showById(@PathVariable Integer id) {
        Cliente cliente = clienteService.findById(id);
        return ClienteDto.builder()
                .idCliente(cliente.getIdCliente())
                .nombre(cliente.getNombre())
                .apellido(cliente.getApellido())
                .email(cliente.getEmail())
                .fechaRegistro(cliente.getFechaRegistro())
                .build();

    }


}
