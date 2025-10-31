package service;


import java.util.List;

import dao.GenreDao;
import dto.GenreDto;

public class GenreService {
    private final GenreDao dao = new GenreDao();

    public List<GenreDto> getAllGenres() {
        return dao.selectAll();
    }

    public void insertGenre(GenreDto dto) {
        if (dto.getGenreName() == null || dto.getGenreName().isBlank()) {
            throw new IllegalArgumentException("장르 이름은 필수입니다.");
        }
        dao.insert(dto);
    }

    public void deleteGenre(int id) {
        dao.delete(id);
    }
}
