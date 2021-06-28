package eu.ensup.gestionetablissement.mapper;

import eu.ensup.gestionetablissement.business.Director;
import eu.ensup.gestionetablissement.dto.DirectorDTO;

/**
 * The type Director mapper.
 */
public class DirectorMapper {
    /**
     * Business to dto director dto.
     *
     * @param director the director
     * @return the director dto
     */
    public static DirectorDTO businessToDto(Director director){
        DirectorDTO directorDTO = new DirectorDTO();
        directorDTO.setFirstname(director.getLastname());
        directorDTO.setLastname(director.getLastname());
        directorDTO.setMailAddress(director.getMailAddress());
        directorDTO.setAddress(director.getAddress());
        directorDTO.setPassword(director.getPassword());
        directorDTO.setRole(director.getRole());
        directorDTO.setId(director.getId());
        directorDTO.setPhoneNumber(director.getPhoneNumber());
        return directorDTO;
    };

    /**
     * Dto to business director.
     *
     * @param directorDTO the director dto
     * @return the director
     */
    public static Director dtoToBusiness(DirectorDTO directorDTO){
        Director director = new Director();
        director.setFirstname(directorDTO.getLastname());
        director.setLastname(directorDTO.getLastname());
        director.setMailAddress(directorDTO.getMailAddress());
        director.setAddress(directorDTO.getAddress());
        director.setPassword(directorDTO.getPassword());
        director.setRole(directorDTO.getRole());
        director.setId(directorDTO.getId());
        director.setPhoneNumber(directorDTO.getPhoneNumber());
        return director;
    };
}
