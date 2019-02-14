package org.hospitaltoolmanagement.backend.model.ToolDomain.PersistenceObjects;

import lombok.*;
import org.hospitaltoolmanagement.backend.model.HospitalDomain.PersistenceObjects.HospitalRoomEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TOOL")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ToolEntity {

    // column names
    private static final String TOOL_ID_COLUMN = "TOOL_ID";
    private static final String TOOL_NAME_COLUMN = "TOOL_NAME";
    private static final String TOOL_MODEL_COLUMN = "TOOL_MODEL";
    private static final String TOOL_SERIAL_NUMBER_COLUMN = "TOOL_SERIAL_NUMBER";
    private static final String TOOL_YEAR_COLUMN = "TOOL_YEAR";

    // join column names
    private static final String TOOL_MANUFACTURER_COLUMN = "TOOL_MANUFACTURER";
    private static final String TOOL_CATEGORY_COLUMN = "TOOL_CATEGORY";
    private static final String HOSPITAL_ROOM_COLUMN = "HOSPITAL_ROOM";


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = TOOL_ID_COLUMN)
    private int toolId;

    @Column(name = TOOL_NAME_COLUMN)
    private String toolName;

    @Column(name = TOOL_MODEL_COLUMN)
    private String toolModel;

    @Column(name = TOOL_SERIAL_NUMBER_COLUMN)
    private String toolSerialNumber;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = TOOL_YEAR_COLUMN)
    private Date toolYear;

    // bi-directional many-to-one association to ToolManufacturerEntity
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = TOOL_MANUFACTURER_COLUMN)
    private ToolManufacturerEntity toolManufacturerEntity;

    // bi-directional many-to-one association to ToolCategoryEntity
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = TOOL_CATEGORY_COLUMN)
    private ToolCategoryEntity toolCategoryEntity;

    // bi-directional many-to-one association to HospitalRoomEntity
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = HOSPITAL_ROOM_COLUMN)
    private HospitalRoomEntity hospitalRoomEntity;
}
