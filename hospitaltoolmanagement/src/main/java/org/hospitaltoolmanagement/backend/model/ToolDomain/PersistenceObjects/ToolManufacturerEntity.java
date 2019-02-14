package org.hospitaltoolmanagement.backend.model.ToolDomain.PersistenceObjects;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "TOOL_MANUFACTURER")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ToolManufacturerEntity {

    // column names
    private static final String TOOL_MANUFACTURER_ID_COLUMN = "TOOL_MANUFACTURER_ID";
    private static final String TOOL_MANUFACTURER_NAME_COLUMN = "TOOL_MANUFACTURER_NAME";

    // entity associated names
    private static final String TOOL_ENTITY = "toolManufacturerEntity";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = TOOL_MANUFACTURER_ID_COLUMN)
    private int toolManufacturerId;

    @Column(name = TOOL_MANUFACTURER_NAME_COLUMN)
    private String toolManufacturerName;

    // bi-directional many-to-one association to ToolEntity
    @OneToMany(mappedBy = TOOL_ENTITY)
    private Set<ToolEntity> toolEntitySet;
}
