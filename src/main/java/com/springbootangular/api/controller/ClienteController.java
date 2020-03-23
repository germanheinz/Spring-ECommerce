package com.springbootangular.api.controller;


import com.springbootangular.api.domain.Cliente;
import com.springbootangular.api.services.ClienteService;
import com.springbootangular.api.services.UploadFile;
import com.springbootangular.api.v1.model.ClienteDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping(ClienteController.BASE_URL)
public class ClienteController {

    public static final String BASE_URL = "/api/clientes";

    @Autowired
    public UploadFile uploadFile;

    @Autowired
    public ClienteService clienteService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "This will get a list of customers.", notes = "These are some notes about the API.")
    public List<ClienteDTO> getListCustomers() {
        return clienteService.findAll();
    }

    @GetMapping({"page/{page}"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "This will get a list of customers.", notes = "These are some notes about the API.")
    public Page<ClienteDTO> getListCustomers(@PathVariable Integer page) {
        Pageable pageable = PageRequest.of(page, 5);
        return clienteService.findAll(pageable);
    }
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "This will get a list of customers.", notes = "These are some notes about the API.")
    public ClienteDTO getCustomerById(@PathVariable Long id) {
        return clienteService.findById(id);
    }
    /*
     * Para validar todas las anotaciones en el modelo Entity Clientes se debe anotar @Valid seguido de @RequestBody + el objeto
     * Despues BindingResult result nos dira los errores que hubieron
     * se hace un condicional para obtenerlos en caso de que hubiesen
     *
     * */
    @Secured({"ROLE_ADMIN"})
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "This will create a customers.", notes = "These are some notes about the API.")
    public ResponseEntity<?> createCustomer(@Valid @RequestBody ClienteDTO clienteDTO, BindingResult result) {
        Map<String, Object> response = hasErrors(result);
        if(response.containsKey("errors")){
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            clienteService.save(clienteDTO);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El cliente se ha creado con exito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    @Secured({"ROLE_ADMIN"})
    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Service to update Customers", notes = "This service, Updates Customers")
    public ResponseEntity<?> updateCustomer(@Valid @RequestBody ClienteDTO clienteDTO, @PathVariable Long id, BindingResult result) {
        ClienteDTO cliente = clienteService.findById(id);
        ClienteDTO clienteUpdated = null;
        Map<String, Object> response = hasErrors(result);
        if(response.containsKey("errors")){
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        if (cliente == null) {
            response.put("mensaje", "Error el cliente con el id".concat(id.toString().concat("no existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            clienteUpdated = clienteService.saveCustomerByDTO(id, clienteDTO);
        } catch (DataAccessException e) {
            response.put("mensaje", "Erorr al actualizar el cliente");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El cliente actualizado con exito");
        response.put("cliente", clienteUpdated);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
    @Secured({"ROLE_ADMIN"})
    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Service to Delete Customers", notes = "This service, Updates Customers")
    public void deleteCustomer(@PathVariable Long id){
       clienteService.deleteCustomerById(id);
    }
    private Map<String, Object> hasErrors(BindingResult result){
        ClienteDTO clienteNew = null;
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo" + err.getField() + " " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return response;
        }
        response.put("ok", HttpStatus.OK);
        return response;
    }
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id){
        Map<String, Object> response = new HashMap<>();

        ClienteDTO cliente = clienteService.findById(id);

        if(!archivo.isEmpty()) {

            String nombreArchivo = null;
            try {
                nombreArchivo = uploadFile.copiar(archivo);
            } catch (IOException e) {
                response.put("mensaje", "Error al subir la imagen del cliente");
                response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            String nombreFotoAnterior = cliente.getFoto();

            uploadFile.eliminar(nombreFotoAnterior);

            cliente.setFoto(nombreArchivo);

            clienteService.save(cliente);

            response.put("cliente", cliente);
            response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);

        }

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

        //-------Metodo para descargar la Imagen-------------//

    @GetMapping("/uploads/img/{nombreFoto:.+}")
    public ResponseEntity<Resource> getPhotoCustomer(@PathVariable String nombreFoto){

        Resource recurso = null;

        try {
            recurso = uploadFile.cargar(nombreFoto);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        HttpHeaders cabecera = new HttpHeaders();
        cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");

        return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
    }
}
