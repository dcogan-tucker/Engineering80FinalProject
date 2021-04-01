package com.sparta.eng80.onetoonetracker.entities;

import javax.persistence.*;
import java.util.Set;

/**
 * An Entity class represents and stores data from the stream table
 */
@Entity
@Table(name = "stream", schema = "1_to_1_tracker")
public class StreamEntity {

    /**
     * A unique identifier for a stream instance
     */
    private int streamId;

    /**
     * The name of the stream
     */
    private String name;

    /**
     * A small paragraph about the stream
     */
    private String description;

    /**
     * The number of weeks the stream lasts for
     */
    private int duration;

    /**
     * All groups that are apart of the stream
     * @see GroupEntity
     */
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
