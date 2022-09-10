package com.app.example.project;

public class PermissionSetting extends Hook {
    private int enable;

    public PermissionSetting() { }

    public PermissionSetting(Long id, String packageName, String privacyItem, int enable) {
        this.id = id;
        this.packageName = packageName;
        this.privacyItem = privacyItem;
        this.enable = enable;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable1) {
        enable = enable1;
    }
}
