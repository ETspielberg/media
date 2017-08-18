package unidue.ub.media.analysis;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

@Entity
public class UsageData {

    @Id
    private String name;


    private Hashtable<String,Collection> collections;

    public UsageData() {
        collections = new Hashtable<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Collection> getCollections() {
        return new ArrayList<Collection>(collections.values());
    }

    public void setCollections(List<Collection> collections) {
        for (Collection collection : collections) {
            addCollection(collection);
        }
    }

    public void addCollection(Collection collection) {
        this.collections.put(collection.getName(), collection);
    }

    public Collection getCollection(String name) {
        return collections.get(name);
    }

    @JsonIgnore
    public List<String> getCollectionNames() {
        return new ArrayList<String>(collections.keySet());
    }
}
