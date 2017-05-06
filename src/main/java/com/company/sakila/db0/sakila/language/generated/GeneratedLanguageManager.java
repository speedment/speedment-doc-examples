package com.company.sakila.db0.sakila.language.generated;

import com.company.sakila.db0.sakila.language.Language;
import com.speedment.common.annotation.GeneratedCode;
import com.speedment.runtime.core.manager.Manager;

/**
 * The generated base interface for the manager of every {@link
 * com.company.sakila.db0.sakila.language.Language} entity.
 * <p>
 * This file has been automatically generated by Speedment. Any changes made to
 * it will be overwritten.
 * 
 * @author Speedment
 */
@GeneratedCode("Speedment")
public interface GeneratedLanguageManager extends Manager<Language> {
    
    @Override
    default Class<Language> getEntityClass() {
        return Language.class;
    }
}