package com.app.example.project;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class Hook {
    @Id
    Long id;
    String packageName;
    String privacyItem;

    public Hook() { }

    public Hook(Long id, String packageName, String privacyItem) {
        this.id = id;
        this.packageName = packageName;
        this.privacyItem = privacyItem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String name) {
        packageName = name;
    }

    public String getPrivacyItem() {
        return privacyItem;
    }

    public void setPrivacyItem(String privacyItem1) {
        privacyItem = privacyItem1;
    }
}