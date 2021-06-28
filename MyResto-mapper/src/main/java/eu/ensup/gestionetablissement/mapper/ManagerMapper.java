package eu.ensup.gestionetablissement.mapper;

import eu.ensup.gestionetablissement.business.Manager;
import eu.ensup.gestionetablissement.dto.ManagerDTO;

/**
 * The type Manager mapper.
 */
public class ManagerMapper {
    /**
     * Business to dto manager dto.
     *
     * @param manager the manager
     * @return the manager dto
     */
    public static ManagerDTO businessToDto(Manager manager){
        ManagerDTO managerDTO = new ManagerDTO();
        managerDTO.setFirstname(manager.getLastname());
        managerDTO.setLastname(manager.getLastname());
        managerDTO.setMailAddress(manager.getMailAddress());
        managerDTO.setAddress(manager.getAddress());
        managerDTO.setPassword(manager.getPassword());
        managerDTO.setRole(manager.getRole());
        managerDTO.setId(manager.getId());
        managerDTO.setPhoneNumber(manager.getPhoneNumber());
        return managerDTO;
    };

    /**
     * Dto to business manager.
     *
     * @param managerDto the manager dto
     * @return the manager
     */
    public static Manager dtoToBusiness(ManagerDTO managerDto){
        Manager manager = new Manager();
        manager.setFirstname(managerDto.getLastname());
        manager.setLastname(managerDto.getLastname());
        manager.setMailAddress(managerDto.getMailAddress());
        manager.setAddress(managerDto.getAddress());
        manager.setPassword(managerDto.getPassword());
        manager.setRole(managerDto.getRole());
        manager.setId(managerDto.getId());
        manager.setPhoneNumber(managerDto.getPhoneNumber());
        return manager;
    };
}
