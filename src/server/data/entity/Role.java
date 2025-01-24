package server.data.entity;

import java.util.UUID;

public class Role {
    private UUID id;
    private String name;

    public Role(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //"d65e91f2-68bd-4578-93cf-e0bc3ddd0183" - pupil
    //"d65e91f2-68bd-4578-93cf-e0bc3ddd0185" - teacher
}

