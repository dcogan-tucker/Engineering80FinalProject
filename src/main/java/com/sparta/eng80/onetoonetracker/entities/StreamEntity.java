package com.sparta.eng80.onetoonetracker.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "stream", schema = "1_to_1_tracker")
public class StreamEntity {

    private int streamId;
    private String name;
    private String description;
    private int duration;

    private Set<GroupEntity> groups;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stream_id")
    public int getStreamId() {
        return streamId;
    }

    public void setStreamId(int streamId) {
        this.streamId = streamId;
    }

    @Basic
    @Column(name = "stream_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "stream_description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "stream_duration")
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @OneToMany(mappedBy = "stream", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<GroupEntity> getGroups() {
        return groups;
    }

    public void setGroups(Set<GroupEntity> groups) {
        this.groups = groups;
    }
}
