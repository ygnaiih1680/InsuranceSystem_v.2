package system.insurance.backend.resource.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import system.insurance.backend.exception.NoEmployeeException;
import system.insurance.backend.resource.dto.ContractDTO;
import system.insurance.backend.resource.service.SalesService;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/contract")
public class ContractController {
    private final SalesService salesService;

    @Autowired
    public ContractController(SalesService salesService) {
        this.salesService = salesService;
    }

    @GetMapping("/list/responsibility")
    @ResponseBody
    public List<ContractDTO> getContractListByResponsibility(@RequestParam("eid") int eid) {
        System.out.println("하하하"+eid);
        try {
            return this.salesService.getContractList(eid);
        } catch (NoEmployeeException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }



}
