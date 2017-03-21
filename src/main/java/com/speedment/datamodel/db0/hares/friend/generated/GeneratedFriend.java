package com.speedment.datamodel.db0.hares.friend.generated;

import com.speedment.datamodel.db0.hares.friend.Friend;
import com.speedment.datamodel.db0.hares.hare.Hare;
import com.speedment.datamodel.db0.hares.human.Human;
import com.speedment.runtime.config.identifier.ColumnIdentifier;
import com.speedment.runtime.config.identifier.TableIdentifier;
import com.speedment.runtime.core.annotation.GeneratedCode;
import com.speedment.runtime.core.manager.Manager;
import com.speedment.runtime.field.IntForeignKeyField;
import com.speedment.runtime.typemapper.TypeMapper;

/**
 * The generated base for the {@link
 * com.speedment.datamodel.db0.hares.friend.Friend}-interface representing
 * entities of the {@code friend}-table in the database.
 * <p>
 * This file has been automatically generated by Speedment. Any changes made to
 * it will be overwritten.
 * 
 * @author Speedment
 */
@GeneratedCode("Speedment")
public interface GeneratedFriend {
    
    /**
     * This Field corresponds to the {@link Friend} field that can be obtained
     * using the {@link Friend#getHare()} method.
     */
    final IntForeignKeyField<Friend, Integer, Hare> HARE = IntForeignKeyField.create(
        Identifier.HARE,
        Friend::getHare,
        Friend::setHare,
        Hare.ID,
        TypeMapper.primitive(), 
        false
    );
    /**
     * This Field corresponds to the {@link Friend} field that can be obtained
     * using the {@link Friend#getHuman()} method.
     */
    final IntForeignKeyField<Friend, Integer, Human> HUMAN = IntForeignKeyField.create(
        Identifier.HUMAN,
        Friend::getHuman,
        Friend::setHuman,
        Human.ID,
        TypeMapper.primitive(), 
        false
    );
    
    /**
     * Returns the hare of this Friend. The hare field corresponds to the
     * database column db0.hares.friend.hare.
     * 
     * @return the hare of this Friend
     */
    int getHare();
    
    /**
     * Returns the human of this Friend. The human field corresponds to the
     * database column db0.hares.friend.human.
     * 
     * @return the human of this Friend
     */
    int getHuman();
    
    /**
     * Sets the hare of this Friend. The hare field corresponds to the database
     * column db0.hares.friend.hare.
     * 
     * @param hare to set of this Friend
     * @return     this Friend instance
     */
    Friend setHare(int hare);
    
    /**
     * Sets the human of this Friend. The human field corresponds to the
     * database column db0.hares.friend.human.
     * 
     * @param human to set of this Friend
     * @return      this Friend instance
     */
    Friend setHuman(int human);
    
    /**
     * Queries the specified manager for the referenced Hare. If no such Hare
     * exists, an {@code NullPointerException} will be thrown.
     * 
     * @param foreignManager the manager to query for the entity
     * @return               the foreign entity referenced
     */
    Hare findHare(Manager<Hare> foreignManager);
    
    /**
     * Queries the specified manager for the referenced Human. If no such Human
     * exists, an {@code NullPointerException} will be thrown.
     * 
     * @param foreignManager the manager to query for the entity
     * @return               the foreign entity referenced
     */
    Human findHuman(Manager<Human> foreignManager);
    
    enum Identifier implements ColumnIdentifier<Friend> {
        
        HARE  ("hare"),
        HUMAN ("human");
        
        private final String columnName;
        private final TableIdentifier<Friend> tableIdentifier;
        
        Identifier(String columnName) {
            this.columnName      = columnName;
            this.tableIdentifier = TableIdentifier.of(    getDbmsName(), 
                getSchemaName(), 
                getTableName());
        }
        
        @Override
        public String getDbmsName() {
            return "db0";
        }
        
        @Override
        public String getSchemaName() {
            return "hares";
        }
        
        @Override
        public String getTableName() {
            return "friend";
        }
        
        @Override
        public String getColumnName() {
            return this.columnName;
        }
        
        @Override
        public TableIdentifier<Friend> asTableIdentifier() {
            return this.tableIdentifier;
        }
    }
}