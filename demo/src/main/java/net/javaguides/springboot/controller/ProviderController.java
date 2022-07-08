package net.javaguides.springboot.controller;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Downtime;
import net.javaguides.springboot.model.Provider;
import net.javaguides.springboot.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/providers/")
public class ProviderController {

    @Autowired
    private ProviderRepository providerRepository;

    //build get all providers REST API
    @GetMapping("get-all")
    public List<Provider> getAll() {
        return providerRepository.findAll();
    }

    //build create provider REST API
    @PostMapping("create-provider")
    public Provider createProvider(@RequestParam(name = "provider_name") String providerName, @RequestParam(name = "flow_name") String flowName, @RequestBody Downtime downtime) {
        Provider provider = new Provider();
        provider.setProviderName(providerName);
        provider.setFlowName(flowName);
        provider.setDowntimeFrom(downtime.getDowntimeFrom());
        provider.setDowntimeTo((downtime.getDowntimeTo()));
        return providerRepository.save(provider);
    }

    //build get employee by provider_name REST API
    @GetMapping("get-provider/{providerName}")
    public ResponseEntity<Provider> getProviderByID(@PathVariable(value = "providerName") String providerName) throws ResourceNotFoundException {
        Provider provider = providerRepository.findByProviderName(providerName);
        if(provider != null) {
            return ResponseEntity.ok().body(provider);
        }
        ResourceNotFoundException ex = new ResourceNotFoundException("Provider does not exist with name: " + providerName);
        throw ex;
    }

    //build update provider by provider_name REST API
    @PutMapping("update-provider/{providerName}")
    public  ResponseEntity<Provider> updateProvider(@PathVariable(value = "providerName") String providerName, @RequestBody Provider providerDetails) throws ResourceNotFoundException {
        Provider provider = providerRepository.findByProviderName(providerName);
        if(provider != null) {
            if(providerDetails.getProviderName() != null) {
                provider.setProviderName(providerDetails.getProviderName());
            }
            if(providerDetails.getFlowName() != null) {
                provider.setFlowName(providerDetails.getFlowName());
            }
            if(providerDetails.getDowntimeFrom() != null) {
                provider.setDowntimeFrom(providerDetails.getDowntimeFrom());
            }
            if(providerDetails.getDowntimeTo() != null) {
                provider.setDowntimeTo(providerDetails.getDowntimeTo());
            }
            providerRepository.save(provider);

            return ResponseEntity.ok(provider);
        }
        ResourceNotFoundException ex = new ResourceNotFoundException("Provider does not exist with name: " + providerName);
        throw ex;
    }

    //build delete employee by provider_name REST API
    @DeleteMapping("delete-provider/{providerName}")
    public Map<String, Boolean> deleteProvider(@PathVariable(value = "providerName") String providerName) throws ResourceNotFoundException {
        Provider provider = providerRepository.findByProviderName(providerName);
        if(provider != null) {
            providerRepository.delete(provider);
            Map<String, Boolean> response = new HashMap<>();
            response.put("deleted", Boolean.TRUE);

            return response;
        }
        ResourceNotFoundException ex = new ResourceNotFoundException("Provider does not exist with name: " + providerName);
        throw ex;
    }
}