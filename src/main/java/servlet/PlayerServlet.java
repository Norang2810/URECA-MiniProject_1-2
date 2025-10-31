package servlet;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import dto.PlayerDto;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.PlayerService;

@WebServlet("/player")
public class PlayerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final PlayerService service = new PlayerService();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<PlayerDto> list = service.getAllPlayers();
        resp.setContentType("application/json; charset=UTF-8");
        resp.getWriter().print(gson.toJson(list));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        
        String playerName = req.getParameter("playerName");
        String email = req.getParameter("email");

        PlayerDto dto = new PlayerDto();
        dto.setPlayerName(playerName);
        dto.setEmail(email);

        service.insertPlayer(dto);
        resp.setStatus(201);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        service.deletePlayer(id);
        resp.setStatus(204);
    }
}
