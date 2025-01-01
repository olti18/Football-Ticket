package Football_Ticket.service.Impl;

import Football_Ticket.Dto.StadiumDTO;
import Football_Ticket.mapper.StadiumMapper;
import Football_Ticket.model.Stadium;
import Football_Ticket.repository.StadiumRepository;
import Football_Ticket.service.StadiumService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StadiumServiceImpl implements StadiumService {

    private final StadiumRepository stadiumRepository;
    private final StadiumMapper stadiumMapper;

    @Autowired
    public StadiumServiceImpl(StadiumRepository stadiumRepository, StadiumMapper stadiumMapper) {
        this.stadiumRepository = stadiumRepository;
        this.stadiumMapper = stadiumMapper;
    }

    @Override
    public StadiumDTO createStadium(StadiumDTO stadiumDTO, String createdBy) {
        Stadium stadium = stadiumMapper.toEntity(stadiumDTO);
        stadium.setCreatedBy(createdBy); // Set created by from Keycloak
        stadium = stadiumRepository.save(stadium);
        return stadiumMapper.toDTO(stadium);
    }

    @Override
    public StadiumDTO getStadiumById(String id) {
        Stadium stadium = stadiumRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stadium not found"));
        return stadiumMapper.toDTO(stadium);
    }

    @Override
    public List<StadiumDTO> getAllStadiums() {
        return stadiumRepository.findAll()
                .stream()
                .map(stadiumMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StadiumDTO updateStadium(String id, StadiumDTO stadiumDTO) {
        Stadium stadium = stadiumRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stadium not found"));

        stadium.setName(stadiumDTO.getName());
        stadium.setLocation(stadiumDTO.getLocation());
        stadium.setCapacity(stadiumDTO.getCapacity());
        //stadium.setMapImage(stadiumDTO.getMapImage());

        stadium = stadiumRepository.save(stadium);
        return stadiumMapper.toDTO(stadium);
    }

    @Override
    public void deleteStadium(String id) {
        if (!stadiumRepository.existsById(id)) {
            throw new RuntimeException("Stadium not found");
        }
        stadiumRepository.deleteById(id);
    }
}
