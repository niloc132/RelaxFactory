package ds.model;

import rxf.server.CouchService;

import java.util.List;

public interface NpoService extends CouchService<Npo> {

    @Override
    public Npo find(@Key String key);

    @View(map = "function(doc) { emit(doc.name, doc); }")
    List<Npo> findByName(@Key String name);

    @View(map = "function(doc) { emit(doc._id, doc); }")
    List<Npo> findAll();
}
