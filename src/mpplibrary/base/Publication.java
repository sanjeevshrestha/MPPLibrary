/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpplibrary.base;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 984970
 */
abstract public class Publication {

    private long ID;
    private String title;
    private String description;
    private List<Author> authors;
    private List<LendableCopy> lendableCopies;
    private String type;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Publication() {
        this.authors = new ArrayList<>();
        this.lendableCopies = new ArrayList<>();

    }

    public Publication(long ID) {
        this.ID = ID;
        this.authors = new ArrayList<>();
        this.lendableCopies = new ArrayList<>();
    }

    public Publication(long ID, String title) {
        this.ID = ID;
        this.title = title;
        this.authors = new ArrayList<>();
        this.lendableCopies = new ArrayList<>();
    }

    public Publication(long ID, String title, String desc, String type) {
        this.ID = ID;
        this.title = title;
        this.description = desc;
        this.type = type;
        this.authors = new ArrayList<>();
        this.lendableCopies = new ArrayList<>();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public void addLendableCopies(LendableCopy l) {
        this.lendableCopies.add(l);
    }

    public void addLendableCopies(long uniqueid) {
        LendableCopy l = new LendableCopy(uniqueid);

        this.lendableCopies.add(l);
    }

    public List<LendableCopy> getLendableCopies() {
        return lendableCopies;
    }

    public void setLendableCopies(List<LendableCopy> lendableCopies) {
        this.lendableCopies = lendableCopies;
    }
    
    
}
