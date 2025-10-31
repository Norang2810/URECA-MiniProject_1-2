package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import dto.GenreDto;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.GenreService;

@WebServlet("/genre")
public class GenreServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final GenreService service = new GenreService();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<GenreDto> list = service.getAllGenres();
        resp.setContentType("application/json; charset=UTF-8");
        resp.getWriter().print(gson.toJson(list));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BufferedReader br = req.getReader();
        GenreDto dto = gson.fromJson(br, GenreDto.class);
        service.insertGenre(dto);
        resp.setStatus(201);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        service.deleteGenre(id);
        resp.setStatus(204);
    }
}
