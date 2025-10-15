package com.apirescamadas.crud.services;

import com.apirescamadas.crud.dto.ClientDTO;
import com.apirescamadas.crud.entities.Client;
import com.apirescamadas.crud.repositories.ClientRepository;
import com.apirescamadas.crud.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.parser.Entity;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id){
        Optional<Client> result = repository.findById(id);
        Client client = result.orElseThrow(
                () -> new ResourceNotFoundException("Client ID " + id + " not found"));
        return new ClientDTO(client);
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable){
        Page<Client> result = repository.findAll(pageable);
        return result.map(x -> new ClientDTO(x));
    }

    @Transactional
    public ClientDTO insert(ClientDTO clientDto){
        Client client = new Client();
        copyDtoToEntity(clientDto, client);
        client = repository.save(client);
        return new ClientDTO(client);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO clientDto){
        try {
            Client entity = repository.getReferenceById(id);
            copyDtoToEntity(clientDto, entity);
            entity = repository.save(entity);
            return new ClientDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Client ID " + id + " not found");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repository.existsById(id)){
            throw new ResourceNotFoundException("Client ID " + id + " not found");
        }
        repository.deleteById(id);
    }

    private void copyDtoToEntity(ClientDTO clientDto, Client client){
        client.setName(clientDto.getName());
        client.setCpf(clientDto.getCpf());
        client.setIncome(clientDto.getIncome());
        client.setBirthDate(clientDto.getBirthDate());
        client.setChildren(clientDto.getChildren());
    }
}
