package org.hospitaltoolmanagement.backend.model.ToolDomain.PersistenceObjects;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "TOOL_CATEGORY")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ToolCategoryEntity {

    // column names
    private static final String TOOL_CATEGORY_ID_COLUMN = "TOOL_CATEGORY_ID";
    private static final String TOOL_CATEGORY_NAME_COLUMN = "TOOL_CATEGORY_NAME";

    // entity associated names
    private static final String TOOL_ENTITY = "toolCategoryEntity";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = TOOL_CATEGORY_ID_COLUMN)
    private int toolCategoryId;

    @Column(name = TOOL_CATEGORY_NAME_COLUMN)
    private String toolCategoryName;

    // bi-directional many-to-one association to ToolEntity
    @OneToMany(mappedBy = TOOL_ENTITY)
    private Set<ToolEntity> toolEntitySet;
}
