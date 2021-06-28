package eu.ensup.gestionetablissement.mapper;

import java.util.ArrayList;
import java.util.List;

import eu.ensup.gestionetablissement.business.Mark;
import eu.ensup.gestionetablissement.dto.MarkDTO;

/**
 * The type Course mapper.
 */
public class MarkMapper
{
    /**
     * Business to dto course dto.
     *
     * @param course the course
     * @return the course dto
     */
    public static MarkDTO businessToDto(Mark mark){

    	MarkDTO markDto = new MarkDTO();

    	markDto.setId(mark.getId());
    	markDto.setIdCourse(mark.getIdCourse());
    	markDto.setIdStudent(mark.getIdStudent());
    	markDto.setMark(mark.getMark());
    	markDto.setAssessment(mark.getAssessment());

        return markDto;
    }
    
    public static List<MarkDTO> listBusinessToListDto(List<Mark> listMark){

    	List<MarkDTO> listMarkDto = new ArrayList<MarkDTO>();
    	
    	for( Mark mark : listMark )
    		listMarkDto.add(businessToDto(mark));
    	
        return listMarkDto;
    }

    /**
     * Dto to business course.
     *
     * @param coursedto the coursedto
     * @return the course
     */
    public static Mark dtoToBusiness(MarkDTO markDto)
    {
    	Mark mark = new Mark();
    	
    	mark.setId(markDto.getId());
    	mark.setIdCourse(markDto.getIdCourse());
    	mark.setIdStudent(markDto.getIdStudent());
    	mark.setMark(markDto.getMark());
    	mark.setAssessment(markDto.getAssessment());

        return mark;
    }
    
    public static List<Mark> listDtoToListBusiness(List<MarkDTO> listMarkDto){

    	List<Mark> listMark = new ArrayList<Mark>();
    	
    	for( MarkDTO markDto : listMarkDto )
    		listMark.add(dtoToBusiness(markDto));
    	
        return listMark;
    }
}
