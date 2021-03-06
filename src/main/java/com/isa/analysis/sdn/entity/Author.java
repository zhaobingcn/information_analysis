package com.isa.analysis.sdn.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

/**
 * @author zhaobing
 */
@JsonIdentityInfo(generator = JSOGGenerator.class)
@NodeEntity(label = "Author")
public class Author {
    @GraphId
    private Long id;

    @Property(name = "name")
    private String name;

    @Property(name = "institution")
    private String institution;

    @Relationship(type = "work_in", direction = Relationship.OUTGOING)
    private Work work;

    @Relationship(type = "publish", direction = Relationship.OUTGOING)
    private List<Paper> papers;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public List<Paper> getPapers() {
        return papers;
    }

    public void setPapers(List<Paper> papers) {
        this.papers = papers;
    }
}
