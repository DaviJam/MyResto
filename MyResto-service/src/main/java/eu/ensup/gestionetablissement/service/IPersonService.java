package eu.ensup.gestionetablissement.service;

import eu.ensup.gestionetablissement.dto.PersonDTO;

/**
 * The interface Person service.
 */
public interface IPersonService extends IService<PersonDTO> {


    /**
     * Delete int.
     *
     * @param email the email
     * @return the int
     * @throws ExceptionService the exception service
     */
    int delete(String email) throws ExceptionService;
}
