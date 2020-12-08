package system.insurance.backend.service;

import system.insurance.backend.dbo.accident.AccidentType;
import system.insurance.backend.dto.ClientDTO;
import system.insurance.backend.exception.NoAccidentException;
import system.insurance.backend.exception.NoClientException;

import java.time.LocalDateTime;

public interface AccidentService {
    ClientDTO checkRegisteredClient(String name, String contact) throws NoClientException;

    boolean addAccident(int contractId, String accidentArea, AccidentType accidentType, LocalDateTime date);

    boolean saveHandledAccident(int accidentId, String scenario, String damage, String picture,
                                String video, String record, String processingCost) throws NoAccidentException;
}
