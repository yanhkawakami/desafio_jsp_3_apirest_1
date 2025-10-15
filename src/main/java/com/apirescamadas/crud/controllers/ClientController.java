package com.apirescamadas.crud.controllers;

import com.apirescamadas.crud.dto.ClientDTO;
import com.apirescamadas.crud.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping (value = "/clients")
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping (value = "/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable Long id){
        ClientDTO clientDto = service.findById(id);
        return ResponseEntity.ok(clientDto);
    }

    @GetMapping
    public ResponseEntity<Page<ClientDTO>> findAll(Pageable pageable){
        Page<ClientDTO> clientDto = service.findAll(pageable);
        return ResponseEntity.ok(clientDto);
    }

    @PostMapping
    public ResponseEntity<ClientDTO> insert(@Valid @RequestBody ClientDTO clientDto){
        ClientDTO dto = service.insert(clientDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping (value = "/{id}")
    public ResponseEntity<ClientDTO> update (@PathVariable Long id, @Valid @RequestBody ClientDTO clientDto){
        clientDto = service.update(id, clientDto);
        return ResponseEntity.ok(clientDto);
    }

    @DeleteMapping (value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
