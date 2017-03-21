package com.speedment.datamodel.db0.hares.carrot.generated;

import com.speedment.datamodel.db0.hares.carrot.Carrot;
import com.speedment.runtime.config.identifier.TableIdentifier;
import com.speedment.runtime.core.annotation.GeneratedCode;
import com.speedment.runtime.core.manager.AbstractManager;
import com.speedment.runtime.field.Field;
import java.util.stream.Stream;

/**
 * The generated base implementation for the manager of every {@link
 * com.speedment.datamodel.db0.hares.carrot.Carrot} entity.
 * <p>
 * This file has been automatically generated by Speedment. Any changes made to
 * it will be overwritten.
 * 
 * @author Speedment
 */
@GeneratedCode("Speedment")
public abstract class GeneratedCarrotManagerImpl extends AbstractManager<Carrot> implements GeneratedCarrotManager {
    
    private final TableIdentifier<Carrot> tableIdentifier;
    
    protected GeneratedCarrotManagerImpl() {
        this.tableIdentifier = TableIdentifier.of("db0", "hares", "carrot");
    }
    
    @Override
    public TableIdentifier<Carrot> getTableIdentifier() {
        return tableIdentifier;
    }
    
    @Override
    public Stream<Field<Carrot>> fields() {
        return Stream.of(
            Carrot.ID,
            Carrot.NAME,
            Carrot.OWNER,
            Carrot.RIVAL
        );
    }
    
    @Override
    public Stream<Field<Carrot>> primaryKeyFields() {
        return Stream.of(
            Carrot.ID
        );
    }
}