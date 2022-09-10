
package com.app.example.project;

import com.app.example.project.HookCursor.Factory;

import io.objectbox.EntityInfo;
import io.objectbox.Property;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;
import io.objectbox.internal.IdGetter;


// THIS CODE IS GENERATED BY ObjectBox, DO NOT EDIT.

/**
 * Properties for entity "Hook". Can be used for QueryBuilder and for referencing DB names.
 */
public final class Hook_ implements EntityInfo<Hook> {

    // Leading underscores for static constants to avoid naming conflicts with property names

    public static final String __ENTITY_NAME = "Hook";

    public static final int __ENTITY_ID = 1;

    public static final Class<Hook> __ENTITY_CLASS = Hook.class;

    public static final String __DB_NAME = "Hook";

    public static final CursorFactory<Hook> __CURSOR_FACTORY = new Factory();

    @Internal
    static final HookIdGetter __ID_GETTER = new HookIdGetter();

    public final static Property id = new Property(0, 1, Long.class, "id", true, "id");
    public final static Property packageName = new Property(1, 2, String.class, "packageName");
    public final static Property privacyItem = new Property(2, 3, String.class, "privacyItem");

    public final static Property[] __ALL_PROPERTIES = {
        id,
        packageName,
        privacyItem
    };

    public final static Property __ID_PROPERTY = id;

    public final static Hook_ __INSTANCE = new Hook_();

    @Override
    public String getEntityName() {
        return __ENTITY_NAME;
    }

    @Override
    public int getEntityId() {
        return __ENTITY_ID;
    }

    @Override
    public Class<Hook> getEntityClass() {
        return __ENTITY_CLASS;
    }

    @Override
    public String getDbName() {
        return __DB_NAME;
    }

    @Override
    public Property[] getAllProperties() {
        return __ALL_PROPERTIES;
    }

    @Override
    public Property getIdProperty() {
        return __ID_PROPERTY;
    }

    @Override
    public IdGetter<Hook> getIdGetter() {
        return __ID_GETTER;
    }

    @Override
    public CursorFactory<Hook> getCursorFactory() {
        return __CURSOR_FACTORY;
    }

    @Internal
    static final class HookIdGetter implements IdGetter<Hook> {
        @Override
        public long getId(Hook object) {
            Long id = object.id;
            return id != null? id : 0;
        }
    }

}
