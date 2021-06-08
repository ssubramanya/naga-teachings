package com.legion.kickstart.DatabaseEntities;

import lombok.Data;

@Data
public class Component {
    private String type;
    private String brand;
    private String description;
    private String specifications;
    private String price;
}
