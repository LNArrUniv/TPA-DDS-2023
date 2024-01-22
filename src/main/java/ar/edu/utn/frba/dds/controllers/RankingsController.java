package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.rankings.ItemRanking;
import ar.edu.utn.frba.dds.models.rankings.MetodosRanking;
import ar.edu.utn.frba.dds.persistencia.repositorios.RepositorioRankings;
import ar.edu.utn.frba.dds.persistencia.repositorios.RepositoriosItemsRankings;
import ar.edu.utn.frba.dds.server.utils.IcrudViewsHandler;
import io.javalin.http.Context;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RankingsController extends Controller implements IcrudViewsHandler {

  @Override
  public void index(Context context) {
    Map<String, Object> model = new HashMap<>();

    List<MetodosRanking> rankings = RepositorioRankings.getInstance().all();

    model.put("rankings", rankings);

    context.render("rankings.hbs", model);
  }

  @Override
  public void show(Context context) {
    Map<String, Object> model = new HashMap<>();

    long idRanking = Long.parseLong(context.pathParam("id"));
    //List<ItemRanking> items = RepositoriosItemsRankings.getInstance().masRecientes(idRanking);
    List<ItemRanking> items = RepositoriosItemsRankings.getInstance().itemsEstaSemana(idRanking);
    MetodosRanking ranking = RepositorioRankings.getInstance().get(idRanking);

    model.put("items", items);
    model.put("ranking", ranking);

    context.render("ranking.hbs", model);
  }

  @Override
  public void create(Context context) {

  }

  @Override
  public void save(Context context) {

  }

  @Override
  public void edit(Context context) {

  }

  @Override
  public void update(Context context) {

  }

  @Override
  public void delete(Context context) {

  }
}
