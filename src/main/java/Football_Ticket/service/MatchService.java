package Football_Ticket.service;

import Football_Ticket.Dto.MatchDTO;

import java.util.List;

public interface MatchService {
    List<MatchDTO> getAllMatches();

    /**
     * Retrieves a match by its ID.
     * @param id the ID of the match.
     * @return the MatchDTO object.
     */
    MatchDTO getMatchById(String id);

    /**
     * Creates a new match.
     * @param dto the MatchDTO containing match details.
     * @return the created MatchDTO object.
     */
    MatchDTO createMatch(MatchDTO dto);

    /**
     * Updates an existing match.
     * @param id the ID of the match to update.
     * @param dto the MatchDTO containing updated match details.
     * @return the updated MatchDTO object.
     */
    MatchDTO updateMatch(String id, MatchDTO dto);

    /**
     * Deletes a match by its ID.
     * @param id the ID of the match to delete.
     */
    void deleteMatch(String id);
}
