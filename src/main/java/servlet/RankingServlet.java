package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import dto.RankingDto;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.GameRankingService;

@WebServlet("/ranking")
public class RankingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final GameRankingService service = new GameRankingService();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<RankingDto> list = service.getAllRankings();
        resp.setContentType("application/json; charset=UTF-8");
        resp.getWriter().print(gson.toJson(list));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BufferedReader br = req.getReader();
        RankingDto dto = gson.fromJson(br, RankingDto.class);
        service.insertRanking(dto);
        resp.setStatus(201);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        service.deleteRanking(id);
        resp.setStatus(204);
    }
}
